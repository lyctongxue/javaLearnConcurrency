package com.lyc.state;

/**
 * @author laiyinc
 * @description
 * @date 2020/7/22
 */
public class RunnableAndBlocked {
    public static void main(String[] args) throws Exception{
        Thread a = new Thread(()->{
            sleepMethod();
        },"a");
        Thread b = new Thread(()->{
            sleepMethod();
        },"b");
        a.start();                       //线程a启动
        Thread.sleep(1000L);      //主线程休眠1s
        b.start();                       //主线程休眠完，启动b线程

        System.out.println( a.getName() + ":" + a.getState());  //此时a线程获取锁，并休眠2s.状态是 TIMED_WAITING
        System.out.println( b.getName() + ":" + b.getState());  //此时b线程还在等待获取锁， 状态是 BLOCKED
    }

    static synchronized void sleepMethod(){
        try{
            System.out.println("进入睡眠方法....");
            Thread.sleep(2000);
        }catch (Exception e){
        }
    }
}
