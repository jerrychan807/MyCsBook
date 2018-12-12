
- [Chapter25: OOP:The Big Picture](#chapter25-oopthe-big-picture)
    - [好处:minimize redundancy](#%E5%A5%BD%E5%A4%84minimize-redundancy)
    - [entirely optional：](#entirely-optional)
  - [Class 类:](#class-%E7%B1%BB)
    - [Class与OOP的关系:](#class%E4%B8%8Eoop%E7%9A%84%E5%85%B3%E7%B3%BB)
    - [Class与built-in type的关系:](#class%E4%B8%8Ebuilt-in-type%E7%9A%84%E5%85%B3%E7%B3%BB)
    - [](#)
  - [Question:](#question)

# Chapter25: OOP:The Big Picture

---


之前主要是用围绕`object`这个概念去写代码,所以写出来的的代码更多都是`object-based`意义上的.

即
- 在代码中传递`object`
- 调用他们的`method`

但是要使我们的代码真正符合`object-oriented (OO)`标准的话,我们需要用到`inheritance hierarchy`.(层次化的继承)

---

那我们在python中通过什么去用到这种层次化的继承呢?

这个时候我们需要用到`Class`.

Class是我们实现新式类并支持继承的一种媒介。（`class` — a device used to implement new kinds of objects in Python that support inheritance.）

我们就可以在python这门语言中使用`Class`这个工具去用`OOP`了。

---

为什么要用OOP？OOP有什么好处？

因为OOP提供一种更高效的编程视角给我们,使得我们在组织代码的时候能让耦合性最小化,而且在写新的程序的时候,只需要重新定制已有的代码,而不需要重写一遍.

OOP offers a different and often more effective way of looking at programming, in which we factor code to minimize redundancy, and write new programs by customizing existing code instead of changing it in-place

---







### 好处:minimize redundancy

OOP offers a different and often more effective way of looking at programming, in which we factor code to `minimize redundancy`, and write new programs by customizing existing code instead of changing it in-place

OOP提供了一种更有效的编程视角,能够让我们在组织代码的时候耦合性最小化.

> - 低耦合
> - 把事物抽象成一个对象,然后对象做出行为

### entirely optional：

在python中,并非是处处都需要用到OOP思想的。

OOP是一种抽象的思想,要想用好OOP需要提前的计划,更多的是战略上的意义`strategic mode (doing long-term product development)`,而非战术上的`tactical mode (where time is in very short supply)`

---

## Class 类:

定义：a device used to `implement new kinds of objects` in Python that `support inheritance`

- 实现新式类
- 支持继承

### Class与OOP的关系:
class 是OOP的主要工具。


### Class与built-in type的关系:

Class与内置类型很相似,虽然Class设计是用来创建和管理对象的,但是他们有共同点:

- 都是`packages of functions`.
- 支持继承

### 


---

## Question:

- 我之前写的代码比较少去继承自己的实现的父类,所以并非真正意义上的`object-oriented`的代码。



- 还有什么其他tools呢？