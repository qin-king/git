package com.atguigu.com.atguigu.test3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author King-qin
 * @create 2019-12-17 10:57
 */
public class Demo {
    public static void main(String[] args) {
        ExecutorService thread = Executors.newFixedThreadPool(3);
        Tickets ticket = new Tickets();
        try {
            for (int i=1;i<=30;i++){
                thread.execute(()->{ticket.sale();});
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            thread.shutdown();
        }

    }
}
class Tickets {
    private int num = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "\t 卖出第" + (num--) + "\t剩余 " + num);
            }
        } finally {
            lock.unlock();
        }
    }
}