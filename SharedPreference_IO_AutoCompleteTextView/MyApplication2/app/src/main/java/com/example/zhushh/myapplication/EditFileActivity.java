package com.example.zhushh.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zhushh on 2015/11/27.
 */
public class EditFileActivity extends AppCompatActivity {
    private Button saveButton = null;
    private Button readButton = null;
    private Button deleteButton = null;
    private AutoCompleteTextView fileName = null;
    private EditText fileContent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("nice", "here ok3");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_file);

        Log.v("nice", "here ok4");

        fileName = (AutoCompleteTextView)findViewById(R.id.fileName);
        fileName.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, this.fileList()));
        fileContent = (EditText)findViewById(R.id.fileContent);

        saveButton = (Button)findViewById(R.id.saveButton);
        readButton = (Button)findViewById(R.id.readButton);
        deleteButton = (Button)findViewById(R.id.deleteButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils fileUtils = new FileUtils();
                String fileNameText = fileName.getText().toString();
                String fileContentText = fileContent.getText().toString();
                fileUtils.saveContent(EditFileActivity.this, fileNameText, fileContentText);
                fileName.setAdapter(new ArrayAdapter<String>(EditFileActivity.this,
                        android.R.layout.simple_dropdown_item_1line,
                        EditFileActivity.this.fileList()));
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils fileUtils = new FileUtils();
                String fileNameText = fileName.getText().toString();
                String fileContentText = fileUtils.getContent(EditFileActivity.this, fileNameText);
                fileContent.setText(fileContentText);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils fileUtils = new FileUtils();
                String fileNameText = fileName.getText().toString();
                fileUtils.deleteFile(EditFileActivity.this, fileNameText);
                fileName.setAdapter(new ArrayAdapter<String>(EditFileActivity.this,
                        android.R.layout.simple_dropdown_item_1line,
                        EditFileActivity.this.fileList()));
            }
        });
    }
}
