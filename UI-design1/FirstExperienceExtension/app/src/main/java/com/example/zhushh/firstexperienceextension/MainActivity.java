package com.example.zhushh.firstexperienceextension;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


// start here
import java.util.Calendar;

import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

// Spinner start here
import java.util.ArrayList;
import java.util.List;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // date picker variable
    private TextView dateView;
    private int year;
    private int month;
    private int day;

    // spinner variable
    /** Called when the activity is first created. */
    private List<String> list = new ArrayList<String>();
    private TextView myTextView;
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // date picker
        DatePicker datePicker=(DatePicker)findViewById(R.id.datePicker);

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int monthOfYear=calendar.get(Calendar.MONTH);
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
            }
        });

        // spinner
        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("移动");
        list.add("软院");
        list.add("管理");
        list.add("政务");
        list.add("环境");

        myTextView = (TextView)findViewById(R.id.majorFieldText);
        mySpinner = (Spinner)findViewById(R.id.majorFieldSpinner);

        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入myTextView 中*/
                // myTextView.setText("您选择的是："+ adapter.getItem(arg2));
                /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                myTextView.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
        /*下拉菜单弹出的内容选项触屏事件处理*/
        mySpinner.setOnTouchListener(new Spinner.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /**
                 *
                 */
                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });
    }
}
