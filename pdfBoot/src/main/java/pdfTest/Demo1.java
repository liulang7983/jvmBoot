package pdfTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.table.engine.pdf.common.model.CellItem;
import com.hex.table.engine.pdf.common.model.HexTable;
import com.hex.table.engine.pdf.v1.model.HexRectangle;
import com.hex.table.pdfextract.TableExtract;
import org.apache.commons.lang3.StringUtils;
import technology.tabula.TextChunk;
import technology.tabula.TextElement;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/8/28 12:16
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args)throws Exception {
        //String pdfPath="C:\\liming\\租赁物\\报告\\远东\\33199826004256469185\\FEHPH24FL070870-L-01-抵押物清单.pdf";
        //String pdfPath="C:\\Users\\14307\\Desktop\\票据.pdf";
        //String pdfPath="C:\\Users\\14307\\Desktop\\表格\\财报.pdf";
        //String pdfPath="C:\\liming\\租赁物\\问题\\pdf一行读取为多行\\L23A1738001.pdf";
        //String pdfPath="C:\\liming\\租赁物\\问题\\L23A1738001zu\\L23A1738001租赁设备.pdf";
        //String pdfPath="C:\\liming\\租赁物\\矢量\\2\\存在合并行与列\\24DH1N6RMP-租赁物件清单.pdf";
        //String pdfPath="C:\\liming\\租赁物\\报告\\33807221004340133524\\H202408028DDDY-1.pdf";
        //String pdfPath="C:\\Users\\14307\\Desktop\\pdf查重\\国银租赁租赁物清单-20201218.pdf";
        //String pdfPath="C:\\Users\\14307\\Desktop\\pdf查重\\（已压缩）租赁物清单.pdf";
        //String pdfPath="C:\\Users\\14307\\Desktop\\pdf查重\\租赁物清单.pdf";
        //String pdfPath="C:\\liming\\租赁物\\矢量问题汇总\\第四页错列\\01141162000138952100\\20201030181538345.pdf";
        //String pdfPath="C:\\Users\\14307\\Desktop\\pdf查重\\24223200003049997936\\租赁物清单.pdf";
        //String pdfPath="C:\\Users\\14307\\Desktop\\pdf查重\\16532191002011163073\\WXL32201025租赁物清单.pdf";
        //String pdfPath="C:\\liming\\租赁物\\报告\\租赁登记.pdf";
        //String pdfPath="C:\\Users\\14307\\Desktop\\租赁物\\24110050003444165112\\附件1_融资性售后回租项目租赁物清单(动产设备).pdf";
        String pdfPath="C:\\Users\\14307\\Desktop\\租赁物\\37803210004927821124\\正泰安能租赁物清单（简易版）.pdf";
        long l = System.currentTimeMillis();
        List<HexTable> tables = TableExtract.Extract(pdfPath, TableExtract.getLiuShuiConfig(), "", "");
        long end = System.currentTimeMillis();
        System.out.println(end-l);
        System.out.println(tables.size());
        Integer W=3507;
        Integer H=2480;
        Double h=1d;
        Double w=1d;
        BigDecimal h1=new BigDecimal("0");
        BigDecimal w1=new BigDecimal("0");
        JSONObject jsonObject = new JSONObject();
        JSONArray body = new JSONArray();
        for (int i = 0; i <tables.size() ; i++) {
            HexTable hexTable = tables.get(i);
            float pageHeight = hexTable.getPageHeight();
            float pageWidth = hexTable.getPageWidth();
            h=((double) H)/pageHeight;
            w=((double) W)/pageWidth;
            h1=new BigDecimal(String.valueOf(H)).divide(new BigDecimal(String.valueOf(pageHeight)),20,BigDecimal.ROUND_HALF_UP);
            w1=new BigDecimal(String.valueOf(W)).divide(new BigDecimal(String.valueOf(pageWidth)),20,BigDecimal.ROUND_HALF_UP);
            List<CellItem> cellItemList = hexTable.getCellItemList();
            JSONObject boy = new JSONObject();
            JSONArray cells = new JSONArray();
            for (int j = 0; j <cellItemList.size() ; j++) {
                CellItem cellItem = cellItemList.get(j);
                String tableId = cellItem.getTableId();
                TextChunk textChunk = cellItem.getTextChunk();
                String text =textChunk .getText();
                //textChunk.get
                System.out.println(text);
                JSONObject cell = new JSONObject();
                int colNo = cellItem.getColNo();
                int rowNo = cellItem.getRowNo();
                int colSize = cellItem.getColSize();
                int rowSize = cellItem.getRowSize();
                cell.put("tl_col",colNo);
                cell.put("tl_row",rowNo);
                cell.put("br_col",colNo+colSize);
                cell.put("br_row",rowNo+rowSize);
                HexRectangle hexRectangle=(HexRectangle)cellItem.getRectangle();
                if (h!=null&&w!=null){
                    JSONObject itemcoord = new JSONObject();
                    double y = hexRectangle.y;
                    itemcoord.put("x",hexRectangle.x*w);
                    itemcoord.put("y",y);
                    itemcoord.put("width",hexRectangle.width*w);
                    itemcoord.put("height",hexRectangle.height*h);
                    cell.put("itemcoord",itemcoord);
                    JSONArray coords = new JSONArray();
                    JSONArray texts = new JSONArray();
                    List<TextElement> elementList = hexRectangle.getTextElementList();
                    for (int k = 0; k < elementList.size(); k++) {
                        TextElement textElement = elementList.get(k);
                        String text1 = textElement.getText();
                        if (StringUtils.isNotBlank(text1)){
                            float y1 = textElement.y;
                            double v = y1 - y;
                            String s=String.valueOf((int)(textElement.x*w))+","+String.valueOf((int)((textElement.y-v/3)*h))+","+String.valueOf((int)(textElement.width*w))+","+String.valueOf((int)((textElement.height+v/2)*h));
                            coords.add(s);
                            JSONObject textJson=new JSONObject();
                            textJson.put("text",text1);
                            textJson.put("coord",s);
                            texts.add(textJson);
                        }
                    }
                    cell.put("texts",texts);
                    cell.put("coords",coords);
                }


                cell.put("splitCell",false);
                cell.put("itemstring",hexRectangle.getText().replaceAll("\\r\\n", ""));
                cells.add(cell);
            }
            boy.put("cells",cells);
            boy.put("type",1);

            body.add(boy);
        }
        jsonObject.put("isPdfRead",1);
        jsonObject.put("body",body);
        jsonObject.put("type",1);
            System.out.println(jsonObject);

    }
}
