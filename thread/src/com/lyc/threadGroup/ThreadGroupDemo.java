package com.lyc.threadGroup;

/**
 * @author laiyinc
 * @description
 * @date 2020/7/21
 */
public class ThreadGroupDemo {
    public static void main(String[] args) {
//        f1();
        f2(Thread.currentThread().getThreadGroup());
    }


    /**
     * 线程组复制
     * @param threadGroup
     */
    public static void f2(ThreadGroup threadGroup ){
        Thread[] threads = new Thread[threadGroup.activeCount()];
        ThreadGroup threadGroup1 = new ThreadGroup("copyThreadGroup");
        threadGroup.enumerate(threads);
    }

    public static void f1(){
        Thread thread = new Thread(()->{
            System.out.println("当前线程的名称：" + Thread.currentThread().getName());
            System.out.println("当前线程组的名称：" + Thread.currentThread().getThreadGroup().getName());
        });
        thread.start();
        System.out.println("main线程的名称：" + Thread.currentThread().getName());
        System.out.println("main线程组的名称：" + Thread.currentThread().getThreadGroup().getName());
    }

}
