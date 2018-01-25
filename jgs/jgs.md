##### һ�����̻߳�����
- ������������cpu��Դ
- **ynchronizeȡ�õ������Ƕ�����**��������һ�δ��루�������������������п��������̻߳�����������������������ǻ�����Ӱ�졣��һ�����������ͬ���������ھ�̬�����ϼ�synchronized�ؼ��֣���ʾ����.class�࣬��һ�����������ռ.class�ࣩ��
- ��������ACID
- synchronize��������(ͬһ����������ุ�඼��������  )
```java	
public class SyncDubbo(){
public synchronized void metho1d(){
		System.out.println("method1...");
		method2();
}
public synchronized void method2(){
		System.out.println("method2...");
		method3();
}
public synchronized void method3(){
		System.out.println("method3...");
}
}
```	
- volatile����ָֹ�������򡢱�֤�ɼ��ԣ�����֤ԭ���ԣ���AtomicInteger��atomicϵ�ж�������֤int��ԭ���ԣ�
- �̼߳�ͬѧ��
	- 1��wait��notify�������synchronize�ؼ���ʹ��
	- 2��wait�����ͷ�����notify�������ͷ���
- ����ģʽ�Ͷ��̣߳�
	- ����ģʽ������ľ��Ǽ���ģʽ������ģʽ��һ��ֱ��ʵ��������һ���ڵ��÷�����ʱ��ʵ���������ڶ��̵߳�ģʽ�У����ǵ����ܺ��̰߳�ȫ���⣬����һ��ѡ���������ֱȽϾ���ĵ���ģʽ������������ܵ�ͬʱ���б�֤���̰߳�ȫ��dubble check instance,static inner class�����ü򵥰�ȫ���ã� 
		- double check instance
```java	
	private static DubbleSingleton ds;
	public static DubbleSingleton getDs(){
		if(ds == null){
			try {
				//ģ���ʼ�������׼��ʱ��...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DubbleSingleton.class) {
				if(ds == null){
					ds = new DubbleSingleton();
				}
			}
		}
		return ds;
	}
```		
-  -  - static innner class
```java
 public class InnerSingleton {
	private static class Singletion {
		private static Singletion single = new Singletion();
	}
	public static Singletion getInstance(){
		return Singletion.single;
	}	
}
```
- concurrentMap��
	- concurrentHashMap���ֳɶ���Σ���С�������ȣ��ڲ�ʹ�öΣ�segment������ʾ��Щ��ͬ�Ĳ��֣�ÿ������ʵ����һ��С��hashtable���������Լ�������ֻҪ����޸ĳ��������ڲ�ͬ�Ķ��ϣ����ǾͿ��Բ������С���һ������ֳ�16���Ρ�Ҳ�������֧��16���̵߳Ĳ����޸ĳ�������Ҳ���ڶ��̳߳���ʱ��С�������ȴӶ�������������һ�ַ��������Ҵ����д�๲�����ʹ��volatile�ؼ���������Ŀ���ǵ�һʱ���ȡ�޸ĵ����ݣ����ܷǳ��á�
	- cocurrentSkipListMap��������棬������ģ�treeMap�Ĳ����汾��
- Copy-On-Write�������COW,��һ�����ڳ�������е��Ż����ԡ�Ӧ���ڶ���д�ٵ�����¡�
	- cow������дʱ���Ƶ�������ͨ�׵������ǵ�������һ�������������Ԫ�ص�ʱ�򣬲�ֱ������ǰ��������ӣ������ֽ���ǰ��������copy�����Ƴ�һ���յ�������Ȼ���յ������������Ԫ�أ������Ԫ��֮���ٽ�ԭ����������ָ���µ��������������ĺô������ǿ��Զ�cw�������в����Ķ���������Ҫ��������Ϊ��ǰ��Ԭ����������κ�Ԫ�ء�����cow����Ҳ��һ�ֶ�д�����˼�룬����д��ͬ�������� 
- queue
	- concurrentLinkedQueue������������������
	- BlockingQueue�ӿڣ�
		- ArrayBlockingQueue
		- LinkedBlockingQueue
		- PriorityBlockingQueue:
		- DelayQueue
		- SynchronousQueue:һ��û�л���Ķ��У������߲���������ֱ�ӻᱻ�����߻�ȡ�����ѡ�Ҫ��take���ܵ���add��blocking
- futureģʽ��ʵ�֣�
	- ����һ���̰߳�Ҫ���صĶ���ӽ�ȥ��ѯ����ѯ���ܺܺ�ʱ�������Ȱѽ������ȥ��get��ʱ��wait��ֱ��set���֮��notify�ͷ��ؽ���ˡ�
- ReentrantReadWriteLock,��д����
	- ����д�ٵ������ʹ�ö�д������synchronized��ReentrantLockʱ������֪����ͬһ��ʱ���ڣ�ֻ����һ���߳̽��з��ʱ������Ĵ��룬��ô��д����ͬ���䱾���Ƿֳ�����������������д�����ڶ�д����£�����߳̿��Բ����ķ��ʣ�����д����ʱ��ֻ��һ����˳����ʡ�
	- ��������дд���⣬��д����  
- �����Ż���
	- ��������
	- ��С���ĳ���ʱ��
	- ��С��������
	- ���ķ���
	- ����ʹ�������Ĳ�������ԭ�� ������Atomicϵ���ࣩ��volatile�ؼ���
- �ֲ�ʽ����
	- ʵ��1����zookeeperȥͬ����Э������Ҫ���Ķ���ע��zookeeper���Ҫʹ�þͺ�zookeeper˵��ʹ�����zookeeper˵ 
	- ʵ��2��ʹ��redisʵ��
- �߲������˼·��
	- ���磺�����
	- ���ؾ��⣺nginx��
	- cdn����
	- **��ҵ����**��������ɱҵ�����ҵ�����
	- ������java�������ź�����������nginx������redis���� 