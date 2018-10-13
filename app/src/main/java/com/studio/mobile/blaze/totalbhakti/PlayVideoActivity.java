package com.studio.mobile.blaze.totalbhakti;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;



/**
 * Created by Hp-pc on 01-Apr-18.
 */

public class PlayVideoActivity extends YouTubeFailureRecoveryActivity{

    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoId = getIntent().getStringExtra("videoId");
        setContentView(R.layout.fullscreen_demo);
        playerView = findViewById(R.id.player);
        playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        this.player = player;

        // Specify that we want to handle fullscreen behavior ourselves.
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        if (!wasRestored) {
            player.loadVideo(videoId);
            player.setFullscreen(true);
            player.setShowFullscreenButton(false);
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }











}