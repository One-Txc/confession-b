package com.didispace.util;

/**
 * @author: txc
 * @date: 18-11-15 下午5:19
 */
public class ArraySort implements Runnable {

    private int number;

    public ArraySort(int number) {
        this.number = number;
    }

//    public static void main(String[] args) {
//        int[] numbers = new int[]{339,102, 338,338,339, 62, 9132, 580, 666,101};
//        for (int number : numbers) {
//            new Thread(new ArraySort(number)).start();
//        }
//    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.number);
            System.out.println(this.number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
