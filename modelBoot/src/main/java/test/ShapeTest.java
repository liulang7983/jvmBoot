package test;

import model.factory.Shape;
import model.factory.ShapeFactory;

/**
 * @author ming.li
 * @date 2023/11/22 14:34
 */
public class ShapeTest {
    public static void main(String[] args) {
        Shape rectangle = new ShapeFactory().getShape("Rectangle");
        rectangle.draw();
        Shape circle = new ShapeFactory().getShape("Circle");
        circle.draw();
    }
}
