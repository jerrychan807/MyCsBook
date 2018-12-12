
- [Chapter25: OOP:The Big Picture](#chapter25-oopthe-big-picture)
  - [object-based 基于对象:](#object-based-%E5%9F%BA%E4%BA%8E%E5%AF%B9%E8%B1%A1)
  - [object-oriented 面向对象:](#object-oriented-%E9%9D%A2%E5%90%91%E5%AF%B9%E8%B1%A1)
    - [关键点:inheritance hierarchy](#%E5%85%B3%E9%94%AE%E7%82%B9inheritance-hierarchy)
    - [好处:minimize redundancy](#%E5%A5%BD%E5%A4%84minimize-redundancy)
  - [Class 类:](#class-%E7%B1%BB)
    - [Class与OOP的关系:](#class%E4%B8%8Eoop%E7%9A%84%E5%85%B3%E7%B3%BB)



# Chapter25: OOP:The Big Picture

---

## object-based 基于对象:

之前主要是用围绕`object`这个概念去写代码,所以写出来的的代码都是`object-based`.

- 在代码中传递`object`
- 调用他们的`method`

## object-oriented 面向对象:

### 关键点:inheritance hierarchy
但是要使我们的代码真正符合`object-oriented (OO)`标准的话,我们需要用到`inheritance hierarchy`.(层次化的继承)

> 我之前写的代码比较少去继承自己的实现的父类,所以并非真正意义上的`object-oriented`的代码。

### 好处:minimize redundancy

OOP offers a different and often more effective way of looking at programming, in which we factor code to `minimize redundancy`, and write new programs by customizing existing code instead of changing it in-place

OOP提供了一种更有效的编程视角,能够让我们在组织代码的时候耦合性最小化.

---

## Class 类:

定义：a device used to `implement new kinds of objects` in Python that `support inheritance`

- 实现新式类
- 支持继承

### Class与OOP的关系:
class 是OOP的主要工具。

> 还有什么其他tools呢？


