package extendsTest;

/**
 * @author ming.li
 * @date 2023/11/24 9:47
 */
public class TestExtend {
    public static void main(String[] args) {
        Dog dog = new Dog();
        // dog类中并没有定义任何成员变量，name和age属性肯定是从父类Animal中继承下来的
        System.out.println(dog.name);
        System.out.println(dog.age);
        // dog访问的eat()和sleep()方法也是从Animal中继承下来的
        dog.eat();
        dog.sleep();
        dog.bark();
    }
}
