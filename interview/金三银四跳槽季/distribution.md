####  Dubbo的底层实现原理和机制 
#### 描述一个服务从发布到被消费的详细过程
#### 分布式系统怎么做服务治理
#### 接口的幂等性的概念
http://www.360doc.com/content/16/0428/09/21340737_554437264.shtml
https://www.cnblogs.com/dingcee/p/5047752.html
- 幂等性是系统的接口对外一种承诺(而不是实现), 承诺只要调用接口成功, 外部多次调用**对系统的影响是一致的**. 声明为幂等的接口会认为外部调用失败是常态, 并且失败之后必然会有重试.
#### 消息中间件如何解决消息丢失问题
- kafka：
	- 同步模式下，确认机制设置为-1，即让消息写入leader和 Follower之后再确认消息发送成功
	- 异步模式下，为防止缓冲区满，可以在配置文件中设置不限制阻塞超时时间，当缓冲区满时让生产者一直处于阻塞状态
#### Dubbo的服务请求失败怎么处理
#### 重连机制会不会造成错误
#### 对分布式事务的理解 
#### 如何实现负载均衡，有哪些算法可以实现？
https://www.cnblogs.com/szlbm/p/5588555.html
- 轮询
- 随机（Random）法
-  源地址哈希（Hash）法
-  加权轮询（Weight Round Robin）法
-  加权随机（Weight Random）法
-  最小连接数（Least Connections）法
#### Zookeeper的用途，选举的原理是什么
https://blog.csdn.net/liuj2511981/article/details/42460069
- 用途：
	- 集群管理dubbo
	- 分布式锁
	- 数据发布与订阅
	- 分布通知/协调
- 选举原理：
	- 选择最大zxid（第一次启动选择自己） ，因为最新，集群中必须有2n+1个节点，必须有一半以上的节点选举投票通过才能作为leader（必须存活n+1的节点），否则继续选举
	- 算法：fast paxos
#### 数据的垂直拆分水平拆分。
- 垂直拆分：一个数据库由很多表的构成，每个表对应着不同的业务，垂直切分是指按照业务将表进行分类，分布到不同的数据库上面，这样也就将数据或者说压力分担到不同的库上面，
- 水平拆分：相对于垂直拆分，水平拆分不是将表的数据做分类，而是**按照某个字段的某种规则来分散到多个库之中**，每个表中包含一部分数据
#### zookeeper原理和适用场景
- https://blog.csdn.net/xqb_756148978/article/details/52259381
- 工作原理：Zookeeper 的核心是原子广播，这个机制保证了各个Server之间的同步。实现这个机制的协议叫做Zab协议。Zab协议有两种模式，它们分别是恢复模式（选主）和广播模式（同步）。当服务启动或者在领导者崩溃后，Zab就进入了恢复模式，当领导者被选举出来，且大多数Server完成了和 leader的状态同步以后，恢复模式就结束了。状态同步保证了leader和Server具有相同的系统状态。 
为了保证事务的顺序一致性，zookeeper采用了递增的事务id号（zxid）来标识事务。所有的提议（proposal）都在被提出的时候加上了zxid。实现中zxid是一个64位的数字，它高32位是epoch用来标识leader关系是否改变，每次一个leader被选出来，它都会有一个新的epoch，标识当前属于那个leader的统治时期。低32位用于递增计数
#### zookeeper watch机制 
https://blog.csdn.net/z69183787/article/details/53023578
- 一次性触发器
- 发送至客户端
- 设置watch的数据内容：节点创建，节点删除，节点改变，子节点改变等等。Zookeeper维护了两个Watch列表，一个节点数据Watch列表，另一个是子节点Watch列表。getData()和exists()设置数据Watch，getChildren()设置子节点Watch
- Watch是轻量级的，其实就是本地JVM的Callback，服务器端只是存了是否有设置了Watcher的布尔类型。（源码见：org.apache.zookeeper.server.FinalRequestProcessor）
#### redis/zk节点宕机如何处理
- https://www.zhihu.com/question/27052519
- redis：sentinal
- zk：只要集群down机不超过1/2，follower down机没关系，leader down机选举出新的leader
#### 分布式集群下如何做到唯一序列号
- https://github.com/szfst/learnNote/blob/master/tuling/identical-ID/identical-ID.md
#### 如何做一个分布式锁
- redis
- zookeeper
#### 用过哪些MQ，怎么用的，和其他mq比较有什么优缺点，MQ的连接是线程安全的吗
#### MQ系统的数据如何保证不丢失
#### 列举出你能想到的数据库分库分表策略；分库分表后，如何解决全表查询的问题 
- 取模，按照时间
- 垂直拆分，水平拆分
- 解决：查询出state字段符合/不符合的UserId，在查询问答数据的时候使用in/not in进行过滤，排序，分页等。过滤出有效的问答数据后，再调用用户服务获取数据进行组装。
#### zookeeper的选举策略
#### 全局ID
