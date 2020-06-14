- [1. Layering Principle](#1-layering-principle)
    - [1.1. 简述:](#11-%E7%AE%80%E8%BF%B0)
    - [1.2. definition of layering:](#12-definition-of-layering)
    - [1.3. examples of layering:](#13-examples-of-layering)
        - [1.3.1. postal service:](#131-postal-service)
        - [1.3.2. computer system:](#132-computer-system)
            - [1.3.2.1. C programming language:](#1321-c-programming-language)
        - [1.3.3. Internet:](#133-internet)
    - [1.4. Reasons for layering:](#14-reasons-for-layering)
        - [1.4.1. Modularity:](#141-modularity)
        - [1.4.2. Well defined service:](#142-well-defined-service)
        - [1.4.3. Reuse:](#143-reuse)
        - [1.4.4. Separation of concerns:](#144-separation-of-concerns)
        - [1.4.5. Continuous improvement](#145-continuous-improvement)
        - [1.4.6. peer to peer communications](#146-peer-to-peer-communications)

---

# 1. Layering Principle

## 1.1. 简述:
  
`Layering` is a very widely used principle and has been used in networking for decades, predating the Internet.

In fact, layering is a `design principle` used widely outside networking as well --- it is commonly used as a design principle in many many types of `computer system`.

## 1.2. definition of layering:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxurlur4x6j31oa0s6amr.jpg)

- Layering is the name we give to the organization of a system into a number of `separate functional components`, or layers.
- The layers are hierarchical and they `communicate sequentially`; i.e. each layer has an interface only to the layer directly above or below.
- Each layer provides `a well defined service to the layer above`, using the services provided by the layer(s) below and its own private processing.


## 1.3. examples of layering:

### 1.3.1. postal service:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxurpb7qm7j31bf0u04m9.jpg)

- The service is `layered`. 
- At the top, I don’t care how the letter gets from me to Phil – whether it goes by plane, truck or hovercraft. And I don’t care about the route it takes, or how many sorting offices it passes through along the way. I don’t mind whether Olive walks, skips, bicycles or runs to the mailbox; I don’t care which mailbox she posts the letter in
- I want the `lower layers to abstract away the details for me, providing me with a simple service model`
- in turn, Olive doesn’t need to know how the postal service delivers the letter;
- Notice that `each layer communicates only with the layers above and below`
- service deploys new trains, or starts using a different airline freight service, Phil and I don’t need to know about it. IN other words, `because communication is simply up and down, with a well defined interface between layers, we can improve each layer independently over time.`

### 1.3.2. computer system:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxurv2uu7ij31ed0u0qht.jpg)

- `Layering breaks down the overall problem of writing programs` that execute on hardware into modules, or functional components, each with a well defined role and providing a well defined service to the layer above. 
- It also provides `a clear separation of concerns`
- `The compiler can focus on lexical analysis, parsing and so on`; 
- `the linker can focus on efficiently piecing objects together.` 
- Neither has to worry about the job of the other, and each can be improved, upgraded and replaced over time as technology and know-how progress.

#### 1.3.2.1. C programming language:

需要layering:

- C code can be compiled for almost any processor. 
- We can take C code like i++ and compile it for an `ARM processor in a phone`, `an x86_64 processor in a laptop`, or `microcontroller` in an ultra-modern dish washer. 
- In this way the C code is `hardware independent` and so keeps the layering here.

不需要layering的特殊情况:

- But sometimes we need our C code to do `something special that only our processor can do`.
- For example, an x86_64 processor has all kinds of special instructions that a microcontroller doesn’t
- C allows you to directly include `assembly code`. Software like `operating systems kernels` such as Linux and Windows use this for some of their `lowest level implementations`.
- The layering that C provides hides this detail so doesn’t let you do so directly, but you have to do it to achieve your goal. So OS kernels include assembly code. Doing this means that `code is no longer layer independent`
- the Linux context switch assembly written mfor ARM only works for ARM. So you have to` write a version for each layer.`
- If Linux wants to run on a new processor, developers need to `write new assembly code for that processor.`

这样的操作会很繁琐,非不得已的情况不要这样做,在此也体现了分层的好处,可以让事情变得更简单。

### 1.3.3. Internet:

a lot of the practical, operational challenges in the Internet today result from people `breaking layering` and `assuming thinks above and below their service interface`: 

- there’s a continual tension to improve the Internet by making cross-layer optimizations and the resulting loss of flexibility

one really telling example of this with something called `NATs, or Network Address Translators`, tremendously useful devices that have unfortunately made it almost impossible to add new transport protocols to the Internet.

---

## 1.4. Reasons for layering:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxusek552ij31et0u0qdn.jpg)


### 1.4.1. Modularity: 

It breaks down the system into smaller, more manageable modules.

### 1.4.2. Well defined service: 

Each layer provides a well defined service to the layer above.

### 1.4.3. Reuse: 

A layer above can rely on all the hard work put in by others to implement the layers below. It saves us the time to rewrite each layer whenever we build a system.

### 1.4.4. Separation of concerns: 

Each layer can focus on its own job, without having to worry about how other layers do theirs. 

The only communication is up-down the layers, so it helps keep one layer’s processing and data local and internal where possible, minimizing the complex interactions between layers.

### 1.4.5. Continuous improvement

### 1.4.6. peer to peer communications

A 6th benefit is specific to layered communication systems, such as the Internet. 

That is peer to peer communications

In the 4-layer Internet model we saw how each layer communicates with its peer on another system, using the delivery service provided by the layers below. 

Similarly, in the mail example, Phil and I are communicating with each other as users, without worrying about how the communication service works.
