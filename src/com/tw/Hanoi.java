package com.tw;

import com.tw.stack.Stack;

public class Hanoi {
    public void hanoi(Tower x, Tower y, Tower z) {
        hanoiOne(x.dishesCount() - 1, x, y, z);
//        hanoiOne(y.dishesCount() - 1, y, x, z);
    }

    private void hanoiOne(int index, Tower x, Tower y, Tower z) {
        if (index == 0) {
            x.moveTo(z);
        } else {
            hanoiOne(index - 1, x, z, y);
            x.moveTo(z);
            hanoiOne(index -1, y, x, z);
        }
    }

    static class Tower {
        Stack stack = new Stack(10);
        private String name;

        public Tower(String name, int dishes) {
            this.name = name;
            for (int i = 0; i < dishes; i++) {
                stack.push(i);
            }
        }

        public void moveTo(Tower another) {

            int ele = stack.pop();
            System.out.println("Move dish " + ele + " from " + this.name + " to " + another.name);
            another.stack.push(ele);
        }

        public int dishesCount() {
            return stack.size();
        }
    }
}
