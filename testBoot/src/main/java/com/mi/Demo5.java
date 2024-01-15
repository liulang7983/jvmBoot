package com.mi;

public class Demo5 {
    public static void main(String[] args) {
        String filePath="/hexdata/exdoc/medical/20240112/691010/60201b3f20/formatted/1.jpg";
        String substring = filePath.substring(0, filePath.lastIndexOf("."));
        System.out.println(substring);
        String substring1 = filePath.substring(filePath.lastIndexOf("."));
        System.out.println(substring1);
    }
}
