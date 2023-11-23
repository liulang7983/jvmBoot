package model.singleton;

/**
 * @author ming.li
 * @date 2023/11/22 15:27
 */
/**
 * @{NAME}
 * @Description TODO
 * @Author luocong
 * @Date
 * @Version 1.0
 *
 * 单例写法
 * 双重判断式
 **/
public class SignletonThreadTwo {

    //1. 私有的静态的对象
    private static SignletonThreadTwo signletonThreadTwo;

    //2. 私有的构造方法
    private SignletonThreadTwo(){}

    //3. 公共的静态的实例方法 在if里面加上锁synchronized 在锁块中继续判断是否为空
    public static SignletonThreadTwo getInstance(){
        if (signletonThreadTwo==null){
            synchronized (SignletonThreadTwo.class){
                if(signletonThreadTwo==null){
                    signletonThreadTwo=new SignletonThreadTwo();
                }
            }
        }
        return signletonThreadTwo;
    }

    //测试方法
    public static void main(String[] args) {
        //利用for循环 模拟多线程环境调用
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                //看每次获取对象的hashcode是否一致 判断是否获取了同一个对象
                System.out.println("获取的hashCode是： "+SignletonThreadTwo.getInstance().hashCode());
            }).start();
        }
    }
}
