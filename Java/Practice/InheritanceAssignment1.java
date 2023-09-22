package activity;

    class Vehicle{
    	String brand;
    	String model;
    	int year;
    	
    	public Vehicle(String brand, String model, int year) {
    		this.brand = brand;
    		this.model = model;
    		this.year = year;
    	}
    	
    	public void drive() {
    		System.out.println("The vehicle is driving.");
    	}
    }
    
    class Car extends Vehicle {
    	String color;
    	
    	public Car(String brand, String model, int year, String color) {
    		super(brand, model,year);
    		this.color = color;
    	}
    	public void honk() {
    		System.out.println("The car is honking.");
    		
    	}
    }
    
    
public class InheritanceAssignment1 {

	public static void main(String[] args) {
		
      //create object of the car class
		Car myCar = new Car("Toyota", "Camry", 2023 ,"Red");
		
		myCar.drive(); //call method from parent class
		myCar.honk(); //call method from child class
	}

}
