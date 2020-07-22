package com.lyc.state;

/**
 * @author laiyinc
 * @description         Runnable 转换为Waiting状态
 * @date 2020/7/22
 */
public class WaitingAndRunnable {
    public static void main(String[] args) {

    }
}


/**
 *  wait方法进入 使当前线程进入WAITING状态
 */
class WaitMethod{
    public static void main(String[] args) {
        Object lock = new Object();
        Thread a = new Thread(()->{waitMethod(lock);},"a");
        Thread b = new Thread(()->{waitMethod(lock);},"b");
        a.start();
        b.start();
        try{Thread.sleep(500);}catch (Exception e){}
        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
    }
    public static void waitMethod(Object lock){
        /**
         * lock: 共享变量，锁加在该对象上。
         *
         *  lock.wait();是指持有lock对象锁的线程挂起，并释放锁。只有等有另外线程持有该锁并执行lock.notify()方法时，才会被唤醒
         *
         *  lock.notify():随机唤醒 单个持有该lock对象锁并调用了wait()方法并被挂起的线程。
         *
         *  lock.notifyAll(); 唤醒所有 持有lock对象锁并调用了wait()方法被挂起的线程。
         */
        synchronized (lock){
            try{
                //唤醒线程
                lock.notify();
                System.out.println(Thread.currentThread().getName() + ":进入synchronized代码块,获取锁");
                System.out.println(Thread.currentThread().getName() + ":开始执行wait()....");
                //挂起当前线程
                lock.wait();
                System.out.println(Thread.currentThread().getName() + ":结束执行wait()....");
                Thread.sleep(1000);
                //唤醒 持有当前lock对象锁的被挂起的线程
                lock.notify();
            }catch (Exception e){
            }
        }
    }

}
