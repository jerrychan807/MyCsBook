# Boolean Logic:



## 练习题:

### Not:

提示: 应用一个2位的Nand门来实现一个1位的Not门.

Not的Api:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvwz453ygj30v807igq7.jpg)

Nand的Api:
![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvwzarj0lj30uk05442g.jpg)

Nand的真值表:
![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvwwqwlmhj30ji076dh8.jpg)

solution:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvx7je3qpj30hm07a3zb.jpg)

---

### And:

And的真值表:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvxdj5q65j309w0963za.jpg)

Nand的真值表:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvxebfbfmj309e0583yv.jpg)

solution:

观察真值表,And的结果跟Nand的结果刚好是相反的,可以用上之前实现过的`Not`来做取反操作.

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvyoe1imoj30no082jsv.jpg)

---

### Or:

提示: 使用一些简单的布尔操作.

书上的公式: `Or = Nand(Not(a), Not(b))`


solution:

感觉没什么思路,只能用书上给出的公式来做了.

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvyriakfjj30tq0ao0v3.jpg)

---

### Xor:

提示: 使用一些简单的布尔操作.

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvyvrdyjxj30c801ajre.jpg)

solution:

![](https://raw.githubusercontent.com/jerrychan807/imggg/master/006tNc79gy1fzvyw7c3hrj30ky0a8jt3.jpg)

---

