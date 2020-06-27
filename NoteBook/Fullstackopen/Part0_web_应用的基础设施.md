
# Web 应用的基础设施

## HTTP GET


### 请求html页面

![20200623215106](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200623215106.png)

上半部分，General 中的内容，说明了浏览器使用 GET 方法向地址 https://fullstack-exampleapp.herokuapp.com/ 发送了一个请求，并且请求成功，因为服务器响应的状态码为 200。

![20200623215257](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200623215257.png)

上面的 响应头`Response headers`部分告诉我们一些信息，例如

- 响应的大小(以字节为单位)
- 响应的具体时间

有个重要的 header `Content-Type` 告诉我们，响应是utf-8 格式的文本文件，其内容已用 HTML 格式化。 

通过这种方式，浏览器知道响应是一个常规的 html 页面，并将它“像一个网页”一样渲染到浏览器中。



`Response`标签页展示了响应数据，这是一个常规的 html 页面。 

`body`部分决定了其渲染在屏幕上的页面结构:


![20200625022422](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625022422.png)

页面包含一个`div`元素，该元素又包含一个标题、一个指向 `notes`页面的链接，以及一个`img`标签，并显示了创建 note 的数量。

---

### 请求图片:

由于有一个`img`标签，浏览器会执行第二个 http 请求，从服务器获取图像`kuba.png`。 请求的详情如下:

![20200625022936](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625022936.png)

这个请求是给地址`https://fullstack-exampleapp.herokuapp.com/kuva.png`发送的，它的类型是 HTTP GET。 

响应头告诉我们，响应大小为 89350 字节，其Content-type为 image/png，因此它是一个 png 图像。 

浏览器使用这些信息将图像正确地渲染到屏幕上。

---

### 调用链条:

在浏览器上打开页面`https://fullstack-exampleapp.herokuapp.com/`所产生的整个调用链条如下:

![20200625023256](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625023256.png)

首先，浏览器向服务器发出 HTTP GET 请求，以获取页面的 HTML 代码。 

Html 中的 img 标签提示浏览器还要去获取图像 kuba.png。 浏览器将 HTML 页面和图像渲染到屏幕上。

尽管很难观察到，但 HTML 页面在从服务器获取图像之前就已经开始渲染了。


---

## Traditional web applications:

### 模板:
示例应用的主页运作方式与传统的 web 应用类似。

当进入一个页面时，浏览器会从服务器获取 HTML 文档的详细页面结构，以及文本内容。

服务器以某种方式生成了这个文档。 这个文档可能是保存在服务器目录中的静态文本文件， 也可能是服务器根据应用的代码动态构建的 HTML 文档，比如，数据可能是来自数据库的。

示例应用的 HTML 代码是动态的，因为它包含已创建 Note 的数量信息。

主页的 HTML 代码如下所示:

![20200625023555](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625023555.png)


Html 页面的内容被保存为 template 模板字符串，或者说是一个能够运行的字符串，因为它其中包含有变量:如`noteCount`

### 传统的web应用特点:

在传统的 web 应用中，浏览器是个“憨憨”。 它只会从服务器获取 HTML 数据，所有应用的逻辑都在服务器上处理。 


---

## Running application logic on the browser

【在浏览器上运行应用逻辑】

当进入`notes`页面时，浏览器会执行4个HTTP请求:

![20200625024041](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625024041.png)


所有的请求都请求了不同的类型,第一个请求的类型是 document.也就是页面的 HTML 代码.

![20200625024200](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625024200.png)


**当我们比较浏览器上显示的页面和服务器返回的 HTML 代码时，我们注意到这些代码并不包含 Note 列表的数据。**

Html 的 head部分 包含一个 script 标签，它会让浏览器去 获取一个名为 main.js 的 JavaScript 文件。

JavaScript 代码看起来像这样:

![20200625024308](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625024308.png)

在获取到 script 标签后，浏览器便立即开始执行 script 的代码。

最后两行定义了让浏览器对服务器地址 /data.json 执行一个 HTTP GET 请求

### js请求:

JavaScript 代码会下载包含 Note 列表的的 JSON 数据，并利用 Note 的内容构建出一个符号列表:

构建是通过如下代码实现的:

```js
const data = JSON.parse(this.responseText);
console.log(data);

// 创建了一个带有 ul-标签 的无序列表
var ul = document.createElement('ul'); 


// 为每个 Note 加上一个 li-标签。仅将每个 Note 的 content 字段变成了 li-标签 的内容
ul.setAttribute('class', 'notes'); 

data.forEach(function(note) {
  var li = document.createElement('li');

  ul.appendChild(li);
  li.appendChild(document.createTextNode(note.content));
});

document.getElementById('notes').appendChild(ul);
```

### console.log

控制台上能输出内容是因为代码中的 console.log 命令:


```js
const data = JSON.parse(this.responseText);
console.log(data);
```

因此，在从服务器接收到数据之后，代码将其打印到了控制台。

---

## Event handlers and Callback functions

【事件处理和回调函数】

发送到服务器的请求放在了最后一行，但是处理响应的代码却在上面定义了。

这是怎么回事？

![20200625024859](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625024859.png)


`onreadystatechange`这个事件处理程序是定义在`xhttp`对象上的，xhttp对象是用于执行请求的。

当这个对象的状态发生改变时，浏览器调用了这个事件处理函数。

这种调用事件处理程序的机制在 JavaScript 中非常常见。 事件处理函数被称为回调函数（callback functions）。

应用代码并不直接调用函数本身，而是运行时环境（浏览器）会在事件发生时的适当时间调用函数。


---

## Document Object Model or DOM

我们可以将 html 页面看作隐式树结构。

![20200625025037](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625025037.png)

在控制台Elements选项卡中可以看到相同的树状结构。

![20200625025055](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625025055.png)

浏览器的功能就是基于这种，把 HTML元素描述成一棵树的想法。

文档对象模型(Document Object Model，DOM)是一个应用编程接口(Application Programming Interface，API) ，它支持对 web 页面对应的元素树进行编程修改。

上一部分中介绍的 JavaScript 代码就是使用 DOM-API 向页面添加 Note 列表。

下面的代码为变量 ul 创建了一个新节点，并向其添加一些子节点:

```js
var ul = document.createElement('ul');

data.forEach(function(note) {
  var li = document.createElement('li');

  ul.appendChild(li);
  li.appendChild(document.createTextNode(note.content));
});
```

最后，ul 变量的树分支被接到了整个页面的 HTML 树中的适当位置:

```js
document.getElementById('notes').appendChild(ul);
```

---

## Manipulating the document-object from console
【从控制台中操作文档对象】

Html 文档 DOM 树的最顶层节点称为文档document对象。 我们可以使用 DOM-API 在网页上执行各种操作。

![20200625025339](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625025339.png)

---

## CSS

![20200625025450](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625025450.png)

层叠样式表, 或者叫 CSS，是一种用来确定**web应用外观的标记语言**。

获取的 css 文件如下所示:

![20200625025637](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625025637.png)

类选择器的定义始终以句点开头，并包含类的名称。

这些类是属性attributes，可以添加到 HTML 元素中。

---

### 如何查看css属性:

CSS 属性可以在控制台的 element 标签上查看:

![20200625025757](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625025757.png)

- 最外面的`div`元素有`class`属性 ，值为`container` 
- 包含notes列表的`ul`元素也有`class`属性 ，名为`notes`。

---

### css属性详解1:

```css
.container {
  padding: 10px;
  border: 1px solid;
}
```

CSS 规则定义了`container`类的元素，将用一个像素宽的边框 `border`勾勒出来。 

它还为该元素设置了10个像素的填充padding。这会在元素内容和边框之间增加一些空白。

---

### css属性详解2:

```css
.notes {
  color: blue;
}
```

CSS 规则将文本颜色设置为蓝色

---

## Loading a page containing JavaScript - revised
【加载一个包含 JavaScript 的页面-复习】


复习一下在浏览器上打开页面`https://fullstack-exampleapp.herokuapp.com/notes`时会发生什么。

![20200625031646](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200625031646.png)

- 浏览器使用`HTTP GET`请求从服务器获取定义内容和页面结构的 HTML 代码
- Html 代码中的`Links`标签会让浏览器获取 CSS 样式表 `main.css`
- 以及`JavaScript`代码文件`main.js`
- 浏览器执行 JavaScript 代码，代码向地址`https://fullstack-exampleapp.herokuapp.com/data.json`发出 HTTP GET 请求，请求返回了包含 note 的 JSON 数据。
- 获取数据后，浏览器执行一个`event handler`事件处理程序, 使用`DOM-API`将Note渲染到页面

---

## Forms and HTTP POST
【表单与 HTTP POST】

### 302跳转:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/DE455FDB-F4C2-4D12-A770-42793EE9554E.png)

表单提交事件是对服务器`/new_note`地址发送的 HTTP POST请求。 

服务器用 HTTP 状态码 302 进行响应。 

这是一个URL 重定向，服务器通过这个 URL 重定向，请求浏览器执行一个新的 HTTP GET 请求，该请求定义在 Header 的 Location (即 /notes 地址)中。

因此，浏览器重新加载 Notes 页面。 重新加载会导致另外三个 HTTP 请求: 获取样式表(main.css)、 JavaScript 代码(main.js)和 notes 的原始数据(data.json)。


---

### 表单提交:

Form 标签具有属性`action`和`method`，它们定义了将表单作为一个 HTTP POST 请求提交到地址`/new_note`。

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/7515C748-9481-4BBE-A3AD-77C72DE7DF3B.png)

服务器上负责 POST 请求的代码很简单:

```js
app.post('/new_note', (req, res) => {
  notes.push({ // 添加到notes数组里
    content: req.body.note,
    date: new Date(),
  });

  return res.redirect('/notes');
});
```


---

## AJAX

应用的 Notes 页面遵循本世纪初的 web 开发风格，并且“使用了 Ajax”。

这种技术在当时，2000 年初正处于 web 技术浪潮的顶峰。

`AJAX (Asynchronous Javascript and XML)`是在浏览器技术进步的基础上于 2005 年 2 月引入的一个术语，它描述了一种新的革命性的方法，这种方法**使用包含在HTML中的 JavaScript 来获取网页内容，而且不需要重新渲染页面。**

在 AJAX 之前的年代，所有的 web 页面都像我们在本章前面看到的传统 web 应用一样工作。 页面上显示的所有数据都是从服务器生成的 html 代码获取的。

Notes 页面使用了 AJAX 获取 Notes 数据。 提交表单仍然使用传统的 web 表单提交机制。


---

## Single page app
【单页面应用】

在我们的示例应用中，主页的工作方式类似于**传统的网页: 所有的逻辑都在服务器上，浏览器只按照指示渲染HTML。**

Notes 页面为浏览器提供了一些职责，为现有的 Note 生成 HTML 代码。

浏览器通过执行从服务器获取的`JavaScript`代码来处理这个任务。

代码从服务器以`Json`格式获取 Note，并对其添加 HTML元素，并利用`DOM-API`将 Note 显示到页面中。

近年来，创建网络应用的单页应用`Single-page application (SPA)`风格出现了。

**SPA类型的网站不会像我们的示例应用那样从服务器上单独获取所有页面，而是只从服务器获取一个 HTML 页面，其内容由 JavaScript在浏览器中执行操作。**

我们的应用的 Notes 页面与 SPA 风格的应用有一些相似之处，但它还没有完全到位。 

尽管显示Note 的逻辑是在浏览器上运行的，但页面仍然使用传统的方式添加新Note 。 

数据通过表单提交发送到服务器，服务器指示浏览器重新加载带有重定向的 Notes 页面。

单页应用版本可以在`https://fullstack-exampleapp.herokuapp.com/spa`中找到。 

乍一看，这个应用看起来与前一个应用完全相同。

Html 代码几乎完全相同，但JavaScript文件不同(`spa.js`) ，form 标签的定义方式有一个小小的变化

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/45C1E531-E2A2-44F1-AD17-756FD3E66C7D.png)

表单没有`action`属性或`method`属性来定义如何以及往哪里发送输入数据。

你会注意到浏览器只向服务器发送了一个请求。

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/9E926840-B5AE-4B83-B266-B8E9DA679502.png)

Post 请求到地址new_note_spa，包含新 Note 的 JSON 数据，其中既包含 Note 的内容(content) ，也包含时间戳(date) :

```python
{
  content: "single page app does not reload the whole page",
  date: "2019-05-25T15:15:59.905Z"
}
```

请求的`Content-Type`头信息告诉服务器，所包含的数据是以`JSON`格式表示的。

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/5AA354DD-81BC-4172-9C07-489AFE5CF33D.png)

如果没有这个头，服务器将不知道如何正确地解析数据。

服务器用创建的`状态码201`进行响应。 这次服务器没有请求重定向，浏览器会保持在同一页面上，并且不再发送 http 请求。

这个应用的`SPA`版本并不以传统的方式发送表单数据，而是使用从服务器获取的`JavaScript`代码。 

```js
var form = document.getElementById('notes_form'); //从页面中提取 form 元素
form.onsubmit = function(e) {   //注册一个事件处理函数来处理表单提交事件。
  e.preventDefault();  // 事件处理函数将立即调用方法 e.preventDefault () ，以防止对表单 submit 的默认处理

  var note = {   // 创建一个新的 Note
    content: e.target.elements[0].value,
    date: new Date(),
  };

  notes.push(note);  // 使用命令notes.push(note)将其添加到 Note 列表中
  e.target.elements[0].value = '';
  redrawNotes();  // 在页面上重新渲染了 Note 列表
  sendToServer(note);  // 向服务器发送了新建 Note 的请求
};
```

向服务器发送 Note 的代码如下:

```js
var sendToServer = function(note) {
  var xhttpForPost = new XMLHttpRequest();
  // ...

  xhttpForPost.open('POST', '/new_note_spa', true);  // 通过 HTTP POST 请求发送的
  xhttpForPost.setRequestHeader('Content-type', 'application/json'); // 数据类型是 JSON。 数据类型由 Content-type Header 确定
  xhttpForPost.send(JSON.stringify(note));// 数据以 json 字符串的形式发送
};
```

---

## Javascript-libraries
【Javascript 库】

### JQuery:

当时，在 web 应用主要遵循服务器生成 HTML 页面的传统风格，`JQuery`当时是在这种情况下发展起来的。

这种风格的功能通过在浏览器端使用`JavaScript`搭配使用`JQuery`来增强。 

`JQuery`成功的原因之一是它所谓的跨浏览器兼容性。 不管是哪家公司的哪个浏览器，这个库都能正常工作，所以不需要特定于浏览器的解决方案。 

如今，由于`VanillaJS`的进步，使用`JQuery`已经不那么合理了，而且最流行的浏览器通常都能很好地支持基本功能。

### JS的发展:
单页应用的兴起带来了几种比 JQuery 更“现代”的网页开发方式。 第一波开发者的最爱是`BackboneJS`。 

自 2012 年 launch 以来，谷歌的 AngularJS 几乎快速成为现代网络开发的行业标准。

然而，自从`Angular`团队在 2014 年 10 月宣布对第 1 版的支持将结束，`Angular 2`将不再向后兼容第一版后，`Angular`的受欢迎程度直线下降。 `Angular 2`和更新的版本没有得到太热烈的欢迎。

目前，实现 web 应用浏览器端逻辑的最流行的工具是 Facebook 的 React-库。

`React`的势头看起来很猛，但是`JavaScript`的世界是不断变化的。 例如，最近的一个新秀`VueJS`已经引起了一些兴趣。

---

## Full stack -web development
【全栈-web 开发】

几乎所有的 web 应用都有(至少)两个“层” : 最接近最终用户的浏览器是最顶层，而服务器是最底层。

在服务器下面通常还有一个数据库层。 

因此，我们可以**将 web 应用的体系结构看作是一层层的堆栈**。

通常，我们也会讨论`前端frontend`和`后端backend`。 

浏览器是前端，运行在浏览器上的`JavaScript`是前端代码。 另一方面，服务器是后端。

全栈 web 开发意味着我们关注应用的所有部分: 从前端、后端到数据库。 

有时候，服务器上的软件及其操作系统会被看作是全栈的一部分，但我们不会深入讨论这部分内容。

过去，对于开发人员来说，更常见的做法是专注于全栈的某个层，例如后端。 

后端和前端的技术栈完全不同。 

随着全栈趋势的出现，对于开发人员来说，熟练掌握应用和数据库的全栈内容已经变得非常普遍。 

通常情况下，全栈开发人员还必须有足够的配置和管理技能来操作他们的应用，例如，上云。

---

## Javascript fatigue
【Javascript 疲劳】

全栈 web 开发在许多方面都具有挑战性。 

在许多地方会有突发情况，并且调试起来比普通桌面应用要困难得多。 

Javascript (与许多其他语言相比) 并不总是像你期望的那样工作 ，其运行时环境的异步工作方式带来了各种各样的挑战。 网络中的通信需要对 http 协议的知识储备。

还必须处理数据库、服务器管理和配置。 

了解足够的 CSS 至少在一定程度上能够使应用显得好看。

JavaScript 的世界发展得很快，也带来了一系列的挑战。 

工具、库和语言本身都在不断发展。 

有些人已经开始厌倦了这种不断的变化，并为此创造了一个新词: Javascript 疲劳。


----

## Exercises 0.1.-0.6.

