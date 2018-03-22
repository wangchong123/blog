package com.wangchong.blog.thread;

import org.apache.poi.ss.formula.functions.T;

public class One {

    /**
     * JAVA线程(1)
     * 进程与线程：
     * 操作系统在运行一个程序时，会为其创建一个进程，在一个进程里又会创建多个线程，这些线程拥有各自的计数器、堆栈和局部变量等属性，
     * 并且能够访问共享的内存变量。处理器在这些线程上高速切换，让用户感觉到这些线程在同时运行。
     * 多线程好处：
     * 更多的处理器核心：单线程程序在运行时只能使用一个处理器核心，这就浪费了其他的核心资源，而多线程能更好的利用多个处理器核心，
     * 显著减少程序处理时间，提高效率。更快的响应时间：一些复杂的业务逻辑包括许多操作，如果一一串行执行，直到完毕，可能需要很多时间，
     * （很多时间是相对而言，毕竟一个hello world很短很短！），但如果采用多线程技术，就可以将数据一致性不强的操作派发给其他线程，
     * 可以使响应用户请求的线程能够尽快的处理完成，缩短响应时间，提升用户体验。更好的编程模型。
      线程的创建：
     * 继承Thread类
     * 实现runable接口
     * 通过Callable和FutureTask创建线程
     * 线程池
     *
     * JAVA线程（2）
     * 线程启动：start()方法，
     * 暂停：suspend();恢复:resume();停止：stop();这三个方法已过期，不建议使用，因为这些方法在调用后不会保证搜持有资源的释放，
     * 导致程序可能工作在不确定状态下。
     * 线程中断：interrupt():中断可以理解为线程的一个标志位属性，其他线程通过调用方法interrupt()对其进行中断操作，线程通过方法
     * isInterrupted()来进行判断是否被中断。如果线程处于非阻塞状态，被中断后调用isInterrupted()会返回true，若处于阻塞状态
     * （sleep(),wait(),join()），即使被中断过，也会返回false;可以根据此状态来对线程进行操作。
     * 终止线程：可以通过中断的标志位或者设置boolean变量来终止任务
     */

    Thread thread = new Thread(){
        @Override
        public void run() {
            System.out.println("thread");
        }
    };

    Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });

    public static void main(String[] args) {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                //被中断是的操作，如：终止线程等

            }else{
                //do something...
            }
        }
    }
}
