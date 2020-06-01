package com.steven.shi.javastudy.dto;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Person {
    private int i = 0;
    private static sun.misc.Unsafe UNSAFE;
    private static long I_OFFSET;

    static {
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
            I_OFFSET = UNSAFE.objectFieldOffset(Person.class.getDeclaredField("i"));  //计算对象的偏移量  获取person对象的i属性的偏移量

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        final Person person = new Person();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    person.i++;
                    boolean b = UNSAFE.compareAndSwapInt(person, I_OFFSET, person.i, person.i + 1);  //如果当前值正好等于person.i，那么+1
                    if (b) {
                        System.out.println(UNSAFE.getIntVolatile(person, I_OFFSET));
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    boolean b = UNSAFE.compareAndSwapInt(person, I_OFFSET, person.i, person.i + 1);  //如果当前值正好等于person.i，那么+1
                    if (b) {
                        System.out.println(UNSAFE.getIntVolatile(person, I_OFFSET));
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
