package com.study.earn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.study.MainEnter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainEnter.class)
public class AgricultrueTest {

    @Autowired
    Agriculture agriculture;

    @Test
    public void test(){
//        agriculture.getContent(1);
    }

}
