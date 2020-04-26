package com.kaiakz.apchat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String id;
    private String date;
    private TYPE type;
    private Object body;

    public static enum TYPE {
        TEXT, IMAGE;
    }

    public Message(String id, String body) {
        this.body = body;
        this.id = id;
        this.type = TYPE.TEXT;
        fillDate();
    }

    public Message(String id, int body) {
        this.body = body;
        this.id = id;
        this.type = TYPE.IMAGE;
        fillDate();
    }

    public String getText() {
        return (String) body;
    }

    public int getImage() {
        return (Integer)body;
    }

    public TYPE getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void fillDate() {
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }
}
