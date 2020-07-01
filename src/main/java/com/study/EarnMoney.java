package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EarnMoney {

    @Autowired
    RestTemplate restTemplate;
    Map<String,String> idUrl = new HashMap<>();

    static String listPattern = "http://sjcx.fldj.moa.gov.cn/moazzys/feiliao.aspx?page=%d&e=&t=&p=&z=&h=&x=&y=&fd=&yd=";
    Pattern listNoPattern = Pattern.compile("<span id=\"Gvfl_Label1.*?\">([0-9]+).*?<a href='(feiliao_search\\.aspx\\?id=[0-9]+)'",Pattern.DOTALL|Pattern.MULTILINE);
//    Pattern detailPattern = Pattern.compile("feiliao_search\\.aspx\\?id=[0-9]+");
    Pattern contentPattern = Pattern.compile("<table width=\"985\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">.*?<span id=\"lblqyname\">(.*?)</span>.*?<span id=\"lblpnme\">(.*?)</span>.*?<span id=\"lblsname\">(.*?)</span>.*?<span id=\"lblsyzw\">(.*?)</span>.*?<span id=\"lbldjzh\">(.*?)</span>.*?<span id=\"lbljszb\">(.*?)</span>.*?<span id=\"lblyxjzname\">(.*?)</span>.*?<span id=\"lblxt\">(.*?)</span>.*?<span id=\"lblyxdate\">(.*?)</span>.*?<span id=\"yxdate\">(.*?)</span>",Pattern.DOTALL|Pattern.MULTILINE);
    Set<String> totalUrl = new HashSet<>();
    Set<FormatBean> formatBeanSet = new HashSet<>();

    public void collectPages(){
        for(int i=1;i<2;i++){
            getDetails(i);
        }
        List<FormatBean> formatBeanList = formatBeanSet.stream().sorted(Comparator.comparing(FormatBean::getNo)).collect(Collectors.toList());
        for(FormatBean formatBean : formatBeanList){

        }
    }

    public Map<String,String> getDetails(int page){
        String url = String.format(listPattern,page);
        String res = restTemplate.getForObject(url,String.class);
        System.out.println(res);
        Matcher matcher = listNoPattern.matcher(res);
        while (matcher.find()){
            if(matcher.groupCount() == 2){
                idUrl.put(matcher.group(1),matcher.group(2));
                formatBeanSet.addAll(getDetails(matcher.group(1),matcher.group(2)));
            }
        }
        return idUrl;
    }

    public Set<FormatBean> getDetails(String id,String url){
        url = "http://sjcx.fldj.moa.gov.cn/moazzys/" + url;
        String res = restTemplate.getForObject(url,String.class);
//        System.out.println(res);
        Set<FormatBean> formatBeanSet = new HashSet<>();
        Matcher matcher = contentPattern.matcher(res);
        if(matcher.find()){
            FormatBean formatBean = new FormatBean();
            System.out.println(matcher.group(0));
            formatBean.setNo(Integer.parseInt(id.trim()));
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
