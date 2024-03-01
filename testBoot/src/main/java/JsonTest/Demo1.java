package JsonTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author ming.li
 * @Date 2024/2/27 11:06
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("1","1");
        String s = jsonObject.toString();
        System.out.println(JsonUtils.isJsonArray(s));
        System.out.println(JsonUtils.isJsonObject(s));
        JSONArray array=new JSONArray();
        array.add(jsonObject);
        String s1 = array.toString();
        System.out.println(JsonUtils.isJsonArray(s1));
        System.out.println(JsonUtils.isJsonObject(s1));
    }
}
