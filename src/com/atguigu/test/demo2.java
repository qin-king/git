package com.atguigu.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo2 {
    public static void main(String[] args) {
        Tickets ticket = new Tickets();
        new Thread(new Runnable(){
            @Override
            public void run() {
          for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();
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