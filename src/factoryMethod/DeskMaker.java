package factoryMethod;

public class DeskMaker implements Furniture{

	public DeskMaker() {
		System.out.println("å�����Ŀ �����");
	}

	@Override
	public void make() {
		System.out.println("å���� ��������ϴ�.");
	}
	
	@Override
	public void newDesign() {
		System.out.println("���ο� å�� �������� �����մϴ�.");
		
	}
}
