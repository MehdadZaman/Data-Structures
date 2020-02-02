package cars;

public class substringtester {
	public static void main(String[] args)
	{
		String test = "ooooooheooheheahe";
		String[] returnStrings = test.split("he");
		System.out.println(returnStrings.length);
		for(int i = 0; i < returnStrings.length; i++)
		{
			System.out.println(returnStrings[i]);
		}
	}
}
