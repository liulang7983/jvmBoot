package com.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author ming.li
 * @date 2023/6/2 15:21
 */
public class PdfToImageTask implements Callable<List<String>> {
    private float dpi;
    private int startIndex;
    private int endIndex;
    private String filePath;
    private String exportPath;
    private List<Integer> rangeList;

    public PdfToImageTask(String exportPath, String filePath, int startIndex, int endIndex, float dpi, List<Integer> rangeList) {
        this.dpi = dpi;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.filePath = filePath;
        this.exportPath = exportPath;
        this.rangeList = rangeList;
    }

    @Override
    public List<String> call() throws Exception {
        PDDocument doc = null;
        ArrayList imageFiles = new ArrayList();

        ArrayList var4;
        try {
            File pdfFile = new File(this.filePath);
            doc = PDDocument.load(pdfFile);
            PDFRenderer renderer = new PDFRenderer(doc);
            ArrayList var17;
            if (this.endIndex - this.startIndex < 0) {
                var17 = imageFiles;
                return var17;
            }

            for(int index = this.startIndex; index <= this.endIndex; ++index) {
                String photoName = this.rangeList.get(index) + ".jpg";
                String imgFilePath = this.exportPath + File.separator + photoName;
                File file = new File(imgFilePath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                BufferedImage image = renderer.renderImageWithDPI((Integer)this.rangeList.get(index) - 1, this.dpi);
                if (image.getHeight() > 1920) {
                    float scale = (float)(1920.0D / (double)image.getHeight());
                    image = Thumbnails.of(new BufferedImage[]{image}).scale((double)scale).asBufferedImage();
                }

                ImageIO.write(image, "JPG", file);
                imageFiles.add(this.filePath);
            }

            var17 = imageFiles;
            return var17;
        } catch (Exception var14) {
            var4 = imageFiles;
        } finally {
            if (doc != null) {
                doc.close();
            }

        }

        return var4;
    }
}

