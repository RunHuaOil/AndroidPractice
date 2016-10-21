package com.runhuaoil.toolbartest;

import android.content.Intent;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MenuItem itemFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        itemFavorite = menu.findItem(R.id.action_favorite);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        MenuItemCompat.OnActionExpandListener listener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "扩展了", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "折叠了", Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        MenuItemCompat.setOnActionExpandListener(searchItem, listener);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_favorite:
                if (itemFavorite.isChecked()){
                    itemFavorite.setChecked(false);
                    itemFavorite.setIcon(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    itemFavorite.setChecked(true);
                    itemFavorite.setIcon(R.drawable.ic_favorite_black_24dp);
                }

                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this,ChildActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
