package com.tw.tree;

public class RedBlackTree<T extends Comparable> {
    private static final RedBlackTree NIL = new RedBlackTree(null);

    private Element key;
    private RedBlackTree parent = RedBlackTree.NIL;
    private RedBlackTree left = RedBlackTree.NIL;
    private RedBlackTree right = RedBlackTree.NIL;
    private Color color = Color.BLACK;

    public RedBlackTree(Element key) {
        this.key = key;
    }

    public Element<T> getKey() {
        return key;
    }

    public int height() {
        if (this.left == NIL && this.right == NIL) {
            return 0;
        }
        return Math.max(this.left.height(), this.right.height()) + 1;
    }

    public void insert(Element element) {
        int compared = element.compareTo(this.key);
        if (compared == 0) {
            throw new RuntimeException("Key " + element.getData() + " already exists in red black tree");
        } else if (compared < 0) {
            if (this.left == NIL) {
                this.left = new RedBlackTree<T>(element);
                insertAs(this.left);
            }
            else{
                this.left.insert(element);
            }
        } else {
            if (this.right == NIL) {
                this.right = new RedBlackTree<T>(element);
                insertAs(this.right);
            }
            else{
                this.right.insert(element);
            }
        }
    }

    private void insertAs(RedBlackTree node) {
        node.color = Color.RED;
        node.parent = this;
        fixUp(node);
    }

    private void fixUp(RedBlackTree node) {
    }

    public Color color() {
        return this.color;
    }

    public RedBlackTree<T> findKey(Element key) {
        RedBlackTree<T> root = root();
        return root.findByKey(key);
    }

    private RedBlackTree<T> findByKey(Element key) {
        int compared = this.key.compareTo(key);
        if (compared == 0) {
            return this;
        } else if (compared < 0) {
            return this.left == NIL ? NIL : this.left.findByKey(key);
        } else {
            return this.right == NIL ? NIL : this.right.findByKey(key);
        }
    }

    private RedBlackTree<T> root() {
        return this.parent == NIL ?  this : this.parent.root();
    }

    public void traverse() {
        traverseWithHeight(0);
    }

    private void traverseWithHeight(int height) {
        printMe(height);
        if(this.left != NIL){
            this.left.traverseWithHeight(height + 1);
        }
        if(this.right != NIL){
            this.right.traverseWithHeight(height + 1);
        }
    }

    private void printMe(int height) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < height; i++) {
            stringBuffer.append("\t");
        }
        stringBuffer.append("key(").append(this.key.getData()).append(")").append(", color ").append(this.color);
        System.out.println(stringBuffer.toString());
    }


    public static class Element<T extends Comparable> {
        private T data;

        public Element(T data) {
            this.data = data;
        }

        public String toString() {
            return data.toString();
        }

        public int compareTo(Element<T> another) {
            return this.data.compareTo(another.data);
        }

        public T getData() {
            return data;
        }
    }

    public static enum Color {
        RED, BLACK;
    }
}

