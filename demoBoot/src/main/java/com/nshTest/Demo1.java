package com.nshTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.ApiResult;
import com.bean.ContractGoods;
import com.bean.ContractGoodsDetail;
import com.enumUtil.ERetCode;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ming.li
 * @date 2023/8/21 10:35
 */
public class Demo1 {
    public static void main(String[] args) {
        ContractGoods contractGoods=new ContractGoods();
        contractGoods.setType(1);
        //saveGoodsDetail(contractGoods,getGXHT());
        saveGoodsDetail(contractGoods,getSHD());
    }

    public static ApiResult saveGoodsDetail(ContractGoods contractGoods, String engine){
        JSONObject jsonObjectMap=getJSON();
        JSONObject jsonObject = JSONObject.parseObject(engine);
        String errorcode = jsonObject.getString("errorcode");
        if ("0".equals(errorcode)){
            //处理合同的
            if (contractGoods.getType()==0){
                try {
                    JSONArray items = jsonObject.getJSONArray("items");
                    JSONObject coordJson=new JSONObject();
                    Field[] declaredFields = contractGoods.getClass().getDeclaredFields();
                    JSONObject contract = jsonObjectMap.getJSONObject("contract");
                    for (int i = 0; i < items.size(); i++) {
                        JSONObject object = items.getJSONObject(i);
                        String item = object.getString("item");
                        String key = contract.getString(item);
                        if (StringUtils.isNotBlank(key)){
                            for (int j = 0; j < declaredFields.length; j++) {
                                Field field = declaredFields[j];
                                String name = field.getName();
                                if (key.equals(name)){
                                    field.setAccessible(true);
                                    field.set(contractGoods,object.getString("itemstring"));
                                    coordJson.put(name,object.getJSONObject("itemcoord"));
                                }
                            }
                        }
                    }
                    contractGoods.setJson(coordJson.toString());
                    System.out.println(contractGoods);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            JSONObject table = jsonObject.getJSONObject("table");
            JSONArray columns = table.getJSONArray("columns");
            Map<Integer,String> map=new HashMap<>();
            JSONObject contractDetail = jsonObjectMap.getJSONObject("contractDetail");
            for (int i = 0; i < columns.size(); i++) {
                JSONObject object = columns.getJSONObject(i);
                String text = contractDetail.getString(object.getString("text"));
                if (StringUtils.isNotBlank(text)){
                    map.put(object.getInteger("col"),text);
                }
            }
            JSONArray rows = table.getJSONArray("rows");
            Map<Integer,ContractGoodsDetail> detailMap=new HashMap<>();
            for (int i = 0; i < rows.size(); i++) {
                JSONObject object = rows.getJSONObject(i);
                Integer row = object.getInteger("row");
                JSONArray cells = object.getJSONArray("cells");
                JSONObject jsonDetail=new JSONObject();
                JSONObject jsonCoord=new JSONObject();
                for (int j = 0; j < cells.size(); j++) {
                    JSONObject cellsJSONObject = cells.getJSONObject(j);
                    Integer col = cellsJSONObject.getInteger("col");
                    String key = map.get(col);
                    if (StringUtils.isNotBlank(key)){
                        jsonDetail.put(key,cellsJSONObject.getString("text"));
                        jsonCoord.put(key,cellsJSONObject.getString("itemcoord"));
                    }
                }
                ContractGoodsDetail detail = JSONObject.parseObject(jsonDetail.toString(), ContractGoodsDetail.class);
                detail.setJson(jsonCoord.toString());
                detail.setContractId(contractGoods.getId());
                detailMap.put(row,detail);
            }
            Map<Integer, ContractGoodsDetail> result = new LinkedHashMap<>();
            detailMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
            for (ContractGoodsDetail value : result.values()) {
                System.out.println(value);
            }
            return new ApiResult(ERetCode.SUCCESS.getCode(), "文件识别成功");
        }
        return new ApiResult(ERetCode.FAIL.getCode(), "文件识别失败");
    }


    public static JSONObject getJSON(){
        String str="{\n" +
                "  \"contract\": {\n" +
                "    \"合同编号\": \"contractNumber\",\n" +
                "    \"单位名称(章)\": \"buyer\",\n" +
                "    \"供方红章\": \"seller\"\n" +
                "  },\n" +
                "  \"contractDetail\": {\n" +
                "    \"产品名称\": \"goodsName\",\n" +
                "    \"牌号\": \"mark\",\n" +
                "    \"规格型号\": \"spec\",\n" +
                "    \"生产厂家\": \"manufacturer\",\n" +
                "    \"件数\": \"num\",\n" +
                "    \"重量\": \"weigth\",\n" +
                "    \"单价\": \"unitPrice\",\n" +
                "    \"总金额\": \"totalPrice\",\n" +
                "    \"提单号\": \"orderId\",\n" +
                "    \"品名\": \"goodsName\",\n" +
                "    \"材质\": \"texture\",\n" +
                "    \"规格\": \"spec\",\n" +
                "    \"产地\": \"manufacturer\",\n" +
                "    \"数量\": \"num\",\n" +
                "    \"到货重量\": \"weigth\"\n" +
                "  }\n" +
                "}";
        return JSONObject.parseObject(str);
    }

    public static String getGXHT(){
        String str="{\n" +
                "    \"errorcode\": 0,\n" +
                "    \"errormsg\": \"OK\",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"item\": \"合同编号\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"height\": 37,\n" +
                "                \"width\": 184,\n" +
                "                \"x\": 234,\n" +
                "                \"y\": 225\n" +
                "            },\n" +
                "            \"itemstring\": \"PCG390023040400126\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": \"供方红章\",\n" +
                "            \"itemcoord\": {\n" +
                "                \"height\": 221,\n" +
                "                \"width\": 213,\n" +
                "                \"x\": 323,\n" +
                "                \"y\": 1296\n" +
                "            },\n" +
                "            \"itemstring\": \"北京锦磊天然物资有限公司\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"session_id\": \"123\",\n" +
                "    \"table\": {\n" +
                "        \"columns\": [\n" +
                "            {\n" +
                "                \"col\": 0,\n" +
                "                \"text\": \"产品名称\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 1,\n" +
                "                \"text\": \"牌号\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 2,\n" +
                "                \"text\": \"规格型号\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 3,\n" +
                "                \"text\": \"生产厂家\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 4,\n" +
                "                \"text\": \"件数\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 5,\n" +
                "                \"text\": \"单价\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"col\": 6,\n" +
                "                \"text\": \"总金额\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"rows\": [\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 0,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 25,\n" +
                "                            \"width\": 67,\n" +
                "                            \"x\": 215,\n" +
                "                            \"y\": 400\n" +
                "                        },\n" +
                "                        \"text\": \"螺纹钢\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 24,\n" +
                "                            \"width\": 86,\n" +
                "                            \"x\": 304,\n" +
                "                            \"y\": 401\n" +
                "                        },\n" +
                "                        \"text\": \"HRB400E\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 20,\n" +
                "                            \"width\": 46,\n" +
                "                            \"x\": 416,\n" +
                "                            \"y\": 402\n" +
                "                        },\n" +
                "                        \"text\": \"∮18世\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 27,\n" +
                "                            \"width\": 93,\n" +
                "                            \"x\": 563,\n" +
                "                            \"y\": 396\n" +
                "                        },\n" +
                "                        \"text\": \"曾钢长治\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 25,\n" +
                "                            \"width\": 79,\n" +
                "                            \"x\": 675,\n" +
                "                            \"y\": 396\n" +
                "                        },\n" +
                "                        \"text\": \"7,0000\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 5,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 28,\n" +
                "                            \"width\": 114,\n" +
                "                            \"x\": 859,\n" +
                "                            \"y\": 391\n" +
                "                        },\n" +
                "                        \"text\": \"4,245,0000\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 6,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 24,\n" +
                "                            \"width\": 103,\n" +
                "                            \"x\": 985,\n" +
                "                            \"y\": 392\n" +
                "                        },\n" +
                "                        \"text\": \"85,579.20\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"cells\": [\n" +
                "                    {\n" +
                "                        \"col\": 0,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 26,\n" +
                "                            \"width\": 68,\n" +
                "                            \"x\": 214,\n" +
                "                            \"y\": 429\n" +
                "                        },\n" +
                "                        \"text\": \"螺纹钢\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 1,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 16,\n" +
                "                            \"width\": 65,\n" +
                "                            \"x\": 314,\n" +
                "                            \"y\": 436\n" +
                "                        },\n" +
                "                        \"text\": \"HRB400E\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 2,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 24,\n" +
                "                            \"width\": 62,\n" +
                "                            \"x\": 410,\n" +
                "                            \"y\": 429\n" +
                "                        },\n" +
                "                        \"text\": \"∮16甘\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 3,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 29,\n" +
                "                            \"width\": 89,\n" +
                "                            \"x\": 569,\n" +
                "                            \"y\": 424\n" +
                "                        },\n" +
                "                        \"text\": \"首钢长治\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 4,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 19,\n" +
                "                            \"width\": 61,\n" +
                "                            \"x\": 682,\n" +
                "                            \"y\": 428\n" +
                "                        },\n" +
                "                        \"text\": \"5,0000\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 5,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 29,\n" +
                "                            \"width\": 114,\n" +
                "                            \"x\": 859,\n" +
                "                            \"y\": 420\n" +
                "                        },\n" +
                "                        \"text\": \"4,275.0000\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"col\": 6,\n" +
                "                        \"itemcoord\": {\n" +
                "                            \"height\": 24,\n" +
                "                            \"width\": 99,\n" +
                "                            \"x\": 982,\n" +
                "                            \"y\": 420\n" +
                "                        },\n" +
                "                        \"text\": \"63,826.75\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"row\": 1\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        return str;
    }
    public  static String getSHD(){
        String str="{\"errorcode\":0,\"errormsg\":\"OK\",\"items\":[],\"session_id\":\"\",\"table\":{\"columns\":[{\"col\":0,\"text\":\"\\u5361\\u53f7\"},{\"col\":1,\"text\":\"\\u7269\\u8d44\\u540d\\u79f0\"},{\"col\":2,\"text\":\"\\u6750\\u8d28\"},{\"col\":3,\"text\":\"\\u89c4\\u683c\"},{\"col\":4,\"text\":\"\\u4ea7\\u5730\"},{\"col\":5,\"text\":\"\\u5b9e\\u53d1\\u91cd\\u91cf\"}],\"rows\":[{\"cells\":[{\"col\":0,\"itemcoord\":{\"height\":31,\"width\":244,\"x\":302,\"y\":378},\"text\":\"PCG390022092301307\"},{\"col\":1,\"itemcoord\":{\"height\":28,\"width\":53,\"x\":614,\"y\":385},\"text\":\"\\u9ad8\\u7ebf\"},{\"col\":2,\"itemcoord\":{\"height\":24,\"width\":78,\"x\":746,\"y\":391},\"text\":\"HPB300\"},{\"col\":3,\"itemcoord\":{\"height\":25,\"width\":33,\"x\":909,\"y\":392},\"text\":\"\\u222e6\"},{\"col\":4,\"itemcoord\":{\"height\":27,\"width\":93,\"x\":1064,\"y\":392},\"text\":\"\\u5c71\\u897f\\u664b\\u5357\"},{\"col\":5,\"itemcoord\":{\"height\":27,\"width\":64,\"x\":1541,\"y\":389},\"text\":\"9.35\"}],\"row\":0},{\"cells\":[{\"col\":0,\"itemcoord\":{\"height\":29,\"width\":245,\"x\":300,\"y\":434},\"text\":\"PCG390022092301307\"},{\"col\":1,\"itemcoord\":{\"height\":28,\"width\":72,\"x\":606,\"y\":439},\"text\":\"\\u87ba\\u7eb9\\u94a2\"},{\"col\":2,\"itemcoord\":{\"height\":21,\"width\":90,\"x\":739,\"y\":445},\"text\":\"MRB4O0E\"},{\"col\":3,\"itemcoord\":{\"height\":25,\"width\":57,\"x\":896,\"y\":445},\"text\":\"\\u222e18#\"},{\"col\":4,\"itemcoord\":{\"height\":26,\"width\":50,\"x\":1088,\"y\":445},\"text\":\"\\u9152\\u94a2\"},{\"col\":5,\"itemcoord\":{\"height\":31,\"width\":84,\"x\":1542,\"y\":438},\"text\":\"17.01\"}],\"row\":1},{\"cells\":[{\"col\":0,\"itemcoord\":{\"height\":30,\"width\":22,\"x\":304,\"y\":489},\"text\":\"0\"},{\"col\":1,\"itemcoord\":null,\"text\":\"\"},{\"col\":2,\"itemcoord\":null,\"text\":\"\"},{\"col\":3,\"itemcoord\":null,\"text\":\"\"},{\"col\":4,\"itemcoord\":{\"height\":24,\"width\":13,\"x\":1131,\"y\":512},\"text\":\"A\"},{\"col\":5,\"itemcoord\":null,\"text\":\"\"}],\"row\":2},{\"cells\":[{\"col\":0,\"itemcoord\":null,\"text\":\"\"},{\"col\":1,\"itemcoord\":null,\"text\":\"\"},{\"col\":2,\"itemcoord\":null,\"text\":\"\"},{\"col\":3,\"itemcoord\":null,\"text\":\"\"},{\"col\":4,\"itemcoord\":{\"height\":28,\"width\":24,\"x\":1083,\"y\":541},\"text\":\"U\"},{\"col\":5,\"itemcoord\":null,\"text\":\"\"}],\"row\":3},{\"cells\":[{\"col\":0,\"itemcoord\":null,\"text\":\"\"},{\"col\":1,\"itemcoord\":null,\"text\":\"\"},{\"col\":2,\"itemcoord\":null,\"text\":\"\"},{\"col\":3,\"itemcoord\":{\"height\":75,\"width\":115,\"x\":908,\"y\":584},\"text\":\"x\\u4e2d\"},{\"col\":4,\"itemcoord\":null,\"text\":\"\"},{\"col\":5,\"itemcoord\":null,\"text\":\"\"}],\"row\":4}]}}";
        return str;
    }

}
