package com.kevin.adoberecreated;

import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView recentRecyclerView;
    FloatingActionButton addFab;

    ArrayList<RecentModel> recentModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Home");

        bottomNavigationView = findViewById(R.id.bottom_navigation_menu);
        addFab = findViewById(R.id.add_fab);

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetAddDialog bottomSheetAddDialog = new BottomSheetAddDialog();
                bottomSheetAddDialog.show(getSupportFragmentManager(), "tag");
            }
        });

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recentRecyclerView = findViewById(R.id.recent_recyclerview);
        recentRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recentRecyclerView.addItemDecoration(itemDecoration);
        recentModelArrayList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSON());
            JSONArray jsonArray = obj.getJSONArray("Recent");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objc = jsonArray.getJSONObject(i);
                recentModelArrayList.add(new RecentModel(objc));
            }

            recentRecyclerView.setAdapter(new RecentAdapter(recentModelArrayList, this));
        } catch (JSONException e) {

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_sheet_menu_trigger, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile_item:
                Toast.makeText(getApplicationContext(), "This should display the Profile activity", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.options_item:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                bottomSheetDialog.show(getSupportFragmentManager(), "tag");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public String loadJSON() {
        String json = null;
        try {
            InputStream stream = getAssets().open("recent.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
