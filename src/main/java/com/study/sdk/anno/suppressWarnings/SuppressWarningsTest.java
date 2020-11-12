package com.study.sdk.anno.suppressWarnings;

import java.util.Date;

public class SuppressWarningsTest {
    @SuppressWarnings(value = {"deprecation"})
    public static void doSomething(){
        Date date = new Date(113,2,3);
        System.out.println(date);
    }

    public static void main(String[] args) {
        doSomething();
    }
}
