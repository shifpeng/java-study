package com.steven.shi.javastudy.server;

import java.util.ArrayList;
import java.util.List;

public class ExceptionThread {
    public synchronized void exec() {
        int count = 0;
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count =" + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                int i = 1 / 0;
                System.out.println(i);
            }
        }

    }

    public static void main(String[] args) {
        ExceptionThread t = new ExceptionThread();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.exec();
            }
        };
        new Thread(r, "t1").start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r, "t2").start();
    }
}
