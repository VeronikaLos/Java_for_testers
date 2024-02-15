package ru.stga.geometry.figures;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
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
}
