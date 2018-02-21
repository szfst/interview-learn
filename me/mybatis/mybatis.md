#mybatis
- mybatis 缓存机制：MyBatis 提供了查询缓存来缓存数据，以提高查询的性能。MyBatis 的缓存分为一级缓存和二级缓存。参考：http://blog.csdn.net/u010858605/article/details/70906617?locationNum=2&fps=1
  - 一级缓存是 SqlSession 级别的缓存：一级缓存是 SqlSession 级别的缓存，是基于 HashMap 的本地缓存。不同的 SqlSession 之间的缓存数据区域互不影响。
一级缓存的作用域是 SqlSession 范围，当同一个 SqlSession 执行两次相同的 sql 语句时，第一次执行完后会将数据库中查询的数据写到缓存，第二次查询时直接从缓存获取不用去数据库查询。当 SqlSession 执行 insert、update、delete 操做并提交到数据库时，会清空缓存，保证缓存中的信息是最新的。
MyBatis 默认开启一级缓存。
  - 二级缓存是 mapper 级别的缓存，多个 SqlSession 共享：二级缓存是 mapper 级别的缓存，同样是基于 HashMap 进行存储，多个 SqlSession 可以共用二级缓存，其作用域是 mapper 的同一个 namespace。不同的 SqlSession 两次执行相同的 namespace 下的 sql 语句，会执行相同的 sql，第二次查询只会查询第一次查询时读取数据库后写到缓存的数据，不会再去数据库查询。
MyBatis 默认没有开启二级缓存，开启只需在配置文件中写入如下代码：
···
<settings>  
      <setting name="cacheEnabled" value="true"/>  
</settings>
···
