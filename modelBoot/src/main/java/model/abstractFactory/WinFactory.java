package model.abstractFactory;

/**
 * @author ming.li
 * @date 2023/11/22 14:55
 */
public class WinFactory implements GUIFactory {
    // 返回WinButton类的实例
    @Override
    public Button createButton() {
        return new WinButton();
    }

    // 返回WinCheckbox类的实例
    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

