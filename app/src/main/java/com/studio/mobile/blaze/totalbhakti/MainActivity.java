
package com.studio.mobile.blaze.totalbhakti;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // boolean isHideToolbarView = false;
     static int count = 0;
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
    AdView mAdView;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        if (internet_connection()) {
            // if connection exists...!!
            my_tabs = findViewById(R.id.tabs);
            my_pages = findViewById(R.id.viewpager);
            my_tabs.setupWithViewPager(my_pages);
            Set_up_view_Pager(my_pages);



            final ImageView bkg =  findViewById(R.id.IMAGE);
            my_pages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {

                    count++;
                    if(count==3) { count = 0; mInterstitialAd.show(); }

                    switch(position){
                        case 0 : bkg.setImageResource(R.drawable.motivation);
                                 break;
                        case 1 : bkg.setImageResource(R.drawable.no_thumbnail);
                                 break;
                        case 3 : bkg.setImageResource(R.drawable.motivation);
                                 break;
                        case 4 : bkg.setImageResource(R.drawable.no_thumbnail);
                                 break;
                        case 5 : bkg.setImageResource(R.drawable.motivation);
                                 break;
                        case 6 : bkg.setImageResource(R.drawable.no_thumbnail);
                            break;
                    }

                }
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    // if you want some fade in / fade out anim, you may want the values provided here
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
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
                             startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.studio.mobile.blaze.totalbhakti")));
                             break;

                        case R.id.nav_feedback:
                            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            final PackageManager pm = getPackageManager();
                            final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
                            ResolveInfo best = null;
                            for (final ResolveInfo info : matches)
                                if (info.activityInfo.packageName.endsWith(".gm") ||
                                        info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
                            if (best != null)
                                intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                            intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "pranjulsharma653@gmail.com" });
                            startActivity(intent);

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
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Here is the share content body";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
            });
        }

        else
            {
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
        adapter.AddFragmentPage(new Tab_2(), "TAB - 1");
        adapter.AddFragmentPage(new Tab_3(), "TAB - 2");
        adapter.AddFragmentPage(new Tab_4(), "Tab - 3");
        adapter.AddFragmentPage(new Tab_5(), "Tab - 4");
        adapter.AddFragmentPage(new Tab_6(), "TAB - 5");
        adapter.AddFragmentPage(new Tab_7(), "TAB - 6");
        adapter.AddFragmentPage(new Tab_8(), "Tab - 7");
        adapter.AddFragmentPage(new Tab_9(), "Tab - 8");
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
                      final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                      intent.setType("text/plain");
                      final PackageManager pm = getPackageManager();
                      final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
                      ResolveInfo best = null;
                      for (final ResolveInfo info : matches)
                          if (info.activityInfo.packageName.endsWith(".gm") ||
                                  info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
                      if (best != null)
                          intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                      intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "pranjulsharma653@gmail.com" });
                      startActivity(intent);
                      break;

                }
                return true;
            }
        });
        popup.show();
    }


}
