package activity;

abstract class Shape {
    abstract double calculateArea();
}

class Circle extends Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double calculateArea() {
        return length * width;
    }
}

public class AbstractClass1 {
    public static void main(String[] args) {
        // Create a Circle object and calculate its area
        Circle circle = new Circle(5.0);
        double circleArea = circle.calculateArea();
        System.out.println("Area of Circle: " + circleArea);

        // Create a Rectangle object and calculate its area
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        double rectangleArea = rectangle.calculateArea();
        System.out.println("Area of Rectangle: " + rectangleArea);
    }
}
