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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
            Set<String> urlSet = getList(i);
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
