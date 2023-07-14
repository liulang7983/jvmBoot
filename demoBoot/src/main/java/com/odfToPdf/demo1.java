package com.odfToPdf;

import org.ofdrw.converter.export.OFDExporter;
import org.ofdrw.converter.export.PDFExporterPDFBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ming.li
 * @date 2023/7/13 15:17
 */
public class demo1 {
    public static void main(String[] args) throws FileNotFoundException {
       /* String fmsFileFullPath="C:\\Users\\ken\\Desktop\\日志\\转\\27.ofd";
        String newFullPath= fmsFileFullPath.substring(0,fmsFileFullPath.lastIndexOf(".")+1)+"pdf";
        ConvertHelper.toPdf(new FileInputStream(fmsFileFullPath),new File(newFullPath));*/
        //ConvertHelper.
        Path ofdPath = Paths.get("C:\\Users\\ken\\Desktop\\日志\\转\\27.ofd");

        Path pdfPath = Paths.get("C:\\Users\\ken\\Desktop\\日志\\转\\27.pdf");

        try (OFDExporter exporter = new PDFExporterPDFBox(ofdPath, pdfPath)) {

            exporter.export();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
