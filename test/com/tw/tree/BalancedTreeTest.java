package com.tw.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BalancedTreeTest {
    @Test
    public void test_isFull_returns_true_for_2_order_node_contains_empty_elements() {
        BalancedTree root = new BalancedTree(2);
        assertThat(root.isFull(), is(false));
    }

    @Test
    public void test_isFull_returns_false_when_2_order_node_contains_1_element() {
        BalancedTree root = new BalancedTree(2);
        root.insert(new BalancedTree.Element(17));
        assertThat(root.isFull(), is(true));
    }

    @Test
    public void test_add_17_as_first_key() {
        BalancedTree root = new BalancedTree(3);
        BalancedTree.Element<Integer> element = new BalancedTree.Element<Integer>(17);
        root.insert(element);

        BalancedTree.Element<Integer>[] keys = root.getKeys();
        assertThat(keys[0], is(element));
    }

    @Test
    public void test_bigger_element_added_as_second_key() {
        BalancedTree root = new BalancedTree(3);
        BalancedTree.Element<Integer> firstElement = new BalancedTree.Element<Integer>(8);
        root.insert(firstElement);
        BalancedTree.Element<Integer> secondElement = new BalancedTree.Element<Integer>(17);
        root.insert(secondElement);

        BalancedTree.Element<Integer>[] keys = root.getKeys();
        assertThat(keys[0], is(firstElement));
        assertThat(keys[1], is(secondElement));
    }

    @Test
    public void test_smaller_element_added_as_first_key() {
        BalancedTree root = new BalancedTree(3);
        BalancedTree.Element<Integer> firstElement = new BalancedTree.Element<Integer>(17);
        root.insert(firstElement);
        BalancedTree.Element<Integer> secondElement = new BalancedTree.Element<Integer>(8);
        root.insert(secondElement);

        BalancedTree.Element<Integer>[] keys = root.getKeys();
        assertThat(keys[0], is(secondElement));
        assertThat(keys[1], is(firstElement));
    }

    @Test
    public void test_height_is_0_when_inserting_2_elements_to_3_order_tree() {
        BalancedTree root = new BalancedTree(3);
        root.insert(new BalancedTree.Element<Integer>(17));
        root.insert(new BalancedTree.Element<Integer>(8));

        assertThat(root.height(), is(0));
    }

    @Test
    public void test_height_is_1_when_inserting_3_elements_to_3_order_tree() {
        BalancedTree root = new BalancedTree(3);
        root = root.insert(new BalancedTree.Element<Integer>(35));
        root = root.insert(new BalancedTree.Element<Integer>(8));
        BalancedTree.Element<Integer> element = new BalancedTree.Element<Integer>(12);
        root = root.insert(element);

        assertEquals(element, root.getKeys()[0]);
        assertThat(root.height(), is(1));
    }

    @Test
    public void test_height_is_2_when_inserting_5_elements_to_3_order_tree() {
        BalancedTree root = new BalancedTree(3);
        root = root.insert(new BalancedTree.Element<Integer>(35));
        root = root.insert(new BalancedTree.Element<Integer>(8));

        root = root.insert(new BalancedTree.Element<Integer>(12));
        BalancedTree.Element<Integer> element = new BalancedTree.Element<Integer>(9);
        root = root.insert(element);
        assertEquals(element, root.getKeys()[0]);

        assertThat(root.height(), is(1));
    }
}
