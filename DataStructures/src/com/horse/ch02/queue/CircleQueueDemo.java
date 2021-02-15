package com.horse.ch02.queue;

import java.util.Scanner;

/**
 * @Description 数组模拟环形队列
 * @Author Mr.Horse
 * @Version v1.0
 * @Since 1.0
 * @Date 2021/2/15
 */
public class CircleQueueDemo {
    public static void main(String[] args) {
        /*
        //测试一把
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        CircleArray queue = new CircleArray(4); //说明设置4, 其队列的有效数据最大是3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
        */

        CircleArray arr = new CircleArray(4);
        arr.addQueue(10);
        arr.addQueue(20);
        arr.addQueue(30);
        arr.addQueue(40);   //队列已满,无法添加元素

        System.out.println(arr.getQueue());
        System.out.println(arr.getQueue());
        System.out.println(arr.getQueue());
//        System.out.println(arr.getQueue()); //报错

        arr.showQueue();
        System.out.println(arr.size());
        System.out.println(arr.isEmpty());
        System.out.println(arr.isFull());
    }
}

class CircleArray {
    private int maxSize;    // 表示数组的最大容量,最大容量是(maxSize - 1)
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear 的初始值 = 0
    private int rear;
    private int[] arr;  // 该数据用于存放数据, 模拟队列

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //添加数据到队列中
    public void addQueue(int value) {
        // 判断队列是否满
        if(this.isFull()) {
            System.out.println("队列已满,无法添加元素");
            return;
        }

        //直接将元素添加
        arr[rear] = value;
        this.rear = (rear + 1) % maxSize;   //将 rear 后移, 这里必须考虑取模
    }

    // 获取队列的数据, 出队列
    public int getQueue() {
        //判断队列是否为空
        if(this.isEmpty()) {
            throw new RuntimeException("队列为空,无法取出数据");
        }

        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        if(this.isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }

        for(int i = front; i < front + size(); i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }

    // 求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        if(this.isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }

        return arr[front];
    }


}
