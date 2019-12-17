package com.atguigu.test;

public class lambda {
    public static void main(String[] args) {

       Foo foo=(a,b)->{
            System.out.println("dfd");
            return a + b;
        };
        System.out.println(foo.add(3,39));
        System.out.println(foo.div(4,3));
        System.out.println(Foo.mul(4,3));
    }
}
@FunctionalInterface
interface Foo{
    public int add(int a,int b);
    default int div(int a,int b){
        return a/b;
    }
    static int mul(int x,int y){
        return x*y;
    }
}