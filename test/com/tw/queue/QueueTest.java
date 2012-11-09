package com.tw.queue;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueueTest {
    private Queue queue;

    @Before
    public void setup() {
        queue = aQueue();
    }

    @Test
    public void should_return_true_when_stack_is_empty() {
        assertThat(queue.empty(), is(true));
        assertThat(queue.size(), is(0));
    }

    @Test
    public void should_return_false_after_push_element() {
        queue.inQ(10);
        assertThat(queue.empty(), is(false));
    }

    @Test
    public void should_return_size_1_after_push_1_element() {
        queue.inQ(10);
        assertThat(queue.size(), is(1));
    }

    @Test
    public void should_pop_top_element_after_push_1_element() {
        int originalEle = 10;
        queue.inQ(originalEle);
        int ele = queue.deQ();

        assertThat(ele, is(originalEle));
        assertThat(queue.empty(), is(true));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_poping_empty_stack() {
        queue.deQ();
    }

    private Queue aQueue() {
        return new Queue();
    }
}
