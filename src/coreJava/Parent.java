package coreJava;

public class Parent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(" I Started the JAVA Learning");
		
		
		//Add two numbers
		
//		int a=2;
//		int b=3;
//		int sum = a+b;
//		System.out.println("The Sum of two number "+ a +  " And " + b + " is: "+  sum);
		
		System.out.println("I Navigated to the Home Page");
		
		//Calling method from another class
		Methods b2 = new Methods();
		b2.ValidateHeader();
		System.out.println(b2.returnOutput());
	}

}
