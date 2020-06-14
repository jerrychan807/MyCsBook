
- [1. Packet Switching Principle:](#1-packet-switching-principle)
    - [1.1. idea:](#11-idea)
    - [1.2. 什么是Packet?](#12-%E4%BB%80%E4%B9%88%E6%98%AFpacket)
    - [1.3. 什么是Packet switching？](#13-%E4%BB%80%E4%B9%88%E6%98%AFpacket-switching)
        - [1.3.1. 简单的定义:](#131-%E7%AE%80%E5%8D%95%E7%9A%84%E5%AE%9A%E4%B9%89)
        - [1.3.2. 描述:](#132-%E6%8F%8F%E8%BF%B0)
        - [1.3.3. packet switching的例子:](#133-packet-switching%E7%9A%84%E4%BE%8B%E5%AD%90)
    - [1.4. 两个重点:](#14-%E4%B8%A4%E4%B8%AA%E9%87%8D%E7%82%B9)
        - [1.4.1. Simple packet forwarding:](#141-simple-packet-forwarding)
            - [1.4.1.1. No per-flow state required:](#1411-no-per-flow-state-required)
        - [1.4.2. Efficient sharing of links:](#142-efficient-sharing-of-links)
            - [1.4.2.1. Data traffic is bursty:](#1421-data-traffic-is-bursty)
    - [1.5. Summary:](#15-summary)

---

# 1. Packet Switching Principle:

## 1.1. idea:

When the Internet was designed, it was based on a controversial(有争议的) and revolutionary(革命的) idea: `packet switching`.



## 1.2. 什么是Packet?

A self-contained unit of data that carries information necessary for it to reach its destination.

## 1.3. 什么是Packet switching？

### 1.3.1. 简单的定义:

- Independently for each arriving packet,pick its outgoing link. 
- If the link is free, send it. Else hold the packet for later.


### 1.3.2. 描述:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxq7d3mgxej30zc0iq45a.jpg)


- `Packet switching` is the idea that we `break` our data up into
`discrete, self-contained chunks of data`. 
- Each chunk, called a `packet`, carries sufficient information that a network can deliver the packet to its destination
-  In the simplest form of `packet switching`, each `packet` is routed `separately and independently`

### 1.3.3. packet switching的例子:

简单的模型:

- ![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxq7p8ve7jj319y0eaqbl.jpg)
- `“self routing”` or `“source routing,”` : each packet contains an explicit route, specifying the IDs of each packet switch along the way
- but it raises `big security issues`. People owning routers don’t want you telling them how to send packets, because maybe you can trick them to sending them somewhere they shouldn’t, such as secure computers.

优化的模型:

- ![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxq7wht7avj319k0ecn4o.jpg)
- a `switch` can have `a table of destination addresses and the next hop.` 
- When it receives a packet, it looks up the
address in the table, and sends the packet to the
appropriate next hop
- In this model, all the `packet` needs to carry is the `destination address`. Using the address, each `switch` along the way can make the right decision.

---

## 1.4. 两个重点:

### 1.4.1. Simple packet forwarding:

- a switch can make individual, local decisions for each
packet. 
- It doesn’t need to `keep extra state` on the packets its
seen or whether two packets go to the same destination.
- Even if many packets are part of some larger transfer or
protocol, the switch doesn’t need to know or care. 
- The switch doesn’t need to know that some packets are a Skype
call, others are a web request, and others still are a
firmware update for your computer. 
- It `just forwards packets`. 

#### 1.4.1.1. No per-flow state required:

`Flow`:  

- `A collection of datagrams` belonging to the same endto-end
communication, e.g. a TCP connection.

`packet switches` don't need `state for each flow` --- each packet is `self-contained`

- Because each packet is `self-contained`, a `switch` doesn’t
need to know about `groups of packets`, or `flows of packets`.
- Imagine if every switch had to keep track of every single
web connection passing through it. This would require a
huge amount of state that would be hard to manage!
- Instead, treating each packet independently means the
switch can be much simpler to build, manage, and
troubleshoot

`No per-flow state to be added/removed`

- The switch doesn’t need to worry about adding or removing
this per-flow state. 
- Imagine if every time you wanted to load a web page, you had to communicate with every switch along the path to set up state so your request would work. This could make things much slower.
- Instead, you can just send packets and the switches forward them appropriately.

`No per-flow state to be stored`

-  Because `switches` have to be fast, they’d need to store this state in very fast memory, which is expensive. 
-  This lets switches focus on doing one thing, forwarding packets quickly.

`No per-flow state to be changed upon failure`

-  `switches` don’t have to worry about failures
-  例子: what happens when you start a web request but then your tablet `runs out of energy`. The switch is going to keep the per-flow state for the request, but if one of the nodes that created the state fails, the switch needs to know how to `clean up` after it. Otherwise you can have millions, billions of dead flows `eating up your memory`.
- With `packet switching`, a switch has `no per-endpoint state`
-  If your tablet dies, the switch doesn’t care, it just means that it stops receiving packets from it

### 1.4.2. Efficient sharing of links:

例子:

- consider a wireless router in a home with two people browsing the Internet on their laptop.
- If one person is reading a page, then the other person can download a file at the full speed of the link. 
- If the first person starts loading a new web page, the link can be shared between the two of them. 
- Once the download completes, the first person can use the full speed
of the link.

#### 1.4.2.1. Data traffic is bursty:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxs9lru3t2j30lg09xdhz.jpg)

Data traffic is `bursty`: rather than always sending
and receiving data at `a fixed rate`, usage jumps and drops,
goes up and down, over time

- `Packet switching` allows flows to use all available link capacity.
- `Packet switching` allows flows to share link capacity.


This idea of taking `a single resource` and `sharing it across multiple users` in a `probabilistic or statistical way`is called
`statistical multiplexing`

It’s statistical in that each user receives a statistical share of the resource based on how much others are using it. 

For example, if your friend is reading, you can use all of the link. If both of you are loading a page, you receive half of the link capacity.


----

## 1.5. Summary:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxs9r3lro9j30l60awq5e.jpg)


This `simple building block` was revolutionary at the
time, but it’s now accepted as the `common way` to `build networks.`