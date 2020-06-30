package org.study.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test5 {

    @Test
    @DisplayName("exception test")
    public void exceptionTest(){
int sum = 1000;
if(sum%1000 == 0){
    System.out.println(sum);
}
    }

}
