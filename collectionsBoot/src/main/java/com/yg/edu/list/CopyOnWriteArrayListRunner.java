package com.yg.edu.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CopyOnWriteArrayListRunner {

    //使用ArrayList会报java.util.ConcurrentModificationException

    /**
     * 读线程
     * @author wangjie
     *
     */
    private static class ReadTask implements Runnable {
        List<String> list;

        public ReadTask(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (String str : list) {
                System.out.println(str);
            }
        }
    }
    /**
     * 写线程
     * @author wangjie
     *
     */
    private static class WriteTask implements Runnable {
        List<String> list;
        int index;

        public WriteTask(List<String> list, int index) {
            this.list = list;
            this.index = index;
        }

        @Override
        public void run() {
            list.remove(index);
            list.add(index, "write_" + index);
        }
    }

    public void run() {
        final int NUM = Runtime.getRuntime().availableProcessors();
        List<String> list = new CopyOnWriteArrayList<>();
        //List<String> list =new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            list.add("main_" + i);
        }
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(NUM, NUM,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < NUM; i++) {
            executorService.execute(new ReadTask(list));
            executorService.execute(new WriteTask(list, i));
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        new CopyOnWriteArrayListRunner().run();
    }
}
