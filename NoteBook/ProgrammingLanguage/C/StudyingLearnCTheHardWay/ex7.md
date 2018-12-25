# Exercise 7. Variables and Types:





```
#include <stdio.h>

int main(int argc, char * argv[])
{
    int distance = 100;
    float power = 2.345f;
    double super_power = 56789.4532;
    char initial = 'A';
    char firstname[] = "Zed";
    char lastname[] = "Shaw";
    
    printf("You are %d miles away.\n", distance);
    printf("You are %f levels of power.\n", power);
    printf("You are %f awesome super powers.\n", super_power);
    printf("I have an initial %c.\n", initial);
    printf("I have a first name %s.\n", firstname);
    printf("I have a last name %s.\n", lastname);
    printf("My whole name is %s %c. %s .\n",
           firstname, initial, lastname);
    
    int bugs = 100;
    double bug_rate = 1.2;
    
    printf("You have %d bugs at the imaginary rate of %f.\n",
           bugs, bug_rate);
    
    long universe_of_defects = 1L * 1024L * 1024L * 1024L;
    printf("The entire universe has %ld bugs.\n", universe_of_defects);
    
    double expected_buga = bugs * bug_rate;
    printf("You are expected to have %f bugs.\n", expected_buga);
    
    double part_of_universe = expected_buga / universe_of_defects;
    printf("That is only a %e portion of the universe.\n",
           part_of_universe);
    
    // something weird
    char nul_byte = '\0';
    int care_percentage = bugs * nul_byte;
    printf("Which means you should care %d%%.\n",care_percentage);
    
    return 0;
}

```

## Break it:

调试:

```
printf("print: %c", nul_byte);
printf("print: %s", nul_byte);
```

输出是：

```
print: 
print: (null)
```

gdb下的`nul_byte = 0 '\000'`


> The printf function is a variadic function, meaning that it has variable number of arguments. Arguments are pushed on the stack before the function (printf) is called. In order for the function printf to use the stack, it needs to know information about what is in the stack, the format string is used for that purpose.
> 
> e.g.
> ```
> printf( "%c", ch );    tells the function the argument 'ch' 
>                        is to be interpreted as a character and sizeof(char)
> ```
> 
> whereas
> 
> ```
> printf( "%s", s );   tells the function the argument 's' is a pointer 
>                      to a null terminated string sizeof(char*)
> ```
> 


- [Why does C's printf format string have both %c and %s?](https://stackoverflow.com/questions/10846024/why-does-cs-printf-format-string-have-both-c-and-s)

> ```
> %s prints out chars until it reaches a 0 (or '\0', same thing).
> ```