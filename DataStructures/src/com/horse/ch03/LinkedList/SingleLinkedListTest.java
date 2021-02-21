package com.horse.ch03.LinkedList;

import java.util.Stack;

/**
 * @Description 几道面试题
 * @Author Mr.Horse
 * @Date 2021/2/19
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        Node hero1 = new Node(1, "宋江", "及时雨");
        Node hero2 = new Node(2, "卢俊义", "玉麒麟");
        Node hero3 = new Node(3, "吴用", "智多星");
        Node hero4 = new Node(4, "林冲", "豹子头");
        Node hero5 = new Node(5, "关胜", "大刀");

        //创建要给链表
        SingleLinkList singleLinkList = new SingleLinkList();
        singleLinkList.add(hero1);
        singleLinkList.add(hero2);
        singleLinkList.add(hero4);
        singleLinkList.add(hero5);
        singleLinkList.addByOrder(hero3);

        //1.求单链表中有效节点的个数
        int length = getLength(singleLinkList.getHead());
        System.out.println(length);
        //2.查找单链表中的倒数第k个结点【新浪面试题】(方法1)
        Node lastIndexNode = findLastIndexNode(singleLinkList.getHead(), 1);
        System.out.println(lastIndexNode);
        //2.查找单链表中的倒数第k个结点【新浪面试题】(方法2)
        Node lastIndexNode2 = findLastIndexNode2(singleLinkList.getHead(), 1);
        System.out.println(lastIndexNode2);

        //3.单链表的反转【腾讯面试题，有点难度】
        System.out.println("反转后的链表是:");
//        reverseLinkedList(singleLinkList.getHead());
//        singleLinkList.list();
        reversePrint(singleLinkList.getHead());


    }

    //面试题1:求单链表中有效节点的个数
    //方法:获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    private static int getLength(Node head) {
        //空链表
        if(head.next == null) {
            return 0;
        }

        Node curNode = head.next;
        int length = 0;
        while(curNode != null) {
            length ++;
            curNode = curNode.next;
        }
        return length;
    }

    //面试题2:查找单链表中的倒数第k个结点【新浪面试题】
    /**
     *
     * @param head
     * @param index
     * @return
     */
    public static Node findLastIndexNode(Node head, int index) {
        //首先找出链表总长度
        int length = getLength(head);
        if(index > length || index <= 0) {
            return null;
        }

        Node curNode = head.next;
        for(int i = 0; i < length-index; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }

    //面试题2:查找单链表中的倒数第k个结点【新浪面试题】
    //方法2:用栈解决
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点
    public static Node findLastIndexNode2(Node head, int index) {
        Stack<Node> stack = new Stack<Node>();
        Node temp = head.next;
        while(temp != null) {
            stack.add(temp);
            temp = temp.next;
        }

        Node result = null;
        for(int x=0; x<index; x++) {
            result = stack.pop();
        }
        return result;
    }

    //面试3: 单链表的反转【腾讯面试题，有点难度】
    private static void reverseLinkedList(Node head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        Node reverseHead = new Node(0, "", "");
        Node curNode = head.next;
        Node next = null; // 指向当前节点[cur]的下一个节点

        while(curNode != null) {
            next = curNode.next; //先暂时保存当前节点的下一个节点，因为后面需要使用
            curNode.next = reverseHead.next; //将curNode的下一个节点指向新的链表的最前端
            reverseHead.next = curNode; //将cur 连接到新的链表上
            curNode = next;
        }
        head.next = reverseHead.next;
    }

    //方式2：
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(Node head) {
        if(head.next == null) {
            System.out.println("链表为空");
        }

        //创建要给一个栈，将各个节点压入栈
        Stack<Node> stack = new Stack<>();
        Node cur = head.next;
        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        //将栈中的节点进行打印,pop 出栈
        while(!stack.empty()) {
            System.out.println(stack.pop());
        }

    }


}

class SingleLinkList {
    private Node head = new Node(0, "", "");

    //返回头节点
    public Node getHead() {
        return head;
    }

    //添加
    public void add(Node node) {
        Node temp = head;
        while(true) {
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    //按照排名添加
    public void addByOrder(Node node) {
        Node temp = head;
        boolean flag = false;
        while(true) {
            if(temp.next == null) {
                break;
            }

            if(temp.next.no > node.no) {
                break;
            }else if(temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        
        if(flag) {
            System.out.printf("当前编号 %d 已存在,无法进行添加操作", node.no);
        }else {
            node.next = temp.next;
            temp.next = node;
        }
    }
    
    //修改
    public void update(Node node) {
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        
        Node temp = head.next;
        boolean flag = false;
        while (true) {
            if(temp == null) {
                break;
            }
            
            if(temp.no == node.no) {
                flag = true;
                break;
            }
            
            temp = temp.next;
        }
        
        if(flag) {
            temp.name = node.name;
            temp.nickname = node.nickname;
        }else {
            System.out.printf("当前节点编号 %d 不存在,无法更新", node.no);
        }
    }
    
    //删除节点
    public void del(int no) {
        Node temp = head;
        boolean flag = false;
        while(true) {
            if(temp.next == null) {
                break;
            }
            
            if(temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        
        if(flag) {
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
    
    public void list() {
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        
        Node temp = head.next;
        while(true) {
            if(temp == null) {
                break;
            }
            
            System.out.println(temp);
            temp = temp.next;
        }
    }


}

//定义Node ， 每个Node 对象就是一个节点
class Node {
    public int no;
    public String name;
    public String nickname;
    public Node next; //指向下一个节点
    //构造器
    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "Node [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
