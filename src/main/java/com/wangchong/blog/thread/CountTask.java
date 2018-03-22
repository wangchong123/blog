package com.wangchong.blog.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask {

    private static final int THRESHOLD = 100000;
    private int start;
    private int end;

    public CountTask(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Object compute() {
        int sum = 0;
        boolean can = (end - start) <= THRESHOLD;
        if(can){
            for(int i = start; i <= end; i++){
                sum += i;
            }
        }else{
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle + 1,end);
            leftTask.fork();
            rightTask.fork();
            int leftResult = (int) leftTask.join();
            int rightResult = (int) rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1,100000000);
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long e = System.currentTimeMillis();
        System.out.println(e-s);
    }
}
