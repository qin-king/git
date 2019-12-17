package com.atguigu.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo3 {
    public static void main(String[] args) {
        Ticket1 ticket = new Ticket1();
        new Thread(() -> {
            for (int i = 0; i <30 ; i++) ticket.sale();
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i <30 ; i++) ticket.sale();
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i <30 ; i++) ticket.sale();
        },"C").start();
    }
}

class Ticket1 {
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