# Python Module

<!-- TOC -->

- [Python Module](#python-module)
- [Preface:](#preface)
- [Keyword:](#keyword)
- [What](#what)
    - [Module是什么及其作用?](#module是什么及其作用)
    - [import Module的相关语法？](#import-module的相关语法)
    - [Module的功能角色?](#module的功能角色)
- [Why](#why)
    - [为什么需要Module?](#为什么需要module)
    - [为什么使用Module?](#为什么使用module)
- [How](#how)
    - [如何从Module到一个project/program?](#如何从module到一个projectprogram)
        - [`top-level file`:](#top-level-file)
        - [`other module`:](#other-module)
        - [`import`->`access`](#import-access)
        - [示例：](#示例)
        - [`object.attribute`](#objectattribute)
- [When&Where&Who](#whenwherewho)
    - [什么时候用import或者from?](#什么时候用import或者from)
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

估计对python的module的import机制还是不够熟悉,

so need to clear Python的`import`机制和`module`这个概念。

---

# Keyword:

`Module`、``

---

# What

## Module是什么及其作用?

> module, **the highest-level program organization unit**, which packages program code and data for reuse

- 模块是什么？
    - 模块是最高级的程序组织单元
- 作用是？
    - 它将代码和数据封装起来以便重用。

---

## import Module的相关语法？

- `import`
    - let a importer fetch a module **as a whole**
- `from`
    - allow importer to fetch **particular** names from a module 
- `imp.reload`
    - Provides a way to **reload** a module’s code **without stopping Python**

Q1: 他们的区别是什么?  使用的场景不同吧~性能也不同

---

## Module的功能角色?

从抽象的角度看,`Module`有三个功能角色

- `Code reuse` 
    - 你可以按照需要任意次数地重载和重新运行模块
    - modules are a place to define names, known as `attributes`, which may be referenced by multiple external clients
- `System namespace`
    - Modules are `the highest-level program organization unit` in Python
    - In fact, everything “lives”in a module
    - Modules seal up names into self-contained packages, which helps avoid name clashes 避免变量名的冲突
    - modules are natural tools for grouping system components.  
- `Implementing shared services or data`
    - if you need to provide a `global object` that’s used by more than one function or file, you can code it in a module that can then be imported by many clients.
    - > 比如数据库配置文件等

---

# Why

## 为什么需要Module?



## 为什么使用Module?

> `modules` provide an easy way to organize components into a system by serving as `self-contained packages of variables` known as `namespaces`. 
> 
> All the names defined at the top level of a module file become attributes of the imported module object


---

# How

## 如何从Module到一个project/program?

![](https://ws4.sinaimg.cn/large/006tNbRwgy1fwlnhjndutj30tk0h4gno.jpg)

The program is structured as **one main, top-level file**, along with zero or more supplemental files known as modules in Python.

### `top-level file`:
- contains the **main flow of control** of your program 包含整体的执行流程
- this is the file you run to launch your application 直接运行

Top-level files use tools defined in module files, and modules use tools defined in other modules

### `other module`:

- Module files generally don’t do anything when run directly一般不直接运行
- they define tools intended for use in other files 类似函数库

### `import`->`access`

也就是说，B模块是如何使用C模块当中的东西的？

B file import C module to **gain access** to the tools it defines.  通过导入来获取权限

### 示例：

```
def spam(text):
    print(text, 'spam')

import b 
b.spam('gumby')
```

### `object.attribute`

You’ll see the `object.attribute` notation used throughout Python scripts—most objects have useful attributes that are fetched with the “.” `operator`. 

Some are callable things like `functions`, and others are `simple data values` that give object properties (e.g., a person’s name)


---

# When&Where&Who

## 什么时候用import或者from?

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

---








