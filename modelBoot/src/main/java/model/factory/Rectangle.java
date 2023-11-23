package model.factory;

/**
 * @author ming.li
 * @date 2023/11/22 14:36
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
