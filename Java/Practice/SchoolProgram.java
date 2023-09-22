package activity;

import java.util.Scanner;

class Subject {
    private String name;
    private int marks;

    public Subject(String name) {
        this.name = name;
        this.marks = 0; 
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}

class Student {
    private String name;
    private String rollNumber;
    private Subject[] subjects;

    public Student(String name, String rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjects = new Subject[2];
        subjects[0] = new Subject("Maths");
        subjects[1] = new Subject("English");
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public Subject[] getSubjects() {
        return subjects;
    }
}

class Teacher {
    public void setMarks(Student student, int[] marks) {
        Subject[] subjects = student.getSubjects();
        for (int i = 0; i < subjects.length; i++) {
            subjects[i].setMarks(marks[i]);
        }
    }
}

public class SchoolProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Student[] students = new Student[10];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student("Student" + (i + 1), "Roll" + (i + 1));
        }

        Teacher teacher = new Teacher();

        // Take input from the teacher to set marks for 10 students
        for (int i = 0; i < students.length; i++) {
            System.out.println("Enter marks for " + students[i].getName() + " (" + students[i].getRollNumber() + "):");
            int[] marks = new int[2];
            for (int j = 0; j < marks.length; j++) {
                System.out.print(students[i].getSubjects()[j].getName() + ": ");
                marks[j] = scanner.nextInt();
            }
            teacher.setMarks(students[i], marks);
        }

        // Calculate and display the average marks
        int totalMarks = 0;
        for (Student student : students) {
            for (Subject subject : student.getSubjects()) {
                totalMarks += subject.getMarks();
            }
        }
        double averageMarks = (double) totalMarks / (students.length * 2);
        System.out.println("Average Marks: " + averageMarks);

        // Find and display the maximum and minimum marks
        int maxMarks = Integer.MIN_VALUE;
        int minMarks = Integer.MAX_VALUE;
        for (Student student : students) {
            for (Subject subject : student.getSubjects()) {
                int marks = subject.getMarks();
                maxMarks = Math.max(maxMarks, marks);
                minMarks = Math.min(minMarks, marks);
            }
        }
        System.out.println("Maximum Marks: " + maxMarks);
        System.out.println("Minimum Marks: " + minMarks);
    }
}
