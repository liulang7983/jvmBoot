package model.factory;

/**
 * @author ming.li
 * @date 2023/11/22 14:42
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
