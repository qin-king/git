package com.atguigu.test;

/**
 * @author King-qin
 * @create 2019-12-16 19:06
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
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
class AirConditioner{
    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        /*  synchronized(This)*/
      while(number !=0){
            this.wait();
        }
        ++number;
        System.out.println(Thread.currentThread().getName()+"\t" + number);
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {

        while (number == 0) {
          this.wait();
        }
        --number;
        System.out.println(Thread.currentThread().getName()+"\t" + number);
        this.notifyAll();
    }
}
