package stringTest;

/**
 * @Author ming.li
 * @Date 2024/12/5 10:41
 * @Version 1.0
 */
public class Demo3 {
    public static void main(String[] args) {
        String itemString="广西壮族自治区男上市xxxxxxxx省";
        if (itemString.contains("省") && itemString.contains("市")) { //提取省~市之间的信息
            if (itemString.indexOf("省")<itemString.indexOf("市")){
              String  cityInfo = itemString.substring(itemString.indexOf("省") + 1, itemString.indexOf("市") + 1);
            }
        }

    }
}
