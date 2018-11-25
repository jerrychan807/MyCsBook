# The 4 layer Internet model


上一节学到的不同Application(BitTorrent,Skype,the World Wide Web)

communicate over the Internet using a `simple model` : `bi-directional reliable byte stream`. 

虽然他们以各异的速度去发送各式各样的数据，但是他们有个相似点。

## Application的相似点:

applications want to send and receive data without having to worryabout the `path`, or `route`, that the data takes across
the Internet. 

And almost all applications want to be
confident that **their data is correctly delivered, with any lost or corrupted data automatically retransmitted until it is received correctly.**

----

应用程序只想自己的数据能够正确快速的传输过去，不想考虑过多的细节。

传输数据的细节交给`Internet`这个black box去考虑.



---

## Internet：

数据在Internet中的传输操作很复杂

为了applications can **reuse** `the
same building blocks` over and over again, without having to create them from scratch for every application.

早期的Internet的创始人创造了`4 Layer Internet Model`这个概念,去描述数据传输在Internet中各层的操作。

这里面涉及到一个重要的思想：Layering


## Layering:

Layering is an important and frequently used concept in networking.

接下来会在常常看到这个概念:`Layering`.

---






