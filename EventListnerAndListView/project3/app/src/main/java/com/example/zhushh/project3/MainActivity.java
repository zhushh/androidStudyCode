package com.example.zhushh.project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String[] fruitNames = {
            "apple", "banana", "cherry", "coco", "kiwi"
    };
    private Integer[] imageIds = {
            R.drawable.apple, R.drawable.banana, R.drawable.cherry,
            R.drawable.coco, R.drawable.kiwi
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView)findViewById(R.id.listView);
        List<Fruit> listItems = createListItems();
//        SimpleAdapter simpleAdapter = new SimpleAdapter(
//                this,
//                listItems,
//                R.layout.listview_item,
//                new String[] {"image", "fruitName"},
//                new int[] {R.id.imageItem, R.id.fruitName});
        FruitAdapter fruitadapter = new FruitAdapter(this, R.layout.listview_item, listItems);
        listview.setAdapter(fruitadapter);

        // extension task
        listview.setLongClickable(true);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                FruitAdapter fruitAdapter = (FruitAdapter)(parent.getAdapter());
                List<Fruit> fruitlists = fruitAdapter.getFruitList();
                Fruit fruit = fruitlists.get(position);
                fruitlists.remove(fruit);
                fruitAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private List<Fruit> createListItems() {
//        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < fruitNames.length; i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("image", imageIds[i]);
//            map.put("fruitName", fruitNames[i]);
//            listItems.add(map);
//        }
        List<Fruit> listItems = new ArrayList<Fruit>();
        for (int i = 0; i < fruitNames.length; i++) {
            listItems.add(new Fruit(fruitNames[i], imageIds[i]));
        }
        return listItems;
    }
}
