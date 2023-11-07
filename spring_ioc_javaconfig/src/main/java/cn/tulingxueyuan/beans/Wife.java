package cn.tulingxueyuan.beans;

import org.springframework.beans.factory.annotation.Value;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class Wife {
    @Value("迪丽热巴")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
