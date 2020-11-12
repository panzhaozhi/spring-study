package com.study.aop;

import org.springframework.stereotype.Service;

@Service
public class AopRun {

    @Pan
    public void run(int a,String b){
        System.out.println("run ...");
    }

}
