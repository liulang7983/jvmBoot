package com.bean;

/**
 * @Author ming.li
 * @Date 2024/8/2 9:14
 * @Version 1.0
 */
public class Model {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Model getModel(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Model model = new Model();
        model.setId(1);
        return model;
    }
}
