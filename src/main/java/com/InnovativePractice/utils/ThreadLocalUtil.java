package com.InnovativePractice.utils;

public class ThreadLocalUtil {
    // Fixed ThreadLocal declaration
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    // Get the value from the ThreadLocal
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    // Set the value in the ThreadLocal
    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    // Remove the value from the ThreadLocal
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}