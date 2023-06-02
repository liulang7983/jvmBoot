package com.model.contract;


import com.model.contract.page.*;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseOcrFile {
    public static final Logger logger = LoggerFactory.getLogger(ParseOcrFile.class);

    protected void ReadPage(String file,
                            PageContent page,
                            StringBuffer sbText,
                            LineNum lineNum,
                            WordIndexNum wordIndexNum) {


    }

    int[] ReviseCoordinate(int[] p) {
        int[] ret = new int[4];
        if (null == p || 8 != p.length) {
            return ret;
        }

        if (p[6] < p[0] && p[3] > p[1]) {
            //左高右低
            //x
            ret[0] = p[6];
            //y
            ret[1] = p[1];
            //width
            ret[2] = p[2] - p[6];
            //height
            ret[3] = p[5] - p[1];
        } else if (p[6] > p[0] && p[3] < p[1]) {
            //右低右高
            //x
            ret[0] = p[0];
            //y
            ret[1] = p[3];
            //width
            ret[2] = p[4] - p[0];
            //height
            ret[3] = p[7] - p[3];
        } else {
            //x
            ret[0] = p[0];
            //y
            ret[1] = p[1];
            //width
            ret[2] = p[2] - p[0];
            //height
            ret[3] = p[5] - p[3];
        }
        return ret;
    }

    protected void InitLineMaxWordIndex(int lineNum, PageContent page) {
        List<WordInfo> listWordInfo = new ArrayList();
        page.getWords().forEach((pageNum, wordInfo) -> {
            if (wordInfo.getLineNum() == lineNum) {
                listWordInfo.add(wordInfo);
            }
        });
        if (null == listWordInfo || 0 == listWordInfo.size()) {
            return;
        }
        int maxWordIndex = listWordInfo.stream().mapToInt(WordInfo::getWordIndex).max().getAsInt();
        int minWordIndex = listWordInfo.stream().mapToInt(WordInfo::getWordIndex).min().getAsInt();
        listWordInfo.forEach((wordInfo) -> {
            wordInfo.setLineMinWordIndex(minWordIndex);
            wordInfo.setLineMaxWordIndex(maxWordIndex);
            String key = String.format("%s_%d", wordInfo.getWordTxt(), wordInfo.getWordIndex());
            page.getWords().put(key, wordInfo);
        });
        listWordInfo.clear();
    }


}
