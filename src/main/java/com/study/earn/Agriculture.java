package com.study.earn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Agriculture {

    @Autowired
    RestTemplate restTemplate;
    String pagePattern = "http://sjcx.fldj.moa.gov.cn/moazzys/feiliao.aspx?page=%d&e=&t=&p=&z=&h=&x=&y=&fd=&yd=";

    public void process(){

        for(int i=1;i<=1494;i++){
            getContent(i);
        }

    }

    public String getContent(int page){

        String url = String.format(pagePattern,page);
        String res = restTemplate.getForObject(url,String.class);
        System.out.println(res);
        return res;

    }


}
