# 题目:

# SQli

# 分类:
最基础的注入知识（数字型、字符型、搜索型）
注入模式：

基于布尔 
基于时间 
基于报错 
联合注入 
堆查询注入 

## 宽字节注入:

宽字节注入指的是mysql数据库在使用宽字节（GBK）编码时，会认为两个字符是一个汉字（前一个ascii码要大于128（比如%df），才到汉字的范围），而且当我们输入单引号时，mysql会调用转义函数，将单引号变为\'，其中\的十六进制是%5c,mysql的GBK编码，会认为%df%5c是一个宽字节，也就是'運'，从而使单引号闭合（逃逸），进行注入攻击

## 二次注入:

二阶注入类似存储型XSS，是指输入提交的语句，无法直接对WEB应用程序产生影响，通过其它的辅助间接的对WEB产生危害，这样的就被称为是二阶注入.

## 联合注入:
联合查询注入两个前提

1. 存在数据显示位
2. union左右列数相同

联合查询注入分三步

order by猜解列数
union select 寻找显示位
注入数据库查询攻击
查基本信息 (database () 数据库名、version () 数据库版本、user () 用户名)
查库
查表
查字段
查数据

- [SQL注入之联合查询注入](https://wileysec.com/archives/194/)





---

## 绕过waf:

- base64encode.py 对给定的payload全部字符使用Base64编码
- charencode.py 对给定的payload全部字符使用URL编码（不处理已经编码的字符）
- space2comment.py 用“/**/”替换空格符
- space2plus.py 用加号“+”替换空格符
- xforwardedfor.py 添加一个伪造的HTTP头“X-Forwarded-For”来绕过WAF

[sqlmap使用总结](https://zerokeeper.com/web-security/sqlmap-usage-summary.html)




---

## xss:

发包看返回包

- 存储型XSS：你发送一次带XSS代码的请求，以后这个页面的返回包里都会有XSS代码；
- 反射型XSS：你发送一次带XSS代码的请求，只能在当前返回的数据包中发现XSS代码；
- DOM型XSS：你发送一次带XSS代码的请求，在返回包里压根儿就找不到XSS代码的影子；


# 2.mysql的网站注入，5.0以上和5.0以下有什么区别？


---


# 反序列化:


序列化就是把**对象转换成字节流**，便于保存在内存、文件、数据库中；
反序列化即逆过程，由字节流还原成对象。

Java中的ObjectOutputStream类的writeObject()方法可以实现序列化，类ObjectInputStream类的readObject()方法用于反序列化。

下面是将字符串对象先进行序列化，存储到本地文件，然后再通过反序列化进行恢复

问题在于，如果Java应用对用户输入，即不可信数据做了反序列化处理，那么攻击者可以通过构造恶意输入，让反序列化产生非预期的对象，非预期的对象在产生过程中就有可能带来任意代码执行。


---

# 上传漏洞:

文件上传
简单的上传文件，查看响应

是否只是前端过滤后缀名，文件格式，抓包绕过

是否存在截断上传漏洞

是否对文件头检测，（图片马等等）
是否对内容进行了检测，尝试绕过方法

是否存在各种解析漏洞

---

# 解析漏洞:
## apache
一、不可识别解析
apache解析文件的规则是从右到左开始判断解析,如果后缀名为不可识别文件解析,就再往左判断。比如 test.php.owf.rar “.owf”和”.rar” 这两种后缀是apache不可识别解析,apache就会把wooyun.php.owf.rar解析成php。

## iis

在IIS-6.0的版本，服务器默认不解析;后面的内容，所以xxx.asp;.jpg会被解析成xxx.asp。



---


## 上传

A客户端javascript检测(通常为检测文件扩展名)

B服务端MIME类型检测(检测Content-Type内容)

![2D3C5CEC-D23B-4AD9-90B0-6D9044E37544](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/2D3C5CEC-D23B-4AD9-90B0-6D9044E37544.png)

C服务端目录路径检测(检测跟path参数相关的内容)

![20200914000100](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200914000100.png)


D服务端文件扩展名检测(检测跟文件extension相关的内容)

![20200914000126](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200914000126.png)

E服务端文件内容检测(检测内容是否合法或含有恶意代码

![39EFE11A-7C4F-42C0-B013-00F5061E1F9B](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/39EFE11A-7C4F-42C0-B013-00F5061E1F9B.png)

[文件上传攻击框架](https://www.mi1k7ea.com/2019/10/14/文件上传漏洞总结/#绕过方法-4)

---


XSIO 攻击http://huaidan.org/archives/2154.html#more-2154

LDAP注入

XPATH注入

XML注入

XXE注入http://blog.csdn.net/u011721501/article/details/43775691