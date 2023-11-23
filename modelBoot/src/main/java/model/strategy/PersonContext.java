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
 * 上下文类 对实现类和接口进行封装
 **/
public class PersonContext {
    //1. 定义私有对象
    private StrategyInt strategyInt;

    //2. 定义有参构造方法
    public PersonContext(StrategyInt strategyInt) {
        this.strategyInt = strategyInt;
    }

    //3. 定义计算价格的方法
    public double getPrice(double price,int n){
        return strategyInt.getPrice( price, n);
    }


}
