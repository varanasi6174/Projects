package activity;

interface Person {
    void speak();
}
class Student implements Person {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void speak() {
        System.out.println("Student " + name + " says: Hello, I'm a student.");
    }
}

class Teacher implements Person {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void speak() {
        System.out.println("Teacher " + name + " says: Good morning, I'm a teacher.");
    }
}

public class InterfaceAssignment2 {
    public static void main(String[] args) {
     
        Student student = new Student("Alice");
        student.speak();

        Teacher teacher = new Teacher("Mr. Smith");
        teacher.speak();
    }
}
