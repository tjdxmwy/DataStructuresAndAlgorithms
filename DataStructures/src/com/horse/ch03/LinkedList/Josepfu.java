package com.horse.ch03.LinkedList;

/**
 * @Description 约瑟夫
 * @Author Mr.Horse
 * @Date 2021/2/20
 */
public class Josepfu {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.show();

        circleSingleLinkedList.countBoy(1,2,5); //2->4->1->5->3
    }
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
        for(int i = 1; i <= num; i++) {
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
            System.out.printf("小孩的编号 %d \n", curBoy.id);
            if(curBoy.next == first) {
                break;
            }

            curBoy = curBoy.next;
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo
     *      表示从第几个孩子开始数数
     * @param countNum
     *      表示数几下
     * @param nums
     *      一共有多少个小孩
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //1.校验
        if(first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误");
            return;
        }

        //2.创建要给辅助指针,帮助完成小孩出圈(找出first的前一个节点)
        Boy helper = first;
        while(true) {
            if(helper.next == first) {
                break;
            }
            helper = helper.next;
        }

        //3.将helper和first同时移动(startNo - 1)次
        for(int i=0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        //4.小孩报数时,将helper和first同时移动(countNum-1)次
        while(true) {
            if(helper == first) {
                break;
            }

            for(int j = 0; j < countNum - 1; j++) {
                first = first.next;
                helper = helper.next;
            }
            System.out.printf("出圈的小孩编号: %d ", first.id);

            first = first.next;
            helper.next = first;
        }

        System.out.printf("留在圈的小孩编号: %d ", first.id);


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
