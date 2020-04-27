package com.kaiakz.apchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView MessageView;
    EditText MessageTxt;
    ImageButton BtnSendTxt;
    ImageButton BtnSendImage;
    ImageButton BtnSendFile;

    MessageAdapter adapter;

    WifiP2pManager manager;
    WifiP2pManager.Channel channel;
    BroadcastReceiver receiver;

    IntentFilter intentFilter;

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

        this.manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = manager.initialize(this, getMainLooper(), null);     // which is used to connect your application to the Wi-Fi P2P framework.
        this.receiver = new WifiDirectBroadcastReceiver(this.manager, this.channel, this);

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

        // Add the same intents that your broadcat receiver checks for
        this.intentFilter = new IntentFilter();
        this.intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        this.intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        this.intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        this.intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        // Discover peers
        this.manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {}

            @Override
            public void onFailure(int reasonCode) {}
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(this.receiver, this.intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(this.receiver);
    }
}
