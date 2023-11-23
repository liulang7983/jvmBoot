package model.abstractFactory;

/**
 * @author ming.li
 * @date 2023/11/22 14:50
 */
public interface GUIFactory {
    //抽象工厂模式
    Button createButton();
    Checkbox createCheckbox();
}
