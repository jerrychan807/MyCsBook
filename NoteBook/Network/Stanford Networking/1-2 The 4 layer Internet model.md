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

## 1.3. Black box--- Internet：

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

![](https://ws3.sinaimg.cn/large/006tNbRwgy1fxltprgfnrj30n90bu41c.jpg)


Internet由什么组成?

- The `Internet` is made up of `endhosts`, `links` and `routers`.

Link layers有什么种类?

- `Ethernet` and `WiFi` – these are two examples of different Link layers. 

Link layers的职责是?

- The `Link Layer’s job` is to carry the `data` over one link at a time.  

Data是怎么传输的?

- Data is delivered `hop-by-hop` over each link in turn.
- Data is delivered in `packets`.
- A `packet` consists of the data we want to be delivered, along with a `header` that tells the network where the packet is to be delivered, where it came from and so on


----

#### 1.3.1.2. Network layer:

network layer的职责是什么?

- to deliver `packets` end-to-end across the Internet from the source to the destination.

packet是什么?

![](https://ws1.sinaimg.cn/large/006tNbRwgy1fxlu41d5njj30m307twht.jpg)


- A packet is an important `basic building block` in networks. 
- A packet is the name we give to `a self-contained collection of data`, plus a `header` that describes what the data is,where it is going and where it came from.

datagrams是什么?

- `Network layer packets` are called `datagrams`. 
- They consist of `some data` and a `head` containing the “To” and “From” addresses – just like we put the “To:” and “From” addresses on a letter.

----

那么 `Network layer` 与 `Link Layer`之间的数据是怎么传输的呢?

----

#### 1.3.1.3. Network layer 与 Link Layer的交互步骤:

1.
![](https://ws1.sinaimg.cn/large/006tNbRwgy1fxluclgdw1j30hb0c3zn9.jpg)



- The `Network` hands the `datagram` to the `Link Layer` below **telling it to send the datagram over the first link.**  
- In other words, the `Link Layer` is providing a `service` to the Network Layer. 
- Essentially, the Link Layer says: “if you give me a `datagram` to send, I will transmit it over one link for you”. 

2.

![](https://ws3.sinaimg.cn/large/006tNbRwgy1fxluhkskz7j30hd0c9adc.jpg)

- The `Link Layer of the router` accepts the `datagram`
from the link, and hands it up to the `Network Layer` in the router.

- The `Network Layer on the router` **examines** `the destination address of the datagram`, and is responsible for **routing** the `datagram` one hop at a time towards its eventual destination.

3.

![](https://ws3.sinaimg.cn/large/006tNbRwgy1fxlumlpv9tj30xy0c4agm.jpg)

- It does this by sending to the `Link Layer` again, to carry it
over the next link. 
- And so on until it reaches `the Network
Layer at the destination`. 


在这个过程中,每一层只需要做好自己的工作。


`Network Layer` does not need to concern itself
with **how the `Link Layer` sends the `datagram` over the
link.**

In fact, different Link Layers work in very different ways; 

`Ethernet` and `WiFi` are clearly very different. 

This separation of concerns between the `Network Layer` and the `Link Layer` **allows each to focus on its job, without worrying about how the other layer works***. 

It also means that `a single Network Layer` has a `common
way` to talk to `many different Link Layers` by simply handing
them datagrams to send. 

This separation of concerns is made possibly by `the modularity of each layer` and `a common welldefined API` to the layer below. 

----

#### 1.3.1.4. the network layer is special：

- We must use the `Internet Protocol`. It is the `Internet Protocol`, or `IP`,that holds the `Internet` together

- `IP` makes a `best-effort attempt` to deliver our
packets to the other end. But it makes `no promises`.
- IP packets can get lost, can be delivered out of
order, and can be corrupted. There are `no guarantees`.


----

How can the Internet work at all when the packets are not guaranteed to be delivered? 

if an application wants a guarantee
that its data will be retransmitted when necessary and
will be delivered to the application in order and
without corruption then it needs another protocol
running on top of IP. 

This is `the job of the Transport Layer`….

----

#### 1.3.1.5. Transport Layer:

The most common Transport
Layer is TCP (`Transmission Control Protocol`)


`TCP/IP`, which is when an application uses both TCP and IP together

TCP提供的服务:

- `TCP` makes sure that data sent by an application at one end of the Internet is `correctly delivered – in the right order` -
to the application at the other end of the Internet. 
- If the `Network Layers` `drops` some datagrams, TCP will retransmit them, multiple times if need-be. 
- If the `Network Layer` delivers them `out of order` – perhaps because two packets follow a different path to their destination – TCP will put the data back into the right order again.

最重要的是：

TCP provides a `service` to an application guaranteeing correct
in-order delivery of data, running on top of the `Network Layer service`, which provides an `unreliable datagram delivery
service`. 

这么做的好处是

`applications` such as a web client, or an email client, find
`TCP` very useful indeed. 

By `employing TCP` to make sure data is delivered correctly, they don’t have to worry about implementing all of the mechanisms inside the application.

They can take advantage of the huge effort that
developers put into correctly implementing TCP, and reuse it
to deliver data correctly.

`Reuse is another big advantage of layering.`

