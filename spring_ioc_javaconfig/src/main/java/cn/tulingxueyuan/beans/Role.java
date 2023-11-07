package cn.tulingxueyuan.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
//@DependsOn("user")  depends-on
//@Lazy  ==lazy-init
//@Scope("prototype")
public class Role {
    @Value("管理员")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role() {
        System.out.println("Role已加载!");
    }
/*
    // 生命周期回调-3  初始化回调
    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }

    // 生命周期回调-3  销毁回调
    @PreDestroy
    public  void destory(){
        System.out.println("销毁");
    }*//*public Role() {
        System.out.println("Role已加载!");
    }

    // 生命周期回调-3  初始化回调
    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }

    // 生命周期回调-3  销毁回调
    @PreDestroy
    public  void destory(){
        System.out.println("销毁");
    }*/
}
