package com.studio.mobile.blaze.totalbhakti;



public class VideoItem {
    private String duration;
    private String id;
    private String thumbnail_url;
    private String title;

    public VideoItem(String id, String title, String thumbnail_url, String duration) {
        this.id = id;
        this.title = title;
        this.thumbnail_url = thumbnail_url;
        this.duration = duration;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail_url() {
        return this.thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
