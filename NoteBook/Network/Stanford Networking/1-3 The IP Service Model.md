

- [1. The IP Service Model:](#1-the-ip-service-model)
    - [1.1. 数据的传输过程](#11-%E6%95%B0%E6%8D%AE%E7%9A%84%E4%BC%A0%E8%BE%93%E8%BF%87%E7%A8%8B)
    - [1.2. property of IP service:](#12-property-of-ip-service)
        - [1.2.1. Datagram:](#121-datagram)
        - [1.2.2. Unreliable](#122-unreliable)
        - [1.2.3. Best effort](#123-best-effort)
        - [1.2.4. Connectionless](#124-connectionless)

---

# 1. The IP Service Model:

the Network Layer is the most important layer of the Internet

each layer provides a “service” to the layer above.


![](https://ws3.sinaimg.cn/large/006tNbRwgy1fxn0eopujxj30je09bwh9.jpg)


## 1.1. 数据的传输过程

- When the `transport layer` has data to send, it hands a `Transport Segment` to t`he Network layer` below
- `The network layer` puts the `transport segement` inside a
new `IP datagram`. 
- `IP` sends the `datagram` to the `Link Layer` that puts it
inside a `Link frame`,

---

- The IP service can be characterized by four properties 
- It sends `Datagrams` from end host to end
host; 
- it is `unreliable`, but makes a `best-effort` to deliver
the datagrams. 
- The network maintains `no per-flow state`
associated with the datagrams.


----

## 1.2. property of IP service:

![](https://ws3.sinaimg.cn/large/006tNbRwgy1fxn0ukhuirj30kq098whu.jpg)


### 1.2.1. Datagram:

![](https://ws1.sinaimg.cn/large/006tNbRwgy1fxn13rv28mj30l0095gpg.jpg)

提供的服务:
- `IP` is a `datagram service`
- When we ask `IP` to send some data for us, it
creates a `datagram` and puts our data inside. 
- The `IP service model` provides a `service`
which includes the `routing to the destination`. 

Datagram是什么？

- The `datagram` is a packet that is `routed individually` through the network based on the information in its `header`. 
- the `datagram` is `self-contained`.


The datagram header的组成部分?

- `The datagram header` contains the `IP address of the destination`(IPDA) and `IP source address` (IP SA)
    - The `forwarding decision` at each router is based on the `IP DA`
    - `IP SA`, saying where the packet came from, so the receiver knows where to send any response

传输方式:

- Datagrams are routed `hop-by-hop` through the network from
one router to the next, all the way from the `IP source address`
to the `IP destination address` 

router:

- each router contains a `forwarding table` that tells it where to send packets matching a given destination address. 
- The router doesn’t know the `whole path`
- it simply uses the `destination address` to index into its `forwarding table` so that it can `forward the packet` to the next hop along the path towards its final destination. 

比喻:

- In the postal service, we put a letter into the mail box with the address of the destination and the letter is routed – invisibly to us – `hop by hop` from sorting office to sorting office until it reaches its destination.
- Neither the `sender` or the `receiver` know – or need to know – the `path` taken by letters in the postal service or by datagrams
in the Internet

### 1.2.2. Unreliable

-  IP is `unreliable` and makes `no guarantees`.
    -  IP makes `no promise` that packets will be delivered to the destination. 
    - They could be `delivered late`, `out of sequence`, or `never delivered` at all. 
    - It’s possible that a `packet` will be duplicated along the way

### 1.2.3. Best effort

-  it won’t drop datagrams `arbitrarily`(随意的) just because it feels like it
- IP does make the promise to only
drop datagrams if necessary

- 丢包例子:
    - the `packet queue in a router` might fill up because of `congestion`, forcing the router to drop the next arriving packet. 
    - a `faulty routing table` might cause a packet to be sent to the wrong destination

- IP won’t make any attempt to `resend` the data
- IP doesn’t makes no promises these errors won’t happen, nor does it `detect` them when they do

为什么ip不做过多的动作,比如检测和重发?

- 比喻 : The basic postal service makes no promise that our letters will be delivered on time, or that if we send 2-3 letters on successive days that they will be received in the order they were sent, and it makes no promise they will be delivered at all (unless we pay for a more expensive endto-end
service to guarantee delivery).

- `IP` is **an extremely simple, minimal service**. It maintains `no state` at all related to a communication. 
- We say that a communication service is `“connectionless”`


### 1.2.4. Connectionless

- IP doesn’t start by establishing some end to state
associated with the communication. 

例子:

- In other words, when we make a Skype call lasting several minutes and consisting of many IP datagrams, the IP layer maintains no knowledge of the call, and simply `routes` each `datagram` individually and independently of all the others.