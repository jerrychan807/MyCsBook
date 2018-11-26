# 网站后台


---

# Preface:

需要做一个功能模块去找网站的后台的路径

login page to the backend of a website

---

# What

## 什么是网站的后台?

所谓的网站后台管理系统主要是用于对网站前台的信息管理，如文字、图片、影音、和其他日常使用文件的发布、更新、删除等操作，同时也包括会员信息、订单信息、访客信息的统计和管理等相关操作。

简单来说就是对网站数据库和文件的快速操作和系统管理，方便前台内容的更新及管理。

---

## 一般网站后台会存在哪些地方？

### 在当前网站

#### 根目录下:

- 常见通用后台地址
    - `Admin.php`、`login.action` 、`manage.asp` 等
- 添加前缀后缀
    - `initLogin.action`

#### 不在根目录下:

- 即在某个特殊文件夹下
    - `/DatastatisticsThree/loginAction/goIndex.action`
    - `/platform/login!login.action`
    - `/system/gotoLogin.action`

---

### 不在当前网站
#### 在旁站上

- 在别的端口后面
    - `xxx.com.cn:9092/admin/login.action`

#### 在二级域名上

- 原网站是www.xxx.me
- 后台在`http://service.xxx.me/admin/login `    
    - (当然有可能是其他业务后台）

#### 在其他主机上

---

# Why

## 为什么要找网站的后台？

### 从attack face角度来看:

属于attack face中的一点,是攻击的入口点之一,所以有必要找出来。

### 从安全管理角度来看:

后台被爆破是个常见的安全问题,找出

### 从Pentest角度来看:

有个原则: 最小的权限+最少的服务=最大的安全

而后台的权限更高,服务多，那么它的安全性大大降低。

对于渗透测试来说,得到后台的必要性：

具体操作:

- 后台能执行更多的敏感操作，如上传webshell。
- 通过SQL注入等拿到后台账户密码需要后台地址来登陆。
- 后台验证的相关安全性会更低点。


---

# How

## 有什么方法找到网站的后台?这些方法的原理?优劣之分?

### 穷举猜解

原理:

- 字典爆破
- 返回值筛选


注意点：

要看工具猜解路径的运用原理，大部分是根据返回的头部的代码，如200,404等等，这里要特别注意一些奇葩设置网站，所有路径都会200成功状态的那种。

### 搜索引擎:

google dork 
`site:bgy.com.cn intitle:管理|后台|登陆|`

```
site:www.exeHack.Net intext:管理  无利用
site:www.exehack.Net inurl:login(登陆的页面)
site:www.exehack.net intitle:后台 (很容易的就找到了网站的后台登陆口)
site:exehack.net intext:管理|后台|登陆|用户名|密码|验证码|系统|帐号|manage|admin|login|system
site:exehack.net inurl:login|admin|manage|manager|admin_login|login_admin|system
site:exehack.net intitle:管理|后台|登陆|
site:heimian.com intext:验证码
```

更多参考:[Google Hacking Database (GHDB)](https://www.exploit-db.com/google-hacking-database/12/?pg=1)


baidu dork

参考：[How To系列(二):how to baidu dork](http://bbs.51cto.com/thread-1169775-1.html)


## #

---

# When&Where&Who


---

# History:


---

# ADEPT:

## Analogy 类比：这个概念像是什么已知的东西？

## Diagram 可视化：把这个概念画出来是什么样子？

## Example 实例：有什么身边的案例？

## Plain English 大白话讲解：如何向一个小孩讲解这个概念？

## Technical Definition：真正专业的定义和描述是什么样的？

---

# Principle:

## 它的本质是什么？

## 它的第一原则是什么？

## 它的知识结构是怎样的?


---

# Expansion



---

# Inspire


----

# Refs：

- [web安全之如何全面发现系统后台](https://www.secpulse.com/archives/59065.html)
- [查找网站后台方法整理](https://www.waitalone.cn/find-admin-page.html)
- [如何快速查找网站后台地址方法整理](https://www.exehack.net/39.html)
- [Google Hacking Database (GHDB)](https://www.exploit-db.com/google-hacking-database/12/?pg=1)
- [How To系列(二):how to baidu dork](http://bbs.51cto.com/thread-1169775-1.html)

---


# Github Resource:

- [A Python Script to find admin panel of a site](https://github.com/bdblackhat/admin-panel-finder)
- [website admin panel finder](https://github.com/susmithHCK/cpscan)
- [Blazing fast admin panel finder with multi-threading](https://github.com/the-c0d3r/admin-finder)
- [Admin Panel Finder](https://github.com/Cvar1984/Spyder)
- [This script is written in Perl this is very good admin page finder.](https://github.com/AppoPro/Admin-Control-Panel-Finder)
- [web安全之如何全面发现系统后台](https://www.secpulse.com/archives/59065.html)


---








