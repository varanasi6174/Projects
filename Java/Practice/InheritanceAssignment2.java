package activity;

class Person {
   String name;
     int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void speak() {
        System.out.println(name + " says: Hello!");
    }
}

class Student extends Person {
    int grade;

    public Student(String name, int age, int grade) {
        super(name, age);
        this.grade = grade;
    }

    public void study() {
        System.out.println( name + " is studying hard to get a good grade in grade " + grade );
    }
}

public class InheritanceAssignment2{
    public static void main(String[] args) {
        // Create a Student object
        Student student1 = new Student("Alice", 17, 12);

        // Call the speak method of the Person class 
        student1.speak();

        // Call the study method of the Student class
        student1.study();
    }
}
