package com.study.basic;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal first = new BigDecimal("1.111");
        BigDecimal second = new BigDecimal("0");

        first = first.multiply(new BigDecimal("100"));
        first = first.subtract(new BigDecimal(first.longValue()));



        System.out.println(first.toString());
        System.out.println(second.toString());
        System.out.println(second.compareTo(first));
    }
}
