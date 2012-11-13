package com.tw.tree;

public class BalancedTree<T extends Comparable> {
    private BalancedTree<T>[] children;
    private Element<T>[] keys;
    private int degree;
    private BalancedTree<T> parent;

    public BalancedTree(int degree) {
        this.degree = degree;
        this.keys = new Element[degree - 1];
        this.children = new BalancedTree[degree];
    }

    public int height() {
        int maxChildHeight = this.isLeaf() ? 0 : 1;
        for (int i = 0; i < children.length; i++) {
            BalancedTree<T> child = children[i];
            if (child == null) {
                break;
            }

            int height = child.height();
            if (height > maxChildHeight) {
                maxChildHeight = height;
            }
        }
        return maxChildHeight;
    }

    public BalancedTree<T> insert(Element<T> element) {
        System.out.println("Node is full: " + this.isFull());
        if (!isFull()) {
            int index = getIndex(element, keys);
            insertToIndex(element, index, keys);
        } else {
            Element<T> middleElement = getMiddleElement(this.keys, element);
            BalancedTree<T> parent = new BalancedTree<T>(degree);
            parent.parent = this.parent;
            parent.insert(middleElement);

            parent.children[0] = this;
            parent.children[0].parent = parent;
            parent.children[1] = new BalancedTree<T>(degree);
            parent.children[1].parent = parent;

            for (int i = 0; i < keys.length; i++) {
                Element<T> key = keys[i];
                int compared = key.compareTo(middleElement);
                if (compared > 0) {
                    parent.children[1].insert(key);
                }

                if (compared >= 0) {
                    this.keys[i] = null;
                }
            }
        }

        return root();
    }

    private Element<T> getMiddleElement(Element[] keys, Element<T> element) {
        int middleElementIndex = this.keys.length / 2;
        int index = getIndex(element, keys);
        if (index == middleElementIndex) {
            return element;
        } else if (index < middleElementIndex) {
            return keys[index - 1];
        } else {
            return keys[index];
        }


    }

    private void insertToIndex(Element element, int index, Element<T>[] keys) {
        for (int i = keys.length - 1; i > index; i--) {
            keys[i] = keys[i - 1];
        }
        keys[index] = element;
    }

    private int getIndex(Element<T> element, Element<T>[] keys) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                if (element.compareTo(keys[i]) < 0) {
                    return i;
                }
            } else {
                return i;
            }
        }
        return 0;
    }

    private BalancedTree<T> root() {
        if (this.isRoot()) {
            return this;
        }
        return this.parent.root();
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    private boolean isLeaf() {
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == null) {
                return false;
            }
        }
        return true;
    }

    public Element<T>[] getKeys() {
        return keys;
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

    }
}
