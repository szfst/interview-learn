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
#### tomcat的三种线程模式
- 1.7之前bio
- 1.7nio，1.8默认开启nio
- apr：原生的c语言编写的非阻塞io，目前性能最理想。
  - tomcat-native.tar.gz解压，然后./configure 参数是apr的安装路径。还要做别的配置
  - 压测比较，apr稳定性好，波动小

#### tomcat集群
- tomcat自带集群，集群之间session可以传输（一般不推荐使用这种方式，效率问题）
- 使用memecache(MSM)，性能比redis高

#### openresty
- 基于nginx和lua的代理框架
