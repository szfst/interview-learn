##### 一、wb3.0
- 技术：
	- elasticsearch：
	- springCloud：
	- 文件服务器：
	- mongodb
	- redis：
	- kafka：
		- from begin参数
		- consumer的时候需要groupId：自定义，可以防止重复消费；使用Consumer high level API时，同一Topic的一条消息只能被同一个Consumer Group内的一个Consumer消费，但多个Consumer Group可同时消费这一消息。
		- 防止重复消费：https://www.cnblogs.com/liuwei6/p/6900686.html
			- 提高partition的数量，从而提高了consumer的并行能力，从而提高数据的消费能力
			- 对于单partition的消费线程，增加一个固定长度的阻塞队列和工作线程进一步提高并行消费能力
			- 使用spring-kafka消费机制：一批消息没有消费完成也可以提交offset，从而避免重复消费又进入死循环，其实也是使用阻塞队列，创建新的线程取消费
			- 将消息的唯一表示保存到外部介质中，每次消费时判断是否处理过即可。
		- 防止消息丢失：同步模式下，确认机制设置为-1，即让消息写入leader和 Follower之后再确认消息发送成功；异步模式下，为防止缓冲区满，可以在配置文件中设置不限制阻塞超时时间，当缓冲区满时让生产者一直处于阻塞状态
		- 问题集合：https://www.cnblogs.com/paul8339/p/7412512.html
		- 问题集合2：http://orchome.com/kafka/index
		- 如何保证消息的顺序：http://f.dataguru.cn/forum.php?mod=viewthread&tid=723257&page=1 保证只要一个分区
		- kafka 面试题 ：
		  kafka节点之间如何复制备份的？
		  kafka消息是否会丢失？为什么？
		  kafka最合理的配置是什么？
		  kafka的leader选举机制是什么？
		  kafka对硬件的配置有什么要求？
		  kafka的消息保证有几种方式？
	- elk：
	- websocket：
	- nginx：
	- mybatis:
	- vue
- 内容：
	-  财务分析：MongoDB，多线程，redis
	-  新闻公告：elasticsearch，kafka，zookeeper
	-  评级：
##### 二、ax
- 技术：
	- netty
	- springCloud：
	- shiro：
- 内容：
	- 数据同步：netty，protocolbuf
	- 评级模型：多线程
	- 账户：shiro
##### 三、hn
- 技术:
	- elastisearch
	- springCloud：
- 内容:
	-  新闻公告：elasticsearch
	- springCloud：
##### 四、zq
- 技术:
	- springboot+spingmvc+mybatis
	- 前后端分离
	- bootstrap
	- restful
- 内容：
	- 账户：单点登录
	- 基本信息