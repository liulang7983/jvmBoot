package com.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.excel.ExcelDataListener;
import com.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author ming.li
 * @date 2023/5/11 19:19
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public String upload(MultipartFile file) {
        byte[] bytes = xls2Bytes(file);
        if (bytes == null) {
            return "解析失败";
        }
        /*
         初始化一个监听器
         */
        ExcelDataListener excelDataListener = new ExcelDataListener();
        /*
         读取文件数据
         */
        InputStream inputStream = new ByteArrayInputStream(bytes);
        EasyExcelFactory.read(inputStream, excelDataListener).sheet().doRead();
        try {
            inputStream.close();
        } catch (IOException e) {

        }
        List<Map<Integer, String>> dataList = excelDataListener.getDataList();
        for (int i = 0; i < dataList.size(); i++) {
            Map<Integer, String> map = dataList.get(i);
            int size = map.size();
            for (int j = 0; j < size; j++) {
                System.out.println(map.get(j));
            }
            System.out.println("获取完一行");

        }
        return "成功";
    }

    private static byte[] xls2Bytes(MultipartFile file) {
        byte[] bytes = null;
        try {
            if (file != null) {
                bytes = file.getBytes();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return bytes;
    }
}
