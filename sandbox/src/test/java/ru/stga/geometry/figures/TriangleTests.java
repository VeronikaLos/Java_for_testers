package ru.stga.geometry.figures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
     void canCalculateArea() {
        Assertions.assertEquals(12.0, new Triangle(5.0, 6.0, 5.0).area());
        Assertions.assertEquals(14.696938456699069, new Triangle(5.0, 6.0, 7.0).area());
        Assertions.assertEquals(10.825317547305483, new Triangle(5.0, 5.0, 5.0).area());
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(16.0, new Triangle(5.0, 6.0, 5.0).perimeter());
        Assertions.assertEquals(18.0, new Triangle(5.0, 6.0, 7.0).perimeter());
        Assertions.assertEquals(15.0, new Triangle(5.0, 5.0, 5.0).perimeter());
    }
}
