package com.service.impl;



import com.model.contract.OcrEngineOpt;
import com.model.contract.page.Compare;
import com.model.file.OcrFileFactory;
import com.model.file.OcrFilePool;

import java.util.List;


public class OcrResultServiceImpl{

    public Compare getOcrResultFile(List<String> list, OcrEngineOpt ocrEngineOpt){
        Compare compare = new Compare();

        OcrFileFactory ocrFileFactory = OcrFileFactory.GetInstance();
        OcrFilePool filePool = ocrFileFactory.getOcrFilePoolInstance(ocrEngineOpt);
        filePool.ReadOcrFile(list,compare);
        return compare;
    }

    /*public static void main(String[] args) {
        OcrFileFactory ocrFileFactory = OcrFileFactory.GetInstance();
        OcrFilePool filePool = ocrFileFactory.getOcrFilePoolInstance(OcrEngineOpt.TABLE);
        Compare compare = new Compare();
        String path = "D:\\ocrData\\hex_table";
        File dir = new File(path);
        File[] files= dir.listFiles();
        List<File> listFileDir= Arrays.asList(files);
        filePool.ReadOcrFile(listFileDir,compare);
        System.out.println("sfsfs");
    }*/

   /* public static void main(String[] args) {
        OcrFileFactory ocrFileFactory = OcrFileFactory.GetInstance();
        OcrFilePool filePool = ocrFileFactory.getOcrFilePoolInstance(OcrEngineOpt.INTSIG);
        Compare compare = new Compare();
        String path = "D:\\ocrData\\hehe";
        File dir = new File(path);
        File[] files= dir.listFiles();
        List<File> listFileDir= Arrays.asList(files);
        filePool.ReadOcrFile(listFileDir,compare);
        System.out.println("sfsfs");
    }*/

}
