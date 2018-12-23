# Exercise 3. Formatted Printing

Many programming languages use the C way of formatting output.


---

```
#include <stdio.h>

int main()
{
    int age = 10;
    int height = 72;
    
    printf("I am %d years old.\n", age);
    printf("I am %d inches tail.\n", height);

    return 0;
}
```

`#include <stdio.h>`

- including another header file called `stdio.h`. This tells the compiler that you’re going to use the `standard Input/Output functions`
- 比如 `printf()`


`printf("I am %d years old.\n", age);`

- 格式字符串(format string)之后，你传入了变量，它们被printf“替换”进格式字符串中
- 结果就是你用`printf`处理了一些变量，并且它会构造出一个新的字符串，之后将它打印在终端上

`The format is a character string which contains three types of objects`

- 文本字符串 plain characters
  - simply copied to standard output 
- 转义序列 Escape sequences 
    - converted and copied to the standard output
  - 例子: `\n` or `\t` that let you print a newline or tab, respectively. 
- 格式化序列 `Format sequences`
  - the `%s` or `%d` that let you print a string or integer

---



如果你一直在自己尝试了解问题之前去问其它人，你永远都不会学到独立解决问题。

这会让你永远都不会在自己的技能上建立信心，并且总是依赖别人去完成你的工作。

打破你这一习惯的方法就是强迫你自己先试着自己回答问题，并且确认你的回答是正确的。你可以通过打破一些事情，用实验验证可能的答案，以及自己进行研究来完成它。


