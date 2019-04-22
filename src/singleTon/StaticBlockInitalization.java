package singleTon;
/*
 * [����2] static �ʱ�ȭ ���� �̿��� sigleTon
 * �ʱ�ȭ���� �̿��ϸ� logic�� ���� �� �ֱ� ������ ������ �ʱ⺯�� ����, ����ó���� ���� ������ ���� �� ����.
 * [����1]���� ���ƺ�������, �ν��Ͻ��� ���Ǵ� ������ �����Ǵ� ���� �ƴ�.
 */
public class StaticBlockInitalization 
{
	private static StaticBlockInitalization instance;
	private StaticBlockInitalization () {}
	
	static 
	{
		try 
		{
			System.out.println("instance create..");
			instance = new StaticBlockInitalization();
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("Exception creating StaticBlockInitalization instance.");
		}
	}
	
	public static StaticBlockInitalization getInstance () 
	{
		return instance;
	}
	
	public void print () 
	{
		System.out.println("It's print() method in StaticBlockInitalization instance.");
		System.out.println("instance hashCode > " + instance.hashCode());
	}
}
