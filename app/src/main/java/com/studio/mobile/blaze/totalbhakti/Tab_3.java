package com.studio.mobile.blaze.totalbhakti;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Tab_3 extends Fragment {

    public  static  String  PLAYLIST_ID_TOTALBAKTI="PLJUsSxHILtTikOHsyEB6GRZG3VujmyXNr";
    public  static String API_KEY="AIzaSyDySG6BdY-cziAmIYvfBDhlLhOIi9N4rUg";
    public static String APP_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet," +
            "contentDetails&playlistId="+PLAYLIST_ID_TOTALBAKTI+"&key="+API_KEY+"&maxResults=50";
    private RecyclerView mdatalist;
    YouVideoListAdapter mdatapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_1, container, false);
        mdatalist = v.findViewById(R.id.TAB);
        LoadVideoTask mvoid=new LoadVideoTask(APP_URL, new LoadVideoTask.CallApiListener() {
            @Override
            public void onFinish(ArrayList<VideoItem> videoItems, Exception e, String nextPageToken) {
                mdatapter=new YouVideoListAdapter(getContext(),videoItems);
                mdatalist.setAdapter(mdatapter);
                mdatalist.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        });

        mvoid.execute();
        return v;
    }


}
