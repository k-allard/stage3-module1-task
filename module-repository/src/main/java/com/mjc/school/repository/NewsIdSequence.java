package com.mjc.school.repository;

import java.util.concurrent.atomic.AtomicInteger;

public class NewsIdSequence {

    private static AtomicInteger idSequence = new AtomicInteger(0);

    public static int getNextIdVal() {
        return idSequence.incrementAndGet();
    }
}
