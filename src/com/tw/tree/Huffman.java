package com.tw.tree;

import java.util.LinkedList;
import java.util.List;

public class Huffman {
    public BinaryTree<Node> buildTree(List<Node> nodes) {
        List<BinaryTree<Node>> trees = new LinkedList<BinaryTree<Node>>();
        for (Node node : nodes) {
            trees.add(new BinaryTree<Node>(node));
        }
        while (trees.size() >= 2) {
            BinaryTree min = getMin(trees);
            BinaryTree min2 = getMin(trees);
            BinaryTree<Node> newTree = mergeTree(min, min2);
            trees.add(newTree);
        }
        return trees.get(0);
    }

    private BinaryTree getMin(List<BinaryTree<Node>> trees) {
        BinaryTree<Node> ret = trees.get(0);
        for (BinaryTree<Node> tree : trees) {
            if (tree.getData().getWeight() < ret.getData().getWeight()) {
                ret = tree;
            }
        }
        trees.remove(ret);
        return ret;
    }

    private BinaryTree<Node> mergeTree(BinaryTree<Node> min, BinaryTree<Node> max) {
        BinaryTree<Node> ret = new BinaryTree<Node>(new Node(min.getData().getWeight() + max.getData().getWeight()));
        ret.addLeftChild(min);
        ret.addRightChild(max);
        return ret;
    }

    public void buildCode(BinaryTree<Node> binaryTree) {
        buildHuffmanCode(new StringBuffer(), binaryTree);
    }

    private void buildHuffmanCode(StringBuffer bits, BinaryTree<Node> binaryTree) {
        BinaryTree left = binaryTree.leftChild();
        if (left != null) {
            bits.append("0");
            buildHuffmanCode(bits, left);
        }
        BinaryTree right = binaryTree.rightChild();
        if (right != null) {
            bits.append("1");
            buildHuffmanCode(bits, right);
        }

        if(left == null && right == null){
            binaryTree.getData().setHuffmanCode(bits);
        }
    }

    static class Node {
        private int weight;
        private String huffmanCode;

        public Node(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public void setHuffmanCode(StringBuffer huffmanCode) {
            this.huffmanCode = huffmanCode.toString();
        }
    }
}
