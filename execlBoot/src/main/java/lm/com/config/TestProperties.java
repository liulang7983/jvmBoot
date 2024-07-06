package lm.com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ming.li
 * @Date 2024/7/5 14:38
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties("test")
public class TestProperties {
    public String url;
    public Integer port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
