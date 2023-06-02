package com.model.file.delegate;


import com.model.contract.page.LineNum;
import com.model.contract.page.PageContent;
import com.model.contract.page.WordIndexNum;

/**
 * 解析ocr处理后返回的文件，用于参数传递回调
 * Created by kang on 2019-1-25.
 */
@FunctionalInterface
public interface IParseOcrFile {

    /**
     * 读取每一页内容
     * @param file                  文件名称
     * @param page                  页对象
     * @param sbText                内容
     * @param lineNum               文本块行号(引用传递)
     * @param wordIndexNum          文本块中的字在全文中的位置(引用传递)
     */
    void ReadEachPage(String file,
                      PageContent page,
                      StringBuffer sbText,
                      LineNum lineNum,
                      WordIndexNum wordIndexNum);
}
