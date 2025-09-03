package test;

/**
 * 装饰器模式
 * @Author ming.li
 * @Date 2025/9/3 14:06
 * @Version 1.0
 */
// 咖啡接口
interface Coffee {
    String getDescription();
    double getCost();
}

// 基础咖啡：黑咖啡
class BlackCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Black Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}

// 装饰器抽象类
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

// 具体装饰器：牛奶
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

// 具体装饰器：糖
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.3;
    }
}

// 具体装饰器：奶泡
class FoamDecorator extends CoffeeDecorator {
    public FoamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Foam";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.7;
    }
}

// 使用示例
class DecoratorDemo {
    public static void main(String[] args) {
        // 基础咖啡
        Coffee coffee = new BlackCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        // 加牛奶的咖啡
        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.println(milkCoffee.getDescription() + " $" + milkCoffee.getCost());

        // 加牛奶和糖的咖啡
        Coffee milkSugarCoffee = new SugarDecorator(new MilkDecorator(coffee));
        System.out.println(milkSugarCoffee.getDescription() + " $" + milkSugarCoffee.getCost());

        //加两份糖
        Coffee ugarCoffee = new SugarDecorator(new SugarDecorator(coffee));
        System.out.println(ugarCoffee.getDescription() + " $" + ugarCoffee.getCost());

        // 加所有配料的咖啡
        Coffee fullCoffee = new FoamDecorator(new SugarDecorator(new MilkDecorator(coffee)));
        System.out.println(fullCoffee.getDescription() + " $" + fullCoffee.getCost());
    }
}

