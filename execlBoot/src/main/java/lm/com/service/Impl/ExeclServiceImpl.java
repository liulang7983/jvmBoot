package lm.com.service.Impl;

import com.alibaba.fastjson.JSONObject;
import lm.com.bean.User;
import lm.com.service.ExeclService;
import lm.com.utils.FileUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author ming.li
 * @date 2023/9/6 10:20
 */
@Service
public class ExeclServiceImpl implements ExeclService {

    private static JSONObject execlMap;

    @PostConstruct
    public void construct() {
        execlMap = FileUtils.getExeclMap();
    }

    @Override
    public void execl1(HttpServletResponse response) {
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=execl1.xls");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("execl1");
        JSONObject execl1 = execlMap.getJSONObject("execl1");
        Set<String> headSet = execl1.keySet();
        Set<Map.Entry<String, Object>> entries = execl1.entrySet();
        //设置表头的值
        XSSFRow row = sheet.createRow(0);
        int index = 0;
        Iterator<String> it = headSet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            row.createCell(index).setCellValue(next);
            index++;
        }
        // 创建一个样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置自动换行
        cellStyle.setWrapText(true);
        sheet.setDefaultRowHeight((short)300);

        List<User> list=new ArrayList<>();
        list.add(new User(1L,"张三","张三三"));
        list.add(new User(3L,"李四","李四\r\n四================================================================"));

        try {
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                row = sheet.createRow(1+i);
                Field[] declaredFields = user.getClass().getDeclaredFields();
                index = 0;
                for (Map.Entry<String, Object> entrie : entries) {
                    String next = entrie.getKey();
                    System.out.println("打印next："+next);
                    String string = execl1.getString(next);
                    for (Field field:declaredFields){
                        String name = field.getName();
                        if (name.equals(string)){
                            field.setAccessible(true);
                            String s = field.get(user).toString();
                            System.out.println("查到的值:"+s);
                            XSSFCell cell = row.createCell(index);
                            cell.setCellStyle(cellStyle);
                            cell.setCellValue(s);
                            index++;
                        }
                    }

                }
            }
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            workbook.close();
            out.close();
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
