package com.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

public class Demo2 {
    public static void main(String[] args) {
        String s="1.jpg";
        System.out.println(s.substring(0,s.indexOf(".")));
        System.out.println("----------");
        JSONObject getjson = getjson();
        System.out.println(getjson);
        int maxColOfPage = getMaxColOfPage(getjson);
        System.out.println(maxColOfPage);
    }



    public static int getMaxColOfPage(JSONObject page) {
        int maxCol = 0;
        JSONObject tableObj = page.getJSONArray(JsonConstant.TEXT_TABLE).getJSONObject(0);
        JSONArray titles = tableObj.getJSONArray(JsonConstant.TEXT_TITLES);
        JSONArray rows = tableObj.getJSONArray(JsonConstant.TEXT_ROWS);
        if(CollectionUtils.isEmpty(rows)){
            return 0;
        }
        for (int i = 0; i < rows.size(); i++) {
            JSONObject row = rows.getJSONObject(i);
            JSONArray cells = row.getJSONArray(JsonConstant.TEXT_CELLS);
            if(CollectionUtils.isEmpty(cells)){
                continue;
            }
            JSONObject lastCell = cells.getJSONObject(cells.size()-1);
            int lastCol = lastCell.getIntValue(JsonConstant.TEXT_COL);
            if (maxCol < lastCol) {
                maxCol = lastCol;
            }
        }
        return maxCol;
    }

    public static  JSONObject getjson(){
        String s="{\"fileName\":\"0135_20240112140801_9b8fc7fa-2ee2-42f8-8b98-912d1ee263e7.jpg\",\"tables\":[{\"area\":\"95,149,733,149,95,783,733,783\",\"tableType\":\"nltable\",\"titles\":[],\"rows\":[{\"cells\":[{\"col\":1,\"rect\":\"100,151,178,12\",\"origText\":\"项目名称\",\"text\":\"项目名称\",\"textcoords\":\"206,151,59,12\",\"textRect\":\"206,151,59,12\"},{\"col\":2,\"rect\":\"396,151,18,12\",\"origText\":\"\",\"text\":\"\",\"textcoords\":\"\",\"textRect\":\"\"},{\"col\":3,\"rect\":\"445,151,56,12\",\"origText\":\"/单位\",\"text\":\"/单位\",\"textcoords\":\"445,151,34,12\",\"textRect\":\"445,151,34,12\"},{\"col\":5,\"rect\":\"664,151,24,12\",\"origText\":\"元)\",\"text\":\"元)\",\"textRect\":\"591,151,24,12\"},{\"col\":4,\"rect\":\"587,151,77,12\",\"origText\":\"1(\",\"text\":\"1(\",\"textRect\":\"587,151,4,12\"}],\"row\":1},{\"cells\":[{\"col\":1,\"rect\":\"100,175,178,13\",\"origText\":\"西药费(小计:11345.3600元)\",\"text\":\"西药费(小计:11345.3600元)\",\"textcoords\":\"105,175,163,13\",\"textRect\":\"105,175,163,13\"},{\"col\":2,\"rect\":\"396,175,18,13\",\"origText\":\"\",\"text\":\"\",\"textcoords\":\"\",\"textRect\":\"\"},{\"col\":3,\"rect\":\"445,175,56,13\",\"origText\":\"\",\"text\":\"\",\"textcoords\":\"\",\"textRect\":\"\"},{\"col\":5,\"rect\":\"662,175,26,13\",\"origText\":\"\",\"text\":\"\",\"textRect\":\"\"},{\"col\":4,\"rect\":\"587,175,75,13\",\"origText\":\"\",\"text\":\"\",\"textRect\":\"\"}],\"row\":2},{\"cells\":[{\"col\":1,\"rect\":\"100,199,178,12\",\"origText\":\"氯化钠注射液\",\"text\":\"氯化钠注射液\",\"textcoords\":\"100,199,88,12\",\"textRect\":\"100,199,88,12\"},{\"col\":2,\"rect\":\"396,199,18,13\",\"origText\":\"50\",\"text\":\"50\",\"textcoords\":\"396,199,20,13\",\"textRect\":\"396,199,20,13\"},{\"col\":3,\"rect\":\"445,200,56,12\",\"origText\":\"袋\",\"text\":\"袋\",\"textcoords\":\"445,200,22,12\",\"textRect\":\"445,200,22,12\"},{\"col\":5,\"rect\":\"666,201,22,11\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"646,201,22,11\"},{\"col\":4,\"rect\":\"587,201,79,11\",\"origText\":\"273.50\",\"text\":\"273.50\",\"textRect\":\"593,201,53,11\"}],\"row\":3},{\"cells\":[{\"col\":1,\"rect\":\"100,223,178,13\",\"origText\":\"氯化钠注射液\",\"text\":\"氯化钠注射液\",\"textcoords\":\"100,223,88,13\",\"textRect\":\"100,223,88,13\"},{\"col\":2,\"rect\":\"396,223,18,13\",\"origText\":\"12\",\"text\":\"12\",\"textcoords\":\"398,223,17,13\",\"textRect\":\"398,223,17,13\"},{\"col\":3,\"rect\":\"445,224,56,11\",\"origText\":\"袋\",\"text\":\"袋\",\"textcoords\":\"445,224,21,11\",\"textRect\":\"445,224,21,11\"},{\"col\":5,\"rect\":\"668,225,20,10\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"650,225,20,10\"},{\"col\":4,\"rect\":\"587,225,81,10\",\"origText\":\"74.04\",\"text\":\"74.04\",\"textRect\":\"600,225,50,10\"}],\"row\":4},{\"cells\":[{\"col\":1,\"rect\":\"100,247,178,12\",\"origText\":\"氧化钠注射液\",\"text\":\"氧化钠注射液\",\"textcoords\":\"100,247,88,12\",\"textRect\":\"100,247,88,12\"},{\"col\":2,\"rect\":\"396,248,18,11\",\"origText\":\"2\",\"text\":\"2\",\"textcoords\":\"396,248,16,11\",\"textRect\":\"396,248,16,11\"},{\"col\":3,\"rect\":\"445,247,56,13\",\"origText\":\"袋\",\"text\":\"袋\",\"textcoords\":\"445,247,22,13\",\"textRect\":\"445,247,22,13\"},{\"col\":5,\"rect\":\"668,249,20,10\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"650,249,20,10\"},{\"col\":4,\"rect\":\"587,249,81,10\",\"origText\":\"13.86\",\"text\":\"13.86\",\"textRect\":\"600,249,50,10\"}],\"row\":5},{\"cells\":[{\"col\":1,\"rect\":\"100,271,178,12\",\"origText\":\"乌拉地尔注射液\",\"text\":\"乌拉地尔注射液\",\"textcoords\":\"101,271,99,12\",\"textRect\":\"101,271,99,12\"},{\"col\":2,\"rect\":\"396,272,18,11\",\"origText\":\"56\",\"text\":\"56\",\"textcoords\":\"396,272,20,11\",\"textRect\":\"396,272,20,11\"},{\"col\":3,\"rect\":\"445,271,56,12\",\"origText\":\"支\",\"text\":\"支\",\"textcoords\":\"445,271,22,12\",\"textRect\":\"445,271,22,12\"},{\"col\":5,\"rect\":\"666,272,22,11\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"648,272,22,11\"},{\"col\":4,\"rect\":\"587,272,79,11\",\"origText\":\"1,242.08\",\"text\":\"1,242.08\",\"textRect\":\"587,272,61,11\"}],\"row\":6},{\"cells\":[{\"col\":1,\"rect\":\"100,295,178,11\",\"origText\":\"奥拉西坦注射液\",\"text\":\"奥拉西坦注射液\",\"textcoords\":\"100,295,100,11\",\"textRect\":\"100,295,100,11\"},{\"col\":2,\"rect\":\"396,295,18,12\",\"origText\":\"12\",\"text\":\"12\",\"textcoords\":\"396,295,20,12\",\"textRect\":\"396,295,20,12\"},{\"col\":3,\"rect\":\"445,295,56,13\",\"origText\":\"支\",\"text\":\"支\",\"textcoords\":\"445,295,23,13\",\"textRect\":\"445,295,23,13\"},{\"col\":5,\"rect\":\"664,296,24,12\",\"origText\":\"自费\",\"text\":\"自费\",\"textRect\":\"644,296,24,11\"},{\"col\":4,\"rect\":\"587,296,77,12\",\"origText\":\"2,419.20\",\"text\":\"2,419.20\",\"textRect\":\"587,296,57,11\"}],\"row\":7},{\"cells\":[{\"col\":1,\"rect\":\"100,318,178,13\",\"origText\":\"甘油灌肠剂\",\"text\":\"甘油灌肠剂\",\"textcoords\":\"100,318,77,13\",\"textRect\":\"100,318,77,13\"},{\"col\":2,\"rect\":\"396,319,18,12\",\"origText\":\"1\",\"text\":\"1\",\"textcoords\":\"398,319,12,12\",\"textRect\":\"398,319,12,12\"},{\"col\":3,\"rect\":\"445,318,56,13\",\"origText\":\"支\",\"text\":\"支\",\"textcoords\":\"445,318,22,13\",\"textRect\":\"445,318,22,13\"},{\"col\":5,\"rect\":\"664,319,24,12\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"646,319,24,12\"},{\"col\":4,\"rect\":\"587,319,77,12\",\"origText\":\"9.00\",\"text\":\"9.00\",\"textRect\":\"602,319,44,12\"}],\"row\":8},{\"cells\":[{\"col\":1,\"rect\":\"100,342,178,12\",\"origText\":\"盐酸雷尼替丁注射液\",\"text\":\"盐酸雷尼替丁注射液\",\"textcoords\":\"101,342,124,12\",\"textRect\":\"101,342,124,12\"},{\"col\":2,\"rect\":\"396,342,18,12\",\"origText\":\"22\",\"text\":\"22\",\"textcoords\":\"396,342,20,12\",\"textRect\":\"396,342,20,12\"},{\"col\":3,\"rect\":\"445,342,56,12\",\"origText\":\"支\",\"text\":\"支\",\"textcoords\":\"445,342,22,12\",\"textRect\":\"445,342,22,12\"},{\"col\":5,\"rect\":\"666,343,22,11\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"647,343,22,11\"},{\"col\":4,\"rect\":\"587,343,79,11\",\"origText\":\"873.40\",\"text\":\"873.40\",\"textRect\":\"595,343,52,11\"}],\"row\":9},{\"cells\":[{\"col\":1,\"rect\":\"100,366,178,12\",\"origText\":\"硝苯地平控释片\",\"text\":\"硝苯地平控释片\",\"textcoords\":\"100,366,100,12\",\"textRect\":\"100,366,100,12\"},{\"col\":2,\"rect\":\"396,366,18,12\",\"origText\":\"19\",\"text\":\"19\",\"textcoords\":\"396,366,20,12\",\"textRect\":\"396,366,20,12\"},{\"col\":3,\"rect\":\"445,366,56,12\",\"origText\":\"片\",\"text\":\"片\",\"textcoords\":\"445,366,22,12\",\"textRect\":\"445,366,22,12\"},{\"col\":5,\"rect\":\"666,367,22,11\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"647,367,22,11\"},{\"col\":4,\"rect\":\"587,367,79,11\",\"origText\":\"72.20\",\"text\":\"72.20\",\"textRect\":\"601,367,46,11\"}],\"row\":10},{\"cells\":[{\"col\":1,\"rect\":\"100,390,178,11\",\"origText\":\"硝苯地平控释片\",\"text\":\"硝苯地平控释片\",\"textcoords\":\"101,390,98,11\",\"textRect\":\"101,390,98,11\"},{\"col\":2,\"rect\":\"396,390,18,12\",\"origText\":\"2\",\"text\":\"2\",\"textcoords\":\"396,390,16,12\",\"textRect\":\"396,390,16,12\"},{\"col\":3,\"rect\":\"445,390,56,12\",\"origText\":\"盒\",\"text\":\"盒\",\"textcoords\":\"445,390,23,12\",\"textRect\":\"445,390,23,12\"},{\"col\":5,\"rect\":\"666,391,22,11\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"647,391,22,11\"},{\"col\":4,\"rect\":\"587,391,79,11\",\"origText\":\"53.20\",\"text\":\"53.20\",\"textRect\":\"602,391,45,11\"}],\"row\":11},{\"cells\":[{\"col\":1,\"rect\":\"100,414,178,11\",\"origText\":\"胞磷胆碱钠胶囊\",\"text\":\"胞磷胆碱钠胶囊\",\"textcoords\":\"101,414,100,11\",\"textRect\":\"101,414,100,11\"},{\"col\":2,\"rect\":\"395,414,19,12\",\"origText\":\"1\",\"text\":\"1\",\"textcoords\":\"395,414,18,12\",\"textRect\":\"395,414,18,12\"},{\"col\":3,\"rect\":\"445,414,56,12\",\"origText\":\"盒\",\"text\":\"盒\",\"textcoords\":\"445,414,22,12\",\"textRect\":\"445,414,22,12\"},{\"col\":5,\"rect\":\"668,415,20,10\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"648,415,20,10\"},{\"col\":4,\"rect\":\"587,415,81,10\",\"origText\":\"60.00\",\"text\":\"60.00\",\"textRect\":\"599,415,49,10\"}],\"row\":12},{\"cells\":[{\"col\":1,\"rect\":\"100,437,178,12\",\"origText\":\"阿司匹林肠溶片\",\"text\":\"阿司匹林肠溶片\",\"textcoords\":\"100,437,101,12\",\"textRect\":\"100,437,101,12\"},{\"col\":2,\"rect\":\"396,438,18,12\",\"origText\":\"3\",\"text\":\"3\",\"textcoords\":\"396,438,16,12\",\"textRect\":\"396,438,16,12\"},{\"col\":3,\"rect\":\"445,437,56,13\",\"origText\":\"盒\",\"text\":\"盒\",\"textcoords\":\"445,437,22,13\",\"textRect\":\"445,437,22,13\"},{\"col\":5,\"rect\":\"664,438,24,12\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"645,438,24,12\"},{\"col\":4,\"rect\":\"587,438,77,12\",\"origText\":\"47.40\",\"text\":\"47.40\",\"textRect\":\"599,438,46,12\"}],\"row\":13},{\"cells\":[{\"col\":1,\"rect\":\"100,461,178,12\",\"origText\":\"厄贝沙坦氢氯嚷凑片\",\"text\":\"厄贝沙坦氢氯嚷凑片\",\"textcoords\":\"101,461,124,12\",\"textRect\":\"101,461,124,12\"},{\"col\":2,\"rect\":\"396,461,18,12\",\"origText\":\"9\",\"text\":\"9\",\"textcoords\":\"396,461,16,12\",\"textRect\":\"396,461,16,12\"},{\"col\":3,\"rect\":\"445,461,56,12\",\"origText\":\"片\",\"text\":\"片\",\"textcoords\":\"445,461,21,12\",\"textRect\":\"445,461,21,12\"},{\"col\":5,\"rect\":\"668,463,20,10\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"649,463,20,10\"},{\"col\":4,\"rect\":\"587,463,81,10\",\"origText\":\"9.18\",\"text\":\"9.18\",\"textRect\":\"603,463,46,10\"}],\"row\":14},{\"cells\":[{\"col\":1,\"rect\":\"100,485,178,12\",\"origText\":\"尼贝沙坦氢氯嘛味片\",\"text\":\"尼贝沙坦氢氯嘛味片\",\"textcoords\":\"100,485,126,12\",\"textRect\":\"100,485,126,12\"},{\"col\":2,\"rect\":\"396,486,18,11\",\"origText\":\"2\",\"text\":\"2\",\"textcoords\":\"396,486,16,11\",\"textRect\":\"396,486,16,11\"},{\"col\":3,\"rect\":\"445,485,56,12\",\"origText\":\"盒\",\"text\":\"盒\",\"textcoords\":\"445,485,23,12\",\"textRect\":\"445,485,23,12\"},{\"col\":5,\"rect\":\"666,486,22,11\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"647,486,22,11\"},{\"col\":4,\"rect\":\"587,486,79,11\",\"origText\":\"28.56\",\"text\":\"28.56\",\"textRect\":\"599,486,48,11\"}],\"row\":15},{\"cells\":[{\"col\":1,\"rect\":\"100,509,178,12\",\"origText\":\"硫酸氢氯毗格雷片\",\"text\":\"硫酸氢氯毗格雷片\",\"textcoords\":\"100,509,114,12\",\"textRect\":\"100,509,114,12\"},{\"col\":2,\"rect\":\"396,509,18,12\",\"origText\":\"4\",\"text\":\"4\",\"textcoords\":\"398,509,11,12\",\"textRect\":\"398,509,11,12\"},{\"col\":3,\"rect\":\"445,509,56,12\",\"origText\":\"盒\",\"text\":\"盒\",\"textcoords\":\"445,509,23,12\",\"textRect\":\"445,509,23,12\"},{\"col\":5,\"rect\":\"666,510,22,11\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"648,510,22,11\"},{\"col\":4,\"rect\":\"587,510,79,11\",\"origText\":\"71.24\",\"text\":\"71.24\",\"textRect\":\"600,510,48,11\"}],\"row\":16},{\"cells\":[{\"col\":1,\"rect\":\"100,532,178,14\",\"origText\":\"阿卡波糖片\",\"text\":\"阿卡波糖片\",\"textcoords\":\"100,532,78,14\",\"textRect\":\"100,532,78,14\"},{\"col\":2,\"rect\":\"396,533,18,12\",\"origText\":\"28\",\"text\":\"28\",\"textcoords\":\"396,533,20,12\",\"textRect\":\"396,533,20,12\"},{\"col\":3,\"rect\":\"445,533,56,13\",\"origText\":\"片\",\"text\":\"片\",\"textcoords\":\"445,533,22,13\",\"textRect\":\"445,533,22,13\"},{\"col\":5,\"rect\":\"666,534,22,11\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"647,534,22,11\"},{\"col\":4,\"rect\":\"587,534,79,11\",\"origText\":\"5.04\",\"text\":\"5.04\",\"textRect\":\"605,534,42,11\"}],\"row\":17},{\"cells\":[{\"col\":1,\"rect\":\"100,557,178,11\",\"origText\":\"阿卡波糖片\",\"text\":\"阿卡波糖片\",\"textcoords\":\"100,557,77,11\",\"textRect\":\"100,557,77,11\"},{\"col\":2,\"rect\":\"396,557,18,12\",\"origText\":\"2\",\"text\":\"2\",\"textcoords\":\"396,557,15,12\",\"textRect\":\"396,557,15,12\"},{\"col\":3,\"rect\":\"445,557,56,13\",\"origText\":\"盒\",\"text\":\"盒\",\"textcoords\":\"445,557,23,13\",\"textRect\":\"445,557,23,13\"},{\"col\":5,\"rect\":\"666,558,22,11\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"648,558,22,11\"},{\"col\":4,\"rect\":\"587,558,79,11\",\"origText\":\"10.84\",\"text\":\"10.84\",\"textRect\":\"602,558,46,11\"}],\"row\":18},{\"cells\":[{\"col\":1,\"rect\":\"100,580,178,13\",\"origText\":\"阿托伐他汀\",\"text\":\"阿托伐他汀\",\"textcoords\":\"100,580,78,13\",\"textRect\":\"100,580,78,13\"},{\"col\":2,\"rect\":\"396,581,18,12\",\"origText\":\"3\",\"text\":\"3\",\"textcoords\":\"396,581,15,12\",\"textRect\":\"396,581,15,12\"},{\"col\":3,\"rect\":\"445,580,56,13\",\"origText\":\"盒\",\"text\":\"盒\",\"textcoords\":\"445,580,23,13\",\"textRect\":\"445,580,23,13\"},{\"col\":5,\"rect\":\"664,581,24,12\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"646,581,24,12\"},{\"col\":4,\"rect\":\"587,581,77,12\",\"origText\":\"8.58\",\"text\":\"8.58\",\"textRect\":\"606,581,40,12\"}],\"row\":19},{\"cells\":[{\"col\":1,\"rect\":\"100,605,178,11\",\"origText\":\"丁苯酸氛化钠注射液\",\"text\":\"丁苯酸氛化钠注射液\",\"textcoords\":\"101,605,125,11\",\"textRect\":\"101,605,125,11\"},{\"col\":2,\"rect\":\"396,605,18,12\",\"origText\":\"21\",\"text\":\"21\",\"textcoords\":\"396,605,20,12\",\"textRect\":\"396,605,20,12\"},{\"col\":3,\"rect\":\"445,605,56,13\",\"origText\":\"瓶\",\"text\":\"瓶\",\"textcoords\":\"445,605,23,13\",\"textRect\":\"445,605,23,13\"},{\"col\":5,\"rect\":\"666,605,22,11\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"647,605,22,11\"},{\"col\":4,\"rect\":\"587,605,79,11\",\"origText\":\"2,919.00\",\"text\":\"2,919.00\",\"textRect\":\"587,605,60,11\"}],\"row\":20},{\"cells\":[{\"col\":1,\"rect\":\"100,628,178,12\",\"origText\":\"丁苯型软胶囊\",\"text\":\"丁苯型软胶囊\",\"textcoords\":\"101,628,89,12\",\"textRect\":\"101,628,89,12\"},{\"col\":2,\"rect\":\"396,629,18,12\",\"origText\":\"1\",\"text\":\"1\",\"textcoords\":\"396,629,17,12\",\"textRect\":\"396,629,17,12\"},{\"col\":3,\"rect\":\"445,629,56,13\",\"origText\":\"盒\",\"text\":\"盒\",\"textcoords\":\"445,629,23,13\",\"textRect\":\"445,629,23,13\"},{\"col\":5,\"rect\":\"666,629,22,11\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"646,629,22,11\"},{\"col\":4,\"rect\":\"587,629,79,11\",\"origText\":\"80.64\",\"text\":\"80.64\",\"textRect\":\"602,629,44,11\"}],\"row\":21},{\"cells\":[{\"col\":1,\"rect\":\"100,652,178,12\",\"origText\":\"依达拉泰右燕醇注射用浓溶液\",\"text\":\"依达拉泰右燕醇注射用浓溶液\",\"textcoords\":\"102,652,169,11\",\"textRect\":\"102,652,169,11\"},{\"col\":2,\"rect\":\"396,653,19,11\",\"origText\":\"63\",\"text\":\"63\",\"textcoords\":\"396,653,21,11\",\"textRect\":\"396,653,21,11\"},{\"col\":3,\"rect\":\"445,652,56,12\",\"origText\":\"支\",\"text\":\"支\",\"textcoords\":\"445,652,24,12\",\"textRect\":\"445,652,24,12\"},{\"col\":5,\"rect\":\"666,653,22,11\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textRect\":\"645,653,22,11\"},{\"col\":4,\"rect\":\"587,653,79,11\",\"origText\":\"3,074.40\",\"text\":\"3,074.40\",\"textRect\":\"587,653,58,11\"}],\"row\":22},{\"cells\":[{\"col\":1,\"rect\":\"100,676,178,12\",\"origText\":\"床位费(小计:920.0000元)\",\"text\":\"床位费(小计:920.0000元)\",\"textcoords\":\"101,676,158,12\",\"textRect\":\"101,676,158,12\"},{\"col\":2,\"rect\":\"396,676,18,12\",\"origText\":\"\",\"text\":\"\",\"textcoords\":\"\",\"textRect\":\"\"},{\"col\":3,\"rect\":\"445,676,56,12\",\"origText\":\"\",\"text\":\"\",\"textcoords\":\"\",\"textRect\":\"\"},{\"col\":5,\"rect\":\"664,676,24,12\",\"origText\":\"\",\"text\":\"\",\"textRect\":\"\"},{\"col\":4,\"rect\":\"587,676,77,12\",\"origText\":\"\",\"text\":\"\",\"textRect\":\"\"}],\"row\":23},{\"cells\":[{\"col\":1,\"rect\":\"100,700,178,12\",\"origText\":\"床位费(三人间)\",\"text\":\"床位费(三人间)\",\"textcoords\":\"100,700,105,12\",\"textRect\":\"100,700,105,12\"},{\"col\":2,\"rect\":\"396,700,18,12\",\"origText\":\"1\",\"text\":\"1\",\"textcoords\":\"396,700,17,12\",\"textRect\":\"396,700,17,12\"},{\"col\":3,\"rect\":\"445,700,56,14\",\"origText\":\"天\",\"text\":\"天\",\"textcoords\":\"445,700,23,14\",\"textRect\":\"445,700,23,14\"},{\"col\":5,\"rect\":\"666,701,22,11\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"645,701,22,11\"},{\"col\":4,\"rect\":\"587,701,79,11\",\"origText\":\"70.00\",\"text\":\"70.00\",\"textRect\":\"600,701,45,11\"}],\"row\":24},{\"cells\":[{\"col\":1,\"rect\":\"100,724,178,12\",\"origText\":\"床位费(双人间)\",\"text\":\"床位费(双人间)\",\"textcoords\":\"101,724,103,12\",\"textRect\":\"101,724,103,12\"},{\"col\":2,\"rect\":\"396,725,20,11\",\"origText\":\"10\",\"text\":\"10\",\"textcoords\":\"396,725,22,11\",\"textRect\":\"396,725,22,11\"},{\"col\":3,\"rect\":\"445,724,56,12\",\"origText\":\"日\",\"text\":\"日\",\"textcoords\":\"445,724,23,12\",\"textRect\":\"445,724,23,12\"},{\"col\":5,\"rect\":\"666,725,22,11\",\"origText\":\"甲类\",\"text\":\"甲类\",\"textRect\":\"644,725,22,11\"},{\"col\":4,\"rect\":\"587,725,79,11\",\"origText\":\"850.00\",\"text\":\"850.00\",\"textRect\":\"596,725,48,11\"}],\"row\":25},{\"cells\":[{\"col\":1,\"rect\":\"100,748,178,12\",\"origText\":\"检查费(小计:3263.0000元)\",\"text\":\"检查费(小计:3263.0000元)\",\"textcoords\":\"101,748,165,12\",\"textRect\":\"101,748,165,12\"},{\"col\":2,\"rect\":\"396,748,18,12\",\"origText\":\"\",\"text\":\"\",\"textcoords\":\"\",\"textRect\":\"\"},{\"col\":3,\"rect\":\"445,748,56,12\",\"origText\":\"\",\"text\":\"\",\"textcoords\":\"\",\"textRect\":\"\"},{\"col\":5,\"rect\":\"664,748,24,12\",\"origText\":\"\",\"text\":\"\",\"textRect\":\"\"},{\"col\":4,\"rect\":\"587,748,77,12\",\"origText\":\"\",\"text\":\"\",\"textRect\":\"\"}],\"row\":26},{\"cells\":[{\"col\":1,\"rect\":\"100,771,178,11\",\"origText\":\"X线计算机休层(CT)增强扫描\",\"text\":\"X线计算机休层(CT)增强扫描\",\"textcoords\":\"101,771,179,11\",\"textRect\":\"101,771,179,11\"},{\"col\":2,\"rect\":\"396,771,18,12\",\"origText\":\"3\",\"text\":\"3\",\"textcoords\":\"396,771,17,12\",\"textRect\":\"396,771,17,12\"},{\"col\":3,\"rect\":\"445,771,56,12\",\"origText\":\"每个部位\",\"text\":\"每个部位\",\"textcoords\":\"445,771,57,12\",\"textRect\":\"445,771,57,12\"},{\"col\":5,\"rect\":\"668,773,20,10\",\"origText\":\"自费\",\"text\":\"自费\",\"textRect\":\"647,773,20,10\"},{\"col\":4,\"rect\":\"587,773,81,10\",\"origText\":\"1,197.00\",\"text\":\"1,197.00\",\"textRect\":\"587,773,60,10\"}],\"row\":27}]}],\"requestId\":\"1.jpg\",\"page\":1,\"errorcode\":0,\"errormsg\":\"OK\",\"own_info\":{}}\n";
        return JSONObject.parseObject(s);
    }
}
