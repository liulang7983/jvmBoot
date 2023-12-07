package lm.com.controller;

import lm.com.service.ExeclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ming.li
 * @date 2023/9/6 10:15
 */
@RestController
@RequestMapping("execl")
public class ExeclController {
    @Autowired
    private ExeclService execlService;

    @RequestMapping("execl1")
    public void execl1(HttpServletResponse res){
        execlService.execl1(res);
    }

    @RequestMapping("read")
    public void read(@RequestParam("file") MultipartFile file){
        try {
            execlService.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("read2")
    public void read2(@RequestParam("file") MultipartFile file){
        try {
            execlService.read2(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("copy")
    public void copy(@RequestParam("file") MultipartFile file){
        try {
            execlService.copy(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("deleteAndCopy")
    public void deleteAndCopy(){
        try {
            execlService.deleteAndCopy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
