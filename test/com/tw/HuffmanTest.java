package com.tw;

import org.junit.Test;

import static java.util.Arrays.asList;

public class HuffmanTest {
    @Test
    public void should_(){
        Huffman huffman = new Huffman();
        BinaryTree binaryTree = huffman.buildTree(asList(asCode(5), asCode(29), asCode(7), asCode(8), asCode(14), asCode(23), asCode(3), asCode(11)));
        huffman.buildCode(binaryTree);
        binaryTree.getData();
    }

    private Huffman.Code asCode(int weight) {
        return new Huffman.Code(weight);
    }
}
