package com.wangchong.blog.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Task {

    private static final Exchanger<String> ex = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(3);

  //  private static String A;
  //  private static String B;

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
               String A = "银行流水A";
                try {
                    ex.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    String B = "银行流水B";
                    String A=ex.exchange(B);
                    System.out.println(A);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        threadPool.shutdown();



    }
}
