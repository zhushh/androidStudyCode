package com.example.zhushh.project2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button resetButton;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = (Button)findViewById(R.id.resetButton);
        imageButton = (ImageButton)findViewById(R.id.imageButton);

        imageButton.setOnClickListener(imageListener);
        imageButton.setOnLongClickListener(imageLongListener);
        resetButton.setOnClickListener(buttonListener);

        addDynamicTextView();
    }

    private void addDynamicTextView() {
        // add dynamic TextView
        //LinearLayout mainPannel = (LinearLayout)findViewById(R.id.MainPanel);
        LinearLayout subLinearLayout = (LinearLayout)findViewById(R.id.subLinearLayout);
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView dynamicText = new TextView(this);
        dynamicText.setLayoutParams(vlp);
        dynamicText.setText(R.string.dynamicText);
        //mainPannel.addView(dynamicText);
        subLinearLayout.addView(dynamicText);
    }

    private View.OnClickListener imageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Boolean flag = true;
            EditText userName = (EditText)findViewById(R.id.username);
            EditText userPasswd = (EditText)findViewById(R.id.password);

            // jundge username and password
            if (!"LeiBusi".equals(userName.getText().toString())) {
                flag = false;
            }
            if (!"Ha1o3Q".equals(userPasswd.getText().toString())) {
                flag = false;
            }
            // set state
            if (flag) {
                imageButton.setImageDrawable(getResources().getDrawable(R.drawable.state2));
                userName.setVisibility(View.GONE);
                userPasswd.setVisibility(View.GONE);
            } else {
                imageButton.setImageDrawable(getResources().getDrawable(R.drawable.state1));
                userPasswd.setText("");
                userPasswd.setHint("Wrong user name or password");
                userPasswd.requestFocus();
            }
        }
    };

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText userName = (EditText)findViewById(R.id.username);
            EditText userPasswd = (EditText)findViewById(R.id.password);

            userName.setText("");
            userPasswd.setText("");
            userName.setHint(R.string.usernameHint);
            userPasswd.setHint(R.string.passwordHint);

            userName.setVisibility(View.VISIBLE);
            userPasswd.setVisibility(View.VISIBLE);
            imageButton.setImageDrawable(getResources().getDrawable(R.drawable.state1));
            LinearLayout subLinearLayout = (LinearLayout)findViewById(R.id.subLinearLayout);
            subLinearLayout.removeAllViews();
            addDynamicTextView();
        }
    };

    private  View.OnLongClickListener imageLongListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Boolean result = true;
            addDynamicTextView();
            return result;
        }
    };
}
