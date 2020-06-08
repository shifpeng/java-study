package com.steven.shi.javastudy.server;

import java.math.BigDecimal;

public class BankAccount {
    private String name;
    private BigDecimal balance = new BigDecimal("0.00");

    public synchronized void set(String name, BigDecimal balance) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ BigDecimal  get(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        new Thread(() -> {
            bankAccount.set("张三", new BigDecimal(1000));
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(bankAccount.get("张三"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(bankAccount.get("张三"));
    }
}
