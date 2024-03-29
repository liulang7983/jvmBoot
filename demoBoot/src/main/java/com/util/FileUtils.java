package com.util;

import lombok.SneakyThrows;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileUtils {

    /**
     * 将数据保存到指定文件路径
     */
    public static void saveDataToFile(String filePath,byte[] data) throws IOException {
        File file = new File(filePath);
        if(!file.exists())file.createNewFile() ;
        FileOutputStream outStream = new FileOutputStream(file);
        outStream.write(data);
        outStream.flush();
        outStream.close();
    }

    /**
     * 读取文件数据
     */
    public static byte[] getFileBytes(File file) throws IOException {
        if(!file.exists()||(file.exists()&&!file.isFile())) {
            return null;
        }
        InputStream inStream=new FileInputStream(file);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 读取文本文件字
     */
    public static String getFileText(String path){
        try {
            byte[] data= getFileBytes(new File(path));
            return new String(data);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 清空文件夹下所有文件
     */
    public static void cleanDir(File dir){
        if(dir!=null&&dir.isDirectory()){
            for(File file:dir.listFiles()){
                if(file.isFile()) {
                    file.delete();
                }
                if(file.isDirectory()) {
                    cleanDir(file);
                }
            }
        }
    }

    @SneakyThrows
    public static void changeFile(File file)  {
        try {
            BufferedImage read = ImageIO.read(file);
            int height = read.getHeight();
            int width = read.getWidth();
            if (height>=1000||width>=1000) {
                Thumbnails.of(file).size(1000,1000).toFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文本文件，若文件存在，则在文件末尾追加
     *
     * @param filePath 文本文件路径
     * @param text     写入的文本内容
     */
    public static void writeTextFile(String filePath, String text) {
        if (null == text || 0 == text.length()) {
            return;
        }
        File file = new File(filePath);
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            if (!file.exists() && file.createNewFile()) {
            }
            fileWriter.write(text);
            fileWriter.write("\r\n");
        } catch (Exception e) {
        }
    }
    /**
     * 写入文本文件，若文件存在，则覆盖性写入
     *
     * @param filePath 文本文件路径
     * @param text     写入的文本内容
     */
    public static void writeTextFile1(String filePath, String text) {
        if (null == text || 0 == text.length()) {
            return;
        }
        File file = new File(filePath);
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            if (!file.exists() && file.createNewFile()) {
            }
            fileWriter.write(text);
            fileWriter.write("\r\n");
        } catch (Exception e) {
        }
    }
}
