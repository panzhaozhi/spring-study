package com.study.ydl;

public class YoutubeDLException extends Exception {

    public YoutubeDLException(String message) {
        super(message);
    }

    public YoutubeDLException(String message, Throwable e) {
        super(message, e);
    }

    public YoutubeDLException(Throwable e) {
        super(e);
    }

}
