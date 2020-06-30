package com.study.io;

import java.io.*;
import java.util.Scanner;
//java 读取大容量文件，内存溢出？怎么按几行读取，读取多次
public class TestPrint {
    public static void main(String[] args) throws IOException {
String path = "你要读的文件的路径";
RandomAccessFile br=new RandomAccessFile(path,"rw");//这里rw看你了。要是之都就只写r
String str = null, app = null;
int i=0;
while ((str = br.readLine()) != null) {
    i++;
    app=app+str;
    if(i>=100){//假设读取100行
i=0;
// 这里你先对这100行操作，然后继续读
app=null;
    }
}
br.close();
    }

    //当逐行读写大于2G的文本文件时推荐使用以下代码
    void largeFileIO(String inputFile, String outputFile) {
try {
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(inputFile)));
    BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024 * 1024);//10M缓存
    FileWriter fw = new FileWriter(outputFile);
    while (in.ready()) {
String line = in.readLine();
fw.append(line + " ");
    }
    in.close();
    fw.flush();
    fw.close();
} catch (IOException ex) {
    ex.printStackTrace();
}
    }
}