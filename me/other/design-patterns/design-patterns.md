##### һ��������ģʽ
- ������ģʽ��Ҳ�а�װ����wrapper����spring Դ��������
- java���ࣺ��Ϸ�ʽ�ͼ̳з�ʽ
- ��ϣ�
	- ������Ϸ�ʽ����������Ϊ����������
	- �ص㣺�ѡ��������ߡ���Ϊһ��������ϵ����������У����޸�Ŀ��ӿڰ�װ�ɱ������ߡ����ü򵥣�ֱ��**���Ϊjava��ʵ�ֽӿ�**��Ȼ��ѱ������߼���ʵ�����У�
``` java
public class TwoPlugAdapter implements ThreePlugIf {
    //��������
	private GBTwoPlug plug;
	public TwoPlugAdapter(GBTwoPlug plug){
		this.plug = plug;
	}
	@Override
	public void powerWithThree() {
		System.out.println("ͨ��ת��");
		plug.powerWithTwo();
	}
}
``` 
- �̳У�
	- ���ü̳з�ʽ����������Ϊ��������
	- �ص㣺ͨ�����ؼ̳в����ݽӿڣ�ʵ�ֶ�Ŀ��ӿڵ�ƥ�䣬��һ��Ϊĳ�����ʵ�ֵ�����
``` java
/*
 * ���ü̳з�ʽ�Ĳ���������
 */
public class TwoPlugAdapterExtends extends GBTwoPlug implements ThreePlugIf {
	@Override
	public void powerWithThree() {
		System.out.print("�����̳�������");
		this.powerWithTwo();
	}
}
``` 
- ����
	- ͸����ͨ�����������ͻ��˿��Ե���ͬһ���ӿڣ�����Կͻ�����˵��͸���ġ����������򵥡���ֱ�ӡ������ա�
	- ���ã��������ִ���࣬������ִ���͸��û���Ҫ��һ�µ����⡣
	- ����ϣ���Ŀ���������������ͨ��ӳ��һ�����������������е��������࣬�������޸�ԭ�д��루��ѭ����ԭ�򣬶���չ���ţ��޸Ĺرգ�