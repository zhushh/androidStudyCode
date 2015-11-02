package com.example.zhushh.project2extension;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView phoneText;
    private TableLayout mainTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneText = (TextView)findViewById(R.id.phoneText);
        mainTable = (TableLayout)findViewById(R.id.mainTable);

        for (int i = 0; i < 3; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < 3; j++) {
                Button btn = createTableRowButton(3*i+j+"");
                btn.setOnClickListener(buttonsListener);
                row.addView(btn);
            }
            mainTable.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT
            ));
        }
    }

    private Button createTableRowButton(String buttonName) {
        Button btn = new Button(this);
        btn.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT
        ));
        btn.setText(buttonName);
        return btn;
    }

    private View.OnClickListener buttonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button)view;
            phoneText.setText(phoneText.getText().toString() + b.getText().toString());
        }
    };
}
