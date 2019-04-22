package singleTon;
/*
 * [����3] Ŭ�����ν��Ͻ��� ���Ǵ� ������ �ν��Ͻ��� ����� singleton pattern
 *  ���� ���������� �ν��Ͻ�ȭ ��Ű�� ������ ���α׷��� �޸𸮿� ����Ǵ� ������ �δ��� ���� �ٰԵ�.
 *  ������ ������ ��������.
 *  MultiThread����̶��, singletonPattern�� �������� ����.
 *  ��, ���� ������ getInstance() method�� ȣ���ϸ� �ν��Ͻ��� �ι� ���� ������ ����. 
 */
public class LazyInitialization 
{
	private static LazyInitialization instance;
	private LazyInitialization () {}
	
	public static LazyInitialization getInstance () 
	{
		if ( instance == null )
			instance = new LazyInitialization();
		return instance;
	}
	
	public void print () 
	{
		System.out.println("It's print() method in LazyInitialization instance.");
		System.out.println("instance hashCode > " + instance.hashCode());
	}
}
