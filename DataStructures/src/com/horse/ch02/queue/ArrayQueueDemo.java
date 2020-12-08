package com.horse.ch02.queue;

import java.util.Scanner;

/**
 * @Description 不足之处：数组使用一次后就不能用，没有达到复用的效果
 * @Author Mr.Horse
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2020/12/8
*/
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner sc = new Scanner(System.in);

        boolean loop = true;
        while(loop) {
            System.out.println("-----------Test-----------");
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):显示队列头数据");
            System.out.println("--------------------------");

            key = sc.next().charAt(0); //接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据：");
                    int n = sc.nextInt();
                    queue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 'h':
                    try {
                        int res = queue.peekHead();
                        System.out.printf("队列头的数据是：%d\n", res);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    loop = false;
                    sc.close();
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 使用数组模拟一个queue
 */
class ArrayQueue {
    private int arrMaxSize; //数组的最大容量
    private int[] arr; //存放数据，模拟队列
    private int front; //队列头
    private int rear; //队列尾

    public ArrayQueue(int arrMaxSize) {
        this.arrMaxSize = arrMaxSize;
        arr = new int[arrMaxSize];
        front = -1; //指向队列头的前一个位置
        rear = -1; //指向队列尾部的位置
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == arrMaxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        if(isFull()) {
            System.out.println("队列已满，无法添加数据");
            return;
        }
        rear ++; //rear后移
        arr[rear] = n;
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列是空，无法获取数据");
        }
        front++; //front后移
        return arr[front];
    }

    // 显示队列的所有数据
    public void showQueue() {
        if(isEmpty()) {
            System.out.println("队列是空，没有数据");
            return;
        }
        for(int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]: %d\n", i, arr[i]);
        }
    }

    // 显示队列的头数据，但不取出
    public int peekHead() {
        if(isEmpty()) {
            throw new RuntimeException("队列是空，无法获取头数据");
        }
        return arr[front+1];
    }

}
