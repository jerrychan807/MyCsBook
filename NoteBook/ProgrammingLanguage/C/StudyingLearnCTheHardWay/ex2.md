# Exercise 2. Using Makefiles to Build

## make:

```
make ex1
```

实际运行的是`cc ex1.c -o ex1`

```
CFLAGS="-Wall" make ex1
```

`CFLAGS="-Wall"`

- pass modifiers to the `make` command
- tells the compiler `cc` to report all warnings

## Makefile:

Makefile文件的描述规则

```
TARGET... :PREREQUISITES...
    COMMAND
```


