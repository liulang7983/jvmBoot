package test;

/**
 * @author 李明
 * @date Created in 2024/2/26 23:36
 */
public class Demo26_1 {
    /*给定一个整形数组arr，返回排序后相邻两数的最大差值
    arr = [9, 3, 1, 10]。如果排序，结果为[1, 3, 9, 10]，9和3的差为最大差值，故返回6。
    arr = [5, 5, 5, 5]。返回0*/
    public static void main(String[] args) throws Exception {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String[] ss = br.readLine().trim().split(" ");*/
        int len=4;
        int[]  array = {9,3, 1, 10};
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
        int max=0;
        for (int i = 1; i < array.length; i++) {
            int i1 = array[i] - array[i - 1];
            if (i1>max){
                max=i1;
            }
        }
        System.out.println(max);
    }






}
