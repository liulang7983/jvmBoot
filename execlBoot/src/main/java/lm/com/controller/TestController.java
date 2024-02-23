package lm.com.controller;

import lm.com.bean.DownloadVo;
import lm.com.service.ExeclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ming.li
 * @date 2023/9/6 10:15
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private ExeclService execlService;

    @RequestMapping("download")
    public void download(HttpServletResponse res, DownloadVo downloadVo){
        execlService.execl1(res);
    }

}
