package com.studio.mobile.blaze.totalbhakti;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YouVideoListAdapter extends RecyclerView.Adapter<YouVideoListAdapter.MyViewHolder> {


    Context context;
    List<VideoItem> data;
    LayoutInflater inflater;
    private final Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;
    private final YouVideoListAdapter.ThumbnailListener thumbnailListener;

    public YouVideoListAdapter(Context context, List<VideoItem> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        thumbnailViewToLoaderMap = new HashMap<YouTubeThumbnailView, YouTubeThumbnailLoader>();
        thumbnailListener = new YouVideoListAdapter.ThumbnailListener();
    }

    @Override
    public YouVideoListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
                  v = inflater.inflate(R.layout.video_list_item, parent, false);
              MyViewHolder holder = new MyViewHolder(v);
              return holder;
    }

    @Override
    public void onBindViewHolder(YouVideoListAdapter.MyViewHolder holder, int position) {


             try {
                 holder.titletextData.setText(data.get(position).getTitle());
                 holder.ThumbnailView.initialize(DeveloperKey.DEVELOPER_KEY, thumbnailListener);
                 YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(holder.ThumbnailView);
                 if (loader == null) {
                     holder.ThumbnailView.setTag(data.get(position).getId());
                 } else {
                     holder.ThumbnailView.setImageResource(R.drawable.placeholder1);
                     loader.setVideo(data.get(position).getId());
                 }
             }

             catch (Exception e) {

             }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        YouTubeThumbnailView ThumbnailView;
        TextView titletextData;
        public MyViewHolder(View itemView) {
            super(itemView);
            ThumbnailView = itemView.findViewById(R.id.thumbnail);
            titletextData = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 VideoItem vdata = data.get(getAdapterPosition());
                    Intent mIntent = new Intent(context,PlayVideoActivity.class);
                    mIntent.putExtra("videoId",vdata.getId());
                    context.startActivity(mIntent);

                }
            });


        }
    }



    private final class ThumbnailListener implements
            YouTubeThumbnailView.OnInitializedListener,
            YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        @Override
        public void onInitializationSuccess(
                YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
            loader.setOnThumbnailLoadedListener(this);
            thumbnailViewToLoaderMap.put(view, loader);
            view.setImageResource(R.drawable.placeholder1);
            String videoId = (String) view.getTag();
            loader.setVideo(videoId);
        }

        @Override
        public void onInitializationFailure(
                YouTubeThumbnailView view, YouTubeInitializationResult loader) {
            view.setImageResource(R.drawable.placeholder2);
        }

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView view, String videoId) {
        }

        @Override
        public void onThumbnailError(YouTubeThumbnailView view, YouTubeThumbnailLoader.ErrorReason errorReason) {
            view.setImageResource(R.drawable.placeholder2);
        }}


}
