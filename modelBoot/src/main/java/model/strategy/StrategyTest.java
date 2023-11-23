package model.strategy;

/**
 * @author ming.li
 * @date 2023/11/22 15:40
 */
/**
 * @{NAME}
 * @Description TODO
 * @Author luocong
 * @Date
 * @Version 1.0
 * 测试类  演示策略模式的使用场景
 **/
public class StrategyTest {

    public static void main(String[] args) {
        //定义三个类型的对象
        NormalPerson normalPerson = new NormalPerson();
        Vip1Person vip1Person = new Vip1Person();
        Vip2Person vip2Person = new Vip2Person();
        //new context类对象 将三个类型的对象传入
        PersonContext npersonContext = new PersonContext(normalPerson);
        PersonContext v1personContext = new PersonContext(vip1Person);
        PersonContext v2personContext = new PersonContext(vip2Person);
        //利用多态 通过调用context类对象的计算价格方法 实际上调用的子类的计算价格方法 得到最终价格
        System.out.println("普通会员: "+npersonContext.getPrice(300,20));
        System.out.println("VIP1: "+v1personContext.getPrice(300,20));
        System.out.println("VIP2: "+v2personContext.getPrice(300,20));
    }
}
