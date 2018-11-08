# Vauditdemo 命令注入 
# 什么是命令注入？

![](https://ws2.sinaimg.cn/large/006tNc79gy1fi7lokdyaoj30za0jq0wq.jpg)

---

# 大局阅读：
## 入口文件index.php

入口文件一般包含一些配置信息，可以知道配置信息的目录。

```
/* index.php*/
<?php 
require_once('sys/config.php');
require_once('header.php');
?>
```

---

## 配置文件config.php

```
<?php
//代码审计通常要把错误回显打开
error_reporting(E_ALL);//error_reporting(0);

//查看是否含有安装文件，如果没有，则跳到安装页面
if (!file_exists($_SERVER["DOCUMENT_ROOT"].'/sys/install.lock')){
	header("Location: /install/install.php");
exit;
}

//包含公共函数文件
include_once($_SERVER["DOCUMENT_ROOT"].'/sys/lib.php');

//数据库的配置信息
$host="localhost"; 
$username="root"; 
$password="root"; 
$database="vauditdemo";

//连接数据库
$conn = mysql_connect($host,$username,$password);
mysql_query('set names utf8',$conn);
mysql_select_db($database, $conn) or die(mysql_error());
if (!$conn)
{
	die('Could not connect: ' . mysql_error());
	exit;
}

//开启session
session_start();

?>
```

---

## 公共函数文件lib.php
```

<?php

date_default_timezone_set('UTC');
header("Content-type: text/html; charset=utf-8");

//判断有无开启gpc，没有开启就用sec函数进行过滤
if( !get_magic_quotes_gpc() ) {
	$_GET = sec ( $_GET );  //用sec函数进行过滤
	$_POST = sec ( $_POST );
	$_COOKIE = sec ( $_COOKIE ); 
}
$_SERVER = sec ( $_SERVER );

function sec( &$array ) {
	//如果是数组，遍历数组
	if ( is_array( $array ) ) {
		foreach ( $array as $k => $v ) {
			$array [$k] = sec ( $v );
		}
	}
	//如果是字符串，直接转义
	else if ( is_string( $array ) ) {
		$array = addslashes( $array );
	}
	//如果是数字，用intval转化
	else if ( is_numeric( $array ) ) {
		$array = intval( $array );
	}
	return $array;
}


/* Maybe Bypass尝试绕过*/
function sqlwaf( $str ) {
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
	$str = str_ireplace( "concat", "sqlwaf", $str );
	$str = str_ireplace( "\\", "\\\\", $str );
	$str = str_ireplace( "&&", "", $str );
	$str = str_ireplace( "||", "", $str );
	$str = str_ireplace( "'", "", $str );
	$str = str_ireplace( "%", "\%", $str );
	$str = str_ireplace( "_", "\_", $str );
	return $str;
}

//获取ip函数，可能存在注入
function get_client_ip(){
	if ($_SERVER["HTTP_CLIENT_IP"] && strcasecmp($_SERVER["HTTP_CLIENT_IP"], "unknown")){
		$ip = $_SERVER["HTTP_CLIENT_IP"];
	}else if ($_SERVER["HTTP_X_FORWARDED_FOR"] && strcasecmp($_SERVER["HTTP_X_FORWARDED_FOR"], "unknown")){
		$ip = $_SERVER["HTTP_X_FORWARDED_FOR"];
	}else if ($_SERVER["REMOTE_ADDR"] && strcasecmp($_SERVER["REMOTE_ADDR"], "unknown")){
		$ip = $_SERVER["REMOTE_ADDR"];
	}else if (isset($_SERVER['REMOTE_ADDR']) && $_SERVER['REMOTE_ADDR'] && strcasecmp($_SERVER['REMOTE_ADDR'], "unknown")){
		$ip = $_SERVER['REMOTE_ADDR'];
	}else{
		$ip = "unknown";
	}
	return($ip);
}

//清理污染数据
function clean_input( $dirty ) {
	//stripslashes反转义 mysql_real_escape_string mysql的转义函数
	return mysql_real_escape_string( stripslashes( $dirty ) );
}


//分割判断后缀
function is_pic( $file_name ) {
	$extend =explode( "." , $file_name );
	$va=count( $extend )-1;
	if ( $extend[$va]=='jpg' || $extend[$va]=='jpeg' || $extend[$va]=='png' ) {
		return 1;
	}
	else
		return 0;
}

//自定义404文件
function not_find( $page ) {
	echo "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\"><html><head><title>404 Not Found</title></head><body><h1>Not Found</h1>
		<p>The requested URL ".$page." was not found on this server.</p></body></html>";
}
?>

```

# 开始审计：敏感函数参数回溯法

## 全局搜索
全局搜索快捷键:command + shift + f

- 全局搜索system关键字。


![](https://ws4.sinaimg.cn/large/006tKfTcgy1fi6kwrgl23j30oa0kq40p.jpg)

在css文件里面，没什么用。


- 全局搜索exec关键字。

![](https://ws3.sinaimg.cn/large/006tKfTcgy1fi6kyoqeyvj317q0qa783.jpg)


## 跟踪敏感函数
```
<?php
			if( isset( $_POST[ 'submit' ] ) ) {
				$target = $_POST[ 'target' ];

				//判断操作系统
				if (stristr(php_uname('s'), 'Windows NT')) { 
					$cmd = 'ping ' . $target;
				} else { 
					$cmd = 'ping -c 3 ' . $target;
				}
				//执行命令
				$res = shell_exec( $cmd );
				echo "<br /><pre>$cmd\r\n".iconv('GB2312', 'UTF-8',$res)."</pre>";
			}
			?>
```

target是直接post过来的数据后再拼接。
然后进入shell_exec()函数。
```string shell_exec ( string $cmd )
//shell_exec — Execute command via shell and return the complete output as a string```


该文件只有管理员才有权限访问。
![](https://ws1.sinaimg.cn/large/006tKfTcgy1fi6l8jpo8wj317g08mmyr.jpg)

---

# 测试:

![](https://ws3.sinaimg.cn/large/006tKfTcgy1fi6wtns1uij31080bcwg2.jpg)

![](https://ws2.sinaimg.cn/large/006tKfTcgy1fi6wus6uv0j31h00bqmzq.jpg)

---



# 防护：
```
if(preg_match('/^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$|^((25[0-5]|2[0-4]\d|[01]?\d\d?)($|(?!\.$)\.)){4}$/',$target)){
                    if (stristr(php_uname('s'), 'Windows NT')) {
                        $cmd = 'ping ' . $target;
                    } else {
                        $cmd = 'ping -c 3 ' . $target;
                    }
                    $res = shell_exec( $cmd );
                    echo "<br /><pre>$cmd\r\n".iconv('GB2312', 'UTF-8',$res)."</pre>";
                }else{
                    echo "IP is error";
                }
            }

```

由于只是ping功能，所以参数只能接符合ip格式的参数。
传进来的参数要满足ip地址的格式才能进行调用,用正则表达式去匹配。

---

# 审计思路展示：
![](https://ws2.sinaimg.cn/large/006tNc79gy1fi7lrvn8f5j30w60pk406.jpg)


---


# 启发：
##一些想法：

每次动态调试的时候，都感觉很有趣。
每看一次，都感觉对网站整个流程：接受请求，参数处理，返回参数都清晰了许多。


## 验证码函数captcha.php:

```
<?php
session_start();
$rand = '';
for($i=0; $i<4; $i++){
	$rand.= dechex(rand(1,15)); //dechex() 函数把十进制转换为十六进制
}
$_SESSION['captcha']=$rand;
$im = imagecreatetruecolor(100,30); //用imagecreatetruecolor(int x,int y)建立的是一幅大小为 x和 y的黑色图像(默认为黑色)
$bg=imagecolorallocate($im,0,0,0);// imagecolorallocate - 为一幅图像分配颜色
$te=imagecolorallocate($im,255,255,255);
imagestring($im,rand(5,6),rand(25,30),5,$rand,$te); //画图
header("Content-type:image/jpeg");//// Output the image
imagejpeg($im);
?> 

```

原来验证码生成的方法之一是这样的！


---

# 其他：

## phpstorm使用中的一些问题：

![](http://ww2.sinaimg.cn/large/006tKfTcgy1ffkww3qbplj30d401l3yg.jpg)

那是删除线，是因为sql语句以后将被sqli语句代替，过几个版本sql语句就不存在了

横线划掉的意思就是PHP在某个版本开始就要废弃这个函数了，以后可能再也不用了，一般的PHP官方手册里会有对应的替换的扩展以及扩展里面的函数可用！


