package com.tw;

public class Stack {
    private Integer[] list;
    private int top = -1;

    public Stack(int limit) {
        this.list = new Integer[limit];
    }

    public boolean empty() {
        return top < 0;
    }

    public void push(int ele) {
        list[++top] = ele;
    }

    public int size() {
        return top + 1;
    }

    public int pop() {
        int oldValue = list[top];
        list[top--] = null;
        return oldValue;
    }

    public boolean full() {
        return top + 1 == list.length;
    }
}
