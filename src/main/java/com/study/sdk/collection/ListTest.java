package com.study.sdk.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> all = new ArrayList<>();
        all.addAll(Arrays.asList("a","b","c"));
        List<String> sub1 = Arrays.asList("a","c");
        List<String> sub2 = Arrays.asList("c","a");
        System.out.println(all.containsAll(sub1));
        System.out.println(all.containsAll(sub2));
    }
}
