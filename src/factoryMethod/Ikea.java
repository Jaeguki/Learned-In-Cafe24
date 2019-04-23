/*
 * �ʿ��� ��ü�� IkeaFactory ��ü�κ��� �޾ƿ� ���ϴ� ����� �����Ѵ�.
 * IkeaFactory ��ü �ϳ��� �����ϸ� �Ǳ� ������ Ȯ�强�̳� �������� �鿡�� ����.
 * 
 * */
package factoryMethod;

public class Ikea {

	public static void main(String[] args) {
		
		IkeaFactory ikeaFactory = new IkeaFactory();
		
		System.out.println("�ֹ� 1: å�� ����");
		ikeaFactory.order("å��").make();
		
		System.out.println("�ֹ� 2: ���� ����");
		ikeaFactory.order("����").make();
		
		System.out.println("�ֹ� 3: ħ�� ������ ����");
		ikeaFactory.order("ħ��").newDesign();
		
	}

}
