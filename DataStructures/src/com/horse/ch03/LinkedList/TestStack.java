package com.horse.ch03.LinkedList;

import java.util.Stack;

/**
 * @Description
 * @Author Mr.Horse
 * @Date 2021/2/19
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.push("Jack");
        stack.push("Peter");
        stack.push("Mary");
        //出栈
        while(!stack.empty()) {
            System.out.println(stack.pop());
        }

    }
}
