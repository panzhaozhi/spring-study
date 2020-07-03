package com.study.ydl;

public interface DownloadProgressCallback {
    void onProgressUpdate(float progress, long etaInSeconds);
}
