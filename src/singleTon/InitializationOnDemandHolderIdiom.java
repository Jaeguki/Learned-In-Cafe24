package singleTon;

/*[����5] ���� �̱��� ������ ������ �ذ��ϱ� ���� ���ο� �̱��� ����

* jvm�� class loader�� ��Ŀ����� class�� load������ �̿��Ͽ� ���� Ŭ������ ������Ŵ����
* thread���� ����ȭ������ �ذ���.
* lazy initializaition�� �����ϸ�, ��� java������, jvm���� ����� ������.
* ���� java���� singleton�� ������Ų�ٰ��ϸ� ���� �Ʒ��� ����� ����Ѵٰ� �����.
* 
*/
public class InitializationOnDemandHolderIdiom 
{
	private InitializationOnDemandHolderIdiom () {}
	private static class Singleton 
	{
		private static final InitializationOnDemandHolderIdiom instance = new InitializationOnDemandHolderIdiom();
	}
	
	public static InitializationOnDemandHolderIdiom getInstance () 
	{
		System.out.println("create instance");
		return Singleton.instance;
	}
}
