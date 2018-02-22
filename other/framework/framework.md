##### 一、SpringMVC和Struts2的区别
- SpringMVC的方法级别的拦截，Struts2是类级别的拦截；
- SpringMVC是基于Servlet实现Controller，Struts2是基于Filter实现；
- SpringMVC性能和安全性高于Struts2；
- SpringMVC更加组件化和流程化，易于扩展，比如返回JSON通过设置@ResponseBody即可；
- Struts2更加无耦合，比较纯粹，但是需要更多的自行开发的代码以支持更多功能。
##### 二、Hibernate与MyBatis的比较
- Hibernate完全实现对象关系映射（ORM），MyBatis实现的是SQL Mapping
- MyBatis可以进行更为细致的SQL优化，可以减少查询字段。比Hibernate容易掌握，Hibernate门槛较高
- Hibernate的DAO层开发比MyBatis简单，Mybatis需要维护SQL和结果映射
- Hibernate对对象的维护和缓存要比MyBatis好，对增删改查的对象的维护要方便。
- Hibernate数据库移植性很好，MyBatis的数据库移植性不好，不同的数据库需要写不同SQL。
- Hibernate有更好的二级缓存机制，可以使用第三方缓存。MyBatis本身提供的缓存机制不佳。