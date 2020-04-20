package com.kaiakz.apchat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String id;
    private String text;
    private String date;

    public Message(String id, String text) {
        this.text = text;
        this.id = id;
        fillDate();
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void fillDate() {
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new Date(System.currentTimeMillis()));
    }
}
