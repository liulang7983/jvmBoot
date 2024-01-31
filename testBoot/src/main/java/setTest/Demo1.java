package setTest;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Demo1 {
    public static void main(String[] args) {
        Set<Integer> set=new TreeSet<>();
        set.add(43);
        set.add(34);
        set.add(5);
        set.add(9);
        set.add(9);
        ArrayList<Integer> list = new ArrayList<>(set);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
        int a=2;
        int b=4;
        System.out.println((double) a/b>0.4);
    }
}
