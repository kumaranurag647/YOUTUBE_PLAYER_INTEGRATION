package com.studio.mobile.blaze.totalbhakti;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 */

public class LoadVideoTask extends AsyncTask<Void, Void, Void> {
    public interface CallApiListener {
        void onFinish(ArrayList<VideoItem> videoItems, Exception e, String nextPageToken);
    }


    private String url;
    private CallApiListener listener;
    private ArrayList<VideoItem> videoItems;
    private Exception exception;
    private String nextPageToken;

    public LoadVideoTask(String url, CallApiListener listener) {
        this.url = url;
        this.listener = listener;
        videoItems = null;
    }

    @Override
    protected Void doInBackground(Void... params) {
        videoItems = openHttpConnection(url);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (listener != null) {
            listener.onFinish(videoItems, exception, nextPageToken);
        }
    }

    private ArrayList<VideoItem> openHttpConnection(String urlStr) {
        ArrayList<VideoItem> responseItems = null;
        String response = null;
        InputStream in = null;
        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            urlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.setReadTimeout(45000);
            httpConn.setConnectTimeout(60000);
            httpConn.connect();
            int resCode = httpConn.getResponseCode();

            Log.d("CallApiGet", "Response >> " + resCode + " >> " + httpConn.toString());

            if (200 <= resCode && resCode <= 299) {
                in = httpConn.getInputStream();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            in.close();
            response = sb.toString();
           Log.d("CallApiGet", "Response >> " + response );
        } catch (Exception e) {
            exception = e;
            e.printStackTrace();
        }
        try {
            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray list = jsonObject.getJSONArray("items");
                responseItems = new ArrayList<>();
                 for (int i = 0; i < list.length(); i++) {
                    try {
                        JSONObject snippet = list.getJSONObject(i).getJSONObject("snippet");
                        String videoId = snippet.getJSONObject("resourceId").getString("videoId");
                        String title = snippet.getString("title");
                        String publishedAt = snippet.getString("publishedAt");
                        String url_image = snippet.getJSONObject("thumbnails").getJSONObject("standard").getString("url");
                        VideoItem model = new VideoItem(videoId, title, url_image, publishedAt);
                        responseItems.add(model);
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
                nextPageToken = jsonObject.optString("nextPageToken");
                Log.v("nextPageToken",nextPageToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return responseItems;
    }
}
