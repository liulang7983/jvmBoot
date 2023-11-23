package model.factory;

/**
 * @author ming.li
 * @date 2023/11/22 14:37
 */
public class ShapeFactory {
    // 使用getShape方法获取形状类型的对象
    public Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        if (shapeType.equalsIgnoreCase("Circle")) {
            return new Circle();
        }
        // other shape types...
        return null;
    }
}
