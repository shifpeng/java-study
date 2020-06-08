package com.steven.shi.javastudy.server;

public class T implements Runnable {
    public /*volatile*/ int count = 10;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "thread" + i).start();
        }
    }
}
