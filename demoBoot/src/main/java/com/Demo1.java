package com.mi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/12/6 14:01
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        String ocrResult = FileUtils.readFileToString(new File("C:\\liming\\医保\\fy2.json"), StandardCharsets.UTF_8);
        transTextinOcrResult(ocrResult,"ss");
    }

    public static String transTextinOcrResult(String result,String fileName) {
        System.out.println("开始");
        try {
            JSONObject textinJson = JSONObject.parseObject(result);
            //错误码
            if (textinJson.getInteger("code") != 200 || textinJson.getJSONObject("result") == null) {
                return "";
            }
            JSONObject resultJson = textinJson.getJSONObject("result");
            JSONArray tableJsonArray = resultJson.getJSONArray("tables");
            //内部结构
            JSONObject finalResult = new JSONObject();
            JSONObject data = new JSONObject();
            finalResult.put("data", data);
            finalResult.put("errorcode", 0);
            finalResult.put("errormsg", "OK");
            finalResult.put("processTime", textinJson.getInteger("duration"));
            data.put("pageCount", 1);
            data.put("ocrstatus", 1);
            data.put("processTime", 1);
            JSONArray pages = new JSONArray();
            data.put("pages", pages);
            JSONObject page = new JSONObject();
            pages.add(page);
            page.put("area", "");
            page.put("fileName", fileName);
            page.put("requestId", fileName);
            page.put("page", 1);
            page.put("errorcode", 0);
            page.put("errormsg", "OK");
            JSONObject table = new JSONObject();
            page.put("table", table);
            JSONArray rows = new JSONArray();
            table.put("rows", rows);

            for (Object obj : tableJsonArray) {
                //row
                JSONObject tableJson = (JSONObject) obj;
                JSONArray tableCells = tableJson.getJSONArray("table_cells");
                if (CollectionUtils.isEmpty(tableCells)) {
                    continue;
                }
                int rowSize = tableJson.getInteger("table_rows");
                int rowNum = 1;
                for (int i = 0; i < rowSize; i++) {
                    JSONObject row = new JSONObject();
                    JSONArray cells = new JSONArray();
                    for (Object obj2 : tableCells) {
                        JSONObject tableCell = (JSONObject) obj2;
                        int curRow = tableCell.getInteger("start_row");
                       /* if (curRow > rowNum - 1) {
                            continue;
                        }*/
                        if (curRow == i) {
                            String text = tableCell.getString("text");
                            JSONArray position = tableCell.getJSONArray("position");
                            int col = tableCell.getInteger("start_col");
                            JSONObject cell = new JSONObject();
                            cell.put("col", col);
                            cell.put("text", text);
                            String rect = calculateRect(position);
                            /*cell.put("textRect", rect);
                            cell.put("textcoords", rect);*/
                            cell.put("rect", rect);
                            cells.add(cell);
                        }
                    }
                    row.put("cells", cells);
                    row.put("row", i);
                    row.put("PageNo", 1);
                    rows.add(row);
                }
               /* while (rowNum++ < rowSize + 1) {
                    JSONObject row = new JSONObject();
                    JSONArray cells = new JSONArray();
                    for (Object obj2 : tableCells) {
                        JSONObject tableCell = (JSONObject) obj2;
                        int curRow = tableCell.getInteger("start_row");
                       *//* if (curRow > rowNum - 1) {
                            continue;
                        }*//*
                        if (curRow == rowNum - 1) {
                            String text = tableCell.getString("text");
                            JSONArray position = tableCell.getJSONArray("position");
                            int col = tableCell.getInteger("start_col");
                            JSONObject cell = new JSONObject();
                            cell.put("col", col);
                            cell.put("text", text);
                            String rect = calculateRect(position);
                            *//*cell.put("textRect", rect);
                            cell.put("textcoords", rect);*//*
                            cell.put("rect", rect);
                            cells.add(cell);
                        }
                    }
                    row.put("cells", cells);
                    row.put("row", rowNum);
                    row.put("PageNo", 1);
                    rows.add(row);
                }*/
            }
            System.out.println("transOtherResult:"+finalResult.toJSONString());
            return finalResult.toJSONString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
    private static String calculateRect(JSONArray array) {
        List<Integer> rectList = array.toJavaList(Integer.class);
        int x1 = rectList.get(0);
        int y1 = rectList.get(1);
        int x2 = rectList.get(2);
        int y2 = rectList.get(3);
        int x3 = rectList.get(4);
        int y3 = rectList.get(5);
        int x4 = rectList.get(6);
        int y4 = rectList.get(7);
        int width = x3 - x1;
        int height = y3 - y1;
        /*rectList.clear();
        rectList.add(x1);
        rectList.add(y1);
        rectList.add(width);
        rectList.add(height);
        return String.join(",", (CharSequence) rectList);*/
        return x1 + "," + y1 + "," + width + "," + height;
    }
}
