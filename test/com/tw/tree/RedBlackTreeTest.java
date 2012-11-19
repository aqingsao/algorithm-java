package com.tw.tree;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class RedBlackTreeTest {
    @Test
    public void test_height_should_be_0_for_1_key_tree() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));

        assertThat(root.getKey().getData(), is(26));
        assertThat(root.height(), is(0));
    }

    @Test
    public void test_key_26_should_be_root_child_and_of_color_black() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));

        assertThat(root.getKey().getData(), is(26));
        assertThat(root.getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.isRoot(), is(true));
    }

    @Test
    public void test_key_17_should_be_left_child_and_of_color_red() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));

        root.traverse();
        RedBlackTree<Integer> left = root.getLeft();
        assertThat(left.getKey().getData(), is(17));
        assertThat(left.getColor(), is(RedBlackTree.Color.RED));
        assertThat(left.getParent().getKey().getData(), is(26));
    }

    @Test
    public void test_key_41_should_be_right_child_and_of_color_red() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(41));

        RedBlackTree<Integer> right = root.getRight();
        assertThat(right.getKey().getData(), is(41));
        assertThat(right.getColor(), is(RedBlackTree.Color.RED));
        assertThat(right.getParent().getKey().getData(), is(26));
    }

    @Test
    public void test_left_rotate_node_17() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(41));
        root.insert(new RedBlackTree.Element<Integer>(14));
        root.insert(new RedBlackTree.Element<Integer>(21));
        root.insert(new RedBlackTree.Element<Integer>(19));
        root.insert(new RedBlackTree.Element<Integer>(23));
        assertThat(root.getLeft().getKey().getData(), is(17));
        assertThat(root.getLeft().getLeft().getKey().getData(), is(14));
        assertThat(root.getLeft().getRight().getKey().getData(), is(21));

        root.findKey(new RedBlackTree.Element(17)).leftRotate();
        assertThat(root.getLeft().getKey().getData(), is(21));
        assertThat(root.getLeft().getLeft().getKey().getData(), is(17));
        assertThat(root.getLeft().getRight().getKey().getData(), is(23));
    }

    @Test
    public void test_right_rotate_node_17() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(21));
        root.insert(new RedBlackTree.Element<Integer>(41));
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(23));
        root.insert(new RedBlackTree.Element<Integer>(14));
        root.insert(new RedBlackTree.Element<Integer>(19));
        assertThat(root.getLeft().getKey().getData(), is(21));
        assertThat(root.getLeft().getLeft().getKey().getData(), is(17));
        assertThat(root.getLeft().getRight().getKey().getData(), is(23));

        root.findKey(new RedBlackTree.Element(21)).rightRotate();
        assertThat(root.getLeft().getKey().getData(), is(17));
        assertThat(root.getLeft().getLeft().getKey().getData(), is(14));
        assertThat(root.getLeft().getRight().getKey().getData(), is(21));
    }

    @Test
    public void test_insert_child_when_parent_is_black() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(41));
        root.insert(new RedBlackTree.Element<Integer>(14));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.BLACK));

        root.insert(new RedBlackTree.Element<Integer>(21));

        assertThat(root.getLeft().getRight().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getLeft().getRight().getKey().getData(), is(21));
    }

    @Test
    public void test_insert_left_child_when_parent_is_red_and_uncle_is_red() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(41));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.RED));

        root.insert(new RedBlackTree.Element<Integer>(14));

        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getLeft().getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.BLACK));
    }

    @Test
    public void test_insert_left_child_when_parent_is_left_red_and_uncle_is_black() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.BLACK));


        root.insert(new RedBlackTree.Element<Integer>(14));

        root = root.root();
        assertThat(root.getKey().getData(), is(17));
        assertThat(root.getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getLeft().getKey().getData(), is(14));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getRight().getKey().getData(), is(26));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.RED));
    }

    @Test
    public void test_insert_left_child_when_parent_is_right_red_and_uncle_is_black() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(41));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.RED));


        root.insert(new RedBlackTree.Element<Integer>(35));

        root = root.root();
        assertThat(root.getKey().getData(), is(35));
        assertThat(root.getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getLeft().getKey().getData(), is(26));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getRight().getKey().getData(), is(41));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.RED));
    }

    @Test
    public void test_insert_right_child_when_parent_is_right_red_and_uncle_is_black() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(41));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.RED));


        root.insert(new RedBlackTree.Element<Integer>(45));

        root = root.root();
        assertThat(root.getKey().getData(), is(41));
        assertThat(root.getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getLeft().getKey().getData(), is(26));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getRight().getKey().getData(), is(45));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.RED));
    }

    @Test
    public void test_insert_right_child_when_parent_is_left_red_and_uncle_is_black() {
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.BLACK));


        root.insert(new RedBlackTree.Element<Integer>(19));

        root = root.root();
        assertThat(root.getKey().getData(), is(19));
        assertThat(root.getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getLeft().getKey().getData(), is(17));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getRight().getKey().getData(), is(26));
        assertThat(root.getRight().getColor(), is(RedBlackTree.Color.RED));
    }
    
    @Test
    public void test_delete_root_node(){
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));

        root.delete();

        assertThat(root.getKey(), nullValue());
    }

    @Test
    public void test_delete_a_node_which_has_only_one_left_child(){
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(41));
        root.insert(new RedBlackTree.Element<Integer>(9));
        assertThat(root.getLeft().getRight(), is(RedBlackTree.NIL));


        RedBlackTree<Integer> key = root.findKey(new RedBlackTree.Element(17));
        key.delete();

        assertThat(root.getLeft().getKey().getData(), is(9));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
    }

    @Test
    public void test_delete_a_node_which_has_2_children(){
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(41));
        root.insert(new RedBlackTree.Element<Integer>(9));
        root.insert(new RedBlackTree.Element<Integer>(21));
        assertThat(root.getLeft().getLeft(), not(RedBlackTree.NIL));
        assertThat(root.getLeft().getRight(), not(RedBlackTree.NIL));


        RedBlackTree<Integer> key = root.findKey(new RedBlackTree.Element(17));
        key.delete();

        assertThat(root.getLeft().getKey().getData(), is(9));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.BLACK));
    }

    @Test
    public void test_delete_a_red_node_who_has_child_nodes(){
        RedBlackTree<Integer> root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(26));
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(41));
        root.insert(new RedBlackTree.Element<Integer>(14));
        root.insert(new RedBlackTree.Element<Integer>(21));
        root.insert(new RedBlackTree.Element<Integer>(9));
        root.insert(new RedBlackTree.Element<Integer>(15));
        root = root.root();
        assertThat(root.getLeft().getKey().getData(), is(17));
        assertThat(root.getLeft().getColor(), is(RedBlackTree.Color.RED));
        assertThat(root.getLeft().getLeft().getKey().getData(), is(14));
        assertThat(root.getLeft().getLeft().getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getLeft().getRight().getKey().getData(), is(21));
        assertThat(root.getLeft().getRight().getColor(), is(RedBlackTree.Color.BLACK));


        root.delete();

        assertThat(root.getColor(), is(RedBlackTree.Color.BLACK));
        assertThat(root.getRight(), is(RedBlackTree.NIL));
    }

    @Ignore
    public void test__node_should_be_red() {
        RedBlackTree root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(17));
        RedBlackTree<Integer> byKey = root.findKey(new RedBlackTree.Element<Integer>(17));

        assertThat(byKey.getKey().getData(), is(17));
        assertThat(byKey.getColor(), is(RedBlackTree.Color.RED));
    }

    @Ignore
    public void test_insert_more_nodes_to_rbtree() {
        RedBlackTree.Element<Integer> key = new RedBlackTree.Element<Integer>(26);
        RedBlackTree root = RedBlackTree.newTree();
        root.insert(new RedBlackTree.Element<Integer>(17));
        root.insert(new RedBlackTree.Element<Integer>(41));
        root.insert(new RedBlackTree.Element<Integer>(14));
        root.insert(new RedBlackTree.Element<Integer>(21));
        root.insert(new RedBlackTree.Element<Integer>(10));
        root.insert(new RedBlackTree.Element<Integer>(16));
        root.insert(new RedBlackTree.Element<Integer>(114));
        root.insert(new RedBlackTree.Element<Integer>(23));
        root.insert(new RedBlackTree.Element<Integer>(7));
        root.insert(new RedBlackTree.Element<Integer>(12));
        root.insert(new RedBlackTree.Element<Integer>(15));
        root.insert(new RedBlackTree.Element<Integer>(20));
        root.insert(new RedBlackTree.Element<Integer>(3));
        root.insert(new RedBlackTree.Element<Integer>(30));
        root.insert(new RedBlackTree.Element<Integer>(47));
        root.insert(new RedBlackTree.Element<Integer>(28));
        root.insert(new RedBlackTree.Element<Integer>(38));
        root.insert(new RedBlackTree.Element<Integer>(35));
        root.insert(new RedBlackTree.Element<Integer>(314));
        root.traverse();
    }
}
