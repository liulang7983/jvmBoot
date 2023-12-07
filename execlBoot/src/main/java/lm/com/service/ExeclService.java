package lm.com.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ming.li
 * @date 2023/9/6 10:19
 */
public interface ExeclService {
    void execl1(HttpServletResponse res);
    void read(MultipartFile file) throws IOException;
    void read2(MultipartFile file) throws Exception;
    void copy(MultipartFile file) throws Exception;
    void deleteAndCopy() throws Exception;
}
