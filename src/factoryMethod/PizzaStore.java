package factoryMethod;

public class PizzaStore {

	public static void main(String[] args) {

		PizzaFactory pizzaFactory = new PizzaFactory();
		
		System.out.println("�ֹ� 1: ġ������");
		pizzaFactory.order("ġ��").makePizza();
		
		System.out.println("�ֹ� 2: �Ұ������");
		pizzaFactory.order("�Ұ��").makePizza();
		
		System.out.println("�ֹ� 3: ����������");
		pizzaFactory.order("������").makePizza();
		
	}

}
