package activity;

import java.util.Scanner;

public class AddTwoNumbers {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter First Number: ");
		
		double num1 = scan.nextDouble();
		
		System.out.println("Enter Second Number: ");
		
		double num2 = scan.nextDouble();
		
		double sum = num1 + num2;
		
		System.out.println("The sum of " + num1 + " and " + num2 + " is: " + sum);

	}

}
