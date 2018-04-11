#### BIO、NIO和AIO
#### Netty 的各大组件 
- https://blog.csdn.net/shayu8nian/article/details/54017164
- Bootstrap:一个应用通常有一个Bootstrap 开始，他主要是配置整个Netty 程序，串联各个组件，Bootstrap 有两种类型，ServerBootstrap 和 Bootstrap 分别用于Serber 端和 Client 端 
- Channel 渠道:（ChannelFuture） 代表一个Socker链接 或者其他的IO相关组件 
- EventLoop （NioEventLoopGroup）为Channel 处理I/O操作 一个EventLoop 可以为多个Channel 服务，理解为一个线程。
- EventLoopGroup 一个EventLoopGroup 包含多个EventLoop 可以理解为一个线程池
- Handler
- ![atatar](https://img-blog.csdn.net/20170104111400557?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hheXU4bmlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
####  Netty的线程模型 
https://blog.csdn.net/king866/article/details/54427447
####  TCP 粘包/拆包的原因及解决方法 
- 解决办法：自定义分隔符DelimiterBasedFrameDecoder（用的比较多，方便），固定长度的字节FixedLengthFrameDecoder，将消息分为消息头和消息尾（安全）,回车换行分隔符LineBasedFrameDecoder（自动的，不需要定义）
####  了解哪几种序列化协议？包括使用场景和如何去选择 
- 序列化（编解码技术）的作用：，用于网络传输，对象持久化
- 主流的编解码框架：
	- json：简单，方便，跨平台
	- java原生的序列化
	- JBoss的Marshalling包：对java原生的序列化做了优化，需要实现serializable接口，server端和client端的包名要一致，两端的serialVersionUID也要一致
	- google的Protobuf
	- 基于Protobuf的Kyro
	- MessagePack框架
#### Netty的零拷贝实现 
https://blog.csdn.net/baiye_xing/article/details/73351252
- 使用堆外内存，也就是直接内存，避免是从堆到内存的拷贝
- 体现在：
	- Netty的接收和发送ByteBuffer采用DIRECT BUFFERS，使用堆外直接内存进行Socket读写，不需要进行字节缓冲区的二次拷贝。如果使用传统的堆内存（HEAP BUFFERS）进行Socket读写，JVM会将堆内存Buffer拷贝一份到直接内存中，然后才写入Socket中。相比于堆外直接内存，消息在发送过程中多了一次缓冲区的内存拷贝。
	- Netty 提供了 CompositeByteBuf 类, 它可以将多个 ByteBuf 合并为一个逻辑上的 ByteBuf, 避免了传统通过内存拷贝的方式将几个小Buffer合并成一个大的Buffer
	- 通过 FileRegion 包装的FileChannel.tranferTo方法 实现文件传输, 可以直接将文件缓冲区的数据发送到目标 Channel，避免了传统通过循环write方式导致的内存拷贝问题。
	- 通过 wrap 操作, 我们可以将 byte[] 数组、ByteBuf、ByteBuffer等包装成一个 Netty ByteBuf 对象, 进而避免了拷贝操作。
#### Netty的高性能表现在哪些方面
- 异步非阻塞io
- 高效的Reactor线程模型
- volatile的大量、正确使用
-  CAS和原子类的广泛使用；
-  线程安全容器的使用；
-  通过读写锁提升并发性能。
-  高性能的序列框架
-  零拷贝，IO性能优化
-  tcp参数可以灵活配置
