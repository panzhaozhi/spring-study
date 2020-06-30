package com.study.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadMethodsCompare {
    public static void main(String[]args){

        File file=new File("/Users/panzz/Downloads/range-4611686018427387907_-3843071682022823257.9");
        try{

            if(file.exists()){
                long startTime=System.currentTimeMillis();
                FileReader fr=new FileReader(file);
                LineNumberReader lnr=new LineNumberReader(fr);
                int lines=0;
                while(lnr.readLine()!=null){
                    ++lines;
                }

                lnr.close();
                long endTime=System.currentTimeMillis();
                System.out.println("方案一读取总行数 : "+lines+" 共耗时 : "+(endTime-startTime));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            if(file.exists()){
                long startTime=System.currentTimeMillis();
                long fileLength=file.length();
                LineNumberReader lineNumberReader=new LineNumberReader(new FileReader(file));
                lineNumberReader.skip(fileLength);
                int lines=lineNumberReader.getLineNumber();
                lineNumberReader.close();
                long endTime=System.currentTimeMillis();
                System.out.println("方案二读取总行数 : "+lines+" 共耗时 : "+(endTime-startTime));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            long startTime=System.currentTimeMillis();
            long lines= Files.lines(Paths.get(file.getPath())).count();
            long endTime=System.currentTimeMillis();
            System.out.println("方案三读取总行数 : "+lines+" 共耗时 : "+(endTime-startTime));
        }catch(Exception e){
            e.printStackTrace();
        }

        InputStream is=null;
        try{
            long startTime=System.currentTimeMillis();
            long lines=0l;
            int readChars=0;
            byte[]buffer=new byte[4096];
            is=new BufferedInputStream(new FileInputStream(new File("D:\\resultBigDataFile\\HumanBigData1.csv")));
            while((readChars=is.read(buffer,0,4096))!=-1){
                for(int i=0;i<readChars; ++i){
                    if(buffer[i]=='\n'){
                        ++lines;
                    }
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.println("方案四读取总行数 : "+lines+" 共耗时 : "+(endTime-startTime));
        }catch(Exception e){

        }finally{
            try{
                is.close();
            }catch(Exception e){

            }
        }


        BufferedReader bufferedReader=null;
        try{
            long startTime=System.currentTimeMillis();
            long lines=0l;
            char[]readChars=new char[4096];
            char[]nChar=new char[]{'\n'};
            int length=0;
            bufferedReader=new BufferedReader(new FileReader(file));
            while((length=bufferedReader.read(readChars,0,4096))!=-1){
                for(int i=0;i<length; ++i){
                    if(readChars[i]==nChar[0]){
                        ++lines;
                    }
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.println("方案五读取总行数 : "+lines+" 共耗时 : "+(endTime-startTime));
        }catch(Exception e){

        }finally{
            try{
                bufferedReader.close();
            }catch(Exception e){

            }
        }

        BufferedReader br=null;
        try{
            long startTime=System.currentTimeMillis();
            long lines=0;
            FileInputStream fis=new FileInputStream(file);
            InputStreamReader isr=new InputStreamReader(fis);
            br=new BufferedReader(isr);
            while((br.readLine())!=null){
                ++lines;
            }
            long endTime=System.currentTimeMillis();
            System.out.println("方案六读取总行数 : "+lines+" 共耗时 : "+(endTime-startTime));
        }catch(Exception e){

        }finally{
            if(br!=null){
                try{
                    br.close();
                }catch(Exception e){
                }
            }
        }

        BufferedReader bufReader=null;
        try{
            String line="";
            long lines=0l;
            long startTime=System.currentTimeMillis();
            bufReader=new BufferedReader(new FileReader(new File("D:\\resultBigDataFile\\HumanBigData1.csv")));
            while((line=bufReader.readLine())!=null){
                ++lines;
            }
            long endTime=System.currentTimeMillis();
            System.out.println("方案七读取总行数 : "+lines+" 共耗时 : "+(endTime-startTime));
        }catch(Exception e){

        }finally{
            if(bufReader!=null){
                try{
                    bufReader.close();
                }catch(Exception e){
                }
            }
        }

    }
}
