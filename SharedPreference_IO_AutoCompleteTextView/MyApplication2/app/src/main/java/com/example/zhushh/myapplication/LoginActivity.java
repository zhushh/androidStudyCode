package com.example.zhushh.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText userName = null;
    private EditText passwd = null;
    private CheckBox ischeckBox = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText)findViewById(R.id.userName);
        passwd = (EditText)findViewById(R.id.passwd);

        ischeckBox = (CheckBox)findViewById(R.id.checkBox);
        ischeckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                    userName.setText(sharedPreferences.getString("userName", ""));
                    passwd.setText(sharedPreferences.getString("passwd", ""));
                } else {
                    userName.setText("");
                    passwd.setText("");
                }
            }
        });

        Button registerButton = (Button)findViewById(R.id.registerButton);
        Button loginButton = (Button)findViewById(R.id.loginButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameText = userName.getText().toString();
                String passwdText = passwd.getText().toString();
                if (!(userNameText.equals("") || passwdText.equals(""))) {
                    SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                    editor.putString("userName", userNameText);
                    editor.putString("passwd", passwdText);
                    editor.commit();
                    Toast.makeText(LoginActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String sharedUserName = sharedPreferences.getString("userName", "");
                String sharedPasswd = sharedPreferences.getString("passwd", "");
                String NameText = userName.getText().toString();
                String passwdText = passwd.getText().toString();
                if (sharedUserName.equals(NameText) && sharedPasswd.equals(passwdText)) {
                    Log.v("nice", "here ok1");
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, EditFileActivity.class);
                    startActivity(intent);
                    Log.v("nice", "here ok2");
                } else {
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
