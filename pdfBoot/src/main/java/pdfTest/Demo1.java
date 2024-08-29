package pdfTest;

import com.hex.table.engine.pdf.common.model.CellItem;
import com.hex.table.engine.pdf.common.model.HexTable;
import com.hex.table.pdfextract.TableExtract;
import technology.tabula.TextChunk;

import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/8/28 12:16
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args)throws Exception {
        //String pdfPath="C:\\liming\\租赁物\\报告\\远东\\33199826004256469185\\FEHPH24FL070870-L-01-抵押物清单.pdf";
        String pdfPath="C:\\Users\\14307\\Desktop\\票据.pdf";
        List<HexTable> tables = TableExtract.Extract(pdfPath, TableExtract.getLiuShuiConfig(), "", "");
        System.out.println(tables.size());
        for (int i = 0; i <tables.size() ; i++) {
            HexTable hexTable = tables.get(i);
            List<CellItem> cellItemList = hexTable.getCellItemList();

            for (int j = 0; j <cellItemList.size() ; j++) {
                CellItem cellItem = cellItemList.get(j);
                String tableId = cellItem.getTableId();
                TextChunk textChunk = cellItem.getTextChunk();
                String text =textChunk .getText();
                //textChunk.get
                System.out.println(text);
            }
            System.out.println();
        }
    }
}
