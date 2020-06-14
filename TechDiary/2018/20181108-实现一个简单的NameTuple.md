# 做题记录:


# 题目

## 介绍：

tuple 是 Python 的一种不可变数据类型，用于列表数据一旦初始化就不会再修改的场景。

tuple 只能通过**位置索引**去访问里面的元素，但有时候我们需要给每个元素取个别名，以便通过别名去获取对应的元素。

本次挑战就是需要大家自己来实现一个简单的命名 Tuple。

## 目标：

功能点:

- 模块内实现一个 NamedTuple 类，其构造函数接受 `iterable` 和 `fields` 两个参数，分别用于传递数据及其对应的别名
- `NamedTuple` 需要支持通过**位置索引**和**别名属性**两种方式去获取数据
- `NamedTuple` `repr` 输出格式类似于”NamedTuple(x=1, y=2)“，其中 x、y 是别名，1、2 是数据。
- 不能使用 Python 标准库里的实现，代码里不能出现 `namedtuple` 相关字样

## 答案验证:

```
import unittest

from namedtuple import NamedTuple


class TestNamedTuple(unittest.TestCase):
    def test_features(self):
        fields = ['x', 'y']
        values = [1, 2]
        nt = NamedTuple(values, fields)

        self.assertEqual(nt[0], 1)
        self.assertEqual(nt[1], 2)

        self.assertEqual(nt.x, 1)
        self.assertEqual(nt.y, 2)

        self.assertEqual(repr(nt), 'NamedTuple(x=1, y=2)')
```


----

# 具体实现：

## 修改`__new__`函数:

tuple是immutable Sequence.

这里需要override `__new__`  方法

```
class NamedTuple(tuple):
    def __new__(cls, x, y):
        return tuple.__new__(cls, (x, y))
```


## 修改`__getitem__ `函数:

位置索引

有时，我们希望可以使用 `obj[n]` 这种方式对实例对象进行取值,这时我们需要在类中实现 `__getitem__ `方法

```
def __getitem__(self, index):
    return self.values[index]
```

---

## 修改 `__getattr__`函数:

别名属性

当我们获取对象的某个属性，如果该属性不存在，会抛出 `AttributeError` 异常.

为了不让它抛出异常,只需在类的定义中加入 `__getattr__` 方法。

```
def __getattr__(self, attr):
    '''
    找到attr对应的位置
    '''
    return self.values[self.fields.index(attr)]
```

## 修改`__repr__`函数:

```
def __repr__(self):
    tem_list = []
    for index in range(len(self.values)):
        tem_str = "{0}={1}".format(self.fields[index], self.values[index])
        tem_list.append(tem_str)

    last_str =  ", ".join(tem_list)
    return "NamedTuple({0})".format(last_str)
```

---

# 知识点：

- `Special Methods`
    - `__new__`
    - `__init__`
    - `__getitem__`
    - `__getattr__`
    - `__repr__`

---

## 知识结构:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fx4dtxe0h0j31jc0xc7hi.jpg)


---

## `Special Methods`

### `Special Methods`是什么？

`A class can implement certain operations that are invoked by special syntax (such as arithmetic operations or subscripting and slicing) by defining methods with special names`

类可以通过定义有着特殊名字的方法，来实现特定的操作。

`This is Python’s approach to operator overloading, allowing classes to define their own behavior with respect to language operators`

通过这些特殊方法去重载一些类的操作，从而达到`your object`定义自己的行为的目的。

> 这里就是通过override这几个Special Methods 来实现我们要的特定操作

---

## instance creation and destruction 对象的生命周期:

- **creation**
    - `object.__new__(cls[, ...])` to create object
    - `object.__init__(self[, ...])` to customize object
- **destruction**
    - `object.__del__(self)` to destroy object

---

## `__new__`:

### 什么是`__new__`?

- 描述：
    -  `__new__()` is a static method (special-cased so you need not declare it as such)
    - `__new__()` 是在一个对象实例化的时候所调用的第一个方法。


`__new__(cls[, ...])` 的函数定义:

- Input:
    - **first argument** : the class of which an instance was requested
    - **The remaining arguments** : those passed to the object constructor expression (the call to the class).
- Output:
    - `the new object instance (usually an instance of cls).` 


### `__new__`的作用:

- `create a new instance of class cls`

### 为什么要使用`__new__`?有什么应用场景?

最典型的应用场景就是:

- allow `subclasses of immutable types` (like int, str, or tuple) to customize instance creation 定制实例的创建
- overridden in custom metaclasses in order to customize class creation.

### 如何实现？

- using `super().__new__(cls[, ...])` with appropriate arguments 特定的参数
- modifying the newly-created instance as necessary before returning it.

例子：

```
class inch(float):
    "Convert from inch to meter"
    def __new__(cls, arg=0.0):
    return float.__new__(cls, arg*0.0254)

print inch(12)

0.3048

```

---

## `__init__`函数:

### 什么是`__init__`函数?

### `__init__`函数的调用顺序?

- **after** the instance has been created (by __new__())
- **before** it is returned to the caller. 


### `__init__`的作用


# refs:

- [Python 魔术方法指南](https://pycoders-weekly-chinese.readthedocs.io/en/latest/issue6/a-guide-to-pythons-magic-methods.html)
- [Overriding the __new__ method](https://www.python.org/download/releases/2.2/descrintro/#__new__)
- [Question about extending tuple](https://bytes.com/topic/python/answers/624365-question-about-extending-tuple)
