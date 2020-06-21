

- [About OOP:](#%08about-oop)
  - [1.为什么要用OOP？](#1%E4%B8%BA%E4%BB%80%E4%B9%88%E8%A6%81%E7%94%A8oop)
  - [2.一定要使用OOP吗?](#2%E4%B8%80%E5%AE%9A%E8%A6%81%E4%BD%BF%E7%94%A8oop%E5%90%97)
  - [3.如何用好OOP?](#3%E5%A6%82%E4%BD%95%E7%94%A8%E5%A5%BDoop)
  - [4.Class是什么?:](#4class%E6%98%AF%E4%BB%80%E4%B9%88)
- [Why Use Classes?](#why-use-classes)
- [概览OOP](#%E6%A6%82%E8%A7%88oop)
  - [属性继承搜索 Attribute Inheritance Search](#%E5%B1%9E%E6%80%A7%E7%BB%A7%E6%89%BF%E6%90%9C%E7%B4%A2-attribute-inheritance-search)
  - [类和实例 Classes and Instances:](#%E7%B1%BB%E5%92%8C%E5%AE%9E%E4%BE%8B-classes-and-instances)
  - [类方法调用 Class Method Calls:](#%E7%B1%BB%E6%96%B9%E6%B3%95%E8%B0%83%E7%94%A8-class-method-calls)
  - [编写类树:](#%E7%BC%96%E5%86%99%E7%B1%BB%E6%A0%91)
  - [OOP是为了代码重用](#oop%E6%98%AF%E4%B8%BA%E4%BA%86%E4%BB%A3%E7%A0%81%E9%87%8D%E7%94%A8)
  - [总结:](#%E6%80%BB%E7%BB%93)
- [Expansion:](#expansion)


# About OOP:

之前主要是用围绕`object`这个概念去写代码,所以写出来的的代码更多都是`object-based`意义上的.

即
- 在代码中传递`object`
- 调用他们的`method`

但是要使我们的代码真正符合`object-oriented (OO)`标准的话,我们需要用到`inheritance hierarchy`.(层次化的继承)

---

那我们在python中通过什么去用到这种层次化的继承呢?

这个时候我们需要用到`Class`.

通过`Class`这个工具实现`OOP`了。

这就引出了一系列的问题:


- 1.为什么要用OOP?
- 2.一定要使用OOP吗?
- 3.如何用好OOP?
- 4.Class是什么?

---

## 1.为什么要用OOP？

OOP有什么好处？

因为OOP提供一种更高效的编程视角给我们,使得我们在组织代码的时候能让耦合性最小化,而且在写新的程序的时候,只需要重新定制已有的代码,而不需要重写一遍.

(OOP offers a different and often more effective way of looking at programming, in which we factor code to `minimize redundancy`, and write new programs by customizing existing code instead of changing it in-place)

> me: 一种新的组织代码的视角

## 2.一定要使用OOP吗?

未必,OOP是可选项。

## 3.如何用好OOP?

如果想要用好OOP,需要比较好的设计(up-front planning)

更多的是在战略上的意义(strategic mode),适用于需要长期开发维护的项目.

而并非战术上的意义,仅开发短期要用的项目。

## 4.Class是什么?:

定义：a device used to `implement new kinds of objects` in Python that `support inheritance`

- 实现新式类
- 支持继承

---

知道了使用OOP的好处以及可以通过Class来实现OOP之后,接下来看看为什么要用Class,它有什么好处?

---

# Why Use Classes?

如果我们通过Class来实现OOP,我们可以更好对x在真实世界里的结构和关系进行建模。

通过Class实现的OOP提供了两个方面的好处:

1. 继承 `Inheritance`

    常见的特性`common properties`仅需要实现一次,然后可以重用`reused`

2. 组合  `Composition`

    一个实例可以由各种组件组合起来。
    每个组件可以写成一个类

像继承和组合这些一般性OOP概念,适用于能够分解成一组对象的任何应用程序。

从更具体的程序设计的角度来看,类是python的程序组成单元,就像function和module一样:类是封装逻辑和数据的另一种方式(`packaging logic and data`)。

实际上,像module一样,类也定义新的命名空间`namespaces`。

相比`module`和`function`,在建立新的对象的时候,`class`有三个好处:

1. 多重实例

    - 类是产生对象的工厂。
    - 每次调用一个类，就会产生一个有独立命名空间的新对象。`generate a new object with a distinct namespace`
    - 每个由类产生的对象都能读取类的属性,并获得自己的命名空间来存储自己的数据。

2. 通过继承进行定制 `Customization via inheritance`

    - 我们可以在类的外部重新定义其属性从而去扩充这个类。
    - 类可以建立有层次结构的命名空间。

3. 运算符重载 `Operator overloading`

    - 通过特定的协议方法`special protocol methods`，类可以接近像`built-in types`.

---

知道类的好处之后,我们再继续看看OOP。

---

# 概览OOP

OOP不仅仅是一门技术，更是一种经验。

## 属性继承搜索 Attribute Inheritance Search

在我们使用`object.attribute`的时候,实际上python会启动搜索: 搜索对象连接的树，来寻找attribute首次出现的对象。

搜索顺序:

找出attribute首次出现的地方，先搜索object，然后是该对象之上的所有类，由下至上，由左至右。

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79ly1fzdgw1e5edj318k0lq7b8.jpg)


`Superclasses` provide behavior shared by all their `subclasses`, but because the search proceeds from the bottom up, `subclasses` may `override` behavior defined in their superclasses by redefining superclass names lower in the tree.

## 类和实例 Classes and Instances:

- 相同

    在属性继承搜索树中，类和实例跟模块的概念是很相似的，都提供了一种命名空间 `namespace` (a package of variables, and a place where we can attach attributes)

- 不同：

    类和实例的主要差异在于,类是产生实例的工厂.

    类和模块的差异在于，内存中特定模块只有一个实例(所以我们得重载模块以取得其新的代码),而对于类，我们可以无限生成实例。

- 类比:

    OOP和程序加记录的数据处理模型相似，在OOP中，实例就像是带有“数据”的记录，而类是处理这些记录的“程序”。


## 类方法调用 Class Method Calls:

事实上，每当我们调用附属于类的函数时，总会隐含着这个类的实例。

当运算执行时，总是有个主体对象。

例子:比如加薪函数，除非和应该加薪的员工结合在一起使用，不然这种调用是没有意义的。

Python把隐含的实例传进方法中的第一个特殊的参数设置为`self`

## 编写类树:

我们以class语句和类调用来构造树和对象:

- 每个class语句会生成一个新的类对象
- 每次类调用时，就会产生一个新的实例对象
- 实例自动连接至创建了这些实例的类
- 类连接至超类(super class)的方式是，将超类列在类头部的括号内。其从左至右的顺序会决定树中的次序。

知道了继承搜索的顺序后，你要进行属性附加的对象就很重要了:决定了变量名的作用域。

- 附加在实例上的属性只属于那些实例
- 附加在类上的属性则由所有子类及其实例共享。

例子:

```python
class C1():
    def setname(self, who):
        self.name = who

I1 = C1()
I2 = C2()
I1.setname('bob')
I2.setname('mel')
```

- `setname`这个类方法由所有实例共享
- 类方法会自动接收第一个特殊参数`self`,这个参数提供了被处理实例的参照值
- 因为类是多个实例的工厂,通过这个`self`来取出特定实例的属性

---

## OOP是为了代码重用

类所支持的代码重用的方式，是Python其他程序组件难以提供的。

通过类，我们可以定制代码，而不是在修改原来的代码或者重写代码。

和模块一样，类是由函数和其他变量组成的包`Package`.但是我们可以通过自动的属性继承搜索来实现代码的高层次定制，这是模块和函数做不到的。

> 比如在使用网络请求的时候,只需要构造一个requests的实例,`r = requests.get('https://xxx.com')`就可以轻松发送一个get请求了。

----

## 总结:

OOP就是在对象树中搜索属性。

---

# Expansion:

> 我之前写的代码比较少去继承自己的实现的父类,所以并非真正意义上的`object-oriented`的代码。

