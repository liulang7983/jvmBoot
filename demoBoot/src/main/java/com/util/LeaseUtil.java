package com.util;


import com.model.json.Cell;
import com.model.json.HexCell;
import com.model.json.HexRow;
import com.model.json.Item;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class LeaseUtil {

    private static Logger logger = LoggerFactory.getLogger(LeaseUtil.class);

    /**
     * 去除字符串中 全角/半角 有差异的特殊字符
     * @param target     目标字符串
     * @param char_regix
     * @return
     */
    public static String removeChar(String target, String[] char_regix) {
        for (String s : char_regix) {
            target = target.replace(s, "");
        }
        return target;
    }

    public static void copyFile(File isFile,File osFile){
        /*
        下载图片文件
         */
        try(FileInputStream is = new FileInputStream(isFile);
            FileOutputStream os = new FileOutputStream(osFile)){
            IOUtils.copy(is,os);
        }catch (IOException e){
            logger.error("文件[{}]不存在:{}", isFile.getAbsolutePath(), e);
        }
    }

    public static HexCell cell2HexCell(Cell cell){
        HexCell hexCell=new HexCell();
        if(cell==null) {return hexCell;}
        hexCell.setTl_col(cell.getTl_col());
        hexCell.setTl_row(cell.getTl_row());
        hexCell.setBr_col(cell.getBr_col());
        hexCell.setBr_row(cell.getBr_row());
        if(cell.getDescription().equalsIgnoreCase("body")){
            hexCell.setItemstring(cell.getCell_content_text().replace(" ",""));
        }else{
            hexCell.setItemstring(cell.getCell_content_text());
        }

        if(cell.getCell_coord_point().size()==4){
            Rectangle rect=new Rectangle();
            rect.x=cell.getCell_coord_point().get(0).x;
            rect.y=cell.getCell_coord_point().get(0).y;
            rect.width=cell.getCell_coord_point().get(2).x-rect.x;
            rect.height=cell.getCell_coord_point().get(2).y-rect.y;
            hexCell.setItemcoord(rect);
        }
        return hexCell;
    }


    public static HexRow findRow(ArrayList<Item> lstItems){
        final int HEIGHT_BUFFER=5;
        HexRow curRow=new HexRow();
        if(lstItems.size()==0){ return null;}
        Item header=lstItems.get(0);
        curRow.getRow().add (header);
        lstItems.remove(0);
        while(true) {
            boolean hasFind=false;
            for (Item item : lstItems) {
                if (new Rectangle(header.getItemcoord().x, header.getItemcoord().y, 10000, header.getItemcoord().height - HEIGHT_BUFFER)
                        .intersects(new Rectangle(item.getItemcoord().x, item.getItemcoord().y, item.getItemcoord().width, item.getItemcoord().height))) {
                    curRow.getRow().add(item);

                    lstItems.remove(item);
                    hasFind=true;
                    break;
                }
            }
            if(!hasFind){ break;}
            if(lstItems.size()==0) {break;}
        }
        if(curRow.getRow().size()==0){ return null;} else{ return curRow;}
    }

    /**
     * 得到段落信息
     *
     * @param lstItems
     * @return
     */
    public static HexRow findParage(ArrayList<Item> lstItems) {
        final int HEIGHT_BUFFER = 5;
        HexRow curRow = new HexRow();
        if (CollectionUtils.isEmpty(lstItems)) {
            return null;
        }
        Item item;
        item = lstItems.get(0);
        int paraNo = item.getParag().getParagNo();
        StringBuilder itemstring = new StringBuilder();
        itemstring = itemstring.append(item.getItemstring()).append(" ");
        lstItems.remove(0);
        Iterator<Item> itemIterator = lstItems.iterator();
        while (itemIterator.hasNext()) {
            Item item1 = itemIterator.next();
            if (item1.getParag().getParagNo() == paraNo || new Rectangle(item.getItemcoord().x, item.getItemcoord().y, 10000, item.getItemcoord().height - HEIGHT_BUFFER)
                    .intersects(new Rectangle(item1.getItemcoord().x, item1.getItemcoord().y, item1.getItemcoord().width, item1.getItemcoord().height))) {
                itemstring = itemstring.append(item1.getItemstring()).append(" ");
                itemIterator.remove();
            }
        }

        item.setItemstring(itemstring.toString());
        curRow.getRow().add(item);
        return curRow;
    }


    public static synchronized void heheCompressImg(File file) throws IOException {
        BufferedImage read = ImageIO.read(file);
        int height = read.getHeight();
        int width = read.getWidth();
        if (height>=10000||width>=10000) {
            logger.info("[合合引擎]开始压缩,文件大小:" + file.length());
            Thumbnails.of(file).size(1800,1800).toFile(file);
        }
    }

    public static synchronized void compressImg(String photoPath) throws IOException {
        File file = new File(photoPath);
        if (file.length() > 5242880) {
            logger.info("[合同识别]开始压缩,文件大小:" + file.length());
            Thumbnails.of(file).size(1800,1800).toFile(file);
        }
    }

    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-{1,}","");
    }

    public static BigDecimal stringToBigDecimal(String s){
        return new BigDecimal(NumberUtils.isNumber(s) ? s : "0");
    }

    public static double ratio(File file){
        try {
            BufferedImage read = ImageIO.read(file);
            int height = read.getHeight();
            int width = read.getWidth();
            BigDecimal heightDecimal = new BigDecimal(String.valueOf(height));
            BigDecimal widthDecimal = new BigDecimal(String.valueOf(width));
            double doubleValue = heightDecimal.divide(widthDecimal, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            return doubleValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  0d;
    }
}
