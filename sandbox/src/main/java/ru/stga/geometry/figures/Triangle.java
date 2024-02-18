package ru.stga.geometry.figures;

import java.util.Objects;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        if (a <0 || b <0 || c <0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if (a+b <=c || a+c<=b || b+c<=a ) {
            throw new IllegalArgumentException("Sum of two sides shouldn't be less than length of 3rd side");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        var p = perimeter()/2;
        var d = Math.sqrt(p * (p - a) * (p - b) * (p- c));
        return d;
    }

    public double perimeter() {
        var a = this.a + this.b + this.c;
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
