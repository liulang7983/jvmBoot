package test;

/**
 * 观察者模式
 * @Author ming.li
 * @Date 2025/9/3 14:24
 * @Version 1.0
 */
import java.util.ArrayList;
import java.util.List;

// 观察者接口
interface Observer {
    void update(String news);
}

// 被观察者接口
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// 新闻主题（被观察者）
class NewsAgency implements Subject {
    private List<Observer> observers;
    private String latestNews;

    public NewsAgency() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }

    // 设置新闻并通知观察者
    public void setNews(String news) {
        this.latestNews = news;
        notifyObservers();
    }
}

// 具体观察者：报纸
class Newspaper implements Observer {
    private String name;

    public Newspaper(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " 报纸 news: " + news);
        // 处理新闻...
    }
}

// 具体观察者：电视
class TVStation implements Observer {
    private String channel;

    public TVStation(String channel) {
        this.channel = channel;
    }

    @Override
    public void update(String news) {
        System.out.println(channel + " 电视 news: " + news);
        // 处理新闻...
    }
}

// 使用示例
class ObserverDemo {
    public static void main(String[] args) {
        // 创建新闻机构
        NewsAgency newsAgency = new NewsAgency();

        // 创建观察者
        Observer newspaper1 = new Newspaper("报纸观察者1");
        Observer newspaper2 = new Newspaper("报纸观察者2");
        Observer tvStation = new TVStation("电视观察者2");

        // 注册观察者
        newsAgency.registerObserver(newspaper1);
        newsAgency.registerObserver(newspaper2);
        newsAgency.registerObserver(tvStation);

        // 发布新闻
        newsAgency.setNews("发布第一条消息!");

        // 移除一个观察者
        newsAgency.removeObserver(newspaper2);
        System.out.println("发布第二条消息");

        // 再次发布新闻
        newsAgency.setNews("发布第三条消息");
    }
}

