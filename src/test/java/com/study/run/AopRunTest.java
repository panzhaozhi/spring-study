package com.study.run;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.study.aop.AopRun;
import com.study.MainEnter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainEnter.class)
public class AopRunTest {
    @Autowired
    AopRun aopRun;

    @Test
    public void test(){
        aopRun.run(1,"2");
    }

}
