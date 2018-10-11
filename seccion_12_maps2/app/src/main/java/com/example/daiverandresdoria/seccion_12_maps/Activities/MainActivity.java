package com.example.daiverandresdoria.seccion_12_maps.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.daiverandresdoria.seccion_12_maps.Fragments.MapFragment;
import com.example.daiverandresdoria.seccion_12_maps.Fragments.WelcomeFragment;
import com.example.daiverandresdoria.seccion_12_maps.R;

public class MainActivity extends AppCompatActivity {
    private  Fragment currentlyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");

        if (savedInstanceState == null){
            currentlyFragment = new WelcomeFragment();
            changeFragmente(currentlyFragment);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_welcome:
                currentlyFragment = new WelcomeFragment();
                break;
            case R.id.menu_map:
                currentlyFragment = new MapFragment();
                break;
        }
        changeFragmente(currentlyFragment);
        return super.onOptionsItemSelected(item);
    }

    private void changeFragmente(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,fragment).commit();
    }
}
