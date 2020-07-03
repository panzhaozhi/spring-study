package com.study.ydl;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Download{

    protected static final ObjectMapper objectMapper = new ObjectMapper();


    public VideoInfo getInfo(String url) throws YoutubeDLException, InterruptedException {
        YoutubeDLRequest request = new YoutubeDLRequest(url);
        return getInfo(request);
    }

    public VideoInfo getInfo(YoutubeDLRequest request) throws YoutubeDLException, InterruptedException {
        request.addOption("--dump-json");
        YoutubeDLResponse response = execute(request, null);

        VideoInfo videoInfo;
        try {
            videoInfo = objectMapper.readValue(response.getOut(), VideoInfo.class);
        } catch (IOException e) {
            throw new YoutubeDLException("Unable to parse video information", e);
        }

        return videoInfo;
    }

    public YoutubeDLResponse execute(YoutubeDLRequest request) throws YoutubeDLException, InterruptedException {
        return execute(request, null);
    }

    public YoutubeDLResponse execute(YoutubeDLRequest request, DownloadProgressCallback callback) throws YoutubeDLException, InterruptedException {

        // disable caching unless explicitly requested
        if(request.getOption("--cache-dir") == null){
            request.addOption("--no-cache-dir");
        }

        YoutubeDLResponse youtubeDLResponse;
        Process process;
        int exitCode;
        StringBuffer outBuffer = new StringBuffer(); //stdout
        StringBuffer errBuffer = new StringBuffer(); //stderr
        long startTime = System.currentTimeMillis();

        List<String> args = request.buildCommand();
        List<String> command = new ArrayList<>();
        command.add("/usr/local/bin/youtube-dl");
        command.add("-o");
        command.add("'%(playlist_index)s - %(title)s.%(ext)s'");
        command.add("https://www.youtube.com/watch?v=WhxgwTda468");
//        command.add("");
//        command.add("");
        command.addAll(args);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File("/Users/panzz"));

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new YoutubeDLException(e);
        }

        InputStream outStream = process.getInputStream();
        InputStream errStream = process.getErrorStream();

        StreamProcessExtractor stdOutProcessor = new StreamProcessExtractor(outBuffer, outStream, callback);
        StreamGobbler stdErrProcessor = new StreamGobbler(errBuffer, errStream);

        try {
            stdOutProcessor.join();
            stdErrProcessor.join();
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            process.destroy();
            throw e;
        }

        String out = outBuffer.toString();
        String err = errBuffer.toString();

        if (exitCode > 0) {
            throw new YoutubeDLException(err);
        }

        long elapsedTime = System.currentTimeMillis() - startTime;

        youtubeDLResponse = new YoutubeDLResponse(command, exitCode, elapsedTime, out, err);

        return youtubeDLResponse;
    }




    public static void main(String[] args) throws YoutubeDLException, InterruptedException {

        YoutubeDLRequest request  = new YoutubeDLRequest("");
        DownloadProgressCallback callback = new DownloadProgressCallback() {
            @Override
            public void onProgressUpdate(float progress, long etaInSeconds) {

            }
        };
        Download download = new Download();
        download.execute(request,callback);

    }

}
