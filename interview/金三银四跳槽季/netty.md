#### BIO��NIO��AIO
#### Netty �ĸ������ 
- https://blog.csdn.net/shayu8nian/article/details/54017164
- Bootstrap:һ��Ӧ��ͨ����һ��Bootstrap ��ʼ������Ҫ����������Netty ���򣬴������������Bootstrap ���������ͣ�ServerBootstrap �� Bootstrap �ֱ�����Serber �˺� Client �� 
- Channel ����:��ChannelFuture�� ����һ��Socker���� ����������IO������ 
- EventLoop ��NioEventLoopGroup��ΪChannel ����I/O���� һ��EventLoop ����Ϊ���Channel �������Ϊһ���̡߳�
- EventLoopGroup һ��EventLoopGroup �������EventLoop �������Ϊһ���̳߳�
- Handler
- ![atatar](https://img-blog.csdn.net/20170104111400557?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hheXU4bmlhbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
####  Netty���߳�ģ�� 
https://blog.csdn.net/king866/article/details/54427447
####  TCP ճ��/�����ԭ�򼰽������ 
- ����취���Զ���ָ���DelimiterBasedFrameDecoder���õıȽ϶࣬���㣩���̶����ȵ��ֽ�FixedLengthFrameDecoder������Ϣ��Ϊ��Ϣͷ����Ϣβ����ȫ��,�س����зָ���LineBasedFrameDecoder���Զ��ģ�����Ҫ���壩
####  �˽��ļ������л�Э�飿����ʹ�ó��������ȥѡ�� 
- ���л�������뼼���������ã����������紫�䣬����־û�
- �����ı�����ܣ�
	- json���򵥣����㣬��ƽ̨
	- javaԭ�������л�
	- JBoss��Marshalling������javaԭ�������л������Ż�����Ҫʵ��serializable�ӿڣ�server�˺�client�˵İ���Ҫһ�£����˵�serialVersionUIDҲҪһ��
	- google��Protobuf
	- ����Protobuf��Kyro
	- MessagePack���
#### Netty���㿽��ʵ�� 
https://blog.csdn.net/baiye_xing/article/details/73351252
- ʹ�ö����ڴ棬Ҳ����ֱ���ڴ棬�����ǴӶѵ��ڴ�Ŀ���
- �����ڣ�
	- Netty�Ľ��պͷ���ByteBuffer����DIRECT BUFFERS��ʹ�ö���ֱ���ڴ����Socket��д������Ҫ�����ֽڻ������Ķ��ο��������ʹ�ô�ͳ�Ķ��ڴ棨HEAP BUFFERS������Socket��д��JVM�Ὣ���ڴ�Buffer����һ�ݵ�ֱ���ڴ��У�Ȼ���д��Socket�С�����ڶ���ֱ���ڴ棬��Ϣ�ڷ��͹����ж���һ�λ��������ڴ濽����
	- Netty �ṩ�� CompositeByteBuf ��, �����Խ���� ByteBuf �ϲ�Ϊһ���߼��ϵ� ByteBuf, �����˴�ͳͨ���ڴ濽���ķ�ʽ������СBuffer�ϲ���һ�����Buffer
	- ͨ�� FileRegion ��װ��FileChannel.tranferTo���� ʵ���ļ�����, ����ֱ�ӽ��ļ������������ݷ��͵�Ŀ�� Channel�������˴�ͳͨ��ѭ��write��ʽ���µ��ڴ濽�����⡣
	- ͨ�� wrap ����, ���ǿ��Խ� byte[] ���顢ByteBuf��ByteBuffer�Ȱ�װ��һ�� Netty ByteBuf ����, ���������˿���������
#### Netty�ĸ����ܱ�������Щ����
- �첽������io
- ��Ч��Reactor�߳�ģ��
- volatile�Ĵ�������ȷʹ��
-  CAS��ԭ����Ĺ㷺ʹ�ã�
-  �̰߳�ȫ������ʹ�ã�
-  ͨ����д�������������ܡ�
-  �����ܵ����п��
-  �㿽����IO�����Ż�
-  tcp���������������
