package com.intTest;

import java.util.ArrayList;
import java.util.List;

public class Demo4 {
    public static void main(String[] args) {
        Integer currPage=1;
        List<Integer> ocrList = new ArrayList<>();
        ocrList.add(currPage);
        ocrList.add(currPage + 1);
        System.out.println(ocrList);
    }
}
