package model.strategy;

/**
 * @author ming.li
 * @date 2023/11/22 15:38
 */

/**
 * @Author luocong
 * @Description //TODO
 * @Date 12:20 2022/11/8
 * @Param
 * @return
 * 策略模式
 * 定义策略接口
 * 案例场景：
 *  有三种会员 购买相同数量和单价的产品时 需要打不同的折扣
 **/
public interface StrategyInt {

    //price价格 n数量
    public double getPrice(double price,int n);
}
