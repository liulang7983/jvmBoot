package model.singleton;

/**
 * @author ming.li
 * @date 2023/11/22 15:29
 */
/**
 * @{NAME}
 * @Description TODO
 * @Author luocong
 * @Date
 * @Version 1.0
 * 单例模式
 * 通过静态内部类实现懒加载与线程安全
 * 利用JVN特性实现 JVM在加载类和内部类的时候 只会在运行的时候加载一次 从而保证线程安全和懒加载
 **/
public class SignletonStaticClass {

    //1. 私有的无参构造器
    private SignletonStaticClass(){}

    //2. 私有的静态的内部类
    private static class SignletonStatic{
        //3. 在私有的内部类中定义私有的 最终的 静态的对象
        private final static SignletonStaticClass signletonStaticClass=new SignletonStaticClass();
    }

    //4. 公共的静态的实例方法
    public static SignletonStaticClass getInstance(){
        return SignletonStatic.signletonStaticClass;
    }

    //测试方法
    public static void main(String[] args) {
        //利用for循环 模拟多线程环境调用
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                //看每次获取对象的hashcode是否一致 判断是否获取了同一个对象
                System.out.println("获取的hashCode是： "+SignletonStaticClass.getInstance().hashCode());
            }).start();
        }
    }

}
