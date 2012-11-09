package com.tw;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StackTest {

    private Stack stack;

    @Before
    public void setup() {
        stack = aStack(10);
    }

    @Test
    public void should_return_true_when_stack_is_empty() {
        assertThat(stack.empty(), is(true));
        assertThat(stack.size(), is(0));
        assertThat(stack.full(), is(false));
    }

    @Test
    public void should_return_false_after_push_element() {
        stack.push(10);
        assertThat(stack.empty(), is(false));
        assertThat(stack.size(), is(1));
    }

    @Test
    public void should_return_size_1_after_push_1_element() {
        stack.push(10);
        assertThat(stack.size(), is(1));
    }

    @Test
    public void should_pop_top_element_after_push_1_element() {
        int originalEle = 10;
        stack.push(originalEle);
        int ele = stack.pop();

        assertThat(ele, is(originalEle));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_poping_empty_stack() {
        stack.pop();
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_pushing_element_to_a_full_stack() {
        Stack stack = aFullStack();
        assertThat(stack.full(), is(true));
        stack.push(20);
    }

    private Stack aFullStack() {
        Stack stack = aStack(1);
        stack.push(10);
        return stack;
    }

    private Stack aStack(int limit) {
        return new Stack(limit);
    }
}
