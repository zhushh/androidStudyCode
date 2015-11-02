package com.example.zhushh.project3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zhushh on 11/2/15.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = this.getIntent().getExtras();
        String fruitName = (String) extras.get("fruitName");
        TextView textView = (TextView)findViewById(R.id.loveFruitName);
        textView.setText(fruitName);
        Button backBtn = (Button)findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
