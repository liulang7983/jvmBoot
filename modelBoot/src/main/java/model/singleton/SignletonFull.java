package model.singleton;

/**
 * @author ming.li
 * @date 2023/11/22 15:21
 */
/**
 * @{NAME}
 * @Description TODO
 * @Author luocong
 * @Date
 * @Version 1.0
 *
 * 单例模式 懒汉模式
 * 调用实例方法时才new对象
 * 节省空间 缺点是线程不安全
 **/
public class SignletonFull {

    //1. 私有的静态的对象 先不new 默认为null值
    private static SignletonFull signletonFull;

    //2. 私有的无参构造器
    private SignletonFull(){}

    //3. 公共的静态的方法
    public static SignletonFull getInstance() throws InterruptedException {
        if(signletonFull==null){
            Thread.sleep(1000);
            signletonFull=new SignletonFull();
        }
        return signletonFull;
    }

    //测试方法
    public static void main(String[] args) {
        //利用for循环 模拟多线程环境调用
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                //看每次获取对象的hashcode是否一致 判断是否获取了同一个对象
                try {
                    System.out.println("获取的hashCode是： "+SignletonFull.getInstance().hashCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
