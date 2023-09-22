package activity;

import java.util.Scanner;

public class SmallestAndLargest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// array to store 10 integers
		int[] numbers = new int[10];
		
		//user to input ten integers
		System.out.println("Enter ten integers:");
		
		for(int i=0; i<10; i++) {
			System.out.println("Enter integer " + (i + 1) + ":");
			numbers[i] = scan.nextInt();
		}
		//Find smallest and largest values in array
		int smallest = numbers[0];
		int largest = numbers[0];
		
		for(int i=1; i<10; i++) {
		if(numbers[i] < smallest) {
			smallest = numbers[i];
		}
		if(numbers[i] > largest) {
			largest = numbers[i];
		}
		}
		// samllest and largest values
		System.out.println("Smallest value: " + smallest);
		System.out.println("Largest value: " + largest);
	}

}
