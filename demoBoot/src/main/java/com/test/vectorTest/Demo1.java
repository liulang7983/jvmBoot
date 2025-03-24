package com.test.vectorTest;

import java.util.Vector;

/**
 * @Author ming.li
 * @Date 2025/3/4 14:04
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        Vector<Integer> vector=new Vector();
        vector.add(1);
        vector.add(2);
        vector.add(9);
        for(Integer x:vector){
            System.out.println(x);
        }
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i));
        }
    }
}
