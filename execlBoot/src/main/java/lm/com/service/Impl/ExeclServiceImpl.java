package lm.com.service.Impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.*;
import lm.com.bean.User;
import lm.com.service.ExeclService;
import lm.com.utils.ExcelDataListener;
import lm.com.utils.FileUtils;
import org.apache.batik.css.engine.value.StringValue;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        //设置行高，防止自动换行导致变宽
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
                    String string = execl1.getString(next);
                    for (Field field:declaredFields){
                        String name = field.getName();
                        if (name.equals(string)){
                            field.setAccessible(true);
                            String s = field.get(user).toString();
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

    @Override
    public void read(MultipartFile file) throws IOException {
        byte[] b = new byte[20];
        file.getInputStream().read(b, 0, 20);
        String s = bytesToHexString(b);
        SyncReadListener readListener = new SyncReadListener();
        //不读取表头
        ExcelReader excelReader = EasyExcelFactory.read(file.getInputStream(),readListener).headRowNumber(0).build();
        ReadSheet sheet = new ReadSheet(0);
        excelReader.read(sheet);
        List<Object> datas = readListener.getList();
        System.out.println("查看");
    }

    @Override
    public void read2(MultipartFile file) throws Exception {
        List<Object> datas = new ArrayList<>();

        Workbook workbook = new Workbook(file.getInputStream());
        Workbook workbook2 = new Workbook("C:\\liming\\银行流水\\x.xls");
        Workbook workbook3 = new Workbook("C:\\liming\\银行流水\\x3.xls");
        Worksheet worksheet = workbook.getWorksheets().get(0);
        Worksheet worksheet2 = workbook2.getWorksheets().get(0);
        Cells cells = worksheet.getCells();
        int maxDataColumn = cells.getMaxDataColumn();
        for (int i = 0; i < cells.getMaxDataRow()+1; i++) {
            List<Object> objectList = new ArrayList<>();
            int maxDataRow = cells.getMaxDataColumn();
            for (int j = 0; j < cells.getMaxDataColumn()+1; j++) {
                Cell cell = cells.get(i, j);
                objectList.add(cell.getStringValue());
                Style style = cell.getStyle();
                style.getFont().setBold(true);
                style.setForegroundColor(Color.getYellow());
                style.setHorizontalAlignment(TextAlignmentType.CENTER);
                worksheet2.getCells().get(i,j).setValue(cells.get(i,j).getStringValue());
                worksheet2.getCells().get(i,j).setStyle(style);
                System.out.println(cells.get(i,j).getStringValue());
            }
            datas.add(objectList);
            System.out.println("----------");
        }
        workbook3.copy(workbook);
        workbook3.save("C:\\liming\\银行流水\\x3.xls");
        workbook2.save("C:\\liming\\银行流水\\x.xls");
        //workbook.s
    }

    @Override
    public void copy(MultipartFile file) throws Exception {
        Workbook workbook = new Workbook(file.getInputStream());
        Workbook workbook3 = new Workbook("C:\\liming\\银行流水\\x4.xls");
        workbook3.copy(workbook);
        workbook3.save("C:\\liming\\银行流水\\x4.xls");
    }

    @Override
    public void deleteAndCopy() throws Exception {
        String oldFilePath="C:\\liming\\银行流水\\x4.xls";
        String newFilePath="C:\\liming\\银行流水\\x5.xls";
        File file = new File(oldFilePath);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = new Workbook(inputStream);
        Workbook workbook3 = new Workbook(newFilePath);
        inputStream.close();
        new File(oldFilePath).delete();
        workbook3.copy(workbook);
        workbook3.save(newFilePath);
        new File(newFilePath).renameTo(new File(oldFilePath));

    }


    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
       /* if (ArrayUtils.isEmpty(src)) {
            return null;
        }*/
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }


}
