package com.tw.tree;

public class RedBlackTree<T extends Comparable> {
    static final RedBlackTree NIL = new RedBlackTree(Color.BLACK);

    private Element key;
    private RedBlackTree parent = RedBlackTree.NIL;
    private RedBlackTree left;
    private RedBlackTree right;
    private Color color = Color.RED;

    private RedBlackTree(Element key, Color color) {
        this.key = key;
        this.color = color;
    }

    private RedBlackTree(Color color) {
        this.color = color;
    }

    public static <T extends Comparable> RedBlackTree<T> newTree() {
        return new RedBlackTree<T>(Color.BLACK);
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

    public void delete() {
        if (this.left == NIL) {
            RedBlackTree x = this.right;
            x.parent = this.parent;
            if (!this.isRoot()) {
                if (this.isLeftChild()) {
                    this.parent.left = x;
                } else {
                    this.parent.right = x;
                }
            }
            if(this.color == Color.BLACK){
                this.deleteFixUp();
            }
        }
        else if (this.right == NIL) {
            RedBlackTree x = this.left;
            x.parent = this.parent;
            if (!this.isRoot()) {
                if (this.isLeftChild()) {
                    this.parent.left = x;
                } else {
                    this.parent.right = x;
                }
            }
            if(this.color == Color.BLACK){
                this.deleteFixUp();
            }
        }
        else{
            this.key = this.left.key;
            this.left.delete();
            if(this.left.color == Color.BLACK){
                this.deleteFixUp();
            }

        }
    }

    private void deleteFixUp() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void insert(Element element) {
        RedBlackTree<T> root = root();
        if (root.key == null) {
            root.key = element;
            root.left = NIL;
            root.right = NIL;
            fixUp();
        } else {
            root.insert2(element);
        }
    }

    private void insert2(Element element) {
        int compared = element.compareTo(this.key);
        if (compared < 0) {
            if (this.left == NIL) {
                this.left = new RedBlackTree<T>(element, Color.RED);
                this.left.parent = this;
                this.left.left = NIL;
                this.left.right = NIL;
                this.left.fixUp();
            } else {
                this.left.insert2(element);
            }
        } else if (compared > 0) {
            if (this.right == NIL) {
                this.right = new RedBlackTree<T>(element, Color.RED);
                this.right.parent = this;
                this.right.left = NIL;
                this.right.right = NIL;
                this.right.fixUp();
            } else {
                this.right.insert2(element);
            }
        }
    }

    private boolean isLeftChild() {
        return this == this.parent.left;
    }

    private boolean isRightChild() {
        return this == this.parent.right;
    }

    private void fixUp() {
        if (this.isRoot()) {
            this.color = Color.BLACK;
            return;
        }

        if (this.parent.color == Color.RED) {
            RedBlackTree uncle = getUncle();
            if (uncle.color == Color.RED) {
                this.parent.color = Color.BLACK;   // 1
                uncle.color = Color.BLACK;             // 1
                this.parent.parent.color = Color.RED; //1
                this.parent.parent.fixUp();            //1
            } else if (uncle.color == Color.BLACK) {
                if (this.isLeftChild()) {
                    if (this.parent.isLeftChild()) {
                        this.parent.color = Color.BLACK;
                        this.parent.parent.color = Color.RED;
                        this.parent.parent.rightRotate();
                    } else {
                        RedBlackTree parent = this.parent;
                        parent.rightRotate();
                        parent.fixUp();
                    }
                } else {
                    if (this.parent.isRightChild()) {
                        this.parent.color = Color.BLACK;
                        this.parent.parent.color = Color.RED;
                        this.parent.parent.leftRotate();
                    } else {
                        RedBlackTree parent = this.parent;
                        parent.leftRotate();
                        parent.fixUp();
                    }
                }
            }
        }
    }

    private RedBlackTree getUncle() {
        if (this.parent.isLeftChild()) {
            return this.parent.parent.right;
        } else {
            return this.parent.parent.left;
        }
    }

    public Color getColor() {
        return this.color;
    }

    public RedBlackTree<T> findKey(Element key) {
        RedBlackTree<T> root = root();
        return root.findByKey(key);
    }

    private RedBlackTree<T> findByKey(Element key) {
        int compared = key.compareTo(this.key);
        if (compared == 0) {
            return this;
        } else if (compared < 0) {
            return this.left == NIL ? NIL : this.left.findByKey(key);
        } else {
            return this.right == NIL ? NIL : this.right.findByKey(key);
        }
    }

    public RedBlackTree<T> root() {
        return this.parent == NIL ? this : this.parent.root();
    }

    public void traverse() {
        traverseWithHeight(0);
    }

    private void traverseWithHeight(int height) {
        printMe(height);
        if (this.left != NIL) {
            this.left.traverseWithHeight(height + 1);
        }
        if (this.right != NIL) {
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

    public boolean isRoot() {
        return this.parent == NIL;
    }

    public RedBlackTree<T> getLeft() {
        return left;
    }

    public RedBlackTree<T> getRight() {
        return right;
    }

    public void leftRotate() {
        RedBlackTree<T> y = this.right;
        if (y != NIL) {
            this.right = y.left;
            y.left.parent = this;
            y.parent = this.parent;
            this.parent = y;
            y.left = this;

            if (y.parent != NIL) {
                if (y.parent.left == this) {
                    y.parent.left = y;
                } else if (y.parent.right == this) {
                    y.parent.right = y;
                }
            }
        }
    }

    public void rightRotate() {
        RedBlackTree<T> y = this.left;
        if (y != NIL) {
            this.left = y.right;
            y.right.parent = this;
            y.parent = this.parent;
            this.parent = y;
            y.right = this;

            if (y.parent != NIL) {
                if (y.parent.left == this) {
                    y.parent.left = y;
                } else if (y.parent.right == this) {
                    y.parent.right = y;
                }
            }
        }

    }

    public RedBlackTree<T> getParent() {
        return parent;
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

