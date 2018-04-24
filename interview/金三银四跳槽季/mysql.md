####  mysql分页有什么优化
- https://www.cnblogs.com/youyoui/p/7851007.html
- 使用子查询优化：
```sql
select * from orders_history where type=8 limit 100000,1;
select id from orders_history where type=8 limit 100000,1;
select * from orders_history where type=8 and 
id>=(select id from orders_history where type=8 limit 100000,1) 
limit 100;
```
这种方式先定位偏移位置的 id，然后往后查询，这种方式适用于 id 递增的情况。**(使用先使用范围查询定位 id （或者索引），然后再使用索引进行定位数据，能够提高好几倍查询速度。即先 select id，然后再 select *)**
- 这种方式假设数据表的id是连续递增的，则我们根据查询的页数和查询的记录数可以算出查询的id的范围，可以使用 id between and 来查询：
```sql
select * from orders_history where id >= 1000001 limit 100;
```
当然还可以使用 in 的方式来进行查询，这种方式经常用在多表关联的时候进行查询，使用其他表查询的id集合，来进行查询：
```sql
select * from orders_history where id in
(select order_id from trade_2 where goods = 'pen')
limit 100;
```
- 使用临时表优化:这种方式已经不属于查询优化，这儿附带提一下。
对于使用 id 限定优化中的问题，需要 id 是连续递增的，但是在一些场景下，比如使用历史表的时候，或者出现过数据缺失问题时，可以考虑使用临时存储的表来记录分页的id，使用分页的id来进行 in 查询。这样能够极大的提高传统的分页查询速度，尤其是数据量上千万的时候。
- 添加索引
####  悲观锁、乐观锁
- 乐观锁：乐观锁的特点先进行业务操作，不到万不得已不去拿锁。即“乐观”的认为拿锁多半是会成功的，因此在进行完业务操作需要实际更新数据的最后一步再去拿一下锁就好。
乐观锁在数据库上的实现完全是逻辑的，不需要数据库提供特殊的支持。一般的做法是在需要锁的数据上**增加一个版本号，或者时间戳**，然后按照如下方式实现：</br>
```sql
	1. SELECT data AS old_data, version AS old_version FROM …;
	2. 根据获取的数据进行业务操作，得到new_data和new_version
	3. UPDATE SET data = new_data, version = new_version WHERE version = old_version
	if (updated row > 0) {
	    // 乐观锁获取成功，操作完成
	} else {
	    // 乐观锁获取失败，回滚并重试
	}
	
```
乐观锁是否在事务中其实都是无所谓的，其底层机制是这样：在数据库内部update同一行的时候是不允许并发的，即数据库每次执行一条update语句时会获取被update行的写锁，直到这一行被成功更新后才释放。因此在业务操作进行前获取需要锁的数据的当前版本号，然后实际更新数据时再次对比版本号确认与之前获取的相同，并更新版本号，即可确认这之间没有发生并发的修改。如果更新失败即可认为老版本的数据已经被并发修改掉而不存在了，此时认为获取锁失败，需要回滚整个业务操作并可根据需要重试整个过程。</br>
- 悲观锁：悲观锁的特点是先获取锁，再进行业务操作，即“悲观”的认为获取锁是非常有可能失败的，因此要先确保获取锁成功再进行业务操作。通常所说的“一锁二查三更新”即指的是使用悲观锁。通常来讲在数据库上的悲观锁需要数据库本身提供支持，即通过常用的**select … for update**操作来实现悲观锁。当数据库执行select for update时会获取被select中的数据行的行锁，因此其他并发执行的select for update如果试图选中同一行则会发生排斥（需要等待行锁被释放），因此达到锁的效果。select for update获取的行锁会在当前事务结束时自动释放，因此必须在事务中使用。
这里需要注意的一点是不同的数据库对select for update的实现和支持都是有所区别的，例如oracle支持select for update no wait，表示如果拿不到锁立刻报错，而不是等待，mysql就没有no wait这个选项。另外mysql还有个问题是select for update语句执行中所有扫描过的行都会被锁上，这一点很容易造成问题。因此如果在mysql中用悲观锁务必要确定走了索引，而不是全表扫描。
- 行锁，表锁（针对对悲观锁）：
	- 1、明确指定主键，并且有结果集，row-level Lock（id是主键，也即是索引）</br>
			<code>select * from table_a where id = "66" for update</code>
	- 2、明确指定主键，并且无结果集，无Lock（id是主键，也即是索引）</br>
			<code>select * from table_a where id = "-100" for update</code>
	- 3、无主键，Table-Level Lock（name不是主键，也不是索引）</br>
			<code>select * from table_a where name = "iphone" for update</code>	
	- 4、主键不明确，Table-Level Lock</br>
			<code>select * from table_a where id <>  "66"  for update</code><br>
			<code>select * from table_a where id like "66"  for update</code>	
- 总结：
	- 乐观锁在不发生取锁失败的情况下开销比悲观锁小，但是一旦发生失败回滚开销则比较大，因此适合用在取锁失败概率比较小的场景，可以提升系统并发性能
	- 乐观锁还适用于一些比较特殊的场景，例如在业务操作过程中无法和数据库保持连接等悲观锁无法适用的地方
####  组合索引，最左原则
https://www.zhihu.com/question/36996520
####  mysql 的表锁、行锁
- 表级锁：开销小，加锁快；不会出现死锁；锁定粒度大，发生锁冲突的概率最高，并发度最低。
- 行级锁：开销大，加锁慢；会出现死锁；锁定粒度最小，发生锁冲突的概率最低，并发度也最高。
- InnoDB的行锁是针对索引加的锁，不是针对记录加的锁。并且该索引不能失效，否则都会从行锁升级为表锁。
####  mysql 性能优化
- https://www.cnblogs.com/claireyuancy/p/7258314.html
- https://www.cnblogs.com/zhouyusheng/p/8038224.html
- 为搜索字段建索引
- 缓存
-  分析查询日志和慢查询日志
- 读写分离
- 分库分表
- https://www.zhihu.com/question/19719997
#### mysql的索引分类：B+，hash；什么情况用什么索引
- https://blog.csdn.net/gao1440156051/article/details/52096819
- 如果是**等值查询**，那么哈希索引明显有绝对优势，因为只需要经过一次算法即可找到相应的键值；当然了，这个前提是，键值都是唯一的。如果键值不是唯一的，就需要先找到该键所在位置，然后再根据链表往后扫描，直到找到相应的数据
- 如果是**范围查询检索**，这时候哈希索引就毫无用武之地了，因为原先是有序的键值，经过哈希算法后，有可能变成不连续的了，就没办法再利用索引完成范围查询检索；
- 哈希索引也没办法利用索引完成**排序**，以及like ‘xxx%’ 这样的部分模糊查询（这种部分模糊查询，其实本质上也是范围查询）；
- 哈希索引也不支持**多列联合索引的最左匹配规则**；
- B+树索引的关键字检索效率比较平均，不像B树那样波动幅度大，在有大量重复键值情况下，哈希索引的效率也是极低的，因为存在所谓的**哈希碰撞**问题。
####  事务的特性和隔离级别
https://blog.csdn.net/jiesa/article/details/51317164