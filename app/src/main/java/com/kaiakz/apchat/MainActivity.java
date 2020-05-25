package com.kaiakz.apchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Widgets
    ListView MessageView;
    EditText MessageTxt;
    ImageButton BtnSendTxt;
    ImageButton BtnSendImage;
    ImageButton BtnSendFile;

    MessageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Widget
        this.MessageTxt = findViewById(R.id.editText);
        this.MessageView = findViewById(R.id.listview_msg);
        this.BtnSendTxt = findViewById(R.id.btn_sendtxt);
        this.BtnSendImage = findViewById(R.id.btn_sendimg);
        this.BtnSendFile = findViewById(R.id.btn_sendfile);

        // Get Info from LoginActivity
        Intent intent = getIntent();
        final String username = intent.getStringExtra("Username");

        // Init the listview
        this.adapter = new MessageAdapter(MainActivity.this);
        this.MessageView.setAdapter(adapter);


        // Add listener for buttons
        this.BtnSendTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = MessageTxt.getText().toString();
                MessageTxt.setText("");
                adapter.putMessage(new Message(username, msg));
            }
        });

        this.BtnSendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.putMessage(new Message(username, 1));
            }
        });

        this.BtnSendFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
