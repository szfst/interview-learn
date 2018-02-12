##### 一、适配器模式
- 适配器模式：也叫包装器（wrapper），spring 源码里面有
- java分类：组合方式和继承方式
- 组合：
	- 采用组合方式的适配器称为对象适配器
	- 特点：把“被适配者”作为一个对象组合到适配器类中，以修改目标接口包装成被适配者。（好简单，直接**理解为java的实现接口**，然后把被适配者加入实现类中）
``` java
public class TwoPlugAdapter implements ThreePlugIf {
    //被适配者
	private GBTwoPlug plug;
	public TwoPlugAdapter(GBTwoPlug plug){
		this.plug = plug;
	}
	@Override
	public void powerWithThree() {
		System.out.println("通过转化");
		plug.powerWithTwo();
	}
}
``` 
- 继承：
	- 采用继承方式的适配器称为类适配器
	- 特点：通过多重继承不兼容接口，实现对目标接口的匹配，单一的为某个类而实现的适配
``` java
/*
 * 采用继承方式的插座适配器
 */
public class TwoPlugAdapterExtends extends GBTwoPlug implements ThreePlugIf {
	@Override
	public void powerWithThree() {
		System.out.print("借助继承适配器");
		this.powerWithTwo();
	}
}
``` 
- 作用
	- 透明：通过适配器，客户端可以调用同一个接口，因而对客户端来说是透明的。这样做更简单、更直接、更紧凑。
	- 重用：复用了现存的类，解决了现存类和复用环境要求不一致的问题。
	- 低耦合：将目标类和适配器类解耦，通过映入一个适配器类重用现有的适配者类，而无需修改原有代码（遵循开闭原则，对扩展开放，修改关闭）