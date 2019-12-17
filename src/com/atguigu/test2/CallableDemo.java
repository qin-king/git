package com.atguigu.test2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class MyThread implements Callable<Integer>
{
    @Override
    public Integer call() throws Exception
    {
        System.out.println("*******come in call");
        return 1024;
    }
}


/**
 * @auther zzyy
 * @create 2019-02-18 17:11
 * java
 * 多线程中，第3种获得多线程的方式
 *
 */
public class CallableDemo
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        FutureTask futureTask = new FutureTask(new MyThread());

        new Thread(futureTask,"A").start();

        System.out.println(futureTask.get());
    }
}
