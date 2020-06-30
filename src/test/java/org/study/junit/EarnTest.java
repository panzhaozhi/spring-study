package org.study.junit;

import com.study.EarnMoney;
import com.study.EnterStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EnterStart.class})
public class EarnTest {

    @Autowired
    EarnMoney earnMoney;

    @Test
    public void test(){
        earnMoney.getDetails(1);
    }

    @Test
    public void testDetail(){
        String url = "feiliao_search.aspx?id=1829082";
        earnMoney.getDetails("1",url);
    }

}
