package coreJava;

public class StringReverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "madam";
		String outStr = "";

		for(int i = str.length()-1;i>=0; i--)
		{
			outStr = outStr + str.charAt(i);
		}
		System.out.println(outStr);
		
		if(str.equalsIgnoreCase(outStr))
		{
			System.out.println("The String is palindrom");
		}
		else
		{
			System.out.println("The String is reversed and not a palindrom number");
		}

	}

}
