package com.lock;

import com.util.UnsafeInstance;
import sun.misc.Unsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;



public class ThreadCas {
    /**
     * 当前加锁状态,记录加锁的次数
     */
    private volatile int state = 0;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static ThreadCas cas = new ThreadCas();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Worker(),"t-0").start();
        Thread.sleep(100);
        new Thread(new Worker(),"t-1").start();
        Thread.sleep(100);
        new Thread(new Worker(),"t-2").start();
        Thread.sleep(100);
        new Thread(new Worker(),"t-3").start();
        Thread.sleep(100);
        new Thread(new Worker(),"t-4").start();
    }

    static class Worker implements Runnable{

        @Override
        public void run() {
            System.out.println("请求到达预定点,准备开始抢state:)"+Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                if(cas.compareAndSwapState(0,1)){
                    System.out.println("当前请求,抢到锁:"+Thread.currentThread().getName());
                }else{
                    System.out.println("当前请求,抢锁失败:"+Thread.currentThread().getName());
                }
            } catch (InterruptedException|BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 原子操作
     * @param oldValue
     *        oldvalue:线程工作内存当中的值
     * @param
     *        newValue:要替换的新值
     * @return
     */
    public final boolean compareAndSwapState(int oldValue,int newValue){
        return unsafe.compareAndSwapInt(this,stateOffset,oldValue,newValue);
    }

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(ThreadCas.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error();
        }
    }
}
/**
 19:51:10.327 [t-2] INFO com.yg.edu.lock.Juc04_Thread_Cas - 请求:t-2到达预定点,准备开始抢state:)
 19:51:10.327 [t-1] INFO com.yg.edu.lock.Juc04_Thread_Cas - 请求:t-1到达预定点,准备开始抢state:)
 19:51:10.327 [t-3] INFO com.yg.edu.lock.Juc04_Thread_Cas - 请求:t-3到达预定点,准备开始抢state:)
 19:51:10.327 [t-0] INFO com.yg.edu.lock.Juc04_Thread_Cas - 请求:t-0到达预定点,准备开始抢state:)
 19:51:10.327 [t-4] INFO com.yg.edu.lock.Juc04_Thread_Cas - 请求:t-4到达预定点,准备开始抢state:)
 19:51:10.334 [t-2] INFO com.yg.edu.lock.Juc04_Thread_Cas - 当前请求:t-2,抢锁失败!
 19:51:10.334 [t-3] INFO com.yg.edu.lock.Juc04_Thread_Cas - 当前请求:t-3,抢锁失败!
 19:51:10.334 [t-4] INFO com.yg.edu.lock.Juc04_Thread_Cas - 当前请求:t-4,抢到锁!
 19:51:10.334 [t-1] INFO com.yg.edu.lock.Juc04_Thread_Cas - 当前请求:t-1,抢锁失败!
 19:51:10.334 [t-0] INFO com.yg.edu.lock.Juc04_Thread_Cas - 当前请求:t-0,抢锁失败!
 此时只有一个抢到锁
 */
