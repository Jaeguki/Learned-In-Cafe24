package singleTon;
/*
 * [����4] ��Ƽ������ ������ �ذ��ϱ� ���� ����ȭ singleton ����
 * 
 * synchronized�� ����Ͽ� �����ؼ� thread���� ���ÿ� �����ؼ� �ν��Ͻ��� ������Ű�� ������ ������
 *  �� ����  thread���� getInstance()�޼ҵ带 ȣ���ϰԵǸ� ���� cost������� ���� ���α׷����ݿ� �������ϰ� �Ͼ. 
 *  
 */
public class ThreadSafeInitalization 
{
	private static ThreadSafeInitalization instance;
	private ThreadSafeInitalization () {}
	
	public static synchronized ThreadSafeInitalization getInstance () 
	{
		if (instance == null)
			instance = new ThreadSafeInitalization();
		return instance;
	}
	
	public void print () 
	{
		System.out.println("It's print() method in ThreadSafeInitalization instance.");
		System.out.println("instance hashCode > " + instance.hashCode());
	}
}
