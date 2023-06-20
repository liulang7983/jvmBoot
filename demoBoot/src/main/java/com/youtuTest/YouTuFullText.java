package com.youtuTest;

import com.model.contract.OcrEngineOpt;
import com.model.contract.page.Compare;
import com.model.file.OcrFileFactory;
import com.model.file.OcrFilePool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/6/15 15:39
 */
public class YouTuFullText {
    public static void main(String[] args) {
        OcrFileFactory ocrFileFactory = OcrFileFactory.GetInstance();
        OcrFilePool filePool = ocrFileFactory.getOcrFilePoolInstance(OcrEngineOpt.TABLE);
        Compare compare = new Compare();
        String path = "C:\\Users\\ken\\Desktop\\日志\\识别结果\\247dc47a909245d8bf568e5ae2d61b84.json";
        File dir = new File(path);
        List<String> list=new ArrayList<>();
        list.add(path);
        filePool.ReadOcrFile(list,compare);
        System.out.println("sfsfs");
    }
}
