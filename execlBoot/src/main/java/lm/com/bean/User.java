package lm.com.bean;

/**
 * @author ming.li
 * @date 2023/5/18 10:48
 */
public class User {
    private Float id;
    private String name;
    private String message;

    public Float getId() {
        return id;
    }

    public void setId(Float id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public User(){

    }

    public User(Float id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
