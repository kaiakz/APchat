package com.kaiakz.apchat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private EditText etName;
    private EditText etCode;
    private CheckBox cbREM;

    // WiFi P2P
    WifiP2pManager manager;
    WifiP2pManager.Channel channel;
    BroadcastReceiver receiver;

    IntentFilter intentFilter;

    ArrayList<WifiP2pDevice> peersList = new ArrayList<WifiP2pDevice>();

    WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peers) {
            if (peers.getDeviceList().size() == 0) {
                Toast.makeText(getApplicationContext(), "No Devices Found", Toast.LENGTH_SHORT).show();
                return;
            }
            peersList.clear();
            peersList.addAll(peers.getDeviceList());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("INFO", MODE_PRIVATE);

        etName = (EditText) findViewById(R.id.edittext_username);
        etCode = (EditText) findViewById(R.id.edittext_roomid);
        cbREM = (CheckBox) findViewById(R.id.cb_rem);

        String username = sharedPreferences.getString("ID", null);

        if (username != null) {
            etName.setText(username);
            cbREM.setChecked(true);
            String code = sharedPreferences.getString("CODE", null);
            if (code != null) {
                etCode.setText(code);
            }
        }


        this.manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        assert manager != null;
        this.channel = manager.initialize(this, getMainLooper(), null);     // which is used to connect your application to the Wi-Fi P2P framework.
        this.receiver = new WifiDirectBroadcastReceiver(this.manager, this.channel, this);

        // Add the same intents that your broadcat receiver checks for
        this.intentFilter = new IntentFilter();
        this.intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        this.intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        this.intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        this.intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        // Discover peers
        this.manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(int reasonCode) {
            }
        });


    }

    public void login(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        String username = etName.getText().toString().trim();
        String code = etCode.getText().toString().trim();

        // If the checkbox "Remember Me" is checked, save the username and code in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (cbREM.isChecked()) {
            editor.putString("ID", username);
            editor.putString("CODE", code);
        } else {
            editor.clear();
        }
        editor.apply();

        intent.putExtra("Username", username);

        Toast ts = Toast.makeText(getBaseContext(), "Hello " + username, Toast.LENGTH_LONG);
        ts.show();

        startActivity(intent);
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

    public void AsGuest(View view) {
        String target = this.etCode.getText().toString().trim();
        // Search the wifi id
        for (WifiP2pDevice d : peersList) {
            if (d.deviceName.equals(target)) {
                WifiP2pConfig config = new WifiP2pConfig();
                config.deviceAddress = d.deviceAddress;
                manager.connect(channel, config, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(int reason) {

                    }
                });
                return;
            }
        }

        Toast.makeText(getApplicationContext(), "Code Invaild", Toast.LENGTH_SHORT).show();
    }
}
