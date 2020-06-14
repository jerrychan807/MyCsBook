

- [1. The IP Service Model:](#1-the-ip-service-model)
    - [1.1. 数据的传输过程](#11-%E6%95%B0%E6%8D%AE%E7%9A%84%E4%BC%A0%E8%BE%93%E8%BF%87%E7%A8%8B)
    - [1.2. property of IP service:](#12-property-of-ip-service)
        - [1.2.1. Datagram:](#121-datagram)
        - [1.2.2. Unreliable](#122-unreliable)
        - [1.2.3. Best effort](#123-best-effort)
        - [1.2.4. Connectionless](#124-connectionless)
    - [1.3. why the IP service is so simple?](#13-why-the-ip-service-is-so-simple)
    - [1.4. IP Service Model (Details):](#14-ip-service-model-details)
    - [1.5. IPv4 Datagram:](#15-ipv4-datagram)
        - [1.5.1. Destination IP address](#151-destination-ip-address)
        - [1.5.2. Source IP address](#152-source-ip-address)
        - [1.5.3. Protocol ID](#153-protocol-id)
        - [1.5.4. Version](#154-version)
        - [1.5.5. Total packet length](#155-total-packet-length)
        - [1.5.6. Time to Live](#156-time-to-live)
        - [1.5.7. The Packet ID, Flags and Fragment Offset](#157-the-packet-id-flags-and-fragment-offset)
        - [1.5.8. Type of Service field](#158-type-of-service-field)
        - [1.5.9. Header Length](#159-header-length)
        - [1.5.10. Checksum](#1510-checksum)
    - [1.6. Summary:](#16-summary)

---

# 1. The IP Service Model:

the Network Layer is the most important layer of the Internet

each layer provides a “service” to the layer above.


![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxn0eopujxj30je09bwh9.jpg)


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

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxn0ukhuirj30kq098whu.jpg)


### 1.2.1. Datagram:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxn13rv28mj30l0095gpg.jpg)

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

---

## 1.3. why the IP service is so simple?

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxnkh06kt8j311g0k4wpf.jpg)

- To keep the `network` **simple, dumb and minimal**. Faster, more streamlined and **lower cost** to build and maintain
    - It was believed that if the network is `kept simple` with very features and requirements, then packets could be delivered very quickly, and at `low cost`
    - a simple network could be made to run very fast using dedicated `hardware`.
    - the `network` is implemented by `a large number of routers`
scattered throughout the network, if they could be kept simple then are likely to be more reliable, more affordable to maintain and will need to be upgraded less often.

- `The end to end principle`: Where possible, implement features in the end hosts
    -  it is easier to evolve and improve a `feature` if it is
implemented in `software on end computers` rather than
baked into the `hardware of the Internet`. 
    - `features` such as `reliable communications` and `controlling congestion` should be done at the end points – by the source and destination computers, and not by the network

- Allows a variety of `reliable (or unreliable)
services` to be built on top. 
    - if IP was `reliable` – in other words if **any missing packets were retransmitted automatically** – then it would not be ideal for some services.(比如 video chat)
    - IP lets the application choose the reliability service its needs.

- Works over `any link layer`: IP makes very few assumptions about the link layer.
    - `IP` makes very **little expectation** of the `Link layer` below – the link could be wired or wireless, and requires no retransmission or control of congestion

---

In addition to the basic unreliable, best-effort, connectionless datagram service, 

IP also provides a few other carefully chosen services. 

The designers of IP tried very hard to find a balance between providing the bare minimum needed to make communication work,
while not providing such a barebone service that it doesn’t really work. 

IP还是提供了少数可选的服务的。

---

## 1.4. IP Service Model (Details):

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxnkr0ahvzj30tg0hodoc.jpg)

- **prevent packets from looping forever**
    - IP routers forward packets `hop-by-hop` across the Internet
    - it is possible for the `forwarding table` in a router to be wrong, causing a packet to start looping round and around following the same path
    - IP uses a very `simple mechanism` to catch and then delete packets that appear to be stuck in a loop
    -  IP simply adds a hop-count field in the header of
every datagram. It is called `the time to live(TTL field)`
    -  It starts out at a number like 128 and then is **decremented by every router it passes through**. 
    - If it reaches `zero`, IP concludes that it must be stuck in a loop and the router drops the datagram.


- **fragment packets if they are too long**
    - IP is designed to run over any kind of link.
    - Most links have a `limit on the size of the packets` they can carry

- **uses a `header checksum` to reduce chances of
delivering a datagram to the wrong destination**

- Allows for new version of IP
    - currently IPv4 with 32 bit addresses
    - IPv6 with 128 bit addresses
    - Because we are `running out` of IPv4 addresses, the Internet is in a` gradual transition` to IPv6

- allows new fields to be added to the datagram header. 
    - a mixed blessing
    - allows `new features` to be added to the header that turn out to be important, but weren’t in the `original standard`
    - these fields need processing and so require extra
features in the routers along the path, breaking the goal of a simple, dumb, minimal forwarding path
    - In practice, `very few options` are used or processed by the routers.

--- 

## 1.5. IPv4 Datagram:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxnmfebcxfj30q00g2jwa.jpg)

### 1.5.1. Destination IP address

### 1.5.2. Source IP address

### 1.5.3. Protocol ID

- it allows the destination end host to `demultiplex` arriving packets, sending them to the correct code to process the packet
-  If the `Protocol ID` has the value “6” then it tells us the data contains a `TCP Segment`, and so we can safely pass the datagram to the TCP code and it will be able to parse the segment correctly
- `The Internet Assigned Numbers Authority (IANA)` defines over 140 different values of Protocol ID, representing different
`transport protocols. `

### 1.5.4. Version

- tells us which version of IP (IPv4 and IPv6)

### 1.5.5. Total packet length

### 1.5.6. Time to Live

- helps us to prevent packets accidentally looping in the ntwork forever.
- Every router is required to `decrement` the TTL field.
-  If it reaches `zero`, the router should drop the packet.

### 1.5.7. The Packet ID, Flags and Fragment Offset 

- Sometimes a `packet` is too long for the link it is
about to be sent on
-  help `routers` to fragment IP packets into smaller
`self-contained packets` if need-be


### 1.5.8. Type of Service field

- gives a `hint` to routers about **how important this packet is.**

### 1.5.9. Header Length

- tells us how big the header is 
- some headers have optional extra fields to carry extra
information.

### 1.5.10. Checksum

- a checksum is `calculated over the whole header` so just in case the header is corrupted, we are not likely to deliver a packet to the wrong desination by mistake

---

## 1.6. Summary:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxnmsoygqsj30yc0gwdld.jpg)

