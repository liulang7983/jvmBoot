package com.dataMasking;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.SensitiveInfoUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ming.li
 * @date 2023/8/3 19:08
 */
public class Demo1 {
    public static void main(String[] args) {
        //增值税发票
        //dataMasking("00000009",getInvoice());
        //印鉴卡
        //dataMasking("00001025",getYJK());
        //送审单
        //dataMasking("00001026",getSSD());
        //证照
        //dataMasking("certificate",getZZXX());
        //营业执照
        //dataMasking("00000008",getYYZZ());
        //出入境记录
        dataMasking("exit_record",getCRJJL());
        //身份证
        //dataMasking("00000001",getSFZ());

    }

    public static String dataMasking(String interfaceCode, String responseMsg){
        JSONObject dataMaskingMap=getJSON();
        JSONObject jsonObject = JSONObject.parseObject(responseMsg);
        Integer errorcode = jsonObject.getInteger("errorcode");
        System.out.println("开始脱敏:" + dataMaskingMap.getString("engine"));
        JSONObject object1 = dataMaskingMap.getJSONObject(interfaceCode);
        String engine = dataMaskingMap.getString("engine");
        if (engine.contains(interfaceCode)) {
            if (errorcode!=null&&errorcode.equals(0)) {
                //身份证
                if (interfaceCode.equals("00000001")) {
                    Set<String> strings = object1.keySet();
                    for (String item : strings) {
                        String pattern = object1.getString(item);
                        String itemstring = jsonObject.getString(item);
                        if (StringUtils.isNotBlank(itemstring)) {
                            String desensitize = SensitiveInfoUtils.desensitize(itemstring, pattern);
                            jsonObject.put(item, desensitize);
                        }
                    }
                }
                //营业执照/发票/印鉴卡
                if (interfaceCode.equals("00000008") || interfaceCode.equals("00000009") || interfaceCode.equals("00001025") || interfaceCode.equals("00001026")) {
                    JSONArray items = jsonObject.getJSONArray("items");
                    if (items!=null&&items.size()>0){
                        for (int i = 0; i < items.size(); i++) {
                            JSONObject object = items.getJSONObject(i);
                            String item = object.getString("item");
                            String pattern = object1.getString(item);
                            if (StringUtils.isNotBlank(pattern)) {
                                String itemstring = object.getString("itemstring");
                                String desensitize = SensitiveInfoUtils.desensitize(itemstring, pattern);
                                object.put("itemstring", desensitize);
                                items.set(i, object);
                            }
                        }
                        jsonObject.put("items", items);
                    }
                }
                //出入境记录
                if (interfaceCode.equals("exit_record")) {
                    JSONArray items = jsonObject.getJSONArray("items");
                    if (items!=null&&items.size()>0){
                        for (int i = 0; i < items.size(); i++) {
                            JSONObject object = items.getJSONObject(i);
                            String item = object.getString("item");
                            String pattern = object1.getString(item);
                            if (StringUtils.isNotBlank(pattern)) {
                                String itemstring = object.getString("itemstring");
                                String desensitize = SensitiveInfoUtils.desensitize(itemstring, pattern);
                                object.put("itemstring", desensitize);
                                items.set(i, object);
                            }
                        }
                        jsonObject.put("items", items);
                    }
                    JSONObject table = jsonObject.getJSONObject("table");
                    JSONArray columns = table.getJSONArray("columns");
                    JSONArray rows = table.getJSONArray("rows");
                    Map<Integer, String> colMap = new HashMap<>();
                    if (columns!=null&&columns.size()>0){
                        for (int i = 0; i < columns.size(); i++) {
                            JSONObject object = columns.getJSONObject(i);
                            String item = object.getString("text");
                            //根据头找寻到需要屏蔽的列以及其屏蔽规则
                            String pattern = object1.getString(item);
                            if (StringUtils.isNotBlank(pattern)) {
                                Integer col = object.getInteger("col");
                                colMap.put(col, pattern);
                            }
                        }
                        if (colMap.size() > 0) {
                            for (int i = 0; i < rows.size(); i++) {
                                JSONArray cells = rows.getJSONObject(i).getJSONArray("cells");
                                for (int j = 0; j < cells.size(); j++) {
                                    JSONObject object = cells.getJSONObject(j);
                                    Integer col = object.getInteger("col");
                                    if (colMap.containsKey(col)) {
                                        String text = object.getString("text");
                                        String desensitize = SensitiveInfoUtils.desensitize(text, colMap.get(col));
                                        object.put("text", desensitize);
                                        cells.set(j, object);
                                    }
                                }
                                rows.set(i, cells);
                            }
                            table.put("rows", rows);
                            jsonObject.put("table", table);
                        }
                    }
                }
                //证照信息识别
                if (interfaceCode.equals("certificate")) {
                    JSONArray mixedItems = jsonObject.getJSONArray("mixedItems");
                    if (mixedItems!=null&&mixedItems.size()>0){
                        for (int i = 0; i < mixedItems.size(); i++) {
                            JSONObject object = mixedItems.getJSONObject(i);
                            JSONArray items = object.getJSONArray("items");
                            for (int j = 0; j < items.size(); j++) {
                                JSONObject object2 = items.getJSONObject(j);
                                String item = object2.getString("item");
                                String pattern = object1.getString(item);
                                if (org.apache.commons.lang3.StringUtils.isNotBlank(pattern)) {
                                    String itemstring = object2.getString("itemstring");
                                    String desensitize = SensitiveInfoUtils.desensitize(itemstring, pattern);
                                    object2.put("itemstring", desensitize);
                                    items.set(j, object2);
                                }
                            }
                            object.put("items", items);
                            mixedItems.set(i, object);
                        }
                        jsonObject.put("mixedItems", mixedItems);
                    }

                }
            } else {
                //出入境记录
                if (interfaceCode.equals("exit_record")) {
                    JSONArray items = jsonObject.getJSONArray("items");
                    if (items != null && items.size() > 0) {
                        for (int i = 0; i < items.size(); i++) {
                            JSONObject object = items.getJSONObject(i);
                            String item = object.getString("item");
                            String pattern = object1.getString(item);
                            if (org.apache.commons.lang3.StringUtils.isNotBlank(pattern)) {
                                String itemstring = object.getString("itemstring");
                                String desensitize = SensitiveInfoUtils.desensitize(itemstring, pattern);
                                object.put("itemstring", desensitize);
                                items.set(i, object);
                            }
                        }
                        jsonObject.put("items", items);
                        JSONObject table = jsonObject.getJSONObject("table");
                        JSONArray columns = table.getJSONArray("columns");
                        JSONArray rows = table.getJSONArray("rows");
                        Map<Integer, String> colMap = new HashMap<>();
                        for (int i = 0; i < columns.size(); i++) {
                            JSONObject object = columns.getJSONObject(i);
                            String item = object.getString("text");
                            //根据头找寻到需要屏蔽的列以及其屏蔽规则
                            String pattern = object1.getString(item);
                            if (org.apache.commons.lang3.StringUtils.isNotBlank(pattern)) {
                                Integer col = object.getInteger("col");
                                colMap.put(col, pattern);
                            }
                        }
                        if (colMap.size() > 0) {
                            for (int i = 0; i < rows.size(); i++) {
                                JSONArray cells = rows.getJSONObject(i).getJSONArray("cells");
                                for (int j = 0; j < cells.size(); j++) {
                                    JSONObject object = cells.getJSONObject(j);
                                    Integer col = object.getInteger("col");
                                    if (colMap.containsKey(col)) {
                                        String text = object.getString("text");
                                        String desensitize = SensitiveInfoUtils.desensitize(text, colMap.get(col));
                                        object.put("text", desensitize);
                                        cells.set(j, object);
                                    }
                                }
                                rows.set(i, cells);
                            }
                            table.put("rows", rows);
                            jsonObject.put("table", table);
                        }
                    }
                }
            }
        }
        System.out.println(jsonObject);
        return jsonObject.toJSONString();
    }

    public static JSONObject getJSON(){
        String str="{\"00001026\":{\"送审单编号\":\"2,-2\"},\"engine\":\"00000001,00000008,00000009,00001025,00001026,exit_record,certificate\",\"exit_record\":{\"姓名\":\"0,1\",\"证件号码\":\"2,-2\"},\"00001025\":{\"印鉴编号\":\"2,-2\"},\"00000001\":{\"address\":\"4,-4\",\"name\":\"0,1\",\"id\":\"4,-4\"},\"certificate\":{\"姓名\":\"0,1\",\"证件号码\":\"2,-2\"},\"00000009\":{\"开票人\":\"1,1\",\"销售方名称\":\"-6\",\"销售方开户行及账号\":\"-6\",\"销售方地址、电话\":\"-6\",\"购买方名称\":\"-6\",\"销售方识别号\":\"-6\",\"购买方开户行及账号\":\"-6\",\"收款人\":\"1,1\",\"购买方地址、电话\":\"-6\",\"发票号码\":\"-6\",\"购买方识别号\":\"-6\",\"发票代码\":\"-6\"},\"00000008\":{\"注册号\":\"4,-4\",\"法定代表人\":\"0,1\",\"公司名称\":\"4,-4\",\"地址\":\"4,-4\"}}";

        return JSONObject.parseObject(str);
    }

    public static String getInvoice(){
        String str="{\n" +
                "    \"errorcode\": 0,\n" +
                "    \"errormsg\": \"OK\",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"item\": \"发票名称\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1055,\n" +
                "                \"y\": 81,\n" +
                "                \"width\": 671,\n" +
                "                \"height\": 72\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999940395355225,\n" +
                "            \"itemstring\": \"浙江增值税普通发票\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1539,\n" +
                "                \"y1\": 1044,\n" +
                "                \"x2\": 1534,\n" +
                "                \"y2\": 1734,\n" +
                "                \"x3\": 1463,\n" +
                "                \"y3\": 1734,\n" +
                "                \"x4\": 1468,\n" +
                "                \"y4\": 1044\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"发票代码\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 496,\n" +
                "                \"y\": 134,\n" +
                "                \"width\": 416,\n" +
                "                \"height\": 62\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999994039535523,\n" +
                "            \"itemstring\": \"3300172320\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1489,\n" +
                "                \"y1\": 486,\n" +
                "                \"x2\": 1486,\n" +
                "                \"y2\": 919,\n" +
                "                \"x3\": 1425,\n" +
                "                \"y3\": 918,\n" +
                "                \"x4\": 1428,\n" +
                "                \"y4\": 486\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"发票号码\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1969,\n" +
                "                \"y\": 120,\n" +
                "                \"width\": 273,\n" +
                "                \"height\": 63\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999955296516419,\n" +
                "            \"itemstring\": \"No07939761\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1494,\n" +
                "                \"y1\": 1959,\n" +
                "                \"x2\": 1492,\n" +
                "                \"y2\": 2249,\n" +
                "                \"x3\": 1430,\n" +
                "                \"y3\": 2249,\n" +
                "                \"x4\": 1432,\n" +
                "                \"y4\": 1959\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"打印发票代码\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 2280,\n" +
                "                \"y\": 150,\n" +
                "                \"width\": 193,\n" +
                "                \"height\": 36\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999991655349731,\n" +
                "            \"itemstring\": \"3300172320\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1462,\n" +
                "                \"y1\": 2274,\n" +
                "                \"x2\": 1460,\n" +
                "                \"y2\": 2476,\n" +
                "                \"x3\": 1425,\n" +
                "                \"y3\": 2476,\n" +
                "                \"x4\": 1427,\n" +
                "                \"y4\": 2274\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"打印发票号码\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 2283,\n" +
                "                \"y\": 200,\n" +
                "                \"width\": 205,\n" +
                "                \"height\": 40\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999969601631165,\n" +
                "            \"itemstring\": \"No07939761\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1412,\n" +
                "                \"y1\": 2275,\n" +
                "                \"x2\": 1410,\n" +
                "                \"y2\": 2491,\n" +
                "                \"x3\": 1371,\n" +
                "                \"y3\": 2491,\n" +
                "                \"x4\": 1373,\n" +
                "                \"y4\": 2275\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"开票日期\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 2160,\n" +
                "                \"y\": 268,\n" +
                "                \"width\": 298,\n" +
                "                \"height\": 41\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999908804893494,\n" +
                "            \"itemstring\": \"2019年01月25日\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1345,\n" +
                "                \"y1\": 2152,\n" +
                "                \"x2\": 1343,\n" +
                "                \"y2\": 2461,\n" +
                "                \"x3\": 1303,\n" +
                "                \"y3\": 2461,\n" +
                "                \"x4\": 1305,\n" +
                "                \"y4\": 2152\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"校验码\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 599,\n" +
                "                \"y\": 305,\n" +
                "                \"width\": 424,\n" +
                "                \"height\": 33\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999997019767761,\n" +
                "            \"itemstring\": \"83940636253822302393\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1318,\n" +
                "                \"y1\": 593,\n" +
                "                \"x2\": 1315,\n" +
                "                \"y2\": 1024,\n" +
                "                \"x3\": 1283,\n" +
                "                \"y3\": 1024,\n" +
                "                \"x4\": 1286,\n" +
                "                \"y4\": 593\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"购买方名称\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 596,\n" +
                "                \"y\": 375,\n" +
                "                \"width\": 501,\n" +
                "                \"height\": 41\n" +
                "            },\n" +
                "            \"itemconf\": 0.999975323677063,\n" +
                "            \"itemstring\": \"南京途牛国际旅行社有限公司\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1248,\n" +
                "                \"y1\": 587,\n" +
                "                \"x2\": 1244,\n" +
                "                \"y2\": 1099,\n" +
                "                \"x3\": 1204,\n" +
                "                \"y3\": 1099,\n" +
                "                \"x4\": 1208,\n" +
                "                \"y4\": 587\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"密码区1\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1617,\n" +
                "                \"y\": 380,\n" +
                "                \"width\": 798,\n" +
                "                \"height\": 41\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999827742576599,\n" +
                "            \"itemstring\": \"3*-<24/8187965+137>>-8>8<99\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1236,\n" +
                "                \"y1\": 1608,\n" +
                "                \"x2\": 1231,\n" +
                "                \"y2\": 2417,\n" +
                "                \"x3\": 1191,\n" +
                "                \"y3\": 2417,\n" +
                "                \"x4\": 1196,\n" +
                "                \"y4\": 1608\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"购买方识别号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 634,\n" +
                "                \"y\": 436,\n" +
                "                \"width\": 612,\n" +
                "                \"height\": 42\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999983310699463,\n" +
                "            \"itemstring\": \"91320102759471481D\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1186,\n" +
                "                \"y1\": 625,\n" +
                "                \"x2\": 1182,\n" +
                "                \"y2\": 1248,\n" +
                "                \"x3\": 1141,\n" +
                "                \"y3\": 1248,\n" +
                "                \"x4\": 1145,\n" +
                "                \"y4\": 625\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"密码区2\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1627,\n" +
                "                \"y\": 429,\n" +
                "                \"width\": 785,\n" +
                "                \"height\": 40\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999973177909851,\n" +
                "            \"itemstring\": \"78/1486+-11--5628028*1+*976\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1187,\n" +
                "                \"y1\": 1618,\n" +
                "                \"x2\": 1182,\n" +
                "                \"y2\": 2414,\n" +
                "                \"x3\": 1143,\n" +
                "                \"y3\": 2414,\n" +
                "                \"x4\": 1148,\n" +
                "                \"y4\": 1618\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"密码区3\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1621,\n" +
                "                \"y\": 478,\n" +
                "                \"width\": 789,\n" +
                "                \"height\": 43\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999896883964539,\n" +
                "            \"itemstring\": \"+>5--3-7414/>75479+615>3>1/\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1138,\n" +
                "                \"y1\": 1612,\n" +
                "                \"x2\": 1133,\n" +
                "                \"y2\": 2412,\n" +
                "                \"x3\": 1091,\n" +
                "                \"y3\": 2411,\n" +
                "                \"x4\": 1096,\n" +
                "                \"y4\": 1611\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"密码区4\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1619,\n" +
                "                \"y\": 525,\n" +
                "                \"width\": 796,\n" +
                "                \"height\": 42\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999634623527527,\n" +
                "            \"itemstring\": \"9*9/>+>1**13+01>*160843<5<9\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1091,\n" +
                "                \"y1\": 1609,\n" +
                "                \"x2\": 1086,\n" +
                "                \"y2\": 2416,\n" +
                "                \"x3\": 1045,\n" +
                "                \"y3\": 2416,\n" +
                "                \"x4\": 1050,\n" +
                "                \"y4\": 1609\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"货物或应税劳务、服务名称\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 230,\n" +
                "                \"y\": 687,\n" +
                "                \"width\": 321,\n" +
                "                \"height\": 41\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"*旅游服务*旅游费\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"row\": 0,\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 938,\n" +
                "                \"y1\": 225,\n" +
                "                \"x2\": 936,\n" +
                "                \"y2\": 546,\n" +
                "                \"x3\": 895,\n" +
                "                \"y3\": 546,\n" +
                "                \"x4\": 897,\n" +
                "                \"y4\": 225\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"金额\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1900,\n" +
                "                \"y\": 686,\n" +
                "                \"width\": 149,\n" +
                "                \"height\": 34\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999991655349731,\n" +
                "            \"itemstring\": \"10028.30\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"row\": 0,\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 928,\n" +
                "                \"y1\": 1890,\n" +
                "                \"x2\": 927,\n" +
                "                \"y2\": 2048,\n" +
                "                \"x3\": 894,\n" +
                "                \"y3\": 2048,\n" +
                "                \"x4\": 895,\n" +
                "                \"y4\": 1890\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"税率\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 2111,\n" +
                "                \"y\": 685,\n" +
                "                \"width\": 42,\n" +
                "                \"height\": 35\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"6%\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"row\": 0,\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 928,\n" +
                "                \"y1\": 2101,\n" +
                "                \"x2\": 928,\n" +
                "                \"y2\": 2152,\n" +
                "                \"x3\": 894,\n" +
                "                \"y3\": 2152,\n" +
                "                \"x4\": 894,\n" +
                "                \"y4\": 2101\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"税额\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 2392,\n" +
                "                \"y\": 685,\n" +
                "                \"width\": 113,\n" +
                "                \"height\": 32\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999989867210388,\n" +
                "            \"itemstring\": \"601.70\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"row\": 0,\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 926,\n" +
                "                \"y1\": 2383,\n" +
                "                \"x2\": 925,\n" +
                "                \"y2\": 2503,\n" +
                "                \"x3\": 894,\n" +
                "                \"y3\": 2503,\n" +
                "                \"x4\": 895,\n" +
                "                \"y4\": 2383\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"合计金额\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1799,\n" +
                "                \"y\": 1083,\n" +
                "                \"width\": 254,\n" +
                "                \"height\": 38\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999991655349731,\n" +
                "            \"itemstring\": \"¥10028.30\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 532,\n" +
                "                \"y1\": 1787,\n" +
                "                \"x2\": 530,\n" +
                "                \"y2\": 2050,\n" +
                "                \"x3\": 493,\n" +
                "                \"y3\": 2050,\n" +
                "                \"x4\": 495,\n" +
                "                \"y4\": 1787\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"合计税额\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 2310,\n" +
                "                \"y\": 1079,\n" +
                "                \"width\": 198,\n" +
                "                \"height\": 42\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999976754188538,\n" +
                "            \"itemstring\": \"601.70\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 533,\n" +
                "                \"y1\": 2297,\n" +
                "                \"x2\": 531,\n" +
                "                \"y2\": 2506,\n" +
                "                \"x3\": 490,\n" +
                "                \"y3\": 2505,\n" +
                "                \"x4\": 492,\n" +
                "                \"y4\": 2296\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"价税合计(大写)\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 920,\n" +
                "                \"y\": 1167,\n" +
                "                \"width\": 382,\n" +
                "                \"height\": 40\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999987483024597,\n" +
                "            \"itemstring\": \"壹万零陆佰叁拾圆整\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 454,\n" +
                "                \"y1\": 906,\n" +
                "                \"x2\": 451,\n" +
                "                \"y2\": 1299,\n" +
                "                \"x3\": 412,\n" +
                "                \"y3\": 1299,\n" +
                "                \"x4\": 415,\n" +
                "                \"y4\": 906\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"小写金额\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 2061,\n" +
                "                \"y\": 1162,\n" +
                "                \"width\": 284,\n" +
                "                \"height\": 40\n" +
                "            },\n" +
                "            \"itemconf\": 0.9999997019767761,\n" +
                "            \"itemstring\": \"¥10630.00\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 451,\n" +
                "                \"y1\": 2047,\n" +
                "                \"x2\": 449,\n" +
                "                \"y2\": 2342,\n" +
                "                \"x3\": 410,\n" +
                "                \"y3\": 2342,\n" +
                "                \"x4\": 412,\n" +
                "                \"y4\": 2047\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"销售方名称\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 596,\n" +
                "                \"y\": 1254,\n" +
                "                \"width\": 624,\n" +
                "                \"height\": 39\n" +
                "            },\n" +
                "            \"itemconf\": 0.9682235717773438,\n" +
                "            \"itemstring\": \"淳安千岛湖金色假日旅行社有限公司\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 369,\n" +
                "                \"y1\": 583,\n" +
                "                \"x2\": 365,\n" +
                "                \"y2\": 1216,\n" +
                "                \"x3\": 327,\n" +
                "                \"y3\": 1215,\n" +
                "                \"x4\": 331,\n" +
                "                \"y4\": 582\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"销售方识别号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 654,\n" +
                "                \"y\": 1314,\n" +
                "                \"width\": 602,\n" +
                "                \"height\": 40\n" +
                "            },\n" +
                "            \"itemconf\": 0.999984085559845,\n" +
                "            \"itemstring\": \"91330127782395288P\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 308,\n" +
                "                \"y1\": 639,\n" +
                "                \"x2\": 304,\n" +
                "                \"y2\": 1252,\n" +
                "                \"x3\": 265,\n" +
                "                \"y3\": 1252,\n" +
                "                \"x4\": 269,\n" +
                "                \"y4\": 639\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"销售方地址、电话\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 599,\n" +
                "                \"y\": 1371,\n" +
                "                \"width\": 808,\n" +
                "                \"height\": 38\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"淳安千岛湖千岛碧云天2-8号0571-65011688\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 252,\n" +
                "                \"y1\": 590,\n" +
                "                \"x2\": 246,\n" +
                "                \"y2\": 1398,\n" +
                "                \"x3\": 208,\n" +
                "                \"y3\": 1398,\n" +
                "                \"x4\": 214,\n" +
                "                \"y4\": 590\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"销售方开户行及账号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 599,\n" +
                "                \"y\": 1430,\n" +
                "                \"width\": 674,\n" +
                "                \"height\": 37\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"建行淳安支行33001617635053001975\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 193,\n" +
                "                \"y1\": 590,\n" +
                "                \"x2\": 188,\n" +
                "                \"y2\": 1264,\n" +
                "                \"x3\": 151,\n" +
                "                \"y3\": 1263,\n" +
                "                \"x4\": 156,\n" +
                "                \"y4\": 589\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"收款人\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 417,\n" +
                "                \"y\": 1486,\n" +
                "                \"width\": 118,\n" +
                "                \"height\": 40\n" +
                "            },\n" +
                "            \"itemconf\": 0.9982943534851074,\n" +
                "            \"itemstring\": \"方玉珍\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 138,\n" +
                "                \"y1\": 401,\n" +
                "                \"x2\": 137,\n" +
                "                \"y2\": 530,\n" +
                "                \"x3\": 98,\n" +
                "                \"y3\": 530,\n" +
                "                \"x4\": 99,\n" +
                "                \"y4\": 401\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"开票人\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 1576,\n" +
                "                \"y\": 1480,\n" +
                "                \"width\": 91,\n" +
                "                \"height\": 45\n" +
                "            },\n" +
                "            \"itemconf\": 0.9434452652931213,\n" +
                "            \"itemstring\": \"吴俊\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 136,\n" +
                "                \"y1\": 1560,\n" +
                "                \"x2\": 136,\n" +
                "                \"y2\": 1662,\n" +
                "                \"x3\": 92,\n" +
                "                \"y3\": 1662,\n" +
                "                \"x4\": 92,\n" +
                "                \"y4\": 1560\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"是否有公司印章\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"1\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"省\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"浙江省\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"联次\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"二\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"发票消费类型\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"服务\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"发票类型\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"增值税普通发票\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"是否代开\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"购买方地址、电话\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"购买方开户行及账号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"单价\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"备注\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"复核\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"单位\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"数量\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"规格型号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"机器编号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"成品油标志\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"市\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"服务类型\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"通行费标志\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"车船税\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"车牌号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"类型\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"通行日期起\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"通行日期止\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 0,\n" +
                "                \"y\": 0,\n" +
                "                \"width\": 0,\n" +
                "                \"height\": 0\n" +
                "            },\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"\",\n" +
                "            \"coords\": [],\n" +
                "            \"words\": [],\n" +
                "            \"candword\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"vertex\": {\n" +
                "                \"x1\": 1626,\n" +
                "                \"y1\": 0,\n" +
                "                \"x2\": 1626,\n" +
                "                \"y2\": 0,\n" +
                "                \"x3\": 1626,\n" +
                "                \"y3\": 0,\n" +
                "                \"x4\": 1626,\n" +
                "                \"y4\": 0\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"session_id\": \"35\",\n" +
                "    \"angle\": 90.36985778808594,\n" +
                "    \"question_block\": [],\n" +
                "    \"type\": 3,\n" +
                "    \"class\": [],\n" +
                "    \"recognize_warn_code\": [],\n" +
                "    \"recognize_warn_msg\": [],\n" +
                "    \"recognize_warn_code_conf\": []\n" +
                "}";
        return str;
    }

    public static String getYJK(){
        String str="{\n" +
                "    \"errorcode\": 0,\n" +
                "    \"errormsg\": \"OK\",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"item\": \"印鉴编号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"height\": \"35\",\n" +
                "                \"width\": \"243\",\n" +
                "                \"x\": \"100\",\n" +
                "                \"y\": \"128\"\n" +
                "            },\n" +
                "            \"itemstring\": \"3101558577\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"session_id\": \"123\"\n" +
                "}";
        return str;
    }
    public static String getSSD(){
        String str="{\n" +
                "    \"errorcode\": 0,\n" +
                "    \"errormsg\": \"OK\",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"item\": \"送审单编号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"height\": \"48\",\n" +
                "                \"width\": \"353\",\n" +
                "                \"x\": \"272\",\n" +
                "                \"y\": \"94\"\n" +
                "            },\n" +
                "            \"itemstring\": \"060001900719\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"session_id\": \"123\"\n" +
                "}";
        return str;
    }

    public static String getZZXX(){
        String str="{\n" +
                "    \"errorcode\": 0,\n" +
                "    \"errormsg\": \"OK\",\n" +
                "    \"mixedItems\": [\n" +
                "        {\n" +
                "            \"items\": [\n" +
                "                {\n" +
                "                    \"item\": \"证件号码\",\n" +
                "                    \"itemstring\": \"EA7980006\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"item\": \"姓名\",\n" +
                "                    \"itemstring\": \"李勋\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"item\": \"出生日期\",\n" +
                "                    \"itemstring\": \"1978-09-09\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"item\": \"有效期\",\n" +
                "                    \"itemstring\": \"2017-07-19至2027-07-18\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"item\": \"证件类型\",\n" +
                "                    \"itemstring\": \"普通护照\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"item\": \"证件状态\",\n" +
                "                    \"itemstring\": \"未失效\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"typename\": \"普通护照\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"session_id\": \"123\"\n" +
                "}";
        return str;
    }
    public static String getYYZZ(){
        String str="{\n" +
                "    \"question_block\": [],\n" +
                "    \"recognize_warn_code_conf\": [],\n" +
                "    \"session_id\": \"35\",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"91510600MA6B00XP2F\",\n" +
                "            \"item\": \"注册号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 90,\n" +
                "                \"width\": 165,\n" +
                "                \"y\": 227,\n" +
                "                \"height\": 23\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 231,\n" +
                "                \"x1\": 90,\n" +
                "                \"y2\": 227,\n" +
                "                \"x2\": 254,\n" +
                "                \"y3\": 245,\n" +
                "                \"x3\": 254,\n" +
                "                \"y4\": 249,\n" +
                "                \"x4\": 90\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"四川森淼企业管理咨询有限公司\",\n" +
                "            \"item\": \"公司名称\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 206,\n" +
                "                \"width\": 232,\n" +
                "                \"y\": 321,\n" +
                "                \"height\": 26\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 324,\n" +
                "                \"x1\": 206,\n" +
                "                \"y2\": 321,\n" +
                "                \"x2\": 437,\n" +
                "                \"y3\": 342,\n" +
                "                \"x3\": 437,\n" +
                "                \"y4\": 346,\n" +
                "                \"x4\": 206\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"四川省德阳市旌阳区署前街47号\",\n" +
                "            \"item\": \"地址\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 725,\n" +
                "                \"width\": 238,\n" +
                "                \"y\": 415,\n" +
                "                \"height\": 25\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 417,\n" +
                "                \"x1\": 725,\n" +
                "                \"y2\": 415,\n" +
                "                \"x2\": 962,\n" +
                "                \"y3\": 437,\n" +
                "                \"x3\": 962,\n" +
                "                \"y4\": 439,\n" +
                "                \"x4\": 725\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"有限责任公司(自然人独资)\",\n" +
                "            \"item\": \"主体类型\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 208,\n" +
                "                \"width\": 195,\n" +
                "                \"y\": 354,\n" +
                "                \"height\": 26\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 357,\n" +
                "                \"x1\": 208,\n" +
                "                \"y2\": 354,\n" +
                "                \"x2\": 402,\n" +
                "                \"y3\": 375,\n" +
                "                \"x3\": 402,\n" +
                "                \"y4\": 379,\n" +
                "                \"x4\": 208\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"曾莉\",\n" +
                "            \"item\": \"法定代表人\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 206,\n" +
                "                \"width\": 40,\n" +
                "                \"y\": 390,\n" +
                "                \"height\": 20\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 390,\n" +
                "                \"x1\": 206,\n" +
                "                \"y2\": 390,\n" +
                "                \"x2\": 245,\n" +
                "                \"y3\": 409,\n" +
                "                \"x3\": 245,\n" +
                "                \"y4\": 409,\n" +
                "                \"x4\": 206\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"贰佰万元整\",\n" +
                "            \"item\": \"注册资本\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 723,\n" +
                "                \"width\": 90,\n" +
                "                \"y\": 316,\n" +
                "                \"height\": 26\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 319,\n" +
                "                \"x1\": 723,\n" +
                "                \"y2\": 316,\n" +
                "                \"x2\": 812,\n" +
                "                \"y3\": 338,\n" +
                "                \"x3\": 812,\n" +
                "                \"y4\": 341,\n" +
                "                \"x4\": 723\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"2020年12月10日\",\n" +
                "            \"item\": \"成立日期\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 723,\n" +
                "                \"width\": 139,\n" +
                "                \"y\": 352,\n" +
                "                \"height\": 20\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 352,\n" +
                "                \"x1\": 723,\n" +
                "                \"y2\": 352,\n" +
                "                \"x2\": 861,\n" +
                "                \"y3\": 371,\n" +
                "                \"x3\": 861,\n" +
                "                \"y4\": 371,\n" +
                "                \"x4\": 723\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"2020年12月10日至长期\",\n" +
                "            \"item\": \"营业期限\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 723,\n" +
                "                \"width\": 201,\n" +
                "                \"y\": 380,\n" +
                "                \"height\": 25\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 383,\n" +
                "                \"x1\": 723,\n" +
                "                \"y2\": 380,\n" +
                "                \"x2\": 923,\n" +
                "                \"y3\": 402,\n" +
                "                \"x3\": 923,\n" +
                "                \"y4\": 404,\n" +
                "                \"x4\": 723\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"一般项目;企业管理咨询;市场营销策划;企业形象策划;信息咨询服务(不含许可类信息咨询服务);人力资源服务(不含职业中介活动、劳务派遣服务);社会经济咨询服务。(除依法须经批准的项目外,凭营业执照依法自主开展经营活动)\",\n" +
                "            \"item\": \"经营范围\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 208,\n" +
                "                \"width\": 357,\n" +
                "                \"y\": 419,\n" +
                "                \"height\": 67\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 419,\n" +
                "                \"x1\": 208,\n" +
                "                \"y2\": 419,\n" +
                "                \"x2\": 565,\n" +
                "                \"y3\": 486,\n" +
                "                \"x3\": 565,\n" +
                "                \"y4\": 486,\n" +
                "                \"x4\": 208\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 0.949999988079071,\n" +
                "            \"itemstring\": \"二维码\",\n" +
                "            \"item\": \"二维码\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 847,\n" +
                "                \"width\": 69,\n" +
                "                \"y\": 198,\n" +
                "                \"height\": 65\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 198,\n" +
                "                \"x1\": 847,\n" +
                "                \"y2\": 198,\n" +
                "                \"x2\": 915,\n" +
                "                \"y3\": 262,\n" +
                "                \"x3\": 915,\n" +
                "                \"y4\": 262,\n" +
                "                \"x4\": 847\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 0.949999988079071,\n" +
                "            \"itemstring\": \"国徽\",\n" +
                "            \"item\": \"国徽\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 466,\n" +
                "                \"width\": 147,\n" +
                "                \"y\": -7,\n" +
                "                \"height\": 160\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": -2,\n" +
                "                \"x1\": 466,\n" +
                "                \"y2\": -7,\n" +
                "                \"x2\": 606,\n" +
                "                \"y3\": 147,\n" +
                "                \"x3\": 612,\n" +
                "                \"y4\": 152,\n" +
                "                \"x4\": 472\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 0.949999988079071,\n" +
                "            \"itemstring\": \"印章\",\n" +
                "            \"item\": \"印章\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 860,\n" +
                "                \"width\": 123,\n" +
                "                \"y\": 524,\n" +
                "                \"height\": 119\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 524,\n" +
                "                \"x1\": 860,\n" +
                "                \"y2\": 524,\n" +
                "                \"x2\": 982,\n" +
                "                \"y3\": 642,\n" +
                "                \"x3\": 982,\n" +
                "                \"y4\": 642,\n" +
                "                \"x4\": 860\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"candword\": [],\n" +
                "            \"itemconf\": 1.0,\n" +
                "            \"itemstring\": \"营业执照\",\n" +
                "            \"item\": \"标题\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"x\": 341,\n" +
                "                \"width\": 406,\n" +
                "                \"y\": 181,\n" +
                "                \"height\": 90\n" +
                "            },\n" +
                "            \"vertex\": {\n" +
                "                \"y1\": 181,\n" +
                "                \"x1\": 341,\n" +
                "                \"y2\": 181,\n" +
                "                \"x2\": 746,\n" +
                "                \"y3\": 270,\n" +
                "                \"x3\": 746,\n" +
                "                \"y4\": 270,\n" +
                "                \"x4\": 341\n" +
                "            },\n" +
                "            \"words\": [],\n" +
                "            \"wordcoordpoint\": [],\n" +
                "            \"coords\": []\n" +
                "        }\n" +
                "    ],\n" +
                "    \"class\": [],\n" +
                "    \"recognize_warn_msg\": [],\n" +
                "    \"errorcode\": 0,\n" +
                "    \"errormsg\": \"OK\",\n" +
                "    \"recognize_warn_code\": []\n" +
                "}";
        return str;
    }

    public static String getCRJJL(){
        String str="{\n" +
                "    \"ip\": \"10.9.0.247\",\n" +
                "    \"session_id\": \"123\",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"itemstring\": \"李勋\",\n" +
                "            \"item\": \"姓名\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemstring\": \"1978年09月09日 ，\",\n" +
                "            \"item\": \"出生日期\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemstring\": \"2013-08-03\",\n" +
                "            \"item\": \"出入境开始时间\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemstring\": \"2023-08-03\",\n" +
                "            \"item\": \"出入境结束时间\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"channelcode\": \"wangxiaojia\",\n" +
                "    \"table\": {\n" +
                "        \"columns\": [\n" +
                "            {\n" +
                "                \"col\": 1,\n" +
                "                \"text\": \"出境/入境\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 2,\n" +
                "                \"text\": \"出入境日期\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 3,\n" +
                "                \"text\": \"证件名称\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 4,\n" +
                "                \"text\": \"证件号码\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"rows\": [\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"入境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2023-07-02\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"CD2588810\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2023-07-02\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"CD2588810\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"入境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2019-07-31\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"普通护照\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"EA7980006\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 3\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2019-07-27\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"普通护照\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"EA7980006\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 4\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"入境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2018-02-15\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"普通护照\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"EA7980006\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 5\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2018-02-11\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"普通护照\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"EA7980006\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 6\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"入境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2017-10-04\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"普通护照\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"EA7980006\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 7\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2017-09-29\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"普通护照\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"EA7980006\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 8\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2015-04-02\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 9\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"入境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2015-02-03\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017.\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 10\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2015-02-03\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 11\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2014-11-07\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 12\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2014-09-21\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 13\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"入境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2014-08-07\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 14\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2014-08-07\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 15\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2014-07-18\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 16\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2014-02-04\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 17\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2014-01-26\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 18\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"入境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2013-12-23\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 19\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"出境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2013-12-23\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 20\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"text\": \"入境\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"text\": \"2013-08-17\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"text\": \"往来港澳通行证\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"text\": \"W70785017\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 21\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        System.out.println(str);
        return str;
    }
    public static String getSFZ(){
        String str="{\n" +
                "    \"errorcode\": 0,\n" +
                "    \"errormsg\": \"OK\",\n" +
                "    \"session_id\": \"35\",\n" +
                "    \"name\": \"刘松华\",\n" +
                "    \"name_confidence_all\": [\n" +
                "        100\n" +
                "    ],\n" +
                "    \"name_confidence\": 1.0,\n" +
                "    \"sex\": \"男\",\n" +
                "    \"sex_confidence_all\": [\n" +
                "        99\n" +
                "    ],\n" +
                "    \"sex_confidence\": 0.9999998807907105,\n" +
                "    \"nation\": \"汉\",\n" +
                "    \"nation_confidence_all\": [\n" +
                "        100\n" +
                "    ],\n" +
                "    \"nation_confidence\": 1.0,\n" +
                "    \"birth\": \"1981/4/15\",\n" +
                "    \"birth_confidence_all\": [\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100\n" +
                "    ],\n" +
                "    \"birth_confidence\": 1.0,\n" +
                "    \"address\": \"浙江省余姚市阳明街道滨江新村81幢\",\n" +
                "    \"address_confidence_all\": [\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100\n" +
                "    ],\n" +
                "    \"address_confidence\": 1.0,\n" +
                "    \"id\": \"330219198104158333\",\n" +
                "    \"id_confidence_all\": [\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100,\n" +
                "        100\n" +
                "    ],\n" +
                "    \"id_confidence\": 1.0,\n" +
                "    \"frontimage_confidence_all\": [],\n" +
                "    \"watermask_confidence_all\": [],\n" +
                "    \"valid_date_confidence_all\": [],\n" +
                "    \"authority_confidence_all\": [],\n" +
                "    \"backimage_confidence_all\": [],\n" +
                "    \"visa_num_confidence_all\": [],\n" +
                "    \"pass_no_confidence_all\": [],\n" +
                "    \"detail_errorcode\": [],\n" +
                "    \"detail_errormsg\": [],\n" +
                "    \"card_type\": 0,\n" +
                "    \"recognize_warn_code\": [],\n" +
                "    \"recognize_warn_msg\": [],\n" +
                "    \"recognize_warn_code_conf\": []\n" +
                "}";
        return str;
    }
}
