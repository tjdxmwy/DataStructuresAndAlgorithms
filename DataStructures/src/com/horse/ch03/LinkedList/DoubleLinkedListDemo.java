package com.horse.ch03.LinkedList;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @Description 双向链表
 * @Author Mr.Horse
 * @Date 2021/2/20
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        // 先创建节点
        Hero hero1 = new Hero(1, "宋江", "及时雨");
        Hero hero2 = new Hero(2, "卢俊义", "玉麒麟");
        Hero hero3 = new Hero(3, "吴用", "智多星");
        Hero hero4 = new Hero(4, "林冲", "豹子头");

        //添加
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();

        //修改
        Hero newHero = new Hero(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHero);
        System.out.println("修改后的链表");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();


    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    // 先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private Hero head = new Hero(0, "", "");

    // 返回头节点
    public Hero getHead() {
        return head;
    }

    // 遍历双向链表的方法
    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        Hero temp = head.next;
        while(true) {
            if(temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 添加一个节点到双向链表的最后.
    public void add(Hero hero) {
        Hero temp = head;
        while(true) {
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = hero;
        hero.pre = temp;
    }

    // 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
    // 只是 节点类型改成 hero
    public void update(Hero hero) {
        // 判断是否空
        if(head.next == null) {
            System.out.println("链表为空，无法更新");
            return;
        }
        // 找到需要修改的节点, 根据no编号
        // 定义一个辅助变量
        Hero temp = head.next;
        boolean flag = false;
        while(true) {
            if(temp == null) {
                break;
            }
            if(temp.no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag) {
            temp.name = hero.name;
            temp.nickname = hero.nickname;
        } else {
            System.out.printf("当前节点编号 %d 不存在，无法更新", hero.no);
        }
    }

    // 从双向链表中删除一个节点,
    // 说明
    // 1 对于双向链表，我们可以直接找到要删除的这个节点
    // 2 找到后，自我删除即可
    public void del(int no) {
        // 判断当前链表是否为空
        if(head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }

        Hero temp = head.next;
        boolean flag = false;
        while(true) {
            if(temp == null) {
                break;
            }

            if(temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag) {
            temp.pre.next = temp.next;
            // 这里我们的代码有问题?
            // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if(temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }



}

// 定义Hero, 每个Hero对象就是一个节点
class Hero {
    public int no;
    public String name;
    public String nickname;
    public Hero next; // 指向下一个节点, 默认为null
    public Hero pre; //指向前一个节点，默认是null

    //构造方法
    public Hero(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

