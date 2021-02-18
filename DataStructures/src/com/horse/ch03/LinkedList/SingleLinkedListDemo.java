package com.horse.ch03.LinkedList;

/**
 * @Description 单链表的实现
 * @Author Mr.Horse
 * @Date 2021/2/18
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "关胜", "大刀");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero5);
        singleLinkedList.addByOrder(hero3);

        System.out.println("最初的链表");
        singleLinkedList.list();

        HeroNode newHeroNode = new HeroNode(3, "吴用", "智多星星");
        HeroNode newHeroNode2 = new HeroNode(4, "林冲", "八十万禁军教头");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.update2(newHeroNode2);

        System.out.println("更新后的链表");
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后的链表情况:");
        singleLinkedList.list();
    }

}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true) {
            //找到链表的最后
            if(temp.next == null) {
                break;
            }
            //如果没有找到最后, 将将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;
        while(true) {
            if(temp.next == null) {
                //说明temp已经在链表的最后
                break;
            }

            if(temp.next.no > heroNode.no) {
                //位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no) {
                //说明希望添加的heroNode的编号已然存在
                flag = true;
                break;
            }

            temp = temp.next; //后移，遍历当前链表
        }

        //判断flag 的值
        if(flag) {
            //不能添加，说明编号存在
            System.out.printf("英雄的编号 %d 已经存在,无法添加\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改.
    //说明
    //1. 根据 newHeroNode 的 no 来修改即可(第一种写法)
    public void update(HeroNode newHeroNode) {
        //当链表为空时,无法更新
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if(temp.next == null) {
                break;
            }

            if(temp.next.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag) {
            newHeroNode.next = temp.next.next;
            temp.next = newHeroNode;
        }else {
            System.out.printf("未查到 %d 节点,无法更新", newHeroNode.no);
        }
    }

    //第二种写法
    public void update2(HeroNode newHeroNode) {
        //当链表为空时,无法更新
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while(true) {
            if(temp == null) {
                break;
            }

            if(temp.no == newHeroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        //根据flag 判断是否找到要修改的节点
        if(flag) {
            //可以更新
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("未查到 %d 节点,无法更新", newHeroNode.no);
        }
    }

    //删除节点
    //思路
    //1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;// 标志是否找到待删除节点的
        while(true) {
            if(temp.next == null) {
                //已经到链表的最后
                break;
            }

            if(temp.next.no == no) {
                //找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag) {
            //可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true) {
            //判断是否到链表最后
            if(temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }
    }


}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;    //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + "}";
    }
}
