/*
 * Furniture�� ���� Ŭ���� ��ü���� �����ϴ� ������ �����Ѵ�.
 * Ikea�� Furniture�� �������ִ� �߰��� ����
 * 
 * */

package factoryMethod;

public class IkeaFactory {
	
	public Furniture order(String type){
		
		if (type == "ħ��") {return new BedMaker();}
		if (type == "����") {return new ChairMaker();}
		if (type == "å��") {return new DeskMaker();}
		
		return null;
		
	}

}
