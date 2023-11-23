package model.strategy;

/**
 * @author ming.li
 * @date 2023/11/22 15:38
 */
/**
 * @{NAME}
 * @Description TODO
 * @Author luocong
 * @Date
 * @Version 1.0
 * 实现类1 实现接口中定义的计算价格方法
 * 普通会员类 不打折
 **/
public class NormalPerson implements StrategyInt {

    //普通会员不打折
    @Override
    public double getPrice(double price, int n)
    {
        System.out.println("普通会员不打折.....");
        return (price*n);
    }
}
