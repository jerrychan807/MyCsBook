# Scrapy Pipeline

<!-- TOC -->

- [Scrapy Pipeline](#scrapy-pipeline)
- [Preface:](#preface)
- [Keyword : Scrapy Pipeline](#keyword--scrapy-pipeline)
    - [What](#what)
        - [Scrapy 中的Pipeline是什么？](#scrapy-中的pipeline是什么)
        - [pipeline适合什么处理？](#pipeline适合什么处理)
    - [Why](#why)
        - [为什么要用pipeline？](#为什么要用pipeline)
    - [How](#how)
        - [如何使用pipelline？](#如何使用pipelline)
    - [如何在pipeline中对请求进行去重?](#如何在pipeline中对请求进行去重)
        - [原本错误的做法:](#原本错误的做法)
        - [我的做法:](#我的做法)
    - [如何在函数中指定pipeline？](#如何在函数中指定pipeline)
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

需求:需要在过滤掉一些已经Send过的历史请求


---

# Keyword : Scrapy Pipeline

## What

### Scrapy 中的Pipeline是什么？



### pipeline适合什么处理？

---

## Why

### 为什么要用pipeline？

即好处是什么？

---

## How

### 如何使用pipelline？


## 如何在pipeline中对请求进行去重?

### 原本错误的做法:

```
class SubjectIdPipeline(object):
    '''过滤掉已请求过的图书元链接'''
    @check_spider_pipeline
    def process_item(self, item, spider):
        if isinstance(item, DoubanSubjectItem):
            douban_books_db = douban_books_model.DoubanBook()
            if not douban_books_db.checkSubjectIdExists(item['subject_id']):  # 如果没爬过该图书元
                return item
            else:
                raise DropItem("duplicate request subject_id: %s" % item['subject_id'])

```

原本打算是在 `yield request`之前 `yield item`,然后这个item会经过`pipeline`中的`SubjectIdPipeline`

`SubjectIdPipeline`会去检测这个`item`在数据库是否存在，如果存在就Drop掉。

这样确实能够Drop掉该`item` 但是并不能减少`Request`的数量，所以是没有达到预期的目的的

为什么会出现这样的错误?

是我对Scrapy这个框架的执行流程和pipeline本身的应用范围还了解得不够

![执行流程图](https://ws4.sinaimg.cn/large/006tNbRwgy1fw9un40z7uj31kw0yz49b.jpg)

从流程图可以看到

### 我的做法:

```
if not douban_books_db.checkSubjectIdExists(subject_id):  # 如果没爬过该图书元
    yield scrapy.Request(url=book_url, callback=self.parse_book,meta={"subject_id": subject_id})
```

最直接的方式，在spider的正文中进行查询，如果不存在再`yield Request`,这样实际上才能去重原有爬过的链接， 达到大大减少请求量的效果。

## 如何在函数中指定pipeline？


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

---

## Principle:

### 它的本质是什么？

### 它的第一原则是什么？

### 它的知识结构是怎样的?


---

## Expansion


---

## Inspire


----

## Refs：

- [scrapy 如何在一个 spider 中指定对应 pipeline 输出到多张表中](https://www.v2ex.com/t/372925)
- [Scrapy框架的使用之Item Pipeline的用法](https://juejin.im/post/5af95280f265da0ba17ca1ba)
- [《Learning Scrapy》（中文版）第5章 快速构建爬虫](https://www.jianshu.com/p/9d1e00dc40e4)
- [scrapy学习笔记](https://blog.bombox.org/2016-09-11/scrapy-start/)


---








