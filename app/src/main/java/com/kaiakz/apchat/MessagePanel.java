package com.kaiakz.apchat;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class MessagePanel {
    ListView MessageView;

    public MessagePanel(ListView MessageView) {
        this.MessageView = MessageView;
    }

    public void putText(String name, String text) {

    }

    public void putImage() {

    }

    public String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new Date(System.currentTimeMillis()));
    }


}
