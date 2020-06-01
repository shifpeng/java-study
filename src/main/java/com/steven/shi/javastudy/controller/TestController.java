package com.steven.shi.javastudy.controller;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class TestController {

    public static void main(String[] args) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("1", "1");
//        String oldVaue = hashMap.put("1", "2");
//        String newValue = hashMap.get("1");
//
//        System.out.printf("旧值为" + oldVaue + "\n");
//        System.out.printf("新值为" + newValue);

        ReentrantLock reentrantLock = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.printf("线程1获取到了锁");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                reentrantLock.unlock();
            }
        }).start();

        //这里保证线程1先获得锁
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
//                reentrantLock.lock();  //lock的阻塞状态下，是不消耗cpu的
               while (!reentrantLock.tryLock())  //使用while是消耗cpu
               {
                    //但是这个方法的好处是，在阻塞状态下，可以做其他的事情
               }
                System.out.printf("线程2获取到了锁");
                reentrantLock.unlock();
            }
        }).start();

    }


}
