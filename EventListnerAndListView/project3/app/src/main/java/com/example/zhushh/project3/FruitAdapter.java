package com.example.zhushh.project3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhushh on 11/2/15.
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {
    private Context context;
    private int layoutResourceId;
    private List<Fruit> fruits;

    public FruitAdapter(Context context, int layoutResourceId, List<Fruit> fruits) {
        super(context, layoutResourceId, fruits);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.fruits = fruits;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FruitContainer container = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            container = new FruitContainer();
            container.imgIcon = (ImageButton)row.findViewById(R.id.imageItem);
            container.textView = (TextView)row.findViewById(R.id.fruitName);
            row.setTag(container);
        } else {
            container = (FruitContainer)row.getTag();
        }

        final Fruit fruit = fruits.get(position);
        container.textView.setText(fruit.getName());
        container.imgIcon.setImageResource(fruit.getImageId());
        container.imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imageBtn = (ImageButton)v;
                Intent intent = new Intent();
                intent.setClass(context, SecondActivity.class);
                intent.putExtra("fruitName", fruit.getName());
                context.startActivity(intent);
            }
        });
        // make the listItem to be long clickable
        row.setLongClickable(true);
        return row;
    }

    public List<Fruit> getFruitList() {
        return fruits;
    }

    static class FruitContainer {
        ImageButton imgIcon;
        TextView textView;
    }
}
