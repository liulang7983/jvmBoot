package com.stringTest;

import com.util.SensitiveInfoUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ming.li
 * @date 2023/8/1 15:56
 */
public class Demo11 {
    // 身份证号码正则表达式
    private static final String ID_CARD_REGEX = "(\\d{4})\\d{10}(\\w{4})";

    public static void main(String[] args) {
        //System.out.println("身份证:"+SensitiveInfoUtils.desensitize("362201199401224817"));
        //System.out.println("手机号:"+SensitiveInfoUtils.desensitize("18070157983"));
        //System.out.println("发票号:"+SensitiveInfoUtils.desensitize("94018343"));
        //System.out.println("发票号:"+SensitiveInfoUtils.desensitize("033002000411"));
        //System.out.println("全电:"+SensitiveInfoUtils.desensitize("22317000000001586581"));
        //System.out.println("地址和电话:"+SensitiveInfoUtils.desensitize("三门县浦坝港镇洞港工业集聚区13757699959"));
        //System.out.println(masking("三门县浦坝港镇洞港工业集聚区13757699959"));
        //System.out.println("新的替换方式:"+SensitiveInfoUtils.dataDesensitization(3,5,"今天天气可以的"));
        //System.out.println("电话替换方式:"+SensitiveInfoUtils.identify("三门县浦坝港镇洞港工业集聚区13757699959"));
        //System.out.println("电话替换方式:"+SensitiveInfoUtils.identify("台州市路桥区金清镇卷桥村同心路47号021-64958718"));
        //System.out.println("电话替换方式:"+SensitiveInfoUtils.identify("山东曹县青岛南路中段188号0530-3316918"));
        System.out.println("身份证:"+SensitiveInfoUtils.identify("362201199401224"));
        System.out.println("发票:"+SensitiveInfoUtils.identify("2222222222222222222"));
    }

    public static Boolean masking(String str){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
        Matcher m = p.matcher(str);
        return m.matches();

    }
}
