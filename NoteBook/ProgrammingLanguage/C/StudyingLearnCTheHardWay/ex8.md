# Exercise 8. If, Else-If, Else

In C, there really isn’t a `Boolean type`. 

Instead, any integer that’s 0 is false or otherwise it’s true.

the expression `argc > 1` actually resulted in 1 or 0, not an explicit True or False like in Python. 

This is another example of C being closer to how a computer works, because to a computer, `truth values are just integers`

```
#include <stdio.h>

int main(int argc, char *argv[])
{
    int i = 0;

    if(argc == 1) {
        printf("You only have one argument. You suck.\n");
    } else if(argc > 1 && argc < 4) {
        printf("Here's your arguments:\n");

        for(i = 0; i < argc; i++) {
            printf("%s ", argv[i]);
        }
        printf("\n");
    } else {
        printf("You have too many arguments. You suck.\n");
    }

    return 0;
}
```

## if-statement的格式:

```
if(TEST) {     
    CODE; 
} else if(TEST) {
    CODE; 
} else {
     CODE;
```

> 这个format也算从一个abstract的视角去看待

C语言的一些特性:

- TEST表达式值为0时为`false`，其它情况为`true`。
- 你需要在TEST周围写上圆括号，其它语言可能不用。

---

## How to Break It:

没有else的话,容易忽略掉一些边界情况(won’t catch the edge case)

> 在写if-else结构的时候,如果没写else,容易出现一些在编写的时候没有想到的边界情况。

---

## Extra Credit

> 第一个判断所输出的话真的正确吗？由于你的“第一个参数”不是用户输入的第一个参数，把它改正。

运行`./main one`

argv的输出:`2`

argv[0]:`./main` 

argv[1]:`one`

从命令行输入的第一个参数是`./main`,而并不是用户输入的变量 `one`