package lm.com.controller;

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
@RequestMapping("execl")
public class ExeclController {
    @Autowired
    private ExeclService execlService;

    @RequestMapping("execl1")
    public void execl1(HttpServletResponse res){
        execlService.execl1(res);
    }
}
