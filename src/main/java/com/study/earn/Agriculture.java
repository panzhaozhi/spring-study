package com.study.earn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Agriculture {

    @Autowired
    RestTemplate restTemplate;
    String pagePattern = "http://sjcx.fldj.moa.gov.cn/moazzys/feiliao.aspx?page=%d&e=&t=&p=&z=&h=&x=&y=&fd=&yd=";
    static Pattern detaiPattern = Pattern.compile("feiliao_search\\.aspx\\?id=[0-9]+");

    public void process(){

        for(int i=1;i<=1494;i++){
            getList(i);
        }

    }

    public Set<String> getList(int page){

        Set<String> detailUrls = new HashSet<>();
        String url = String.format(pagePattern,page);
        String res = restTemplate.getForObject(url,String.class);
        Matcher matcher = detaiPattern.matcher(res);
        while (matcher.find()){
            detailUrls.add(matcher.group());
        }
        return detailUrls;

    }

    public Set<FormateBean> getDetail(String url){
        Set<FormateBean> formateBeanSet = new HashSet<>();
        String res = restTemplate.getForObject(url,String.class);
        System.out.println(res);
        return formateBeanSet;
    }

    public static void main(String[] args){

       String res = " <a href='feiliao_search.aspx?id=1829081' target=\"_blank\">";
        Matcher matcher = detaiPattern.matcher(res);
        if(matcher.find()){
            System.out.println(matcher.group());
        }

    }


}
