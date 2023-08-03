class VariableName{

static String name ="sankalp";  //static variable

//method
void display(){
//local variable
char my='m';
int num=21;
System.out.println(my+" "+num);

}
long phone=665265352;     //instance variable

public static void main(String args[]){

//creating Object
VariableName obj=new VariableName();
obj.display();  //calling object
System.out.println(obj.phone);   //calling instance variable
System.out.println(VariableName.name);   //calling static variable



 
}
}