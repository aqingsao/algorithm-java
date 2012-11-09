package com.tw;

import java.util.LinkedList;
import java.util.List;

public class Huffman {
    public BinaryTree<Code> buildTree(List<Code> codes) {
        List<BinaryTree<Code>> trees = new LinkedList<BinaryTree<Code>>();
        for (Code code : codes) {
            trees.add(new BinaryTree<Code>(code));
        }
        while (trees.size() >= 2) {
            BinaryTree min = getMin(trees);
            BinaryTree min2 = getMin(trees);
            BinaryTree<Code> newTree = mergeTree(min, min2);
            trees.add(newTree);
        }
        return trees.get(0);
    }

    private BinaryTree getMin(List<BinaryTree<Code>> trees) {
        BinaryTree<Code> ret = trees.get(0);
        for (BinaryTree<Code> tree : trees) {
            if (tree.getData().getWeight() < ret.getData().getWeight()) {
                ret = tree;
            }
        }
        trees.remove(ret);
        return ret;
    }

    private BinaryTree<Code> mergeTree(BinaryTree<Code> min, BinaryTree<Code> max) {
        BinaryTree<Code> ret = new BinaryTree<Code>(new Code(min.getData().getWeight() + max.getData().getWeight()));
        ret.addLeftChild(min);
        ret.addRightChild(max);
        return ret;
    }

    public void buildCode(BinaryTree<Code> binaryTree) {
        buildHuffmanCode(new StringBuffer(), binaryTree);
    }

    private void buildHuffmanCode(StringBuffer bits, BinaryTree<Code> binaryTree) {
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

    static class Code {
        private int weight;
        private String huffmanCode;

        public Code(int weight) {
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
