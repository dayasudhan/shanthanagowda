package com.kuruvatech.dgshonnali;

/**
 * Created by dayas on 29-11-2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kuruvatech.dgshonnali.adapter.FullScreenImageAdapter;

import java.util.ArrayList;

public class FullScreenViewActivity extends AppCompatActivity {

 //   private Utils utils;
    private FullScreenImageAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_view);

        viewPager = (ViewPager) findViewById(R.id.pager);

     //   utils = new Utils(getApplicationContext());

        Intent i = getIntent();
        int position = i.getIntExtra("position", 0);
//        i.getIntExtra("imageurls");
        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("imageurls");
        adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,
                myList);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);
        setToolBar(getString(R.string.titletext));

    }
    private void setToolBar(String areaClicked) {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_back);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(areaClicked);
//        tb.setTitleTextColor(Color.rgb(Constants.TITLE_TEXT_COLOR_RED,
//                Constants.TITLE_TEXT_COLOR_GREEN, Constants.TITLE_TEXT_COLOR_BLUE));
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}