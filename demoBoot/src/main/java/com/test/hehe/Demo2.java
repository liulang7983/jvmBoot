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
public class Demo2 {
    public static void main(String[] args) {
        OcrResultServiceImpl ocrResultService = new OcrResultServiceImpl();
        List<String> list=new ArrayList<>();
        list.add("C:\\Users\\14307\\Desktop\\pdf查重\\合合引擎\\1.json");
        Compare ocrResultFile = ocrResultService.getOcrResultFile(list, OcrEngineOpt.INTSIG);
        System.out.println(ocrResultFile);
    }
}
