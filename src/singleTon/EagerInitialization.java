package singleTon;
/*
 * [����1] ���� �⺻���� SingleTon Pattern
 * private static�� �̿��ؼ� static�� ���� Ŭ���� ������ �ν��Ͻ�ȭ�� ������� ��밡���ϰԵ�.
 * 
 * 1. �����ڸ� private�� �����.
 *  
 * EagerInitialization();���� ���� Ŭ������ load �Ǵ� ������ �ν��Ͻ��� ������Ű�µ� �̸����� �δ㽺���� ���� �ִ�. 
 * ���� �� �ҽ��� EagerInitialization Ŭ������ �ν��Ͻ�ȭ �Ǵ� ������ ��� ����ó���� �� ���� ����.
 */
public class EagerInitialization 
{
		// private static �� ����.
		private static EagerInitialization instance = new EagerInitialization();
		
		// ������
		private EagerInitialization () 
		{
			System.out.println( "call EagerInitialization constructor." );
		}
		// ��ȸ method
		public static EagerInitialization getInstance () 
		{
			return instance;
		}
		
		public void print () 
		{
			System.out.println("It's print() method in EagerInitialization instance.");
			System.out.println("instance hashCode > " + instance.hashCode());
		}
}
