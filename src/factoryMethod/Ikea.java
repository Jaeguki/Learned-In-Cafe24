package factoryMethod;

public class Ikea {

	public static void main(String[] args) {
		
		IkeaFactory ikeaFactory = new IkeaFactory();
		
		System.out.println("�ֹ� 1: å��");
		ikeaFactory.order("å��").make();
		
		System.out.println("�ֹ� 2: ����");
		ikeaFactory.order("����").make();
		
		System.out.println("�ֹ� 3: ħ��");
		ikeaFactory.order("ħ��").make();
		
	}

}
