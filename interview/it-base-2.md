#####��Щ����µĶ���ᱻ�������ջ��ƴ����
https://www.zhihu.com/question/35164211
- ʲôʱ��
	- ��˵����������������ṹ�������minor gc/full gc ��Minor GC
��������ռ䣨���� Eden �� Survivor ���򣩻����ڴ汻��Ϊ Minor GC��Major GC �������������Full GC �����������ѿռ䡪��������������������
	- ��˵��minor gc/full gc�Ĵ���������OOM�Ĵ�������������GC�ĵ��ŵĲ��ԡ� 
	- eden����minor gc
	- ����������Ķ�����������ʣ��ռ�full gc������С��ʱ��HandlePromotionFailure����ǿ��full gc
	- gc���gcʱ���ʱ������GCTimeRatio����������OOM����������ͨ��NewRatio���������������������ͨ��MaxTenuringThreshold���ƽ�������ǰ���������
- ��ʲô����
	- ��gc root��ʼ���������������Ķ���
	- ����ǿ���á������á������á���Ӱ���������
	- ��root�������������Ҿ�����һ�α�ǡ��������Ȼû�и���Ķ��� ��û����finalize�����������渴��Ķ���
- ��ʲô����
	- ��˵�����������������Ǹ�������from survivor��to survivor�Ǹ�ɶ�õġ�����������Ǳ����������������ƬҪ��Ҫ������������ͱ����������ʲô�����Ƶ�
	- ���ܽ�������С����У�����/��������Ƭ����CMS���Ѽ��������õ�������ص㡢�����ƣ�������˵������/�����ռ���ѡ��ķ�ʽ�� 
#####��һ�³������뷽ʽ
utf-8��GBK
#####�޸Ķ���A��equals������ǩ������ôʹ��HashMap����������ʵ����ʱ�򣬻�����ĸ�equals������
- ����ö�������equals������
��==������ǻ������͵Ļ����ǿ����ǵ�����ֵ�Ƿ���ȾͿ��ԡ�
������������͵Ļ����Ƚϵ���ջ�ڴ�ֲ���������ָ����ڴ��е�ָ���ֵ�Ƿ����
��equals����������equals����û����д�Ļ���equals�����͡�==����ͬһ�֡�
hashcod�Ƿ��ض���ʵ���ڴ��ַ��hashӳ�䡣
���������ж����hashӳ�䶼�ǲ���ͬ�ġ�
https://www.jianshu.com/p/985534b21089
#####Java��String���˽�
- 1.String����final���ǲ����Ա��̳еģ��������ĳ�Ա����Ĭ����final������java��final�����ǲ����Ա��̳е�
- 2.String����һ�����������ǹ̶�������ˣ���String������κθı䶼��Ӱ�쵽ԭ������ص��κ�change�������������µĶ���
- 3.ÿ�����Ǵ����ַ�������ʱ��JVM�����ȼ���ַ��������أ�������ַ����Ѿ����ڳ������У���ô��ֱ�ӷ��س������е�ʵ�����á�����ַ��������ڳ������У��ͻ�ʵ�������ַ������ҽ���ŵ��������С�����String�ַ����Ĳ��ɱ������ǿ���ʮ�ֿ϶���������һ��������������ͬ���ַ�
- ��̬�����أ���*.class�ļ��еĳ����أ�class�ļ��еĳ����ز����������ַ���(����)���������������ࡢ��������Ϣ��ռ��class�ļ����󲿷ֿռ�.����ʱ�����أ�����jvm������������װ�ز����󣬽�class�ļ��еĳ��������뵽�ڴ��У��������ڷ������У����ǳ�˵�ĳ����أ�����ָ�������е�����ʱ�����ء�
- 4.intern����ʹ�ã�һ����ʼΪ�յ��ַ����أ�������String����ά���������� intern����ʱ��������Ѿ�����һ�����ڴ�String������ַ�������equals(oject)����ȷ�������򷵻س��е��ַ��������򣬽���String������ӵ����У������ش�String��������á�
#####Java��ʵ�ֶ�̬�Ļ�����ʲô��
- http://blog.csdn.net/bornlili/article/details/55213563
- Javaʵ�ֶ�̬��������Ҫ�������̳С���д������ת��
	- �̳У��ڶ�̬�б�������м̳й�ϵ������͸��ࡣ
	- ��д������Ը�����ĳЩ�����������¶��壬�ڵ�����Щ����ʱ�ͻ��������ķ�����
	- ����ת�ͣ��ڶ�̬����Ҫ����������ø����������ֻ�����������ò��ܹ��߱����ܵ��ø���ķ���������ķ�����
- ��Java����������ʽ����ʵ�ֶ�̬���̳кͽӿڡ�
- ���Զ�̬������ѭ��ԭ�����Ϊ��������������ñ��������������ʱ�������ö�������Ͷ��������ñ��������;����˵���˭�ĳ�Ա������������������õķ����������ڳ����ж�����ģ�Ҳ����˵�����า�ǵķ�������������ȻҪ���ݼ̳����з������õ����ȼ���ȷ�Ϸ����������ȼ�Ϊ��this.show(O)��super.show(O)��this.show((super)O)��super.show((super)O)��
#####��ν�һ��Java�������л����ļ��
- serilization
- fastjson
- protobuf
Ȼ����outputstreamд���ļ�
http://blog.csdn.net/leefengboy/article/details/52724019
#####˵˵���Java��������
- http://blog.csdn.net/piaoyi493279486/article/details/45624257
- JAVA���������������״̬�У���������һ���࣬���ܹ�֪���������������Ժͷ�������������һ�����󣬶��ܹ�������������һ�����������ԣ����ֶ�̬��ȡ����Ϣ�Լ���̬���ö���ķ����Ĺ��ܳ�Ϊjava���Եķ�����ơ�
- Java���������Ҫ�ṩ�����¹��ܣ� ������ʱ�ж�����һ�������������ࣻ������ʱ�ж�����һ���������еĳ�Ա�����ͷ�����������ʱ��������һ������ķ��������ɶ�̬����������ʱ��������һ����Ķ���
- ���÷�������ܻ��ʲô��Ϣ��һ�仰��������ʲô��Ϣ�����Ϳ��Ի��ʲô��Ϣ������ǰ���ǵ�֪��������֣�Ҫ����û�к�����
-  ���ȵø��ݴ�������ȫ��������Class����
	-  Class c=Class.forName("className");ע����className����Ϊȫ����Ҳ���ǵð������������磬cn.netjava.pojo.UserInfo;
	-   Object obj=c.newInstance();//���������ʵ��
	-    ��ù��캯���ķ���
	-   Constructor getConstructor(Class[] params)//����ָ���������public������
	-  Constructor[] getConstructors()//���public�����й�����
	-   Constructor getDeclaredConstructor(Class[] params)//����ָ���������public�ͷ�public�Ĺ�����
	-  Constructor[] getDeclaredConstructors()//���public�����й�����
	-   ����෽���ķ���
	-   Method getMethod(String name, Class[] params),���ݷ��������������ͻ�÷���
	- Method[] getMethods()//������е�public����
    - Method getDeclaredMethod(String name, Class[] params)//���ݷ������Ͳ������ͣ����public�ͷ�public�ķ���
	- Method[] getDeclaredMethods()//������Ե�public�ͷ�public����
	- ����������Եķ���
	- Field getField(String name)//���ݱ������õ���Ӧ��public����
	- Field[] getFields()//�����������public�ķ���
	-  Field getDeclaredField(String name)//���ݷ��������public�ͷ�public����
	-  Field[] getDeclaredFields()//����������е�public�ͷ�public����
- Java�ķ���ǳ�ǿ�󣬴���class�� ���Զ�̬�����ɸ��ࡢȡ��������������Ϣ��������������ԡ������Լ����캯���ȣ���������ȡ���丸��򸸽ӿ���������ݡ�
	- obj.getClass().getDeclaredMethods();//ȡ��obj�����Լ�����ķ����� ����˽�еķ�����
	- obj.getClass().getMethods();//ȡ��obj�����Լ�����ķ������̳й����ķ����� ��˽�з����ò�����
#####˵˵���Javaע������
- https://www.zhihu.com/question/47449512?sort=created
-  java 1.5��ʼ������ע��ͷ��䣬��ȷ����˵ע���Ƿ����һ���֣�û�з��䣬ע���޷�����ʹ�ã����뿪ע�⣬�������ɿ���ʹ�ã������˵������Ķ���Ӧ�ð���ע��ź���һЩ��
-  ע��Ĺ��ܷ֣�
	- 1����д�ĵ���ͨ���������ʶ��Ԫ���������ĵ��������ĵ�doc�ĵ���
	- 2�����������ͨ���������ʶ��Ԫ���ݶԴ�����з�����ʹ�÷��䡿
	- 3�������飺ͨ���������ʶ��Ԫ�����ñ������ܹ�ʵ�ֻ����ı����顾Override��
- Ԫע�⣺
	- 1. Retention�����Ԫע���ʾһ��ע��ᱻ������ʲôʱ��
	- 2��Documented�� ��һ��ע�ⱻ@DocumentedԪע��������ʱ����ô����������ʹ�����ע�⣬���ᱻJavadoc�����ĵ�����
	- 3��Inherited�����������ε�ע���������Զ��̳еġ����������һ������������඼����ĳ��ע�⣬�Ϳ���ʹ��@Inherited���������ע�⡣Ҳ����˵������Parent����Child��ĸ��࣬��ô�������ñ�@InheritedԪע�������ε�ĳ��ע���Parent����������Σ����൱��Child��Ҳ����ע���������ˡ�
	-  4. Target�����Ԫע��˵���˱����ε�ע���Ӧ�÷�Χ��Ҳ���Ǳ����ε�ע���������ע����Щ����Ԫ��
#####˵˵�������ע������
- https://www.jianshu.com/p/ba7dabe61bbe
- https://www.zhihu.com/question/48427693?answer_deleted_redirect=true
- ������A����F��Ҫ������B����ͳ�ĳ����У����Ǿͻ�ȥnewһ����B�Ķ��������A�ͻ�����������B�������˵�����B�����ڣ�����AҲ���޷�ʹ�á���ʹ������ע���Ժ���Aֻ��Ҫȥ����ʵ�ֹ���F�ӿڵ�һ��ʵ���࣬���ʵ�����������B,C�ȵȣ��������˭����Spring�������ļ������ģ�������A�Ͳ�����������B��
- ���ǿ������������Ʒ�ת����Դ������ʹ����Դ��˫�����й��������ɲ�ʹ����Դ�ĵ���������Spring���������й���
- �ô�
	- ��Դ���й���ʵ����Դ�Ŀ��������׹���
	-��**����**�� ����ʹ����Դ˫���������̶�
- Spring Ioc�빤��ģʽ������
	- ����û��������仯��Ҫ��Chinese���޸�һ�¡���ôǰһ�ֹ���ģʽ����Ҫ����Factory��ķ������������±��벼�𡣶�IoCֻ�� Ҫ��class���Ըı�һ�£���������IoC������Java������ƣ���Щ������**��̬����**�ģ���ʱ���ǾͿ����Ȳ岦Chinese���󣨲��ذ�ԭ����ֹͣ �������±��벼��
	- ע�⣬IoC����������д��۵ģ����ò����鷳�����ɶ���ķ�ʽ��ֱ�ۡ�������������ɶ�����Ч������һ�㡣���ʹ��IoCҪ����û�б�Ҫ������Ϊ�Ƚ�ͨ�õ��жϷ�ʽ�ǣ��õ�����ģʽ�ĵط������Կ�����IoCģʽ��
	- ����IoC�ĵ������ԡ�ʲô�ǵ������ԣ�������ù�Struts��EJB�ͻᷢ�֣�Ҫ�̳�һЩ�ӿڻ��࣬�����������ǵĿ�ܿ�����������ϵͳ�ͱ�����Struts��EJB�� �ˣ���ϵͳ�Ŀ���ֲ�Բ���������Ӱ�졣��������к����漰ĳһ����ܵĴ��룬��ô�����ܾͿ��Գ�����һ���������ԵĿ�ܡ�
#####˵һ�·���ԭ��������˵��
- ���Ͳ���:�������Ͳ������������ڶ�����ͬ�Ķ��󣬷���true'
```java
public static void main(String[] args) {
    ArrayList<String> arrayList1=new ArrayList<String>();
    arrayList1.add("abc");
    ArrayList<Integer> arrayList2=new ArrayList<Integer>();
    arrayList2.add(123);
 System.out.println(arrayList1.getClass()==arrayList2.getClass());
}
```
<<�������jvm>>p311
- ���´��벻�ܱ���ͨ��
```java
public class GenericTypes {
    public static void method(List<String> list){
        System.out.println("invoke method(List<String> list");
    }
    public static void method(List<Integer> list){
        System.out.println("invoke method(List<Integer> list");
    }
}
```
- ����ֻ����jdk1.6�вſ������
- invoke method(List<String> list)
- invoke method(List<Integer> list)
```java
public class GenericTypes {
    public static String method(List<String> list){
        System.out.println("invoke method(List<String> list");
        return "";
    }
    public static int method(List<Integer> list){
        System.out.println("invoke method(List<Integer> list");
        return 1;
    }
    public static void main(String[] args) {
        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
```
#####StringΪʲôҪ��Ƴɲ��ɱ�ģ�
- http://blog.csdn.net/renfufei/article/details/16808775
- 1. �ַ��������ص���Ҫ(��ƿ���,)
```java
String s1= "ab" + "cd";
String s2= "abc" + "d";
System.out.println(s1==s2);	//���������   ����Ϊ���ڳ������
String a = "abc";
String b = new String("abc");
System.out.println(a==b);//���������ȣ���Ϊһ������ڶ��һ������ڳ�����  
 ```
- 2. ����String���󻺴�HashCode(Ч���Ż�)
Java��String����Ĺ�ϣ�뱻Ƶ����ʹ��, ������hashMap �������С�
�ַ��������Ա�֤��hash���Ψһ��,��˿��Է��ĵؽ��л���.��Ҳ��һ�������Ż��ֶ�,��ζ�Ų���ÿ�ζ�ȥ�����µĹ�ϣ��. ��String��Ķ����������´���:
- 3. ��ȫ��(��ȫ��)
String������Java��(��)������������,���� �������ӵ�ַURL,�ļ�·��path,���з����������Ҫ��String������, ����String���ǹ̶������,����������ְ�ȫ������
#####Object���equal��hashCode������д��Ϊʲô��
http://blog.csdn.net/shiyanming1223/article/details/6893401
- 1�� ΪʲôҪ����equal������
��ΪObject��equal����Ĭ����������������õıȽϣ���˼����ָ��ͬһ�ڴ�,��ַ����ȣ�������ȣ������������Ҫ���ö��������ֵ���ж��Ƿ���ȣ�������equal������
- 2�� Ϊʲô����hashCode������
һ��ĵط�����Ҫ����hashCode��ֻ�е�����Ҫ����HashTable��HashMap��HashSet�ȵ�hash�ṹ�ļ���ʱ�Ż�����hashCode����ôΪʲôҪ����hashCode�أ���HashMap��˵���ñ�HashMap����һ�����ڴ�飬�����кܶ�С�ڴ�飬С�ڴ��������һϵ�еĶ��󣬿�������hashCode������С�ڴ��hashCode%size(С�ڴ������)�����Ե�equal���ʱ��hashCode������ȣ����������object���󣬱�������hashCode��equal������
- 3�� Ϊʲôequals()��ȣ�hashCode��һ��Ҫ��ȣ���hashCode��ȣ�ȴ��Ҫ��equals���?
	- 1����Ϊ�ǰ���hashCode������С�ڴ�飬����hashCode������ȡ�
	- 2��HashMap��ȡһ�������ǱȽ�key��hashCode��Ⱥ�equalΪtrue��
֮����hashCode��ȣ�ȴ����equal���ȣ��ͱ���ObjectA��ObjectB���Ƕ�������name����ôhashCode����name���㣬����hashCodeһ�������������������ڲ�ͬ���ͣ�����equalΪfalse
- 4�� Ϊʲô��ҪhashCode?
	- 1�� ͨ��hashCode���Ժܿ�Ĳ鵽С�ڴ�顣
	- 2�� ͨ��hashCode�Ƚϱ�equal�����죬��getʱ�ȱȽ�hashCode�����hashCode��ͬ��ֱ�ӷ���false��