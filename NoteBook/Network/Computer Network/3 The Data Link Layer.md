
# 3 The Data Link Layer


## 学习路线：

- an introduction to the key design issues
- study of its protocols by looking at **the nature of errors** and how they can be **detected and corrected**

---

## 引子:

理想情况:

- machine A just puts the bits on the wire
- machine B just takes them off

但是现实情况并不是这样,因为 **communication channels** make errors occasionally。

具体表现在于

- **communication channels** have only a **finite data rate**
- **propagation delay** between the time a bit is sent and the time it is received


为了高效的传输数据，我们有必要去应对这些 **limitations**

那么在考虑实现这个传输协议的时候,这些限制的因素都需要考虑在内。
这就是当前的**Key Design Issues**,也就是我们需要去解决的问题。


---

# Key words:

- key design issues

- units of information called frames
- individual bits

- communication channels
 - finite data rate
 - nonzero propagation delay


