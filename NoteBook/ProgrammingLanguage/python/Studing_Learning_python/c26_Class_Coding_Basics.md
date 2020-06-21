
- [preface:](#preface)
- [1.类产生多个实例对象:](#1%E7%B1%BB%E4%BA%A7%E7%94%9F%E5%A4%9A%E4%B8%AA%E5%AE%9E%E4%BE%8B%E5%AF%B9%E8%B1%A1)
  - [类对象提供默认行为:](#%E7%B1%BB%E5%AF%B9%E8%B1%A1%E6%8F%90%E4%BE%9B%E9%BB%98%E8%AE%A4%E8%A1%8C%E4%B8%BA)
  - [实例对象是具体的元素](#%E5%AE%9E%E4%BE%8B%E5%AF%B9%E8%B1%A1%E6%98%AF%E5%85%B7%E4%BD%93%E7%9A%84%E5%85%83%E7%B4%A0)
- [2.类通过继承进行定制:](#2%E7%B1%BB%E9%80%9A%E8%BF%87%E7%BB%A7%E6%89%BF%E8%BF%9B%E8%A1%8C%E5%AE%9A%E5%88%B6)
  - [类是模块内的属性](#%E7%B1%BB%E6%98%AF%E6%A8%A1%E5%9D%97%E5%86%85%E7%9A%84%E5%B1%9E%E6%80%A7)
- [3.类可以捕获python运算符](#3%E7%B1%BB%E5%8F%AF%E4%BB%A5%E6%8D%95%E8%8E%B7python%E8%BF%90%E7%AE%97%E7%AC%A6)
  - [为什么要使用运算符重载](#%E4%B8%BA%E4%BB%80%E4%B9%88%E8%A6%81%E4%BD%BF%E7%94%A8%E8%BF%90%E7%AE%97%E7%AC%A6%E9%87%8D%E8%BD%BD)
- [世界上最简单的python类](#%E4%B8%96%E7%95%8C%E4%B8%8A%E6%9C%80%E7%AE%80%E5%8D%95%E7%9A%84python%E7%B1%BB)
- [本章习题:](#%E6%9C%AC%E7%AB%A0%E4%B9%A0%E9%A2%98)
- [习题解答:](#%E4%B9%A0%E9%A2%98%E8%A7%A3%E7%AD%94)


# preface:

从最底层来看,类跟模块很像,几乎就是命名空间.

但和模块有3个不同之处,类支持3种特性

1. 产生多个对象 (generating multiple objects)
2. 命名空间继承 (namespace inheritance)
3. 运算符重载 (operator overloading)

---

# 1.类产生多个实例对象:

OOP模型中的两种对象:

1. 类 class

    - 类提供默认行为
    - 是实例对象的工厂
    - 生命周期: 语句声明产生了对象

2. 实例 instance

    - 实例是程序处理时的真正对象
    - 生命周期: 调用时才产生实例

他们各自都有独立的命名空间,但是继承会创建该实例的类中的变量名。

## 类对象提供默认行为:

执行class语句，就会得到类对象。

类主要特点:

1. class语句创建类对象并将其赋值给变量名
2. class语句内的赋值语句会创建类的属性
3. 类属性提供对象的状态和行为

    类对象的属性记录状态信息和行为,可由这个类所创建的所有实例共享

## 实例对象是具体的元素

当调用类对象时，可以得到实例对象。

实例主要特点:

1. 像函数那样调用类对象会创建新的实例对象
2. 每个实例对象继承类的属性并获得自己的命名空间
3. 方法内的`self`在运算时会产生每个实例自己的属性


例子:

```python
class FirstClass:
    def setdata(self, value):
        self.data = value
    def display(self):
        print(self.data)
```

```python
x = FirstClass()
y = FirstClass()
```

此时有3个连接的命名空间:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzebybfyxgj30n20amjsv.jpg)

---

# 2.类通过继承进行定制:

类通过继承,在层次较低的地方覆盖现有的属性，让行为特定化。

实际上,层次越低的部分,就会越特定。

这点和模块不同,模块的属性存在于一个单一的命名空间内(这个命名空间不接受定制化)

实例从类中继承，而类继承于超类`superclass`

属性继承机制的要点:

1. 超类列在类开头的括号中
2. 类从超类中继承属性
3. 实例会从所有可读取类中继承属性
4. 每个`object.attribute`都会开启新的独立搜索
5. 修改的逻辑是通过创建子类,而不是修改超类

属性继承搜索的目的是: 类支持程序的分解和定制。

这样可以把程序的冗余度降到最低(减少维护的成本),把操作分解为单一、共享的实现。


例子:

```python
class FirstClass:
    def setdata(self, value):
        self.data = value
    def display(self):
        print(self.data)
```

```python
class SecondClass(FirstClass):
    def display(self):
    print('Current value = "%s" ' % self.data)
```

在树中较低处发生的重新定义、取代属性的动作称为重载`overloading`

```python
z = SecondClass()
z.setdata(42)
z.display() # Current value = "42"
```

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79ly1fzeiqr73l1j314q0he44v.jpg)

我们不是修改FirstClass,而是对它进行了定制。

像这样在外部组件内进行修改,类所支持的拓展和重用通常比函数或模块更好。

---

## 类是模块内的属性

- 类名称总是存在于模块中。
- 更通用的情况是,每个模块可以任意混合任意数量的变量、函数以及类,而模块内的所有变量名的行为都相同。
- 虽然类和模块都是附加属性的命名空间,但是它们是非常不同的源代码结构: **模块反应了整个文件,而类只是文件内的语句.**

---

# 3.类可以捕获python运算符

类和模块的第三个主要的差别: 运算符重载 `operator overloading`.

简而言之,运算符重载就是让用类写成的对象,可捕获并响应用在内置类型上的运算。

因为运算符重载,让我们自己的对象和python的对象模型更紧密地结合起来:对象行为更像内置对象.


运算符重载的要点:

1. 以双下划线命名的方法`(__X__)`是特殊钩子 special hooks.

    python替每种运算和特殊命名的方法之间,定义了固定不变的映射关系.

2. 当实例出现在内置运算时,这类方法会自动调用.
3. 类可覆盖多数内置类型运算
4. 没有默认运算符重载方法

    如果类中没有`__add__`,使用 `+` 表达式就会报错.
5. 运算符可让类与python的对象模型相集成.

---

## 为什么要使用运算符重载

是否用`Operator Overloading`取决于你有多想让对象的用法和外观看起来更像内置类型.

一般,只有在实现本质为数学的对象时,才会用到许多Operator Overloading方法.

对于简单的类来说,可能根本用不到Operator Overloading,应该用明确的方法调用来实现对象的行为.

---

# 世界上最简单的python类

类产生的基本的继承模型非常简单,也就是在连接的对象树中搜索属性.


```python
class rec():
    pass
```

建立这个类后,可以通过赋值给这个类增加属性

```python
rec.name = 'Bob'
rec.age = 40
```

类本身也是对象,也只是独立完备的命名空间,只要有类的引用值,就可以设定和修改其属性.

```python
print(rec.name) # Bob
```

```python
x = rec()
y = rec()
```

这些实例最初是空的命名空间对象,不过从类对象中取得`name`属性.

```python
x.name = 'Sue'
rec.name, x.name, y.name # ('Bob', 'Sue', 'Bob')
```

属性赋值只会影响属性赋值所在的对象.在这里,x得到自己的`name`, 但y依然继承附加在它的类上的`name`.

事实上,命名空间对象的属性通常都是以字典的形式实现的,而类继承树只是连接至其他字典的字典而已.

`__dict__`属性是针对大多数基于类的对象的命名空间字典.

```python
print rec.__dict__.keys() # ['age', '__module__', '__doc__', 'name']

print x.__dict__.keys() # ['name']

print y.__dict__.keys() # []
```

- rec类的字典显示出了我们进行赋值的`name`和`age`属性
- x有自己的`name`
- y依然是空的

不过每个实例都连接至其类以便于继承.

可以通过`__class__`或者`__base__`进行查看

```python
print (rec.__bases__) # (<class 'object'>,) superclass
print (x.__class__) # <class '__main__.rec'>
print (y.__class__) # <class '__main__.rec'>
```

这两个属性是python在内存中类树常量的表示方式.

重点是:

python的类模型相当动态,类和实例只是命名空间对象`namespace objects`,属性是通过赋值语句动态建立的.

**只要能引用到树中任何一个对象,就可以调用.**

即使是方法,也可以完全独立地在任意类对象的外部创建.

例子:

```python
def upperName(self):
    return self.name.upper()
```

这个函数与类完全没有任何关系,只要我们传进一个带有name属性的对象,就能调用.

```
print (upperName(x)) # SUE
```

如果我们把这个简单函数赋值成类的属性,就可以由任何实例调用.

```python
rec.method = upperName

print(x.method()) # SUE
print(y.method()) # BOB
print(rec.method()) # SUE
```

实际上,这正是`self`参数必须在python方法中明确列出的原因之一：因为方法可以作为一个简单函数,独立与类之外.

因此,必须让隐含的实例参数明确化,否则python无法猜测简单函数是否最终会变成类的方法.

不过,让`self`参数明确化的主要原因是为了让变量名的意义更为明确:**没有通过self而引用的变量名是简单变量,而通过self引用的变量名显然是实例的属性.**

---

# 本章习题:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzelipojhej30qc0imjwj.jpg)

# 习题解答:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzeljifotsj30u011d1kx.jpg)


