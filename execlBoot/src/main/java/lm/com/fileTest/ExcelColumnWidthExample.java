package lm.com.fileTest;

/**
 * @Author ming.li
 * @Date 2025/4/18 16:31
 * @Version 1.0
 */
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelColumnWidthExample {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        // 设置列宽不超过255个字符
        sheet.setColumnWidth(0, 260 * 256);

        try (FileOutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}