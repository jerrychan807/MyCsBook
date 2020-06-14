# 1. Special Methods

<!-- TOC -->

- [1. Special Methods](#1-special-methods)
- [2. Preface:](#2-preface)
- [3. What](#3-what)
    - [3.1. Special Methods是什么？](#31-special-methods是什么)
    - [3.2. Special Methods的分类是？](#32-special-methods的分类是)
- [4. Why](#4-why)
    - [4.1. 为什么也叫magic Methods？](#41-为什么也叫magic-methods)
    - [4.2. 修改Special method 有什么好处？](#42-修改special-method-有什么好处)
    - [4.3. 为什么会有Special Methods?](#43-为什么会有special-methods)
- [5. How](#5-how)
    - [5.1. 如何应用Special Methods?](#51-如何应用special-methods)
    - [5.2. 如何合理应用Special Methods?](#52-如何合理应用special-methods)
- [6. When&Where&Who](#6-whenwherewho)
    - [6.1. Special Method一般被谁调用?](#61-special-method一般被谁调用)
- [7. History:](#7-history)
- [8. ADEPT:](#8-adept)
    - [8.1. Analogy 类比：这个概念像是什么已知的东西？](#81-analogy-类比这个概念像是什么已知的东西)
    - [8.2. Diagram 可视化：把这个概念画出来是什么样子？](#82-diagram-可视化把这个概念画出来是什么样子)
    - [8.3. Example 实例：有什么身边的案例？](#83-example-实例有什么身边的案例)
    - [8.4. Plain English 大白话讲解：如何向一个小孩讲解这个概念？](#84-plain-english-大白话讲解如何向一个小孩讲解这个概念)
    - [8.5. Technical Definition：真正专业的定义和描述是什么样的？](#85-technical-definition真正专业的定义和描述是什么样的)
- [9. Principle:](#9-principle)
    - [9.1. 它的本质是什么？](#91-它的本质是什么)
    - [9.2. 它的第一原则是什么？](#92-它的第一原则是什么)
    - [9.3. 它的知识结构是怎样的?](#93-它的知识结构是怎样的)
- [10. Expansion](#10-expansion)
- [11. Inspire](#11-inspire)
- [12. Refs：](#12-refs)

<!-- /TOC -->

---

# 2. Preface:

need to clear `Special Methods` 特殊方法 这个concept。

---

# 3. What

## 3.1. Special Methods是什么？

> A class can implement certain operations that are invoked by special syntax (such as arithmetic operations or subscripting and slicing) by defining methods with special names

类可以通过定义有着特殊名字的方法，来实现特定的操作。

> This is Python’s approach to operator overloading, allowing classes to define their own behavior with respect to language operators

定义这些特殊方法，可以重载一些类操作，从而达到`your object`定义自己的行为的目的。

---

## 3.2. Special Methods的分类是？

根据官方文档的分类如下

3.3. Special method names
 - 3.3.1. Basic customization
 - 3.3.2. Customizing attribute access
    - 3.3.2.1. Customizing module attribute access
    - 3.3.2.2. Implementing Descriptors
    - 3.3.2.3. Invoking Descriptors
    - 3.3.2.4. `__slots__`
      - 3.3.2.4.1. Notes on using `__slots__`
 - 3.3.3. Customizing class creation
    - 3.3.3.1. Metaclasses
    - 3.3.3.2. Resolving MRO entries
    - 3.3.3.3. Determining the appropriate metaclass
    - 3.3.3.4. Preparing the class namespace
    - 3.3.3.5. Executing the class body
    - 3.3.3.6. Creating the class object
    - 3.3.3.7. Metaclass example
 - 3.3.4. Customizing instance and subclass checks
 - 3.3.5. Emulating generic types
 - 3.3.6. Emulating callable objects
 - 3.3.7. Emulating container types
 - 3.3.8. Emulating numeric types
 - 3.3.9. With Statement Context Managers
 - 3.3.10. Special method lookup



---

# 4. Why

## 4.1. 为什么也叫magic Methods？

> 魔术方法（magic method）是特殊方法的昵称

把Python当成一个framework的话，去定义`methods with special names`

然后Python的`interpreter`会去overload(重载)这些`Special method`

这时候`your-object`就跟`built-in object`高度一致了。拥有共同的接口。

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fwetkmixnqj31kw0szx5n.jpg)

这就是所谓的magic之处吧。

---

## 4.2. 修改Special method 有什么好处？

The **special method** names allow **your objects** to implement, support, and interact with **basic language constructs** such as:

- Iteration
- Collections
- Attribute access
- Operator overloading
- Function and method invocation
- Object creation and destruction
- String representation and formatting
- Managed contexts (i.e., with blocks)

---

## 4.3. 为什么会有Special Methods?

在python里面

- everthing is an object
- Each object has an identity，a type, and a value
  - id(obj)  returns  the  object’s  idenAty
  - type(obj)  returns  the  object’s  type 

> One of the best qualities of Python is its consistency(一致性)

为了保持一致性,故视为`framework`的python提供了一些特殊方法，可以让你去实现。

这样可以让`your-object`的表现和行为也跟`built-object` 高度相似和接近,也能重复利用`built-object` 原有的强大的功能。

---

# 5. How

## 5.1. 如何应用Special Methods?



## 5.2. 如何合理应用Special Methods?



---

# 6. When&Where&Who

## 6.1. Special Method一般被谁调用?

> `special methods` is that they are meant to be called by `the Python interpreter`, and not by you.

> You write `len(my_object)` and, if my_object is an instance of a user-defined class, then Python calls the `__len__` instance method you implemented

由interpreter调用，一般不用用户自己调用。


---

# 7. History:


---

# 8. ADEPT:

## 8.1. Analogy 类比：这个概念像是什么已知的东西？
## 8.2. Diagram 可视化：把这个概念画出来是什么样子？
## 8.3. Example 实例：有什么身边的案例？
## 8.4. Plain English 大白话讲解：如何向一个小孩讲解这个概念？
## 8.5. Technical Definition：真正专业的定义和描述是什么样的？

---

# 9. Principle:

## 9.1. 它的本质是什么？

## 9.2. 它的第一原则是什么？

consistency(一致性).

让`your-object` more like `built-in object`

## 9.3. 它的知识结构是怎样的?


---

# 10. Expansion


---

# 11. Inspire


----

# 12. Refs：

- [官方文档 special-method-names](https://docs.python.org/3/reference/datamodel.html#special-method-names)

- [Intro to the Python Data Model and Pythonic Programming](https://www.youtube.com/watch?v=GrwV2hi4XHg)

- [Python OOP Tutorial 5: Special (Magic/Dunder) Methods](https://www.youtube.com/watch?v=3ohzBxoFHAY)
- [The Python Datamodel: When and how to write objects](https://www.youtube.com/watch?v=iGfggZqXmB0)

---








