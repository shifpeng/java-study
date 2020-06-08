package com.steven.shi.javastudy.server;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadDemo {

    private static class Th1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Th1");
            }
        }


    }

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();


        //1、
        new Th1().start();
        //2、
        new Thread(new Th2()).start();

        //3、lambda
        new Thread(() -> {
            System.out.println("");
        }).start();
        //4、FutureTask +Callable
//        Thread t = new Thread(new FutureTask<String>(new MyCall()));
//        t.start();

        //5、通过线程池
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.printf("");
        });
        service.shutdown();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }

        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    Thread.interrupted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread th3 = new Thread(() -> {
            try {
                th1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static class Th2 implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {

        }
    }



}
