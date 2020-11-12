package com.study.t;

import java.util.List;

class Box<T, V> {
    public T ob1;
    public V ob2;

    Box(T o1, V o2) {
        ob1 = o1;
        ob2 = o2;
    }

    T getob1() {
        return ob1;
    }

    V getob2() {
        return ob2;
    }

    void show() {
        System.out.println(ob1.getClass().getName());
        System.out.println(ob2.getClass().getName());
    }

    public static void main(String args[]) {
//        Box<Integer, String> my = new Box<Integer, String>(88, "fox");
//        my.show();
//        ;
//
//        System.out.println(my.getob1());
//        String str = "a b";
//        if(str.indexOf(" ") != -1){
//            System.out.println("not");
//        }
        List l = null;
        System.out.println(l.stream());

    }
}