package com.study.earn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
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
    int num = 0;

    public void collectPages() throws IOException, InterruptedException {

        String fileName = "/data/earni.xlsx";
        SXSSFWorkbook workbook = new SXSSFWorkbook(200);
        Sheet sheet = workbook.createSheet();
        //设置标题
        Row rowTitle = sheet.createRow(0);
        HashMap<Integer, String> checklogOrderNameMap = new HashMap<>();
        tableName(checklogOrderNameMap);
        for (Map.Entry<Integer, String> entry : checklogOrderNameMap.entrySet()) {
            Cell cell = rowTitle.createCell(entry.getKey());
            RichTextString text = new XSSFRichTextString(entry.getValue());
            cell.setCellValue(text);
        }
        for(int i=1;i<=1;i++){
            System.out.println("process page "+i);
            getDetails(i);
        }
        Iterator iterator = idUrl.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry<String,String> entry = (Map.Entry<String,String>)iterator.next();
//            formatBeanSet.addAll(getDetails(entry.getKey(),entry.getValue()));
//        }
        int rowNum = 1;
        List<FormatBean> formatBeanList = formatBeanSet.stream().sorted(Comparator.comparing(FormatBean::getNo)).collect(Collectors.toList());
        for(FormatBean formatBean : formatBeanList){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(formatBean.getNo());
            row.createCell(1).setCellValue(formatBean.getCompanyName());
            row.createCell(2).setCellValue(formatBean.getProductName());
            row.createCell(3).setCellValue(formatBean.getProductBusi());
            row.createCell(4).setCellValue(formatBean.getProductState());
            row.createCell(5).setCellValue(formatBean.getTech());
            row.createCell(6).setCellValue(formatBean.getPlant());
            row.createCell(7).setCellValue(formatBean.getRecord());
            row.createCell(8).setCellValue(formatBean.getPubDate());
            row.createCell(9).setCellValue(formatBean.getValidDate());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        // 释放掉workbook 创建sheet时的临时文件。
        workbook.dispose();
    }

    public Map<String,String> getDetails(int page) throws InterruptedException {

        String url = String.format(listPattern,page);
        String res = restTemplate.getForObject(url,String.class);
//        System.out.println(res);
        Matcher matcher = listNoPattern.matcher(res);
        while (matcher.find()){
            if(matcher.groupCount() == 2){
                idUrl.put(matcher.group(1),matcher.group(2));
                formatBeanSet.addAll(getDetails(matcher.group(1),matcher.group(2)));
            }
            System.out.println("formatBeanSet " + formatBeanSet.size());
        }
        return idUrl;
    }

    public Set<FormatBean> getDetails(String id,String url) throws InterruptedException {
        url = "http://sjcx.fldj.moa.gov.cn/moazzys/" + url;
        String res = restTemplate.getForObject(url,String.class);
        System.out.println(num++ + " " + url);
        Set<FormatBean> formatBeanSet = new HashSet<>();
        Matcher matcher = contentPattern.matcher(res);
        if(matcher.find()){
            FormatBean formatBean = new FormatBean();
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

    private void tableName(HashMap<Integer, String> checklogOrderNameMap) {
        checklogOrderNameMap.put(0, "序号");
        checklogOrderNameMap.put(1, "企业名称");
        checklogOrderNameMap.put(2, "产品通用名");
        checklogOrderNameMap.put(3, "产品商品名");
        checklogOrderNameMap.put(4, "产品形态");
        checklogOrderNameMap.put(5, "登记技术指标");
        checklogOrderNameMap.put(6, "适宜作物");
        checklogOrderNameMap.put(7, "登记证号");
        checklogOrderNameMap.put(8, "发证日期");
        checklogOrderNameMap.put(9, "有效日期");

    }

}
