package com.test.hehe;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author ming.li
 * @Date 2025/1/6 15:31
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args)throws Exception {
        // image
        File invoiceFile=new File("C:\\Users\\14307\\Desktop\\pdf查重\\报告\\01004a690.jpg");
        byte[] imgData = readFile(invoiceFile);
        URL realUrl = new URL("http://106.13.46.112:30007/ai/service/v2/recognize/table");
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "application/octet-stream");
        conn.setRequestProperty("x-ti-app-id", "84ceee3c9fb69843d5429d55615c9e6d");
        conn.setRequestProperty("x-ti-secret-code", "d6155b5d1aecf2897bc87b736948d527");
        conn.setRequestProperty("character","1");
        conn.setRequestProperty("straighten","1");
        conn.setRequestProperty("output_order","table_and_remain");
        conn.setRequestProperty("table_type_hint","table_with_line");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 设置请求方式
        conn.setRequestMethod("POST");
        String response = getResponse(conn, imgData);
        System.out.println(response);
    }

    private static String getResponse(HttpURLConnection conn, byte[] imgData) throws IOException {
        BufferedReader in = null;
        DataOutputStream out = null;
        String result = "";
        try {
            out = new DataOutputStream(conn.getOutputStream());
            out.write(imgData);
            out.flush();
            out.close();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }catch (Exception e){
            e.fillInStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static byte[] readFile(File file) {
        byte[] data = null;
        try (InputStream in = new FileInputStream(file)){
            data = new byte[in.available()];
            while (in.read(data) > 0) {
                // ...
            }
        } catch (IOException e) {
        }
        return data;
    }
}
