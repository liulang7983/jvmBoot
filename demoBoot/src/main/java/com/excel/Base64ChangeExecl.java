package com.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author ming.li
 * @date 2023/6/2 17:43
 */
public class Base64ChangeExecl {

    public static Sheet decoderBase64Excel(String base64Code) throws Exception {
        // 将base64字符解码成字节流
        byte[] buffer = Base64.getDecoder().decode(base64Code);
        // 字节流转换为输入流
        InputStream input = new ByteArrayInputStream(buffer);
        // 解析成excel结构体
        Workbook workbook = WorkbookFactory.create(input);
        // 取excel的第一个sheet
        return workbook.getSheetAt(0);
    }

}
