import java.util.Scanner; //import scanner class

class NestedIfElse{
	
	static void calc_passfail(int marks){
		if(marks >=1 && marks<=99) { //outer condition
			//inner condition
			if(marks>=35){ //execute if condition1 is true
				System.out.println("You are Pass... Congrats "); //print inner if true
			}
			//inner condition else
			else{
				System.out.println("You are Fail.... Better luck next time "); //print inner if false
			}
		}
		//outer condition else
		else{
			System.out.println("Absent or Incorrect marks....");
		}
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in); //object of Scanner
		System.out.println("Enter marks");
		int marks = sc.nextInt(); //read user input
		
		calc_passfail(marks); //calling static method
	}
}