package com.wangchong.blog.thread;

public class Shutdown {

    private  static class Runner implements  Runnable{

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {

            while(on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("count i = " + i);

        }

        public void cancel(){
            on = false;
        }
    }

    public static void main(String[] args) {
        Runner r = new Runner();
        Thread t = new Thread(r,"countthread");
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r.cancel();

    }
}
