package com.lyc.threadGroup;

/**
 * @author laiyinc
 * @description     线程优先级
 * @date 2020/7/21
 */
public class PriorityDemo {
    public static void main(String[] args) {
        /**
         *  线程优先级 1~10
         *  线程优先级，默认是5，理论上，高优先级的线程将会比低优先级的线程有更高的几率执行
         *  我们使用方法Thread类的setPriority()实例方法来设定线程的优先级。
         */
        Thread thread = new Thread();
        thread.getThreadGroup().setMaxPriority(4);
        System.out.println("线程默认优先级："+thread.getPriority());
        thread.setPriority(10);
        System.out.println("设置线程的优先级" + thread.getPriority());







    }
}
