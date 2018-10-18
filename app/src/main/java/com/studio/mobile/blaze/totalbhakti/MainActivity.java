
package com.studio.mobile.blaze.totalbhakti;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    // boolean isHideToolbarView = false;

    boolean internet_connection() {
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    TabLayout my_tabs;
    ViewPager my_pages;
    DrawerLayout mDrawerLayout;


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
            mDrawerLayout = findViewById(R.id.drawer_layout);
            NavigationView NV = findViewById(R.id.nav_view);
            NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch(item.getItemId()) {

                        case R.id.navigation_home:
                            my_pages.setCurrentItem(0);
                            break;

                        case R.id.nav_rate:
                             my_pages.setCurrentItem(0);
                             break;

                        case R.id.nav_feedback:
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setType("message/rfc822");
                            intent.putExtra(Intent.EXTRA_EMAIL, "pranjulsharma653@gmail.com");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                            startActivity(Intent.createChooser(intent, "Send Email"));
                            break;

                        case R.id.nav_moreapps :
                            Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://play.google.com/store"));
                            startActivity(i);
                    }
                     mDrawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            });

            ImageButton more_apps = findViewById(R.id.IB4);
            ImageButton share = findViewById(R.id.IB3);
            more_apps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store"));
                    startActivity(i);
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
/*
            final HeaderView toolbarHeaderView = findViewById(R.id.toolbar_header_view);
            AppBarLayout appBarLayout = findViewById(R.id.app_bar_layout);

            toolbarHeaderView.bindTo("Larry Page", "Last seen today at 7.00PM");

            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    int maxScroll = appBarLayout.getTotalScrollRange();
                    float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                    System.out.println("Anurag Kumar");
                    if (percentage == 1f && isHideToolbarView) {
                        toolbarHeaderView.setVisibility(View.VISIBLE);
                        isHideToolbarView = !isHideToolbarView;

                    } else if (percentage < 1f && !isHideToolbarView) {
                        System.out.println("Rohit Grim");
                        toolbarHeaderView.setVisibility(View.GONE);
                        isHideToolbarView = !isHideToolbarView;
                    }
                }


            });
*/
        } else {
            CoordinatorLayout CL = findViewById(R.id.activity_main);
            LayoutInflater factory = LayoutInflater.from(MainActivity.this);
            final View view = factory.inflate(R.layout.tap_to_retry, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1000, 1000);
            layoutParams.topMargin = 1000;
            layoutParams.leftMargin = 80;
            CL.addView(view, layoutParams);
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


    public void nav_drawer_kholo(View view) {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    public void menu_dikhao(View view) {

        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                  switch(item.getItemId())
                {
                  case R.id.disclaimer :
                         Intent i = new Intent(getApplicationContext(),Disclaimer.class);
                         startActivity(i);
                         break;

                  case R.id.privacy_policy :
                        i = new Intent(getApplicationContext(),Privacy_Policy.class);
                        startActivity(i);
                        break;

                  case R.id.feedback :
                      Intent intent = new Intent(Intent.ACTION_VIEW);
                      intent.setType("message/rfc822");
                      intent.putExtra(Intent.EXTRA_EMAIL, "pranjulsharma653@gmail.com");
                      intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                      startActivity(Intent.createChooser(intent, "Send Email"));
                      break;

                }
                return true;
            }
        });
        popup.show();
    }


}
