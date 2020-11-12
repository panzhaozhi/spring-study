package com.study.http;

import com.study.MainEnter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainEnter.class)
public class RestTemplateTest {

    @Autowired
    RestTemplate restTemplate;

    /***
     * 检查url是否存在
     * @param url
     * @return
     */
    public boolean checkUrlExsit(String url){
        try {
            HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
            URI location = httpHeaders.getLocation();
            if(location != null){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Test
    public void codeTest(){

        String url = "https://www.youtube.com/channel/UCD5OWQvEP5z2W3guynOahpA";
//        HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
//        System.out.println(JSON.toJSONString(httpHeaders));
//        System.out.println(httpHeaders.getContentLength());
//        System.out.println("..........");
        System.out.println(checkUrlExsit(url));
        url = "https://www.baidu.com/dafdafa";
//        httpHeaders = restTemplate.headForHeaders(url);
//        System.out.println(JSON.toJSONString(httpHeaders));
//        System.out.println(httpHeaders.getLocation().toString());
//        System.out.println(httpHeaders.getContentLength());

        System.out.println(checkUrlExsit(url));

    }

}
