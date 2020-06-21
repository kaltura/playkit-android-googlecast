package com.kaltura.playkit.plugins.googlecast.caf.basic;

import android.graphics.Path;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class PlaybackParams {


    public static final String PLUGINS = "plugins";
    public static final String SOURCES = "sources";
    public static final String APPLICATION_DASH_XML = "application/dash+xml";
    public static final String APPLICATION_X_MPEG_URL = "application/x-mpegURL";
    public static final String VIDEO_MP_4 = "video/mp4";
    public static final String COM_WIDEVINE_ALPHA = "com.widevine.alpha";
    
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
            playbackParams.put(PLUGINS, new JSONObject());
            playbackParams.put(SOURCES, new JSONObject(sourcesJson));
        } catch (JSONException e ) {
            e.printStackTrace();
        }
        return playbackParams;
    }

    public void setDashSource(String id, String url, String licenseUrl) {
        Dash dashSource =  new Dash();
        dashSource.id = id;
        dashSource.url = url;
        dashSource.mimetype = APPLICATION_DASH_XML;

        DrmData drmData = new DrmData();
        drmData.licenseUrl = licenseUrl;
        drmData.scheme = COM_WIDEVINE_ALPHA;
        dashSource.drmData = Collections.singletonList(drmData);
        this.dash = Collections.singletonList(dashSource);
    }

    public void setHlshSource(String id, String url) {
        Hls hlsSource =  new Hls();
        hlsSource.id = id;
        hlsSource.url = url;
        hlsSource.mimetype = APPLICATION_X_MPEG_URL;
        this.hls = Collections.singletonList(hlsSource);
    }

    public void setProgressivehSource(String id, String url) {
        Progressive progressivSource =  new Progressive();
        progressivSource.id = id;
        progressivSource.url = url;
        progressivSource.mimetype = VIDEO_MP_4;

        this.progressive = Collections.singletonList(progressivSource);
    }
}