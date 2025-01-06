package util;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * @Author ming.li
 * @Date 2025/1/3 15:59
 * @Version 1.0
 */
public class PdfUtil {
    /**
     * 判断PDF是否为矢量
     * @param pdfPath
     * @return boolean
     */
    public static boolean checkVecPdf(String pdfPath) {
        String text = readPdf(pdfPath);
        if (StringUtils.isBlank(text)) {
            return false;
        }
        //是否为乱码,true乱码,false非乱码
        boolean isMessyCode=isContainXYYH(text);
        if (text.length() < 100 || !isMessyCode) {
            return false;
        }
        //需要添加条件判断是否是乱码
        return readNormalPdf(text);
    }
    /**
     * 判断pdf文件是否读取正常
     * @param strName
     * @return
     */
    public static boolean readNormalPdf(String strName) {
        char[] ch = strName.trim().toCharArray();
        float count = 0;
        for (char c : ch) {
            if (isChinese(c)) {
                count = count + 1;
                if (count > 10) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
    /**
     * 判断是否包含特殊编码,针对兴业银行的PDF问题
     * @param strName
     * @return
     */
    public static boolean isContainXYYH(String strName) {
        if (StringUtils.isEmpty(strName)) {
            return true;
        }
        //包含兴业银行交易流水的特殊字符
        return !strName.contains("෭๗൹ᥝඪ/තԻฃ");
    }
    /**
     * 读取PDF文件
     * @param pdfFilePath
     * @return 返回PDF文件内容
     */
    public static String readPdf(String pdfFilePath){
        String pdfFileInText = "";
        try {
            PDFTextStripper tStripper = new PDFTextStripper();
            tStripper.setStartPage(1);
            tStripper.setEndPage(1);
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            pdfFileInText = readPdfText(tStripper, document);
            document.close();

        } catch (Exception e) {
        }
        return pdfFileInText;
    }

    /**
     * 读取PDF文件
     * @param tStripper
     * @param document
     * @return
     * @throws IOException
     */
    private static String readPdfText(PDFTextStripper tStripper, PDDocument document) throws IOException {
        String pdfFileInText = tStripper.getText(document);
        pdfFileInText = pdfFileInText.replace("\\r\\n", "");
        pdfFileInText = pdfFileInText.replace(" ", "");

        String patternNewLines = "\\n|\\r|\\r\\n|\\t";
        //不要使用" "代替”\\s+“，不然得出的结果很玄学！！！
        String patternWhitespace = "\\s+";
        pdfFileInText = pdfFileInText.replaceAll(patternNewLines, "");
        pdfFileInText = pdfFileInText.replaceAll(patternWhitespace, "");
        return pdfFileInText;
    }
}
