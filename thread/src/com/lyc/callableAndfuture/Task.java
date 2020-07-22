package com.lyc.callableAndfuture;

import java.util.concurrent.*;

/**
 * @author laiyinc
 * @description
 * @date 2020/7/21
 */
public class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 100;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> submit = executorService.submit(task);
        boolean cancel = submit.cancel(false);
        System.out.println(cancel);
        Integer integer = submit.get();
        System.out.println(integer);
    }
}
