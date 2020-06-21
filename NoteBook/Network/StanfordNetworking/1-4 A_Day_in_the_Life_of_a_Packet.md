
- [1. Life of a Packet:](#1-life-of-a-packet)
    - [1.1. stream of data 传输:](#11-stream-of-data-%E4%BC%A0%E8%BE%93)
    - [1.2. Packet在Server和Browser之间的传输:](#12-packet%E5%9C%A8server%E5%92%8Cbrowser%E4%B9%8B%E9%97%B4%E7%9A%84%E4%BC%A0%E8%BE%93)
        - [1.2.1. open a connection --- Three way handshake:](#121-open-a-connection-----three-way-handshake)
        - [1.2.2. TCP Byte Stream：](#122-tcp-byte-stream)
            - [1.2.2.1. Inside Byte Stream:](#1221-inside-byte-stream)
            - [1.2.2.2. Inside Each Hop:](#1222-inside-each-hop)
    - [1.3. Under the Hood:](#13-under-the-hood)
        - [1.3.1. at the network layer:](#131-at-the-network-layer)
        - [1.3.2. inside the network layer:](#132-inside-the-network-layer)


---

# 1. Life of a Packet:

## 1.1. stream of data 传输:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxnrja2ycnj30yo0eqgpk.jpg)

接下来看看 packets的传输

---

## 1.2. Packet在Server和Browser之间的传输:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxord7du9tj31gq0u042w.jpg)

---

### 1.2.1. open a connection --- Three way handshake:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxorf4ofs5j31pe0sito7.jpg)

1. the client sends a `“synchronize”` message to the server,
2. the server responds with a “synchronize” message that also acknowledges the clients “synchronize”, or a `“synchronize and acknowledge message”`
3. the client responds by `acknowledging` the server’s
synchronize


`three way handshake` is described as `“synchronize,synchronize and acknowledge, acknowledge”`, or `“SYN, SYN-ACK, ACK”`

----

### 1.2.2. TCP Byte Stream：

`the network layer` is responsible for delivering packets to computers, but the `transport layer` is responsible for delivering data to `applications`

From the perspective of the `network layer`, `packets` sent to different applications on the same computer look the same. 

This means that to open `a TCP stream` to another `program`, we need two addresses.

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxosgy4olbj31nj0u0wsl.jpg)

Two addresses：

- `Internet Protocol address`
    - the address the `network layer` uses to deliver packets to the computer
- `TCP port`
    - tells the `computer’s software which application` to deliver data to

---

#### 1.2.2.1. Inside Byte Stream:

这些 IP packets 是如何到达目的地的?

网络拓扑情况:

- We don’t have `a direct wire` connecting my `client` to the `server`. 
- Instead, my `client` is connected to `an intermediate computer, a router`. 
- This `router` is itself connected to `other routers`. 
- `IP packets` between the `client` and `server` take many `“hops`,”where a hop is a link connecting two routers

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxosrpeujnj31r60icqd2.jpg)

例子:

-  my client is on `a WiFi network`
-  `first hop` is wireless to the `WiFi access point`
-  The `access point` has a wired connection to the
`broader Internet`, so it forwards my client’s packets along this wired hop.

Router:

- A `router` can have `many links` connecting it. 
- As each `packet` arrives, a `router` decides which of its `links` to send it out on. 
- `Routers` have `IP addresses`, so it’s also the case that it might not `forward a packet` but rather `deliver it to its own software`

例子:

-  when you log into a router using TCP, the IP packets are destined to the router’s own IP address.

> 例如 ![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxpabbj714j30e709eabr.jpg)

---

#### 1.2.2.2. Inside Each Hop:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxpagk9eohj30kr0e1whi.jpg)

route是如何决定把`packet`转发去哪的?

- 通过`forwarding table`

什么是 `forwarding table`?

- ![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxpaibenrej305b05t74t.jpg)
- A forwarding table consists of a set of `IP address patterns` and the `link` to send across for each pattern.

使用`forwarding table`的过程?

- When a `packet` arrives, the `router` **checks which forwarding table entry’s pattern best matches the packet**
- It forwards the packet along that entry’s link

default route

- the first entry in the table above. 
- The default route is the least specific route -- it matches every IP address
- The default route is especially useful in edge networks
- 例子: 
    - you’re Stanford University
and have `a router connecting you to the larger Internet`. 
    - Your router will have many specific routes for the IP addresses of Stanford’s network: “send packets to the engineering school over this link”, “send packets to the library over that link.” 
    - But if the `destination IP address` isn’t Stanford’s, then the `router` should send it out to the larger Internet.


----

## 1.3. Under the Hood:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxpb1yy1esj30o20akabp.jpg)

how the network looks like to `the end hosts`, `the computers`, as they `exchange packets` at the network layer?

### 1.3.1. at the network layer:

1. open a connection : `three way handshake`
2. request & response

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxpb9huhdjj30z70e5noz.jpg)

---

what does it look like inside the network layer? What hops do these packets take? 

### 1.3.2. inside the network layer:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNbRwgy1fxpbkclh7rj30ze0gtb29.jpg)

We’ve now seen 

- the life of a packet, starting as an application-level client web request
- taking nearly 20 hops through the Internet to reach its destination