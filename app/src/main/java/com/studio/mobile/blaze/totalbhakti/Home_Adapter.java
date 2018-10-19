package com.studio.mobile.blaze.totalbhakti;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.MyViewHolder>{


    private List <two_image_views> list;
    private ViewPager viewp;

        public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageButton B1 , B2;

         public MyViewHolder(View view) {
            super(view);
            B1 =  view.findViewById(R.id.button1);
            B2 =  view.findViewById(R.id.button2);
        }
    }


    public Home_Adapter(List <two_image_views> List , ViewPager VP) {
        this.list = List; this.viewp = VP;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home, parent, false);
               return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        two_image_views T = list.get(position);
        holder.B1.setImageResource(T.getID1());
        holder.B2.setImageResource(T.getID2());
        switch (position) {
            case 0:
                holder.B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewp.setCurrentItem(1);
                    }
                });
                holder.B2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewp.setCurrentItem(2);
                    }
                });
                break;

            case 1:
                holder.B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewp.setCurrentItem(3);
                    }
                });
                holder.B2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewp.setCurrentItem(4);
                    }
                });
                break;
            case 2:
                holder.B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewp.setCurrentItem(5);
                    }
                });
                holder.B2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewp.setCurrentItem(6);
                    }
                });
                break;
            case 3:
                holder.B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewp.setCurrentItem(7);
                    }
                });
                holder.B2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      viewp.setCurrentItem(8,true);
                    }
                });
        }
    }
        @Override
        public int getItemCount () {
            return list.size();
        }


}

