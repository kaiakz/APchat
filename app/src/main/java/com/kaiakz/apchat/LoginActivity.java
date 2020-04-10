package com.kaiakz.apchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText et = (EditText)findViewById(R.id.et_username);
        String username = et.getText().toString();
        intent.putExtra("Username", username);

        Toast ts = Toast.makeText(getBaseContext(), "Hello " + username, Toast.LENGTH_LONG);
        ts.show();

        startActivity(intent);
    }
}
