# 1. The 4 layer Internet model

---

## 1.1. 不同Application

上一节学到的不同Application(BitTorrent,Skype,the World Wide Web)

communicate over the Internet using a `simple model` : `bi-directional reliable byte stream`. 

虽然他们以各异的速度去发送各式各样的数据，但是他们有个相似点。

## 1.2. Application的相似点:

applications want to send and receive data without having to worryabout the `path`, or `route`, that the data takes across
the Internet. 

And almost all applications want to be
confident that **their data is correctly delivered, with any lost or corrupted data automatically retransmitted until it is received correctly.**

----

应用程序只想自己的数据能够正确快速的传输过去，不想考虑过多的细节。

传输数据的细节交给`Internet`这个`black box`去考虑.

---

## 1.3. black box--- Internet：

数据传输在`Internet`中的操作很复杂,这个时候就需要abstract一下，将一些操作抽象成一个`block`.

这些`building blocks`组成起来就成了`Internet`

因此早期的Internet的创始人创造了`4 Layer Internet Model`这个概念，去 describe the
hierarchy of operations that make up the Internet.

这样的好处是,applications can **reuse** `the
same building blocks` over and over again, without having to create them from scratch for every application.


这当中涉及到一个重要的思想：Layering

> Layering is an important and frequently used concept in networking.

---

### 1.3.1. 4 Layer Internet Model：

![](https://ws1.sinaimg.cn/large/006tNbRwgy1fxke0tmhr8j315s0je7a6.jpg)

- each layer has a `different responsibility`
- each layer building a `service` on top of the one below
- all the way to the top where we have the `bi-directional reliable byte stream` communication between applications. 

---

#### 1.3.1.1. Link Layer:




