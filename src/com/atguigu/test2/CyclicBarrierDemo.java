package com.atguigu.test2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @auther zzyy
 * @create 2019-01-01 21:41
 */
public class CyclicBarrierDemo
{
    public static void main (String[] args)
    {
        //CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {System.out.println("****集齐7颗龙珠，召唤神龙");});

        for (int i = 1; i <=7; i++) {
            final int tmpI = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 收集到第："+tmpI+"\t 颗龙珠 ");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
