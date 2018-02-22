##### 一、缓存命中率的计算
- 命中缓存次数/(命中缓存次数+未命中缓存次数) = 命中率
##### 二、Memcache与Redis的区别
- memcache把数据存在内存之中，断电后会挂掉；Redis部分数据持久化在硬盘上，断电不会丢失
- memcache存的是key-value对，redis支持更多的数据结构和数据类型
- memcache可以使用一致性hash做分布式，redis可以做主从同步
- redis单线程，只使用1个cpu
##### 三、如何实现redis的分片
- 使用一致性哈希对数据进行映射https://www.jianshu.com/p/e8fb89bb3a61
- 实现方式：客户端分片（每个客户端对应一个分片）、代理协助分片、查询路由分片；
- 使用redis集群，如codis（豌豆荚，依赖zookeeper）；