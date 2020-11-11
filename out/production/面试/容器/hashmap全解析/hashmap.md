

## hashmap中hash算法确定哈希桶位置

不管增加、删除、查找键值对，定位到哈希桶数组的位置都是很关键的第一步。前面说过HashMap的数据结构是数组和链表的结合，
所以我们当然希望这个HashMap里面的元素位置尽量分布均匀些，尽量使得每个位置上的元素数量只有一个，那么当我们用hash算法求得这个位置的时候，
马上就可以知道对应位置的元素就是我们要的，不用遍历链表，大大优化了查询的效率。HashMap定位数组索引位置，
直接决定了hash方法的离散性能。先看看源码的实现(方法一+方法二):
```java
方法一：
static final int hash(Object key) {   //jdk1.8 & jdk1.7
     int h;
     // h = key.hashCode() 为第一步 取hashCode值
     // h ^ (h >>> 16)  为第二步 高位参与运算
     return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
方法二：
static int indexFor(int h, int length) {  //jdk1.7的源码，jdk1.8没有这个方法，但是实现原理一样的
     return h & (length-1);  //第三步 取模运算
}
```
**Hash算法本质上就是三步**
1. 取key的hashCode值(key.hashCode())
2. 高位运算h = key.hashCode() ^ (h >>> 16) (**高低位同时参与运算降低冲突**)
3. 取模运算。h & (length-1)（**&运算效率高于%**）

对于任意给定的对象，只要它的hashCode()返回值相同,程序调用方法一所计算得到的Hash码值总是相同的.
好的hash算法的目的计算出来的index下标位置尽可能分散均匀一些。一般可以对数组长度取余%arrayLength获得index.
但是jdk设计者并没有这样做。原因有两个

一、直接取余hash算法冲突高。任何key的hashcode只用到了低位定位index,冲突可能性比较高。因此jdk设计者
通过把hashcode的高低16位做异或运算。目的：hash算法冲突率低、分散均匀

二、直接取余%操作效率低下。hashcode % length和hashcode&(length-1)计算最终结果一样，但是
&比取余%运算速度更快。

![hashmap的索引定位](./img/hashmap的索引定位.png)

### 面试题
HashMap的哈希算法是怎么实现的，为什么要这样实现

