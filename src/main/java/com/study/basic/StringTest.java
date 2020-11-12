package com.study.basic;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.alibaba.fastjson.JSON;

public class StringTest {

    public static void main(String[] args){

        formatIds();

    }

    public static void equal(){
        formatIds();
    }

    public static void replace(){

//        String str = "a      b";
//        System.out.println(str.replaceAll(" +"," "));
        formatIds();

    }

    public static void differ(){

        String part1 = "user_id\n"
                + "kfYN\n"
                + "lDf6\n"
                + "lDgF\n"
                + "lDfV\n"
                + "kfXv\n"
                + "lDgC\n"
                + "lDg6\n"
                + "lDfQ\n"
                + "lDfC\n"
                + "lDdR\n"
                + "lDd0\n"
                + "lDfD\n"
                + "lDei\n"
                + "lDeO\n"
                + "lDeD\n"
                + "lDeQ\n"
                + "lDgp\n"
                + "lDcK\n"
                + "lDfS\n"
                + "lDdv\n"
                + "lDfA\n"
                + "lDfk\n"
                + "lDeR\n"
                + "lDgE\n"
                + "lDgo\n"
                + "lDf4\n"
                + "lDe7\n";

        String part2 = "ddpvIB\n"
                + "YWgSVy\n"
                + "J82idn\n"
                + "yEboQ4\n"
                + "FfMNav\n"
                + "b2N6te\n"
                + "4QZAVa\n"
                + "uwlfYs\n"
                + "af3ZmL\n"
                + "tgrQkv\n"
                + "f1CMsJ\n"
                + "zbkYYW\n"
                + "Axp6Lw\n"
                + "0PXYt7\n"
                + "o3NvgI\n"
                + "jKOpJI\n"
                + "WihXnO\n"
                + "wvcCT0\n"
                + "C8KPDk\n"
                + "EaE3Yw\n"
                + "E8vgju\n"
                + "MiRw9P\n"
                + "Q9Vfc1\n"
                + "gyAF5z\n"
                + "8IM8yP\n"
                + "NP9e9P\n"
                + "tIWnZf\n"
                + "cwGo9g\n"
                + "4Wljbu";

         Collection d=  CollectionUtils.subtract(Arrays.asList(part1.split("\n")),Arrays.asList(part2.split("\n")));
         System.out.println(JSON.toJSONString(d));
    }

    public static void formatIds(){

        String idListStr = "BP6wfO\n"
                + "uJkJ4d\n"
                + "6FmPiI\n"
                + "CStSP9\n"
                + "n9vi4B\n"
                + "YD8uGi\n"
                + "Xis0Bm\n"
                + "1Pzlju\n"
                + "xN2xT3\n"
                + "hkLIIa\n"
                + "dSLAez\n"
                + "w0pCNT\n"
                + "Ejf4wo\n"
                + "R9D86s\n"
                + "u5RKlm\n"
                + "hcsnnN\n"
                + "WIwyWx\n"
                + "bxsZRd\n"
                + "Debg2O\n"
                + "a1ld9p\n"
                + "fCTdgB\n"
                + "BUrqPy\n"
                + "BxpRZL\n"
                + "8y2sTY\n"
                + "682nb5\n"
                + "7KLVOD\n"
                + "UDwh7p\n"
                + "nW1lqB\n"
                + "oXty1B\n"
                + "gZWqVX\n"
                + "1opJhh\n"
                + "Wd0dUR\n"
                + "nSEQLy\n"
                + "B9KeBb\n"
                + "chVmau\n"
                + "FQAaOV\n"
                + "RtcLWb\n"
                + "bNLIJG\n"
                + "5ybR6D\n"
                + "zlq6Ln\n"
                + "lh4oJD\n"
                + "Cz4YXu\n"
                + "nuO3gW\n"
                + "NZECS3\n"
                + "gq2uFf\n"
                + "aR9n7t\n"
                + "w7Z2IE\n"
                + "02uICY\n"
                + "HJTepY\n";

        Set<String> ids = new HashSet<>();

        String[] ss = idListStr.split("\n");
        StringBuffer stringBuffer = new StringBuffer();
        for(String s :  ss){
            stringBuffer.append("\"").append(s).append("\",");
            //            stringBuffer.append(s).append(",");
            if(ids.contains(s))
                System.out.println(s);
            ids.add(s);
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        System.out.println(stringBuffer.toString());
        System.out.println(ids.size());

    }

}
