package factoryMethod;

public class PizzaFactory {
	
	public Pizza order(String menu) {
		
		if (menu == "ġ��") {return new CheesePizza();}
		if (menu == "������") {return new ShrimpPizza();}
		if (menu == "�Ұ��") {return new BulgogiPizza();}
		
		return null;
	}

}
