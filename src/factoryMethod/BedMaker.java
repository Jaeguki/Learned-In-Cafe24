package factoryMethod;

public class BedMaker implements Furniture{

	public BedMaker() {
		System.out.println("ħ�����Ŀ �����");
	}

	@Override
	public void make() {
		System.out.println("ħ�븦 ��������ϴ�.");
	}

}
