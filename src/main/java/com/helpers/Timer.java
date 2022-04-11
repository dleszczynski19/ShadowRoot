package com.helpers;

public class Timer {
    long starts;

    private Timer() {
        reset();
    }

    public static Timer start() {
        return new Timer();
    }

    public Timer reset() {
        starts = System.currentTimeMillis();
        return this;
    }

    public long time() {
        long ends = System.currentTimeMillis();
        return ends - starts;
    }
}
