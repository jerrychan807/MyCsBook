# 1. -Go Variables Scope

<!-- TOC -->

- [1. -Go Variables Scope](#1--go-variables-scope)
- [2. Preface:](#2-preface)
- [3. Keyword : Variables](#3-keyword--variables)
    - [3.1. What](#31-what)
        - [3.1.1. 什么是作用域？](#311-什么是作用域)
        - [3.1.2. Go中变量的声明：](#312-go中变量的声明)
        - [3.1.3. 什么是局部变量？](#313-什么是局部变量)
        - [3.1.4. 什么是全局变量？](#314-什么是全局变量)
        - [3.1.5. 什么是形参?](#315-什么是形参)
        - [3.1.6. 什么是实参?](#316-什么是实参)
        - [3.1.7. 各类参数概念的关系是？](#317-各类参数概念的关系是)
        - [3.1.8. if、for作用域:](#318-iffor作用域)
    - [3.2. Why](#32-why)
        - [3.2.1. 作用域的好处是？](#321-作用域的好处是)
    - [3.3. How](#33-how)
        - [3.3.1. 如何判断局部变量的作用域:](#331-如何判断局部变量的作用域)
    - [3.4. When&Where](#34-whenwhere)
        - [3.4.1. 什么时候用全局变量？](#341-什么时候用全局变量)
    - [3.5. Who](#35-who)
        - [3.5.1. 谁第一个提出scope这个概念的？](#351-谁第一个提出scope这个概念的)
    - [3.6. Refs：](#36-refs)
    - [3.7. Imagination:](#37-imagination)
    - [3.8. Expansion](#38-expansion)
    - [3.9. Inspire](#39-inspire)

<!-- /TOC -->

---

# 2. Preface:

艰难入门golang ing...

---

# 3. Keyword : Variables

## 3.1. What

### 3.1.1. 什么是作用域？

作用域为已声明**标识符**所表示的常量、类型、变量、函数或包在源代码中的**作用范围**。



### 3.1.2. Go中变量的声明：

可以在三个地方声明：

- 函数**内**定义的变量称为 `局部变量`
- 函数**外**定义的变量称为 `全局变量`
- 函数定义**中**的变量称为 `形式参数`


### 3.1.3. 什么是局部变量？

在**函数体内**声明的变量称之为局部变量，它们的作用域只在函数体内，参数和返回值变量也是局部变量。

A variable (constant, type, function) is only known in a **certain range** of the program, called the **scope**.

感觉英文的描述理解起来会更准确，你是知道这个变量的**确切使用范围**之后，你才将其declared的,它只在这个范围内 visible and available

示例：

```
package main

import "fmt"

func main() {
   /* 声明局部变量 */
   var a, b, c int 

   /* 初始化参数 */
   a = 10
   b = 20
   c = a + b

   fmt.Printf ("结果： a = %d, b = %d and c = %d\n", a, b, c)
}
```


### 3.1.4. 什么是全局变量？

在**函数体外**声明的变量称之为全局变量，全局变量可以在整个包甚至外部包（被导出后）使用。

全局变量可以在任何函数中使用.

Variables etc. declared outside of any function (**in other words at the top level**) have global (or package) scope: 

they are visible and available in **all source files of the package.**


示例：


```
package main

import "fmt"

/* 声明全局变量 */
var g int

func main() {

   /* 声明局部变量 */
   var a, b int

   /* 初始化参数 */
   a = 10
   b = 20
    //调用全局变量声明的g
   g = a + b

   fmt.Printf("结果： a = %d, b = %d and g = %d\n", a, b, g)
}
```


### 3.1.5. 什么是形参?

形参(**parameter**)：

全称为"形式参数" 由于**它不是实际存在变量**，所以又称**虚拟变量**。

是在定义函数名和函数体的时候使用的参数,目的是用来接收调用该函数时传入的参数.

在调用函数时，实参将赋值给形参。

因而，必须注意实参的个数，类型应与形参一一对应，并且实参必须要有确定的值。


> 提到形参，那就要提一下实参

### 3.1.6. 什么是实参?

实参(**argument**)：

全称为"实际参数"是在调用时传递给函数的参数. 

实参可以是常量、变量、表达式、函数等， 

无论实参是何种类型的量，在进行函数调用时，它们都必须具有确定的值， 以便把这些值传送给形参。

因此应预先用赋值，输入等办法使实参获得确定值。      



### 3.1.7. 各类参数概念的关系是？

- 实参
 - 全局变量
 - 局部变量 
- 形参
 - 局部变量

> 可见，这些参数的概念之间没有很清晰的界限。不能以形参、实参来对全局变量、局部变量进行分类。
>
> 因为他们的本质是指不同的东西。全局、局部变量指的是`scope`作用域，而实参、形参指的是 “真实性”...
>
> maybe他们在内存分配与释放上有所不同~


### 3.1.8. if、for作用域:

```
	if contents, err := ioutil.ReadFile(filename); err != nil{
		fmt.Println(err)
	} else {
		fmt.Printf("%s\n", contents)
	}
	// 在此处contents就无法正常引用了
}
```

if 和 for 这些控制结构，而在这些结构中声明的变量的作用域只在相应的代码块内。




---

## 3.2. Why

### 3.2.1. 作用域的好处是？

不要`scope`作用域这个概念行不行？

我猜测的是，好处可能就是在使用完局部变量之后，可以立即释放出空间出来。

---

## 3.3. How

### 3.3.1. 如何判断局部变量的作用域:

一般情况下，局部变量的作用域可以通过代码块（用**大括号**括起来的部分）判断

---

## 3.4. When&Where

### 3.4.1. 什么时候用全局变量？

换句话说，什么时候要让 `Variable` declared outside of any function (in other words at the top level) 然后让这个 `Variable`have **global (or package) scope**??

当你希望这个变量在other package 能被共用吧，有点像java里面的`public`和`private` 的概念。


---

## 3.5. Who

### 3.5.1. 谁第一个提出scope这个概念的？

-c-

---

## 3.6. Refs：

- [Go 语言变量作用域](http://www.runoob.com/go/go-scope-rules.html)
- [Golang作用域—坑](http://www.cnblogs.com/pyyu/p/8041671.html)
- [实参和形参](http://blog.51cto.com/lazyheart/488803)



---

## 3.7. Imagination:

A variable (constant, type, function) is only known in a certain range of the program, called the **scope**. 

Variables etc. declared outside of any function (in other words at the top level) have **global (or package) scope**: they are visible and available in all source files of the package.

Variables declared in a function have **local scope**: they are only known in that function.

**局部变量**就好像是在学校里的学生卡一样，只有你学校的人认可且知道你的学生卡，所以你的学生卡的作用域比较小，只能在学校范围内使用。

**全局变量**就像每个人的身份证。身份证的作用比学生证的作用大多了，可使用的范围也大很多。

形参感觉范围就更小了，只在**函数定义中**使用，就好像那种发抽奖用的临时小卡片一样。

---

## 3.8. Expansion

- 实参、形参的真正不同?内存分配?
- 编程语言中，不要`scope`作用域这个概念行不行？为什么要有这个概念？局部变量可以提前释放空间？
- java里面的`public`和`private`???
- 谁第一个提出scope这个概念的？

---


## 3.9. Inspire

感觉任何编程语言的一些概念，都有一个更deep的含义，是因为某种需要才这样设计的，才会有这些语法。

所以，maybe 有时间要去看看 how to build a language~

嗯嗯。。编译原理。。。


----







