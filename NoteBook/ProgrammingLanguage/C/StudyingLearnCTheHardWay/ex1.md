# Exercise 1. Dust Off That Compiler

```
#include <stdio.h>

/* comment*/
int main(int argc, char *argv[])
{
    int distance = 100;

    // this is also a comment
    printf("You are %d miles away.\n", distance);
    
    return 0;
}
```

`#include <stdio.h>`

- import the contents of one file into this source file
- using .h extensions for header files

`/* comment */`

- 多行注释

`// this is also a comment`

- 单行注释

`main()`

- 运行该程序时的入口

`{}`

- building block 的 frame 

`int distance = 100;`

- A variable declaration and assignment at the same time
- statement的结束符: `;` 

`printf("You are %d miles away.\n", distance);`

- function calls work with the syntax `name(arg1, arg2)`

`return 0;`

- A return from the main function that gives the operating system (OS) your exit value

