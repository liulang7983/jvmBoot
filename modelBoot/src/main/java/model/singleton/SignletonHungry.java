package model.singleton;

/**
 * @author ming.li
 * @date 2023/11/22 15:01
 */
/**
 * @{NAME}
 * @Description TODO
 * @Author luocong
 * @Date
 * @Version 1.0
 * 单例模式 饿汉模式
 *  上来就new对象
 **/
public class SignletonHungry {

    //1. 私有的静态的最终的对象
    private static final SignletonHungry singl=new SignletonHungry();

    //2. 私有的无参构造函数
    private SignletonHungry(){

    }
    //3. 公共的静态的实例方法
    public static SignletonHungry getInstance(){
        return singl;
    }

    //测试方法
    public static void main(String[] args) {
        //利用for循环 模拟多线程环境调用
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                //看每次获取对象的hashcode是否一致 判断是否获取了同一个对象
                System.out.println("获取的hashCode是： "+SignletonHungry.getInstance().hashCode());
            }).start();
        }
    }
}
