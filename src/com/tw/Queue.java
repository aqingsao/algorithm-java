package com.tw;

public class Queue {
    private Element head = null;

    public boolean empty() {
        return head == null;
    }

    public int size() {
        Element ele = head;
        int count = 0;
        while (ele != null) {
            ele = ele.next;
            count++;
        }
        return count;
    }

    public void inQ(int ele) {
        head = new Element(ele);
    }

    public int deQ() {
        int data = head.data;
        head = head.next;
        return data;
    }

    private static class Element<T> {
        private Element<T> next = null;
        private int data;

        public Element(int ele) {
            this.data = ele;
        }
    }
}
