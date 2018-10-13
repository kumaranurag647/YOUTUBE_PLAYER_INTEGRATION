package com.studio.mobile.blaze.totalbhakti;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    TabLayout my_tabs;
    ViewPager my_pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (internet_connection()) {
            // if connection exists...!!
            my_tabs = findViewById(R.id.tabs);
            my_pages = findViewById(R.id.viewpager);
            my_tabs.setupWithViewPager(my_pages);
            Set_up_view_Pager(my_pages);

        } else {
            CoordinatorLayout CL = findViewById(R.id.activity_main);
            LayoutInflater factory = LayoutInflater.from(MainActivity.this);
            final View view = factory.inflate(R.layout.tap_to_retry, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1000,1000);
            layoutParams.topMargin = 1000;
            layoutParams.leftMargin = 80;
            CL.addView(view,layoutParams);
            Button B = findViewById(R.id.tap_to_retry);
            B.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = getIntent();
                    finish();
                    startActivity(i);
                }
            });
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater factory = LayoutInflater.from(MainActivity.this);
            final View view = factory.inflate(R.layout.exit_main_activity, null);
            builder.setView(view);
            builder.setMessage("Do you want to exit ?");
            builder.setCancelable(true);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog exit = builder.create();
            exit.show();
        }
        return true;
    }


    public void Set_up_view_Pager(ViewPager viewPager) {

        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.AddFragmentPage(new tab_1(), "Home");
        adapter.AddFragmentPage(new Tab_2(), "TAB - 2");
        adapter.AddFragmentPage(new Tab_3(), "TAB - 3");
        adapter.AddFragmentPage(new Tab_4(), "Tab - 4");
        adapter.AddFragmentPage(new Tab_5(), "Tab - 5");
        adapter.AddFragmentPage(new Tab_6(), "TAB - 6");
        adapter.AddFragmentPage(new Tab_7(), "TAB - 7");
        adapter.AddFragmentPage(new Tab_8(), "Favourites");
        viewPager.setAdapter(adapter);
    }


}