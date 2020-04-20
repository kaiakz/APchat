package com.kaiakz.apchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

public class MainActivity extends AppCompatActivity {

    ListView MessageView;
    EditText MessageTxt;
    ImageButton BtnSendTxt;
    ImageButton BtnSendImage;
    ImageButton BtnSendFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

        this.MessageTxt = findViewById(R.id.editText);
        this.MessageView = findViewById(R.id.listview_msg);
        this.BtnSendTxt = findViewById(R.id.btn_sendtxt);
        this.BtnSendImage = findViewById(R.id.btn_sendimg);
        this.BtnSendFile = findViewById(R.id.btn_sendfile);

        MessageAdapter adapter = new MessageAdapter(MainActivity.this);

        this.MessageView.setAdapter(adapter);

        adapter.add(new Message("Kai", "Hello"));

        this.BtnSendTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = MessageTxt.getText().toString();
                Toast ts = Toast.makeText(getBaseContext(), t, Toast.LENGTH_LONG);
                ts.show();
            }
        });

        this.BtnSendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.BtnSendFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
