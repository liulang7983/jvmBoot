package model.strategy;

/**
 * @author ming.li
 * @date 2023/11/22 15:39
 */
/**
 * @{NAME}
 * @Description TODO
 * @Author luocong
 * @Date
 * @Version 1.0
 * 实现类2 实现接口中定义的计算价格方法
 * VIP2会员类 打8折
 **/
public class Vip2Person implements StrategyInt {

    @Override
    public double getPrice(double price, int n) {
        System.out.println("VIP2打8折.....");
        return (price*n)*0.8;
    }
}
