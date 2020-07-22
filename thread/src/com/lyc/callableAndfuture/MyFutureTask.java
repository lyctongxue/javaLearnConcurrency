package com.lyc.callableAndfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author laiyinc
 * @description
 * @date 2020/7/21
 */
public class MyFutureTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 1000;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new MyFutureTask());
        executorService.submit(futureTask);
        System.out.println(futureTask.get());
    }
}
