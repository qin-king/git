package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo5 {
    public static void main (String [] args){

        AirConditioner1 airConditioner = new AirConditioner1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    try {
                        Thread.sleep(200);
                        airConditioner.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    try {
                        Thread.sleep(300);
                        airConditioner.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    try {
                        Thread.sleep(400);
                        airConditioner.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"C").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++) {
                    try {
                        Thread.sleep(500);
                        airConditioner.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"D").start();
    }
}
class AirConditioner1{
    private int number = 0;
    private Lock lock =new ReentrantLock();
    private Condition condition = lock.newCondition();
    public  void increment() throws InterruptedException {
        /*  synchronized(This)*/
        lock.lock();
        try {
            while(number !=0){
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName()+"\t" + number);
            //this.notifyAll();
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public   void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            --number;
            System.out.println(Thread.currentThread().getName()+"\t" + number);
            //this.notifyAll();
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

