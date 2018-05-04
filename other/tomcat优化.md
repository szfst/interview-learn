#### 线程池优化
maxConnections：最大连接数
- 1、ulimit -a查看系统可以支持的线程数，然后修改 /etc/security/limits.conf		
- 2、添加maxConntions的配置
  - 2.1 对CPU要求更高时，建议不要配置过于大
  - 2.2 对CPU要求不是特别高时，建议配置在3000左右	
- 3、配置的地方：server.xml -> connector
#### 线程队列优化
#### 虚拟机内存优化
- 建议最大内存最小内存配置一样
- 最大内存尽量达到可用内存的80%
- 元空间，就是物理内存，建议配置为服务器可用空间，可配可不配
- 在bin里面的catalina.sh里面配置添加，JAVA_OPTS=“-Server -Xms128m -Xmx128m”等等
