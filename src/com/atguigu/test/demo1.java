package com.atguigu.test;

public class demo1 {
    public static void main(String[] args){
        Ticket ticket =new Ticket();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"A").start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"B").start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"C").start();
    }
}
class Ticket{
private int num=30;

    public synchronized void sale() {
        if(num>0){
            System.out.println(Thread.currentThread().getName()+"\t 卖出第"+(num--)+"\t剩余 "+num);
        }
    }
}
