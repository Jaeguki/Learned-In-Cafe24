package factoryMethod;

public class CheesePizza implements Pizza{

	public CheesePizza() {
		System.out.println("ġ������ �ֹ��ϼ̽��ϴ�.");
	}

	@Override
	public void makePizza() {
		System.out.println("ġ�����ڰ� ����������ϴ�.");
		
	}

}
