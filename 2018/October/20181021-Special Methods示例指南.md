
# 1. Special Methods 示例指南

3.3. Special method names

<!-- TOC -->

- [1. Special Methods 示例指南](#1-special-methods-示例指南)
- [2. Basic customization](#2-basic-customization)
- [3. Customizing attribute access](#3-customizing-attribute-access)
    - [3.1. Customizing module attribute access](#31-customizing-module-attribute-access)
    - [3.2. Implementing Descriptors](#32-implementing-descriptors)
    - [3.3. Invoking Descriptors](#33-invoking-descriptors)
    - [3.4. `__slots__`](#34-__slots__)
        - [3.4.1. Notes on using `__slots__`](#341-notes-on-using-__slots__)
- [4. Customizing class creation](#4-customizing-class-creation)
    - [4.1. Metaclasses](#41-metaclasses)
    - [4.2. Resolving MRO entries](#42-resolving-mro-entries)
    - [4.3. Determining the appropriate metaclass](#43-determining-the-appropriate-metaclass)
    - [4.4. Preparing the class namespace](#44-preparing-the-class-namespace)
    - [4.5. Executing the class body](#45-executing-the-class-body)
    - [4.6. Creating the class object](#46-creating-the-class-object)
    - [4.7. Metaclass example](#47-metaclass-example)
- [5. Customizing instance and subclass checks](#5-customizing-instance-and-subclass-checks)
- [6. Emulating generic types](#6-emulating-generic-types)
- [7. Emulating callable objects](#7-emulating-callable-objects)
- [8. Emulating container types](#8-emulating-container-types)
- [9. Emulating numeric types](#9-emulating-numeric-types)
- [10. With Statement Context Managers](#10-with-statement-context-managers)
- [11. Special method lookup](#11-special-method-lookup)

<!-- /TOC -->

# 2. Basic customization

`object.__new__(cls[, ...])`

`object.__init__(self[, ...])`

`object.__del__(self)`

`object.__repr__(self)`

- 通过内置函数调用 : `repr()`
- 作用 : 

`object.__str__(self)`

`object.__bytes__(self)`

`object.__format__(self, format_spec)¶`


`object.__lt__(self, other)`

`object.__le__(self, other)`

`object.__eq__(self, other)`

`object.__ne__(self, other)`

`object.__gt__(self, other)`

`object.__ge__(self, other)`



---

# 3. Customizing attribute access

## 3.1. Customizing module attribute access

## 3.2. Implementing Descriptors
## 3.3. Invoking Descriptors
## 3.4. `__slots__`

### 3.4.1. Notes on using `__slots__`

---

# 4. Customizing class creation

## 4.1. Metaclasses

## 4.2. Resolving MRO entries

## 4.3. Determining the appropriate metaclass

## 4.4. Preparing the class namespace

## 4.5. Executing the class body

## 4.6. Creating the class object

## 4.7. Metaclass example

---

# 5. Customizing instance and subclass checks

---

# 6. Emulating generic types

---

# 7. Emulating callable objects

---

# 8. Emulating container types

---

# 9. Emulating numeric types

---

# 10. With Statement Context Managers

---

# 11. Special method lookup