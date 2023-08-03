import java.util.Scanner;

class IfElse{

static void calc_passfail(int marks){
if(marks>=35){
System.out.println("pass");

}else{
System.out.println("fail");

}
}
public static void main(String args[]){
Scanner sc= new Scanner(System.in);
System.out.println("enter the marks");
int marks=sc.nextInt();

calc_passfail(marks);
}

}