package com.runhuaoil.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fruit> fruitArrayList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        final FruitAdapter fruitAdapter = new FruitAdapter(this,R.layout.item_layout,fruitArrayList);

        ListView listView = (ListView)findViewById(R.id.list_view_id);
        listView.setAdapter(fruitAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitArrayList.get(position);

                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
        fruitArrayList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
        fruitArrayList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
        fruitArrayList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
        fruitArrayList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
        fruitArrayList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
        fruitArrayList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
        fruitArrayList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
        fruitArrayList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
        fruitArrayList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
        fruitArrayList.add(mango);
    }
}
