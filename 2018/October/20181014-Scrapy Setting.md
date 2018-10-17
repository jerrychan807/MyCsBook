# Scrapy Setting

<!-- TOC -->

- [Scrapy Setting](#scrapy-setting)
- [Preface:](#preface)
- [Keyword : Scrapy Setting](#keyword--scrapy-setting)
    - [What](#what)
        - [什么是Scrapy Setting?](#什么是scrapy-setting)
        - [实现方式？](#实现方式)
        - [Setting的设定机制分哪些？](#setting的设定机制分哪些)
    - [Why](#why)
    - [为什么Scrapy Setting那么复杂？](#为什么scrapy-setting那么复杂)
        - [为什么要合理设置Setting?](#为什么要合理设置setting)
    - [How](#how)
        - [How to access settings?](#how-to-access-settings)
    - [When&Where&Who](#whenwherewho)
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

遇到了一些问题:

- download delay 是怎么算的?
- 多个spider会怎么使用配置,会使用同一份配置吗？

---

# Keyword : Scrapy Setting

## What

### 什么是Scrapy Setting?

简言之,就是Scrapy框架的配置模块

### 实现方式？
The infrastructure of the settings provides **a global namespace of key-value mappings** that the code can use to pull configuration values from. 
The settings can be populated through different mechanisms,

从不同的设定机制里,根据优先级，生成一个由许多键值对组成的**全局的命名空间**。


### Setting的设定机制分哪些？

Scrapy Setting由五部分构成，根据优先级可分类

- 1.Command line options (most precedence)
- 2.Settings per-spider
- 3.Project settings module
- 4.Default settings per-command
- 5.Default global settings (less precedence)

这里所谓的`precedence(优先级)`估计是指 如果在不同地方出现了同样的设置参数,precedence高的键值对值会覆盖掉低的。

> 这有点像变量的作用域,局部scope会覆盖global scope。

---

## Why

## 为什么Scrapy Setting那么复杂？

原因：The Scrapy settings allows you to **customize the behaviour of all Scrapy components**, including 

- the core
- extensions
- pipelines
- spiders 


Scrapy的组件就有很多，是一整个爬虫框架，为了能够**高度定制化**各组件的行为，所以这个settings就显得**很臃肿**。

这个框架可以包含很多个爬虫。如果你一个框架里只有一个爬虫的话，那么这个复杂的设定机制就显得有点“杀鸡焉用牛刀”了。
但是如果你有很多个爬虫，那么才能充分发挥出这个设定机制的优势。


> 一开始还以为每写一个新的爬虫就要开一个新的文件夹，其实不然，如果你要做一个新闻类的爬虫的话，完全可以全部放在一个文件夹里。每个不同的网站就是一个spider,然后可以分别定制settings就好了。

### 为什么要合理设置Setting?

这个可以从多个维度来回答。

发起请求方：

- 爬取的速度
- 爬取的量
- the size of proxy ip pools
- 服务器的性能
- 服务器的出口带宽

接受请求方：
- 服务器的性能
- 防护机制

要根据具体情况，综合多种因素来考虑。

各种值不是越大越好，最好能动态调整到趋于合理的设定值。

因为如果你在爬取大量的数据的时候，只有你正式开始爬取上千上万条之后，多次收集log之后，你才能分析并大概猜测到对方的反爬机制，对方的响应速度等。并依据此来动态调整自己的settings.

---

## How

### How to access settings?

两种方式：

- attribute(module):

Settings can be accessed through the `scrapy.crawler.Crawler.settings` attribute of the Crawler that is passed to `from_crawler` method in extensions, middlewares and item pipelines:

```
class MyExtension(object):
    def __init__(self, log_is_enabled=False):
        if log_is_enabled:
            print("log is enabled!")

    @classmethod
    def from_crawler(cls, crawler):
        settings = crawler.settings
        return cls(settings.getbool('LOG_ENABLED'))
```

- dict(api):

The settings object can be used like a dict (e.g., `settings['LOG_ENABLED'])`, but it’s usually preferred to extract the setting in the format you need it to avoid type errors, using one of the methods provided by `the Settings API`.

---

## When&Where&Who


---

## History:


---

## ADEPT:

### Analogy 类比：这个概念像是什么已知的东西？
### Diagram 可视化：把这个概念画出来是什么样子？
### Example 实例：有什么身边的案例？
### Plain English 大白话讲解：如何向一个小孩讲解这个概念？
### Technical Definition：真正专业的定义和描述是什么样的？

The Scrapy settings allows you to customize the behaviour of all Scrapy components, including the core, extensions, pipelines and spiders themselves.

The infrastructure of the settings provides a global namespace of key-value mappings that the code can use to pull configuration values from. The settings can be populated through different mechanisms。

The settings are also the mechanism for selecting the currently active Scrapy project (in case you have many).

---

## Principle:

### 它的本质是什么？

**a global namespace of key-value mappings**


### 它的第一原则是什么？

### 它的知识结构是怎样的?


---

## Expansion


---

## Inspire


----

## Refs：

---








