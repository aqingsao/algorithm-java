package com.tw.tree;

public class BinaryTree<T> {
    private BinaryTree<T> leftChild = null;
    private BinaryTree<T> rightChild = null;
    private T data;

    public BinaryTree(T ele) {
        this.data = ele;
    }

    public T getData() {
        return data;
    }

    public BinaryTree<T> leftChild() {
        return leftChild;
    }

    public void addLeftChild(BinaryTree<T> left) {
        this.leftChild = left;
    }

    public BinaryTree<T> rightChild() {
        return rightChild;
    }

    public void addRightChild(BinaryTree<T> right) {
        this.rightChild = right;
    }
}
