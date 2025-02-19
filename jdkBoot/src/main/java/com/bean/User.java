package com.bean;

/**
 * @author ming.li
 * @date 2023/4/26 11:17
 */
public class User {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void test(){
        System.out.println("=======自己的加载器加载类调用方法=======");
    }
    @Override
    protected void finalize() throws Throwable {
        //OOMTest.list.add(this);
        System.out.println("关闭资源，userid=" + id + "即将被回收");
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public User() {

    }
}
