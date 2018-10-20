# Data Model

<!-- TOC -->

- [Data Model](#data-model)
- [Preface:](#preface)
- [What](#what)
    - [什么是Data Model?](#什么是data-model)
    - [Data Model与Python的关系？](#data-model与python的关系)
    - [Data Model 的作用？](#data-model-的作用)
    - [interpreter & DataModel 的关系?](#interpreter--datamodel-的关系)
- [Why](#why)
    - [为什么要了解Data Model?](#为什么要了解data-model)
- [How](#how)
    - [Special Method被调用的方式？](#special-method被调用的方式)
- [When&Where&Who](#whenwherewho)
    - [Special Method一般被谁调用?](#special-method一般被谁调用)
- [History:](#history)
- [ADEPT:](#adept)
    - [Analogy 类比：这个概念像是什么已知的东西？](#analogy-类比这个概念像是什么已知的东西)
    - [Diagram 可视化：把这个概念画出来是什么样子？](#diagram-可视化把这个概念画出来是什么样子)
    - [Example 实例：有什么身边的案例？](#example-实例有什么身边的案例)
    - [Plain English 大白话讲解：如何向一个小孩讲解这个概念？](#plain-english-大白话讲解如何向一个小孩讲解这个概念)
    - [Technical Definition：真正专业的定义和描述是什么样的？](#technical-definition真正专业的定义和描述是什么样的)
- [Principle:](#principle)
    - [它的本质是什么？](#它的本质是什么)
    - [它的第一原则是什么？](#它的第一原则是什么)
    - [它的知识结构是怎样的?](#它的知识结构是怎样的)
- [Expansion](#expansion)
- [Inspire](#inspire)
- [Refs：](#refs)

<!-- /TOC -->

---

# Preface:

最近在看《fluent python》

need to clear python **Data Model** 这个concept。

---

# What

## 什么是Data Model?

Data model as a **description** of Python as a framework. 

It formalizes the interfaces of the building blocks of the language itself, such as sequences, iterators, functions, classes, context managers, and so on


## Data Model与Python的关系？

> While coding with any framework, you spend a lot of time implementing methods that are called by the framework

Python是framework，而 Data Model是API的references，描述了那些会被framework自身调用的方法。

![](https://ws4.sinaimg.cn/large/006tNbRwgy1fwdft3itr9j30ka0d6408.jpg)

## Data Model 的作用？

> the Python **data model**, and it describes the **API** that you can use to make **your own objects** play well with **the most idiomatic language features**.

简单的说，可以让你的自建对象更强大,拥有原生对象的更多特性功能。

## interpreter & DataModel 的关系?

DataModel说明了 Special methods 的结尾开头都要双下划线(like `__getitem__`)

> The Python interpreter invokes special methods to perform basic object oper‐ations, often triggered by special syntax

interpreter看到双下划线就能解析。

例子：

`obj[key]`调用的是`__getitem__`方法

为了求`my_collection[key]`的值，interpreter实际上会调用`my_collection.__getitem__(key)`

---

# Why

## 为什么要了解Data Model?

即了解清楚Data Model之后有什么好处？

了解Data Model，才能够自己去修改很多`Special method`



---

# How

## Special Method被调用的方式？

> More often than not, the special method call is implicit. 

大多是隐式调用。

例子:

For example, the statement `for i in x`: actually causes the invocation of `iter(x)`, which in turn may call `x.__iter__()` if that is available.


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

- 《fluent python》
- [python3 Data model](https://docs.python.org/3/reference/datamodel.html)

---








