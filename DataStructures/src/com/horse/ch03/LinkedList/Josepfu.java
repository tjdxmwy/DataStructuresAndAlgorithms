package com.horse.ch03.LinkedList;

/**
 * @Description 约瑟夫
 * @Author Mr.Horse
 * @Date 2021/2/20
 */
public class Josepfu {
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first节点,当前没有编号
    private Boy first = null;

    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int num) {
        if(num < 1) {
            System.out.println("num值不正确");
            return;
        }

        //辅助指针
        Boy curBoy = null;
        for(int i = 1; i < num; i++) {
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if(i == 1) {
                first = boy;
                first.next = first; // 构成环
                curBoy = first;   // 让curBoy指向第一个小孩
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void show() {
        // 判断链表是否为空
        if(first == null) {
            System.out.println("环形链表为空");
            return;
        }

        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while(true) {
            if(curBoy.next == first) {
                break;
            }
            System.out.printf("小孩的编号 %d \n", curBoy.id);
            curBoy = curBoy.next;
        }
    }


}

// 创建一个Boy类，表示一个节点
class Boy {
    public int id;  // 编号
    public Boy next;    // 指向下一个节点,默认null

    public Boy(int id) {
        this.id = id;
    }
}
