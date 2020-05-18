package com.kaiakz.apchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private EditText etName;
    private EditText etCode;
    private CheckBox cbREM;

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
}
