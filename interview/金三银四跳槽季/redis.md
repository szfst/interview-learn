#### Redis用过哪些数据数据，以及Redis底层怎么实现
- http://www.cnblogs.com/jaycekon/p/6227442.html
- https://www.cnblogs.com/jaycekon/p/6277653.html
- https://www.linuxidc.com/Linux/2017-09/146889.htm
#### Redis缓存穿透，缓存雪崩 
https://www.cnblogs.com/zhangweizhong/p/6258797.html
- 缓存穿透：缓存穿透是指用户查询数据，在数据库没有，自然在缓存中也不会有。这样就导致用户查询的时候，在缓存中找不到，每次都要去数据库再查询一遍，然后返回空。这样请求就绕过缓存直接查数据库，这也是经常提的缓存命中率问题。**解决的办法就是**：1、如果查询数据库也为空，直接设置一个默认值存放到缓存，这样第二次到缓冲中获取就有值了，而不会继续访问数据库，这种办法最简单粗暴。2、第二种办法就是使用布隆过滤器。
- 缓存雪崩：**缓存雪崩是指在我们设置缓存时采用了相同的过期时间，导致缓存在某一时刻同时失效，请求全部转发到DB，DB瞬时压力过重。**是由于原有缓存失效(过期)，新缓存未到期间。所有请求都去查询数据库，而对数据库CPU和内存造成巨大压力，严重的会造成数据库宕机。从而形成一系列连锁反应，造成整个系统崩溃。雪崩解决办法如下
	- 1、碰到这种情况，一般并发量不是特别多的时候，使用最多的解决方案是加锁排队
	- 2、给每一个缓存数据增加相应的缓存标记，记录缓存的是否失效，如果缓存标记失效，则更新数据缓存。
	- 3、一个简单方案就时讲缓存失效时间分散开，比如我们可以在原有的失效时间基础上增加一个随机值，比如1-5分钟随机，这样每一个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件。
#### 如何使用Redis来实现分布式锁
- https://github.com/szfst/interview-learn/blob/master/me/redis/redis.md
#### Redis的并发竞争问题如何解决
https://www.cnblogs.com/shamo89/p/8385390.html
- 使用自带的原子操作，如incr
- 使用独占锁的方式，分布式锁
- 乐观锁方式：使用redis事务机制
#### Redis持久化的几种方式，优缺点是什么，怎么实现的 
https://blog.csdn.net/yinxiangbing/article/details/48627997
- RDB:
	- 异步
	- fork一个子进程 
	- 快照
	- 默认redis是会以快照的形式将数据持久化到磁盘的（一个二进制文件，dump.rdb，这个文件名字可以指定），在配置文件中的格式是：save N M表示在N秒之内，redis至少发生M次修改则redis抓快照到磁盘。当然我们也可以手动执行save或者bgsave（异步）做快照。
	- 工作原理：当redis需要做持久化时，redis会fork一个子进程；子进程将数据写到磁盘上一个临时RDB文件中；当子进程完成写临时文件后，将原来的RDB替换掉，这样的好处就是可以copy-on-write
	- 缺点：在redis异常死掉时，最近的数据会丢失（丢失数据的多少视你save策略的配置），当业务量很大时，丢失的数据是很多的
- AOF
	-  可以做到全程持久化，只需要在配置文件中开启（默认是no），appendonly yes开启AOF之后，redis每执行一个修改数据的命令，都会把它添加到aof文件中，当redis重启时，将会读取AOF文件进行“重放”以恢复到redis关闭前的最后时刻。
	-  AOF的优化：LOG Rewriting随着修改数据的执行AOF文件会越来越大，其中很多内容记录某一个key的变化情况。因此redis有了一种比较有意思的特性：在后台重建AOF文件，而不会影响client端操作。在任何时候执行BGREWRITEAOF命令，都会把当前内存中最短序列的命令写到磁盘，这些命令可以完全构建当前的数据情况，而不会存在多余的变化情况（比如状态变化，计数器变化等），缩小的AOF文件的大小。所以当使用AOF时，redis推荐同时使用BGREWRITEAOF。
	-  AOF文件刷新的方式，有三种，参考配置参数appendfsync ：appendfsync always每提交一个修改命令都调用fsync刷新到AOF文件，非常非常慢，但也非常安全；appendfsync everysec每秒钟都调用fsync刷新到AOF文件，很快，但可能会丢失一秒以内的数据；appendfsync no依靠OS进行刷新，redis不主动刷新AOF，这样最快，但安全性就差。默认并推荐每秒刷新，这样在速度和安全上都做到了兼顾。
	-  AOF的重写的工作原理： 产生一个子进程，子进程产生一个临时文件，临时文件做aof重写，当有新的数据，同时追加到旧的文件和新的文件。完成之后替换旧文件。https://blog.csdn.net/hezhiqiang1314/article/details/69396887
- 最后，为以防万一（机器坏掉或磁盘坏掉），记得定期把使用 filesnapshotting 或 Append-only 生成的*rdb *.aof文件备份到远程机器上。我是用crontab每半小时SCP一次。我没有使用redis的主从功能 ，因为半小时备份一次应该是可以了，而且我觉得有如果做主从有点浪费机器。这个最终还是看应用来定了。
#### Redis的缓存失效策略
- volatile-lru：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰
- volatile-ttl：从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰
- volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰
- allkeys-lru：从数据集（server.db[i].dict）中挑选最近最少使用的数据淘汰
- allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰
- no-enviction（驱逐）：禁止驱逐数据
https://www.cnblogs.com/xuliangxing/p/7151812.html
#### Redis集群，高可用，原理
- https://www.zhihu.com/question/21419897（第一个回答讲得很好）
#### 集群：
- Redis Cluster：3.0以后有的，Redis Cluster
- Redis Sharding集群：Redis Sharding可以说是Redis Cluster出来之前，业界普遍使用的多Redis实例集群方法。其主要思想是采用哈希算法将Redis数据的key进行散列，通过hash函数，特定的key会映射到特定的Redis节点上。这样，客户端就知道该向哪个Redis节点操作数据。庆幸的是，java redis客户端驱动jedis，已支持Redis Sharding功能，即ShardedJedis以及结合缓存池的ShardedJedisPool。
- 阿里云的解决方案
#### redis高可用
- redis sentinel
- https://www.jianshu.com/p/c2ab606b00b7
#### Redis缓存分片
- redis分片应该就是集群吧
#### Redis的数据淘汰策略
- 就是缓存失效策略
