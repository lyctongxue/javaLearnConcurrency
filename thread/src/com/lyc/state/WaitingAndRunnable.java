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
 *
 *  等待线程执行终止的join()
 */
class JoinMethod{
    public static void main(String[] args) {

        Object lock = new Object();

        Thread a = new Thread(()->{
            try{ Thread.sleep(1000);}catch (Exception e){}

            System.out.println(Thread.currentThread().getName() + ":进入run方法，开始执...." );
            System.out.println(Thread.currentThread().getName() + ":线程sleep 1 s" );
            try{ Thread.sleep(1000);}catch (Exception e){}

            System.out.println(Thread.currentThread().getName() + ":run方法，结束执行...." );
        },"a");

        Thread b = new Thread(()->{
            try{ Thread.sleep(1000);}catch (Exception e){}

            System.out.println(Thread.currentThread().getName() + ":进入run方法，开始执...." );
            System.out.println(Thread.currentThread().getName() + ":线程sleep 1 s" );
            try{ Thread.sleep(1000);}catch (Exception e){}

            System.out.println(Thread.currentThread().getName() + ":run方法，结束执行...." );
        },"b");

        a.start();
        b.start();
        joitMethod(a,lock);     //阻塞main继续往下执行，直到线程a执行完毕.
        joitMethod(b,lock);     //阻塞main线程继续往下执行，直到线程b执行完毕
        System.out.println("main线程执行完毕...");
    }

    static void joitMethod(Thread thread,Object lock){
        synchronized (lock){
            System.out.println(thread.getName() + "执行join()....");
            try{ thread.join();}catch (Exception e){}
        }
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
        try{Thread.sleep(500);}catch (Exception e){}        //main线程暂停0.5s
        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
        lock.notify();  // throws IllegalMonitorStateException
    }
    public static void waitMethod(Object lock){
        /**
         * lock: 共享变量，锁加在该对象上。
         *
         *  lock.wait();是指持有lock对象锁的线程挂起，并释放锁。只有等有另外线程持有该锁并执行lock.notify()方法时，才会被唤醒
         *
         *  -- 只有当前线程获取了对象锁 lock后，才可以调用 lock.notify()方法，否则会抛出异常Illega!MonitorStateE ception
         *
         *  lock.notify():随机唤醒 单个持有该lock对象锁并调用了wait()方法并被挂起的线程。
         *
         *  lock.notifyAll(); 唤醒所有 持有lock对象锁并调用了wait()方法被挂起的线程。
         *
         *   被唤醒的线程可能无法立即从wait()方法返回并继续执行，必须重新回去锁lock才可以返回方法继续执行。
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
