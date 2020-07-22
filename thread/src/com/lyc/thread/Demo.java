package com.lyc.thread;

/**
 * @author laiyinc
 * @description         第一个多线程
 * @date 2020/7/21
 */
public class Demo {

    public static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("thread");
        }
    }
    public static void main(String[] args) throws Exception {
        MyThread myThread = new MyThread();
        myThread.start();

        //Java8 函数式编程
        Thread thread = new Thread(()->{
            System.out.println("Java8 匿名内部类");
        });
        thread.start();
    }

}
