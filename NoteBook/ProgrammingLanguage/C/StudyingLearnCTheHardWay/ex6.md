- [Exercise 6. Memorizing C Syntax](#exercise-6-memorizing-c-syntax)
  - [The Keywords:](#the-keywords)
  - [Syntax Structures:](#syntax-structures)
    - [if-statement：](#if-statement)
    - [switch-statement:](#switch-statement)
    - [while-loop:](#while-loop)
    - [do-while-loop:](#do-while-loop)
    - [for-loop：](#for-loop)
    - [enum:](#enum)
    - [goto:](#goto)
    - [function:](#function)
    - [typedef:](#typedef)
    - [struct:](#struct)
    - [union](#union)

---

# Exercise 6. Memorizing C Syntax

## The Keywords:

The keywords of a language are words that `augment the symbols` so that `the language reads well`

> 原来每个语言里的关键词的目的是 为了提高可读性 ???


The technical term for `processing the symbols and keywords` of a programming language is `lexical analysis`(词法分析). 

The word for one of these symbols or keywords is a `lexeme`


![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fyh3eqbcaqj30u01b1txq.jpg)

> `size of` 应该和python里的`len()`有关联

---

## Syntax Structures:

A syntax structure is a pattern of symbols that make up a C program code form.

### if-statement：

An if-statement is your `basic logic branching control` 逻辑分支控制。

```
if(TEST) {     
    CODE; 
} else if(TEST) {
    CODE; 
} else {
     CODE;
```
> 如果逻辑没理清,那么写if语句会有疏忽吧

``

---

### switch-statement:

A switch-statement is like an if-statement but works on `simple integer constants`.

```
switch (OPERAND) {
    case CONSTANT:
        CODE;
        break;    
    default:
        CODE; 
}
```

---

### while-loop:

A while-loop is your `most basic loop`

```
while(TEST) {
    CODE; 
}
```

use `continue` to `cause it to loop`

```
while(TEST) {
    if(OTHER_TEST) { 
        continue;     
    }    
    CODE; 
}
```

use `break` to `exit a loop`

```
while(TEST) {
    if(OTHER_TEST) { 
        break;    
    }     
        CODE; 
}
```

### do-while-loop:

do-while-loop is an `inverted version of a while-loop` that `runs the code then tests to see if it should run again`

> do-while-loop和`while-loop`是相反的版本,先跑一次代码再测试是否循环.

do {     
    CODE; 
} while(TEST);

---

### for-loop：

The for-loop does a `controlled counted loop` through a (hopefully) fixed number of iterations using a counter.

> for-loop 是有控制的循环.


```
for(INIT; TEST; POST) { 
    CODE; 
}
```

---

### enum:

An enum creates `a set of integer constants`

```
enum { CONST1, CONST2, CONST3 } NAME;
```

---

### goto:

A goto will jump to a label, and is only used in a few useful situations like `error detection and exiting`

```
if(ERROR_TEST) {     
    goto fail; 
} 

fail:
    CODE;
```

> 最原始的抛出异常?

---

### function:

A function is defined this way:

```
TYPE NAME(ARG1, ARG2, ..) { 
    CODE;
    return VALUE; 
}
```

```
int name(arg1, arg2) {
    CODE;
    return 0; 
}
```

> 这种函数定义跟goland就很相似了,不过goland在函数定义的时候还多了一个返回值的类型.

---

### typedef:

A typedef `defines a new type`

```
typedef DEFINITION IDENTIFIER;
```

实例:

```
typedef unsigned char byte;
```

- DEFINITION is unsigned char
- IDENTIFIER is byte

---

### struct:


A struct is `a packaging of many base data types into a single concept`, which are used heavily in C: 

```
struct NAME {     
    ELEMENTS; 
} [VARIABLE_NAME];
```

The `[VARIABLE_NAME]` is optional, and I prefer not to use it except in a few small cases

> 是C语言提供的一种abstract的方式,比base data的抽象程度再高一些.

常和`typedef` 共用:

```
typedef struct [STRUCT_NAME] {          ELEMENTS; 
} IDENTIFIER;
```

---

### union

union creates something like a struct, but `the elements will overlap in memory`

```
union NAME {        
    ELEMENTS; 
}[VARIABLE_NAME];
```

----

Programming is a great way to learn this because it’s so easy to break down into small parts and focus on what’s lacking. 

Take this as an opportunity to build your confidence in tackling large tasks in small pieces

> - chunked
> - focus的转移

To do that you need to apply these facts in different situations until you know how to use them.

> 在各种实际的应用场景中,才能更深刻的理解.毕竟很多概念都是从实例中抽象出来的。

