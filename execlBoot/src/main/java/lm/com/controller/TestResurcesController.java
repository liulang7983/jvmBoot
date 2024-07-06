package lm.com.controller;

import lm.com.config.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ming.li
 * @Date 2024/7/5 14:29
 * @Version 1.0
 */
@RestController
@RequestMapping("testResurces")
public class TestResurcesController {
    @Value("${server.port:8090}")
    public Integer port;

    @Autowired
    private TestProperties testProperties;

    @RequestMapping("/getPort")
    public Integer getPort(){
        return port;
    }

    @RequestMapping("/testProperties")
    public String testProperties(){
        return testProperties.getUrl()+"端口:"+testProperties.getPort();
    }

}
