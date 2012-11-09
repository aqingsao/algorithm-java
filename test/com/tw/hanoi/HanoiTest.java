package com.tw.hanoi;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HanoiTest {

    private Hanoi hanoi = new Hanoi();
    private Hanoi.Tower x;
    private Hanoi.Tower y;
    private Hanoi.Tower z;

    @Test
    public void should_move_1_disk_from_X_to_Z() {
        x = new Hanoi.Tower("x", 1);
        y = new Hanoi.Tower("x", 0);
        z = new Hanoi.Tower("x", 0);
        assertThat(x.dishesCount(), is(1));
        assertThat(y.dishesCount(), is(0));
        assertThat(z.dishesCount(), is(0));

        hanoi.hanoi(x, y, z);

        assertThat(x.dishesCount(), is(0));
        assertThat(y.dishesCount(), is(0));
        assertThat(z.dishesCount(), is(1));
    }

    @Test
    public void should_move_2_disk_from_X_to_Z() {
        x = new Hanoi.Tower("x", 2);
        y = new Hanoi.Tower("y", 0);
        z = new Hanoi.Tower("z", 0);
        assertThat(x.dishesCount(), is(2));
        assertThat(y.dishesCount(), is(0));
        assertThat(z.dishesCount(), is(0));

        hanoi.hanoi(x, y, z);

        assertThat(x.dishesCount(), is(0));
        assertThat(y.dishesCount(), is(0));
        assertThat(z.dishesCount(), is(2));
    }

}
