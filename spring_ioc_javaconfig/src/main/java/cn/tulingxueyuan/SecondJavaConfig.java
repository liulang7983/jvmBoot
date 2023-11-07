package cn.tulingxueyuan;

import cn.tulingxueyuan.beans.Person;
import cn.tulingxueyuan.beans.User;
import cn.tulingxueyuan.beans.Wife;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Configuration
public class SecondJavaConfig {
    @Bean
    public Person person(){
        Wife wife= wife();
        return new Person();
    }
    @Bean
    public Wife wife(){
        return new Wife();
    }
}
