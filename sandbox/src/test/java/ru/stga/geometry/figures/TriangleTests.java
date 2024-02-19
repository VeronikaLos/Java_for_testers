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

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-5.0, 3.0, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithIncorrectSideLength() {
        try {
            new Triangle(2.0, 2.0, 6);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void testEquality() {
        var r1 = new Triangle(5.0, 4.0, 3.0);
        var r2 = new Triangle(5.0, 4.0, 3.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality2() {
        var r1 = new Triangle(4.0, 3.0, 5.0);
        var r2 = new Triangle(5.0, 4.0, 3.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality3() {
        var r1 = new Triangle(3.0, 5.0, 4.0);
        var r2 = new Triangle(5.0, 4.0, 3.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality4() {
        var r1 = new Triangle(2.0, 3.0, 4.0);
        var r2 = new Triangle(2.0, 4.0, 3.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality5() {
        var r1 = new Triangle(2.0, 3.0, 4.0);
        var r2 = new Triangle(4.0, 3.0, 2.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testNonEquality() {
        var r1 = new Triangle(5.0, 4.0, 5.0);
        var r2 = new Triangle(5.0, 4.0, 4.0);
        Assertions.assertNotEquals(r1, r2);
    }

}
