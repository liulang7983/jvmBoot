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
 *实现类2 实现接口中定义的计算价格方法
 *VIP1会员 打9折
 **/
public class Vip1Person implements StrategyInt{

    //VIP1客户 打9折
    @Override
    public double getPrice(double price, int n) {
        System.out.println("VIP1打9折.....");
        return (price*n)*0.9;
    }
}
