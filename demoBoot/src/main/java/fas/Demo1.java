package fas;

import java.util.UUID;

/**
 * @author ming.li
 * @date 2023/8/28 11:00
 */
public class Demo1 {
    public static void main(String[] args) {
        String uuid= UUID.randomUUID().toString().replace("-", "").toLowerCase();
        System.out.println(uuid);
    }
}
