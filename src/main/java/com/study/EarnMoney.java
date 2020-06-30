package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class EarnMoney {

    @Autowired
    RestTemplate restTemplate;
    Map<String,String> idUrl = new HashMap<>();

    static String listPattern = "http://sjcx.fldj.moa.gov.cn/moazzys/feiliao.aspx?page=%d&e=&t=&p=&z=&h=&x=&y=&fd=&yd=";
    Pattern listNoPattern = Pattern.compile("<span id=\"Gvfl_Label1.*?\">([0-9]+).*?<a href='(feiliao_search\\.aspx\\?id=[0-9]+)'",Pattern.DOTALL|Pattern.MULTILINE);
//    Pattern detailPattern = Pattern.compile("feiliao_search\\.aspx\\?id=[0-9]+");
    Pattern contentPattern = Pattern.compile("<table width=\"985\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">.*?<span id=\"lblqyname\">(.*?)</span>.*?<span id=\"lblpnme\">(.*?)</span>.*?<span id=\"lblsname\">(.*?)</span>.*?<span id=\"lblsyzw\">(.*?)</span>.*?<span id=\"lbldjzh\">(.*?)</span>.*?<span id=\"lbljszb\">(.*?)</span>.*?<span id=\"lblyxjzname\">(.*?)</span>.*?<span id=\"lblxt\">(.*?)</span>.*?<span id=\"lblyxdate\">(.*?)</span>.*?<span id=\"yxdate\">(.*?)</span>");
    Set<String> totalUrl = new HashSet<>();

    public void collectPages(){

    }

    public Map<String,String> getDetails(int page){
        String url = String.format(listPattern,page);
        String res = restTemplate.getForObject(url,String.class);
        System.out.println(res);
        Matcher matcher = listNoPattern.matcher(res);
        while (matcher.find()){
            if(matcher.groupCount() == 2){
                idUrl.put(matcher.group(1),matcher.group(2));
            }
        }
        return idUrl;
    }

    public Set<FormatBean> getDetails(String id,String url){
        url = "http://sjcx.fldj.moa.gov.cn/moazzys/" + url;
        String res = restTemplate.getForObject(url,String.class);
        System.out.println(res);
        Set<FormatBean> formatBeanSet = new HashSet<>();
        Matcher matcher = contentPattern.matcher(res);
        System.out.println(matcher.groupCount());
        if(matcher.groupCount() == 10){
            FormatBean formatBean = new FormatBean();
            formatBean.setNo(id);
            formatBean.setCompanyName(matcher.group(1));
            formatBean.setProductName(matcher.group(2));
            formatBean.setProductBusi(matcher.group(3));
            formatBean.setPlant(matcher.group(4));
            formatBean.setRecord(matcher.group(5));
            formatBean.setTech(matcher.group(6));
            formatBean.setProductState(matcher.group(8));
            formatBean.setPubDate(matcher.group(9));
            formatBean.setValidDate(matcher.group(10));
            formatBeanSet.add(formatBean);
        }
        return formatBeanSet;
    }

}
