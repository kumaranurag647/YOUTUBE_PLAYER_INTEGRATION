package com.studio.mobile.blaze.totalbhakti;

import android.content.DialogInterface;
import android.media.Image;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    TabLayout my_tabs;
    ViewPager my_pages;

    public  static  String  PLAYLIST_ID_TOTALBAKTI="PLJUsSxHILtTikOHsyEB6GRZG3VujmyXNr";
    public  static String API_KEY="AIzaSyDySG6BdY-cziAmIYvfBDhlLhOIi9N4rUg";
    public static String APP_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet," +
            "contentDetails&playlistId="+PLAYLIST_ID_TOTALBAKTI+"&key="+API_KEY+"&maxResults=50";
    private RecyclerView mdatalist;
    YouVideoListAdapter mdatapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_tabs = findViewById(R.id.tabs);
        my_pages = findViewById(R.id.viewpager);

        my_tabs.setupWithViewPager(my_pages);
        Set_up_view_Pager(my_pages);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

           if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater factory = LayoutInflater.from(MainActivity.this);
            final View view = factory.inflate(R.layout.exit_main_activity, null);
            builder.setView(view);
            builder.setMessage("Do you want to exit ?");
            builder.setCancelable(true);
            builder.setNegativeButton("No" , new DialogInterface.OnClickListener() {
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


      public void Set_up_view_Pager(ViewPager viewPager){

        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.AddFragmentPage(new tab_1() , "Home");
        adapter.AddFragmentPage(new Tab_2() , "TAB - 2");
        adapter.AddFragmentPage(new Tab_3() , "TAB - 3");
        adapter.AddFragmentPage(new Tab_4() , "Favourites");
        viewPager.setAdapter(adapter);
      }
}