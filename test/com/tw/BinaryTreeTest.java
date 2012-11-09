package com.tw;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class BinaryTreeTest {
    @Test
    public void should_return_root(){
        assertThat(createRoot(50).getData(), is(50));
    }

    @Test
    public void should_return_null_when_not_have_left_child(){
        assertThat(createRoot(50).leftChild(), nullValue());
    }

    @Test
    public void should_return_left_child_when_not_have_left_child(){
        BinaryTree<Integer> root = createRoot(50);

        root.addLeftChild(new BinaryTree(20));
        assertThat(root.leftChild(), notNullValue());
        assertThat(root.leftChild().getData(), is(20));
    }

    @Test
    public void should_return_right_child_when_not_have_left_child(){
        BinaryTree<Integer> root = createRoot(500);

        root.addRightChild(new BinaryTree(30));
        assertThat(root.rightChild(), notNullValue());
        assertThat(root.rightChild().getData(), is(30));
    }

    private BinaryTree<Integer> createRoot(int rootData) {
        return new BinaryTree<Integer>(rootData);
    }
}
