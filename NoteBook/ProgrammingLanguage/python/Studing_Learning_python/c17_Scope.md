
- [python 作用域基础:](#python-%E4%BD%9C%E7%94%A8%E5%9F%9F%E5%9F%BA%E7%A1%80)
  - [作用域的规则 Scope Rules:](#%E4%BD%9C%E7%94%A8%E5%9F%9F%E7%9A%84%E8%A7%84%E5%88%99-scope-rules)
  - [变量名解析Name Resolution: LEGB原则](#%E5%8F%98%E9%87%8F%E5%90%8D%E8%A7%A3%E6%9E%90name-resolution-legb%E5%8E%9F%E5%88%99)
  - [作用域例子:](#%E4%BD%9C%E7%94%A8%E5%9F%9F%E4%BE%8B%E5%AD%90)
  - [内置作用域:](#%E5%86%85%E7%BD%AE%E4%BD%9C%E7%94%A8%E5%9F%9F)
- [global语句](#global%E8%AF%AD%E5%8F%A5)
  - [最小化全局变量](#%E6%9C%80%E5%B0%8F%E5%8C%96%E5%85%A8%E5%B1%80%E5%8F%98%E9%87%8F)

---

# python 作用域基础:

当你在一个程序中使用变量名时,python创建、改变或查找变量名都是在所谓的**命名空间**(一个保存变量名的地方)中进行的。

作用域`scope`指的就是命名空间`namespace`,也就是说,**在代码中变量名被赋值的位置决定了这个变量名能够被访问的范围.**

python的变量名在第一次赋值时已经创建,并且必须经过赋值后才能使用.

由于变量名最初没有声明,所以python将一个变量名被赋值的地点关联(绑定给)一个特定的命名空间.

换句话说,在代码中给一个变量赋值的地方决定了这个变量将存在于哪个命名空间,也就是它可见的范围.

除了packaging code之外,函数还提供了一个额外的命名空间层: 在默认的情况下,一个函数的所有变量名都是与函数的命名空间相关联的.

这意味着:

- 一个在`def`内定义的变量名只能够被def内的代码使用.不能被函数的外部引用
- `def`之中的变量名与`def`之外的变量名并不冲突.

变量可以在3个不同的地方分配,分别对应3种不同的作用域:

1. 一个变量在`def`内赋值,变量被定位在这个函数之内
2. 一个变量在一个嵌套的`def`中赋值,对于嵌套的函数来说,它是非本地的`nonlocal`.
3. 如果在def之外赋值,它对于整个文件来说是全局的.`global to the entire file`

我们称之为**语义作用域`lexical scoping`**,因为变量的作用域完全是由变量在程序文件中源代码的位置而决定的,而不是由函数调用决定.

例子:

```python
x = 99

def func():
    x = 88
```

尽管两个变量名都是x,但是它们作用域可以把它们区别开来.

实际上,函数的作用域有助于防止程序之中变量名的冲突,使得函数成为更独立的程序单元.

---

## 作用域的规则 Scope Rules:

- 函数定义了本地作用域 `local scope`
- 模块定义了全局作用域 `global scope`

这两个作用域有如下的关系:

1. 封闭的模块是一个全局作用域

    - 每一个模块都是一个全局作用域
    - 对于模块外部来说,全局变量变成了一个模块的属性
    - 对于模块内部来说,全局变量可通过变量使用

2. 全局作用域的作用范围仅限于单个文件

    - 全局指的是在一个文件顶部的变量名对于这个文件的内部的代码来说是全局的.

3. 每次的对函数的调用都创建了一个新的本地作用域

    - 每次调用函数,都创建了一个新的本地作用域
    - 每一个`def`语句(以及`lambda`表达式)都定义了一个新的本地作用域

4. 赋值的变量名除非声明为全局或非本地的,否则均为本地的

    - 在默认情况下,所有函数定义内部的变量名都是位于本地作用域内的(与函数调用相关的).

在函数内部的任何类型的赋值都会把该变量名认为是本地的。包括:`=`语句。`import`中的模块名称、`def`中的函数名称,函数参数名等。

---

## 变量名解析Name Resolution: LEGB原则 

对于`def`语句的3条简单的原则:

1. 变量名引用分为3个作用域进行查找:本地`local`、函数内、全局`global`、内置`built-in`

2. 在默认情况下,变量名赋值会创建或者修改本地变量.

3. 全局和非本地声明`global and nonlocal declarations`将赋值的变量名映射到模块文件内部的作用域

换句话说,所有在函数`def`语句内的赋值的变量名默认为本地变量.函数能够在函数内部以及全局作用域直接使用变量名,但是必须声明为非本地变量和全局变量才能去改变它.

python的 变量名解析机制`name-resolution scheme`称为 `LEGB`法则:

1. 当在函数中使用未认证的变量名时,python按顺序搜索4个作用域:[`本地作用域(L)`, `内部中def或lambda的本地作用域(E)`, `全局作用域(G)`, `内置作用域(B)`], 找到就停止.如果变量名在这次搜索没有找到,python会报错.
2. 当在函数内给一个变量名赋值时,python总是创建或改变本地作用域的变量名,除非变量名在函数中提前声明为全局变量
3. 当在函数外给一个变量名赋值时(也就是,在一个模块文件的顶层),变量的本地作用域就是全局作用域

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzfhmt8oovj312m0ncn7p.jpg)

---

## 作用域例子:

```python
# Global scope
x = 99

def func(y): # y and z assigned in function: locals
    # Local scope
    z = x + y   # x is a global
    return z

print func(1) # 100
```


通过使用python的作用域法则,对例子的变量名进行如下定义

Global names: `x`, `func`
    
- 因为`x`是在模块文件顶层赋值的,所以它是全局变量,它能够在函数内部进行引用而不需要特意声明为全局变量.
- 同上,`func`也是全局变量,`def`语句将一个函数对象赋值给了变量名`func`

Local names: `y`, `z`

- 对于这个函数来说,`y`和`z`是本地变量(只在函数运行时存在)
- 因为它们都是通过在函数定义内部进行赋值的: `z` 通过`=`, `y`通过函数传递

这种变量名隔离机制的意义在于:

1. 本地变量只作为临时的变量名,只有在函数运行时才需要它们
2. 本地变量不会与模块命名空间(或者其他函数内的变量名)内的变量名产生冲突
3. 使得函数更容易理解: 大多数需要的变量只会在函数内部出现
4. 使得程序容易调试: 本地变量名不会改变程序中的其他变量

---

## 内置作用域:

内置作用域仅仅是一个名为`builtin`的内置模块.

需要`import builtin`之后才能使用内置作用域,因为变量名`builtin`本身并没有预先内置.

用`dir`看看预定义了哪些变量名

```python
>>> import builtins
>>> dir(builtins)
['ArithmeticError', 'AssertionError', 'AttributeError', 'BaseException', 'BlockingIOError', 'BrokenPipeError', 'BufferError', 'BytesWarning', 'ChildProcessError', 'ConnectionAbortedError', 'ConnectionError', 'ConnectionRefusedError', 'ConnectionResetError', 'DeprecationWarning', 'EOFError', 'Ellipsis', 'EnvironmentError', 'Exception', 'False', 'FileExistsError', 'FileNotFoundError', 'FloatingPointError', 'FutureWarning', 'GeneratorExit', 'IOError', 'ImportError', 'ImportWarning', 'IndentationError', 'IndexError', 'InterruptedError', 'IsADirectoryError', 'KeyError', 'KeyboardInterrupt', 'LookupError', 'MemoryError', 'ModuleNotFoundError', 'NameError', 'None', 'NotADirectoryError', 'NotImplemented', 'NotImplementedError', 'OSError', 'OverflowError', 'PendingDeprecationWarning', 'PermissionError', 'ProcessLookupError', 'RecursionError', 'ReferenceError', 'ResourceWarning', 'RuntimeError', 'RuntimeWarning', 'StopAsyncIteration', 'StopIteration', 'SyntaxError', 'SyntaxWarning', 'SystemError', 'SystemExit', 'TabError', 'TimeoutError', 'True', 'TypeError', 'UnboundLocalError', 'UnicodeDecodeError', 'UnicodeEncodeError', 'UnicodeError', 'UnicodeTranslateError', 'UnicodeWarning', 'UserWarning', 'ValueError', 'Warning', 'ZeroDivisionError', '__build_class__', '__debug__', '__doc__', '__import__', '__loader__', '__name__', '__package__', '__spec__', 'abs', 'all', 'any', 'ascii', 'bin', 'bool', 'bytearray', 'bytes', 'callable', 'chr', 'classmethod', 'compile', 'complex', 'copyright', 'credits', 'delattr', 'dict', 'dir', 'divmod', 'enumerate', 'eval', 'exec', 'exit', 'filter', 'float', 'format', 'frozenset', 'getattr', 'globals', 'hasattr', 'hash', 'help', 'hex', 'id', 'input', 'int', 'isinstance', 'issubclass', 'iter', 'len', 'license', 'list', 'locals', 'map', 'max', 'memoryview', 'min', 'next', 'object', 'oct', 'open', 'ord', 'pow', 'print', 'property', 'quit', 'range', 'repr', 'reversed', 'round', 'set', 'setattr', 'slice', 'sorted', 'staticmethod', 'str', 'sum', 'super', 'tuple', 'type', 'vars', 'zip']
```

概括地讲,前一半是内置的异常,后一半是内置函数.

---

# global语句

global不是一个类型或大小的声明,它是一个命名空间的声明.

声明一个或多个全局变量,这些变量存在于整个模块内部作用域.

全局变量的要点:

1. 全局变量是位于模块文件内部的顶层的变量名
2. 全局变量如果是在函数内被赋值的话,必须经过声明
3. 全局变量名在函数的内部不经过声明也可以被引用

## 最小化全局变量

- 不使用面向对象的编程方法以及类的话,全局变量也许就是python中最直接保存状态信息的方法.因为本地变量在函数返回时将会消失
- 尽量在一个单一个模块文件去定义所有的全局变量.
- 在`def`内部赋值的变量名默认为本地变量,通常是最好的约定.如果在多个函数同时操作一个全局变量,可能会引发一些问题.
- 在python中使用多线程进行并行计算程序实际上是要依靠全局变量的.因为全局变量在并行线程中在不同函数之间成为了共享内存`shared memory`，所以扮演了通信工具的角色.

---

未完....