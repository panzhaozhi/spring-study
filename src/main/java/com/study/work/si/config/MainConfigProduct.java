package com.study.work.si.config;

import com.alibaba.fastjson.JSONArray;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainConfigProduct {

    public static void main(String[] args){
        codeErrorBeanMake();
    }

    public static void codeErrorBeanMake(){

        CodeErrorBean x26Bean = new CodeErrorBean();
        x26Bean.setCode("0x0020006");
        x26Bean.setIgnore(false);
        x26Bean.setMessage("cover repeat");

        CodeErrorBean x33Bean = new CodeErrorBean();
        x33Bean.setCode("0x0030003");
        x33Bean.setIgnore(true);
        x33Bean.setMessage("");

        CodeErrorBean x34Bean = new CodeErrorBean();
        x34Bean.setCode("0x0030004");
        x34Bean.setIgnore(true);
        x34Bean.setMessage("");

        CodeErrorBean x35Bean = new CodeErrorBean();
        x35Bean.setCode("0x0030005");
        x35Bean.setIgnore(true);
        x35Bean.setMessage("");

        CodeErrorBean x47Bean = new CodeErrorBean();
        x47Bean.setCode("0x0040007");
        x47Bean.setIgnore(false);
        x47Bean.setMessage("video error");

        CodeErrorBean x48Bean = new CodeErrorBean();
        x48Bean.setCode("0x0040008");
        x48Bean.setIgnore(false);
        x48Bean.setMessage("cover error");

        CodeErrorBean x5Bean = new CodeErrorBean();
        x5Bean.setCode("0x005");
        x5Bean.setIgnore(false);
        x5Bean.setMessage("video error");

        List<CodeErrorBean> codeErrorBeanList = new ArrayList<>();
        codeErrorBeanList.add(x26Bean);
        codeErrorBeanList.add(x33Bean);
        codeErrorBeanList.add(x34Bean);
        codeErrorBeanList.add(x35Bean);
        codeErrorBeanList.add(x47Bean);
        codeErrorBeanList.add(x48Bean);
        codeErrorBeanList.add(x5Bean);

        Map<String,Object> codeMap = new HashMap<>();
        for(CodeErrorBean codeErrorBean : codeErrorBeanList){
            codeMap.put(codeErrorBean.getCode(),codeErrorBean);
        }

        System.out.println(JSONArray.toJSONString(codeMap));

    }

}
