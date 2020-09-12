package me.daqem.rightclickfarming.utils;

import java.util.Random;

public class DropMath {

    public int getRandomNumberInRange(int min, int max) {
        if (min == max) {
            return min;
        }
        else if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
