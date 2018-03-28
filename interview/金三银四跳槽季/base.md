#### HashMap �����ݹ���
https://blog.csdn.net/aichuanwendang/article/details/53317351
https://www.cnblogs.com/KingIceMou/p/6976574.html
- jdk1.7 indexFor�����¼���ÿ��Ԫ���������е�λ��
```java
static int indexFor(int h, int length) {  
    return h & (length - 1);  
}
```
- jdk1.8����rehash֮��Ԫ�ص�λ��Ҫô����ԭλ�ã�Ҫô����ԭλ�����ƶ�2���ݵ�λ�ã��������¼���hashֵ�ã�����ˣ�table�е�Ԫ��ֻ�����������<code>(e.hash & oldCap) == 0</code>
	- Ԫ��hashֵ��N+1λΪ0������Ҫ����λ�õ���
	- Ԫ��hashֵ��N+1λΪ1��������ԭ����������λ��
#### HashMap 1.7 �� 1.8 �� ����˵�� 1.8 ������Щ�Ż�������Ż��ģ�
https://blog.csdn.net/jek123456/article/details/73869203
- jdk1.8ʹ��һ��Node�������洢���ݣ������Node����������ṹ��Ҳ�����Ǻ�����ṹ����������key��hashcode��ͬ����ô��ЩkeyҲ�ᱻ��λ��Node�����ͬһ������������ͬһ���������key������8����ʹ������ṹ�洢�������������8������ô�����treeifyBin������������ת��Ϊ�����������ô��ʹhashcode��ȫ��ͬ�����ں�������ض�������ĳ���ض�Ԫ�أ�Ҳֻ��ҪO(log n)�Ŀ�����Ҳ����˵put/get�Ĳ�����ʱ�临�Ӷ�ֻ��O(log n)
- ΪʲôҪ��ô�����أ�������ΪӦ����Ϊ�˱���Hash Collision DoS������Java��String��hashcode������ǿ�Ⱥ����������˿��Ժ����׵Ĺ��������hashcode��ͬ��String���󡣣�����������һ���ύ�����hashcode��ͬ���ַ�����������ô���Ժ����׵Ŀ���JDK1.7�汾�ķ�������������String��ȷ��ʵ����Compare�ӿڣ������JDK1.8�汾�ķ������ϣ�Hash Collision DoS������ɲ��ɳ��ܵĿ�����
#### Arrays.sort ʵ��ԭ��� Collection ʵ��ԭ�� 
- https://blog.csdn.net/qq_25673113/article/details/60468364
- https://blog.csdn.net/yangzhongblog/article/details/8184707
- collection.sort�õľ���arrays.sortʵ�ֵ�
- �ܱ����ȶ���stability
- arrays.sortʹ�õ���timSort����������С��ĳ��ֵ��ʱ�򣬾�ʹ�ö��ֲ��Ҳ�����������ʹ��mergeSort�������ϲ���
- �ж�����Ĵ�С��С��32ʹ�ö��ֲ�������
- �������32ʱ�� �����һ�����ʵĴ�С���ڽ����밴������ͽ����ص�����˷��������������ĵ�λ����һ�������������֣�����һ�����Ŀ�-����������ÿһ��������һ��run�������Щ run ���У�ÿ����һ��run������������кϲ���ÿ�κϲ��Ὣ����run�ϲ���һ�� run���ϲ��Ľ�����浽ջ�С��ϲ�ֱ�����ĵ����е�run����ʱ��ջ��ʣ��� run�ϲ���ֻʣһ�� run Ϊֹ����ʱ�����ʣ�� run �����ź���Ľ����
####  LinkedHashMap��Ӧ��
- LinkedHashMap �ܹ��������ղ���˳����߷���˳����е���
- ���� accessOrder �����������������ã�Ĭ��Ϊ false�������ղ���˳����е�������Ȼ������ʽ����Ϊ true�������Է���˳����е�����
- http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html
- ÿ�ε���get()���򽫸ö����Ƶ������β�ˡ�����put�����µĶ���Ҳ�Ǵ洢������β�ˣ��������ڴ滺��ﵽ�趨�����ֵʱ��������ͷ���Ķ��󣨽��������õ��ģ��Ƴ���
#### LRUCacheʵ��
- ˫������+HashMap
- https://blog.csdn.net/hxqneuq2012/article/details/52709652
- ��������ʹ��LinkedHashMap��LinkedHashMap �ṩ�� removeEldestEntry(Map.Entry<K,V> eldest) �������÷��������ṩ��ÿ���������Ŀʱ�Ƴ������Ŀ��ʵ�ֳ���Ĭ�Ϸ��� false����������ӳ�����Ϊ������������ӳ�䣬����Զ�����Ƴ���ɵ�Ԫ��
#### cloneable�ӿ�ʵ��ԭ��
- http://kentkwan.iteye.com/blog/739514
- �����������������б�Ķ����Ӧ�ã���ҪҲ����һ�ݶ�����ֱ��ʹ��Ӧ�ã���Ҫ��дcloneable�ӿڣ�����ʵ��cloneable�ӿڣ���дclone����Ϊreturn super.clone()���ɡ�
####  �쳣�����Լ�������� 
- throwableΪ���࣬error��exceptionΪ����
- ��try catch finally����У����finally����������return��catch�����׳����쳣���ܻᱻ����
```java
    boolean testEx() throws Exception {  
        boolean ret = true;  
        try {  
            throw new Exception();
        } catch (Exception e) {  
            System.out.println("testEx, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx, finally; return value=" + ret);  
            return ret;  
        }  
    } 
    public static void main(String[] args) {  
        TestException testException1 = new TestException();  
        try {  
            testException1.testEx();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
```
#### sleep��wait������
- ����Ҫ��sleep����û���ͷ�������wait�����ͷ�������
- �������������Բ�ͬ����ֱ���Thread��Object
- wait��notify��notifyAllֻ����ͬ�����Ʒ�������ͬ�����ƿ�����ʹ�ã���sleep�������κεط�ʹ��(ʹ�÷�Χ)(ֻ�л�ȡ�������ܵ���wait��������notify)��wait()��notify()��Ϊ��Զ���ġ�����־�����в������������Ǳ�����synchronized������synchronized block�н��е��á������non-synchronized������non-synchronizedblock�н��е��ã���Ȼ�ܱ���ͨ������������ʱ�ᷢ��illegalMonitorStateException���쳣����
- sleep���벶���쳣����wait��notify��notifyAll����Ҫ�����쳣
- ע��sleep()������һ����̬������Ҳ����˵��ֻ�Ե�ǰ������Ч��ͨ��t.sleep()��t�������sleep�������������Ǵ���ģ���ֻ����ʹ��ǰ�̱߳�sleep ������t�߳�
- wait����Object�ĳ�Ա������һ��һ�����������wait����������Ҫ����notify()��notifyAll()�������Ѹý���
#### �������ڴ�����η���
- https://www.cnblogs.com/chenpi/p/5489732.html
- һά�����ά�����ά����ȶ����ڶ��Ϸ���