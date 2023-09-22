package activity;

import java.util.Scanner;

public class SumAndAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an array to store five integers
        int[] numbers = new int[5];

        //  user to input five integers
        System.out.println("Enter five integers:");

        
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }

        // Calculate the sum of the integers
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += numbers[i];
        }

        // Calculate the average of the integers
        double average = (double) sum / 5;

        // Display the sum and average
        System.out.println("Sum of the integers: " + sum);
        System.out.println("Average of the integers: " + average);

       
    }
}
