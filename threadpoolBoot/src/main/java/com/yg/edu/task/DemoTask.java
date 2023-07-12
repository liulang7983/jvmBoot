package com.yg.edu.task;

import java.util.concurrent.Callable;

/**
 * @author ming.li
 * @date 2023/7/12 9:43
 */
public class DemoTask implements Callable<String> {

    private String name;
    private Integer type;
    private Integer number;

    public DemoTask(String name, Integer type, Integer number) {
        this.name = name;
        this.type = type;
        this.number = number;
    }

    @Override
    public String call() throws Exception {
        String type = getType(this.type);
        Thread.sleep(2000);
        String str=name+"是第："+number+"个数字，口号是:"+type;
        return str;
    }

    private String getType(Integer type){
        String str="";
        switch (type){
            case 0:
              str="我是0,我要归零";
              break;
            case 1:
                str="我是1,我要独一无二";
                break;
            case 2:
                str="我是2,我要绝无二心";
                break;
        }

        return str;
    }
}
