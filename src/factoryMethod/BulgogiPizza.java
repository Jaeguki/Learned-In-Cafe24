package factoryMethod;

public class BulgogiPizza implements Pizza{

	public BulgogiPizza() {
		System.out.println("�Ұ������ �ֹ��ϼ̽��ϴ�.");
	}

	@Override
	public void makePizza() {
		System.out.println("�Ұ�����ڰ� ����������ϴ�.");
		
	}

}
