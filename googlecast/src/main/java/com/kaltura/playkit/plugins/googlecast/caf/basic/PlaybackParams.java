package com.kaltura.playkit.plugins.googlecast.caf.basic;

import android.graphics.Path;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class PlaybackParams {


    public String poster;
    public JSONObject options = new JSONObject();
    public List<Hls> hls = null;
    public List<Dash> dash = null;
    public List<Progressive> progressive = null;
    public String id;
    public Integer duration;
    public String type;
    public Boolean dvr;
    public Object vr;
    public Metadata metadata;
    public List<Caption> captions = null;

    public JSONObject getMediaConfig() {
        Gson gson = new Gson();
        String sourcesJson = gson.toJson(this);
        JSONObject playbackParams = new JSONObject();

        try {
            playbackParams.put("plugins", new JSONObject());
            playbackParams.put("sources", new JSONObject(sourcesJson));
        } catch (JSONException e ) {
            e.printStackTrace();
        }
        return playbackParams;
    }

    public void setDashSource(String id, String url, String licenseUrl) {
        Dash dashSource =  new Dash();
        dashSource.id = id;
        dashSource.url = url;
        dashSource.mimetype = "application/dash+xml";

        DrmData drmData = new DrmData();
        drmData.licenseUrl = licenseUrl;
        drmData.scheme = "com.widevine.alpha";
        dashSource.drmData = Collections.singletonList(drmData);
        this.dash = Collections.singletonList(dashSource);
    }

    public void setHlshSource(String id, String url) {
        Hls hlsSource =  new Hls();
        hlsSource.id = id;
        hlsSource.url = url;
        hlsSource.mimetype = "application/x-mpegURL";
        this.hls = Collections.singletonList(hlsSource);
    }

    public void setProgressivehSource(String id, String url) {
        Progressive progressivSource =  new Progressive();
        progressivSource.id = id;
        progressivSource.url = url;
        progressivSource.mimetype = "video/mp4";

        this.progressive = Collections.singletonList(progressivSource);
    }
}