package com.steven.shi.javastudy.server;

public class T {
    public static int count = 10;

    public synchronized static void test() {
        count--;
    }

    public void test1() {
        synchronized (this) {
            count--;
        }
    }
    //以上两个方法是等值的
}
