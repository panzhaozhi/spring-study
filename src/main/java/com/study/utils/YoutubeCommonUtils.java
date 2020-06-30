package com.study.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.sun.deploy.util.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeCommonUtils {

    private static final String YOUTUBE_CHANNEL_URL_PATTERN = "https://www.youtube.com/channel/(.*)?";

    private static final String YOUTUBE_USER_URL_PATTERN = "https://www.youtube.com/user/(.*)/videos";
    private static final String YOUTUBE_USER_URL_PATTERN_2 = "https://www.youtube.com/user/(.*)";

    private static final String YOUTUBE_CHANNEL_URL_FORMAT = "https://www.youtube.com/channel/%s/videos";

    /***
     * 该方法可以完全基于正则表达式，可以优化
     * @param crawlLink
     * @return
     */
    public static String getSourceId(String crawlLink) {
        Pattern pattern = Pattern.compile(YOUTUBE_CHANNEL_URL_PATTERN);
        Matcher matcher = pattern.matcher(crawlLink);
        if (matcher.find()) {
            String sourceIdPart = matcher.group(1);

            int pos = sourceIdPart.indexOf("/");
            if(pos == -1){
                return sourceIdPart;
            }else {
                return sourceIdPart.substring(0,pos);
            }
        }
        return null;
    }

    public static String getUserName(String crawlLink) {
        Pattern pattern = Pattern.compile(YOUTUBE_USER_URL_PATTERN);
        Matcher matcher = pattern.matcher(crawlLink);
        if (matcher.find()) {
            return matcher.group(1);
        }
        pattern = Pattern.compile(YOUTUBE_USER_URL_PATTERN_2);
        matcher = pattern.matcher(crawlLink);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String getChannelBySourceId(String sourceId){
        return String.format(YOUTUBE_CHANNEL_URL_FORMAT,sourceId);
    }

    public static void main(String[] args) throws IOException {

        String str = "https://www.youtube.com/channel/UCqvtaBINBx7k6pQBR5CKYzw/videos\n" +
                "https://www.youtube.com/channel/UC2ziCMHFPWkFHjocUMXT__Q/videos\n" +
                "https://www.youtube.com/channel/UCLTZ5y21NnklrxpU5AfWd8w/videos\n" +
                "https://www.youtube.com/channel/UCx8Z14PpntdaxCt2hakbQLQ/videos\n" +
                "https://www.youtube.com/channel/UCAUNFgpgVisKPL3yq_-Nj-Q/videos\n" +
                "https://www.youtube.com/channel/UCb1ScGnYiuIlc8AT5if67hg/videos\n" +
                "https://www.youtube.com/channel/UCSMSO8ITitwZ_fgIelqRSig/videos\n" +
                "https://www.youtube.com/channel/UCnAp2J0bR9b8pM-Avp1GFOQ/videos\n" +
                "https://www.youtube.com/channel/UCZINqCwuTRertJyDvHUSopA/videos\n" +
                "https://www.youtube.com/channel/UCskG03x3CoEW9W7s2c3IgbA/videos\n" +
                "https://www.youtube.com/channel/UCH6v_SxtFLtfD4Iptx2WbNg/videos\n" +
                "https://www.youtube.com/channel/UCpDxPj3sm40ISX5hn-TlYcw/videos\n" +
                "https://www.youtube.com/channel/UCuzS3rPQAYqHcLWqOFuY0pw/videos\n" +
                "https://www.youtube.com/channel/UC2LMxF5cbXIaej5ifKTQ6WQ/videos\n" +
                "https://www.youtube.com/channel/UCiH76dxhb7cMWiDv1hVR2vg/videos\n" +
                "https://www.youtube.com/channel/UCsJO8p6lnyjZkuLO2k0qFXA/videos\n" +
                "https://www.youtube.com/channel/UCIuM0fIC9VfVko2e_mGgmEg/videos\n" +
                "https://www.youtube.com/channel/UCPckg9pijh0KjJm4X0Xhviw/videos\n" +
                "https://www.youtube.com/channel/UCZF0z6CyEs_e_IXaB57xqSA/videos\n" +
                "https://www.youtube.com/channel/UCwPAS11WSOQvZh5RA36Wr-Q/videos\n" +
                "https://www.youtube.com/channel/UC55LzMuR6ZeSpJMNCAfzb8w/videos\n" +
                "https://www.youtube.com/channel/UCBbpLKJLhIbDd_wX4ubU_Cw/videos\n" +
                "https://www.youtube.com/channel/UC66JzqH4ZlzzbFUEPBuqWXw/videos\n" +
                "https://www.youtube.com/channel/UCvjqPU2VieP_BtYNtnrNatQ/videos\n" +
                "https://www.youtube.com/channel/UCQLEbraENUGWh6p1Rv664rQ/videos\n" +
                "https://www.youtube.com/channel/UCCJRFl1Qjo83GG_09U/videos\n" +
                "https://www.youtube.com/channel/UCwgqmM4JWKAhKzuhGbFIMTg/videos\n" +
                "https://www.youtube.com/channel/UCEhsOgK2u8GDDoynMCj78Ng/videos\n" +
                "https://www.youtube.com/channel/UCZva4a_el1WqNTaJATihKjQ/about/videos\n" +
                "https://www.youtube.com/channel/UC-qQac_ZLmIPV4q8zdPBGvw/videos\n" +
                "https://www.youtube.com/channel/UCLoVx7cgZw7dBaXNf1SB2FA/videos\n" +
                "https://www.youtube.com/channel/UCpkp7uBpXeDXHt4MHttI0Mw/videos\n" +
                "https://www.youtube.com/channel/UCiiNw0Tnqoe65x0Bl7KzP3Q/featured/videos\n" +
                "https://www.youtube.com/channel/UCs9cS7CEy6nkD_R-VsiB8UA/videos\n" +
                "https://www.youtube.com/channel/UCkj6IZELEepcuvcpv9GrFRg/featured/videos\n" +
                "https://www.youtube.com/channel/UC3jcdpf8dWtljex9PyhSM6w/videos\n" +
                "https://www.youtube.com/channel/UCfymZbh17_3T_UhgjkQ9fRQ/videos\n" +
                "https://www.youtube.com/channel/UCMR9_VzJCLGulVRvVMVbXyA/videos\n" +
                "https://www.youtube.com/channel/UCSROkDKetoJNopummlhCS9A/videos\n" +
                "https://www.youtube.com/channel/UCLt4BbJqlNDWUBBF9L2lHMg/videos\n" +
                "https://www.youtube.com/channel/UCDfOFTP71q16F8p1OvW9Xmg/videos\n" +
                "https://www.youtube.com/channel/UCx13HeEAjJM1z1RnKMIubIw/videos\n" +
                "https://www.youtube.com/channel/UCfAYlSfP8mZVZRf1cu69RGg/videos\n" +
                "https://www.youtube.com/channel/UCDzObp9vb2yxD_wLFOsERgw/videos\n" +
                "https://www.youtube.com/channel/UC-1fgd20J64tFY1uwsH1JXQ/videos\n" +
                "https://www.youtube.com/channel/UCE8KNq9oibK4AdPw_iWrS1Q/videos\n" +
                "https://www.youtube.com/channel/UC20y0LpqYwsne0FXVXyGcwQ/videos\n" +
                "https://www.youtube.com/channel/UCUSnmtB3V6Z65lLB3ANRlQw/videos\n" +
                "https://www.youtube.com/channel/UCcTRD6P6N1RmIKFucSqytHg/videos\n" +
                "https://www.youtube.com/channel/UCGXbfo5nXbQZ5QGC3G-fWiA/videos\n" +
                "https://www.youtube.com/channel/UCCt6jsbkeMBSwa7PhBfw7Mg/videos\n" +
                "https://www.youtube.com/channel/UCEljXvdQ273AIJp67edfeeg/videos\n" +
                "https://www.youtube.com/channel/UCt34sjyRNu16Xv4VXNJYQJA/videos\n" +
                "https://www.youtube.com/channel/UC-Yz1K9QH3VQuEL5qzLptJQ/videos\n" +
                "https://www.youtube.com/channel/UC0Nf0_j2P9DR-jw5mKxvt2Q/videos\n" +
                "https://www.youtube.com/channel/UCVMmwqDZ9vjuOmSL-rAQwQw/videos\n" +
                "https://www.youtube.com/channel/UCfg6LbPz07csM3RgaRrvJGQ/videos\n" +
                "https://www.youtube.com/channel/UCeHTzY_gxSLC4M9i80s1D9Q/videos\n" +
                "https://www.youtube.com/channel/UC-LrOZnSOXh8SzDH5OPAKjA/videos\n" +
                "https://www.youtube.com/channel/UCieUvzV5sY3FCAGBBmbB3rQ/videos\n" +
                "https://www.youtube.com/channel/UCIM8hrm144a142WQqdX1HyA/videos\n" +
                "https://www.youtube.com/channel/UC0Qx29G4AUmhsy9O1EavVVw/videos\n" +
                "https://www.youtube.com/channel/UCDgmtqSkTBRQQhcBEsMPoUg/videos\n" +
                "https://www.youtube.com/channel/UClvshObfmyyCA5H6MTnnR6A/videos\n" +
                "https://www.youtube.com/channel/UCriatuMARJZeVULTUpOlmvg/featured/videos\n" +
                "https://www.youtube.com/channel/UCbKPgv34xvrmsF4Ku9-3F0Qhttps:/videos\n" +
                "https://www.youtube.com/channel/UCr27a45x93eb6AXWKFfyYow/videos\n" +
                "https://www.youtube.com/channel/UC8Z-VjXBtDJTvq6aqkIskPg/videos\n" +
                "https://www.youtube.com/channel/UCgLJs7zqFO-uz8EAGDL6ESw/videos\n";

        String[] ss = str.split("\n");
        System.out.println(ss.length);
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> urlSet = new HashSet<>();
        for(String s : ss){
            stringBuffer.append("\"").append(getSourceId(s)).append("\",");
            urlSet.add(getSourceId(s));
        }
        System.out.println(stringBuffer.toString());
        String jsonStr = read(new File("d://ttt"));
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONObject("hits").getJSONArray("hits");
        for(int i=0;i<jsonArray.size();i++){
            String sourceId = jsonArray.getJSONObject(i).getJSONObject("_source").getString("source_id");
            urlSet.remove(sourceId);
        }
        System.out.println(urlSet);
    }

    public static String read(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str = "";
        String line = null;
        while ((line=bufferedReader.readLine()) != null){
            str += line + "\n";
        }
        return str;
    }

}
