package com.example.zhushh.project3andextension;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] fruitNames = {
            "apple", "banana", "cherry", "coco", "kiwi",
            "orange", "pear", "strawberry", "watermelon"
    };
    private Integer[] imageIds = {
            R.drawable.apple, R.drawable.banana, R.drawable.cherry,
            R.drawable.coco, R.drawable.kiwi, R.drawable.orange, R.drawable.pear,
            R.drawable.strawberry, R.drawable.watermelon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView)findViewById(R.id.listView);
        List<Fruit> listItems = createListItems();
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
        List<Fruit> listItems = new ArrayList<Fruit>();
        for (int i = 0; i < fruitNames.length; i++) {
            listItems.add(new Fruit(fruitNames[i], imageIds[i]));
        }
        return listItems;
    }
}
