package com.example.zhushh.myapplication;

import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editTextForSend);
        // registerButton
        registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            private BroadcastReceiver br = new MyBroadcastReceiver();
            @Override
            public void onClick(View v) {
                Button btn = ((Button)v);
                String buttonName = btn.getText().toString();
                if (buttonName.equals("Unregister")) {
                    unregisterReceiver(br);
                    btn.setText("Register");
                } else {
                    IntentFilter filter = new IntentFilter();
                    filter.addAction("SYSU_ANDROID_2015");
                    registerReceiver(br, filter);
                    btn.setText("Unregister");
                }
            }
        });

        Button sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("SYSU_ANDROID_2015");
                intent.putExtra("message", editText.getText().toString());
                sendBroadcast(intent);
            }
        });
    }
}
