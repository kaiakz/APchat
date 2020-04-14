package com.kaiakz.apchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

public class MainActivity extends AppCompatActivity {

    ListView MessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

        ImageButton btnSend = findViewById(R.id.btnSend);
        this.MessageView = findViewById(R.id.listview_msg);



    }
}
