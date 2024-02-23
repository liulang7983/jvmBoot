package test;

public class Demo4 {
    //给定一个数组arr，其中只可能含有0、1、2三个值，请实现arr的排序
    public static void main(String[] args) {
        int[] array={40,10,30,20,40};
        for(int i=1;i<array.length;i++){
            //比较相邻两个元素，较大的元素往后冒泡
            for(int j=0;j<array.length-i;j++){
                if(array[j]>array[j+1]){
                    int temp=array[j];  //把第一个元素值保留到临时变量中
                    array[j]=array[j+1];  //把第二个元素的值保存到第一个元素单位中
                    array[j+1]=temp;   //把临时变量（第一个元素原值）保存到第二个元素单位中
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
