package com.test.hehe;

import com.model.contract.OcrEngineOpt;
import com.model.contract.page.Compare;
import com.service.impl.OcrResultServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2025/1/6 15:39
 * @Version 1.0
 */
public class Demo3 {
    public static void main(String[] args) {
        OcrResultServiceImpl ocrResultService = new OcrResultServiceImpl();
        List<String> list=new ArrayList<>();
        list.add("C:\\Users\\14307\\Desktop\\pdf查重\\35\\矢量\\矢量封装.json");
        Compare ocrResultFile = ocrResultService.getOcrResultFile(list, OcrEngineOpt.PDRREAD);
        System.out.println(ocrResultFile);
    }
}
