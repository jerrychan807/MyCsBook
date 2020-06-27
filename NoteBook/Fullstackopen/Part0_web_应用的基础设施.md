
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


---

## AJAX

---

## Single page app

---

## Javascript-libraries

---

## Full stack -web development

## Javascript fatigue

## Exercises 0.1.-0.6.

