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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class tab_1 extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_1, container , false);
        recyclerView = v.findViewById(R.id.TAB);
      List <two_image_views> home_list = new ArrayList<>();
      home_list.add(new two_image_views(R.drawable.no_thumbnail,R.drawable.no_thumbnail));
      home_list.add(new two_image_views(R.drawable.no_thumbnail,R.drawable.no_thumbnail));
      home_list.add(new two_image_views(R.drawable.no_thumbnail,R.drawable.no_thumbnail));
      home_list.add(new two_image_views(R.drawable.no_thumbnail,R.drawable.no_thumbnail));
        Home_Adapter HA = new Home_Adapter(home_list);
        recyclerView.setAdapter(HA);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      return v;
    }

   }
