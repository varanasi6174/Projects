class OperatorExample{


//static method
static void calc_arithmatic(){
int num1=40,num2=80;
System.out.println("addition is "+ (num1+num2));
System.out.println("subtraction is "+ (num1-num2));
System.out.println("multiplication is "+ (num1*num2));
System.out.println("division is "+ (num1/num2));

}
//static method
static void calc_relational(){
double num1=40.20,num2=80.20;
System.out.println("greater number is "+ (num1>num2));
System.out.println("lesser is "+ (num1>num2));
}

//static method
static void calc_assignment(){
int num1=400;
num1+=20;     //400+20
System.out.println(" "+ num1);
int num2=800;
num2-=20;
System.out.println(" "+ num2);
}

//static method
static void calc_unary(){
int num=6;
System.out.println(num++);
System.out.println(--num);
System.out.println(num--);
System.out.println(++num);
System.out.println(num++);
System.out.println(num);


}

//static method
static void calc_ternary(){
int num1=30, num2=40;
int res=(num1>num2)?num1:num2;
System.out.println(res);
}

//static method
static void calc_logical(){
double num1=20.34, num2=28.70, num3=35.56;
System.out.println(num1<num2 || num1>num3);
System.out.println(num1<num2 && num1>num3);


}


public static void main(String args[]){
//calling static method with classname
OperatorExample.calc_arithmatic();
OperatorExample.calc_relational();
OperatorExample.calc_assignment();
OperatorExample.calc_unary();
OperatorExample.calc_ternary();
OperatorExample.calc_logical();


}

}

