package com.kaltura.playkit.plugins.googlecast.caf.basic;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class PlaybackParams {

    private static final String TAG = PlaybackParams.class.getSimpleName();

    public static final String PLUGINS = "plugins";
    public static final String SOURCES = "sources";

    public static final String MIMETYPE_DASH = "application/dash+xml";
    public static final String MIMETYPE_HLS = "application/x-mpegURL";
    public static final String MIMETYPE_MP4 = "video/mp4";

    public static final String DRMSCHEME_WIDEVINE = "com.widevine.alpha";

    public String poster;
    public JSONObject options = new JSONObject();
    public List<Hls> hls = null;
    public List<Dash> dash = null;
    public List<Progressive> progressive = null;
    public String id;
    public Integer duration;
    public String type;
    public Boolean dvr;
    public Object vr; // If VR Object is null means media is nor VR media. If vr Object is empty json it means media is VR media
    public Metadata metadata;
    public List<Caption> captions = null;

    public PlaybackParams() {}

    public PlaybackParams(String id, Dash dash, String poster, Integer duration, String type, Boolean dvr, Metadata metadata) {
        this.poster = poster;
        this.dash =  Collections.singletonList(dash);
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.dvr = dvr;
        this.metadata = metadata;
    }

    public PlaybackParams(String id, Hls hls, String poster, Integer duration, String type, Boolean dvr, Metadata metadata) {
        this.poster = poster;
        this.hls = Collections.singletonList(hls);
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.dvr = dvr;
        this.metadata = metadata;
    }

    public PlaybackParams(String id, Progressive progressive, String poster, Integer duration, String type, Boolean dvr, Metadata metadata) {
        this.poster = poster;
        this.progressive = Collections.singletonList(progressive);
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.dvr = dvr;
        this.metadata = metadata;
    }
    public JSONObject getMediaConfig() {
        Gson gson = new Gson();
        String sourcesJson = gson.toJson(this);
        JSONObject playbackParams = new JSONObject();

        try {
            playbackParams.put(PLUGINS, new JSONObject());
            playbackParams.put(SOURCES, new JSONObject(sourcesJson));
        } catch (JSONException e ) {
            Log.e(TAG, e.getMessage());
        }
        return playbackParams;
    }

    public void setDashSource(String id, String url, String licenseUrl) {
        Dash dashSource =  new Dash();
        dashSource.id = id;
        dashSource.url = url;
        dashSource.mimetype = MIMETYPE_DASH;

        DrmData drmData = new DrmData();
        drmData.licenseUrl = licenseUrl;
        drmData.scheme = DRMSCHEME_WIDEVINE;
        dashSource.drmData = Collections.singletonList(drmData);
        this.dash = Collections.singletonList(dashSource);
    }

    public void setHlsSource(String id, String url) {
        Hls hlsSource =  new Hls();
        hlsSource.id = id;
        hlsSource.url = url;
        hlsSource.mimetype = MIMETYPE_HLS;
        this.hls = Collections.singletonList(hlsSource);
    }

    public void setProgressiveSource(String id, String url) {
        Progressive progressivSource =  new Progressive();
        progressivSource.id = id;
        progressivSource.url = url;
        progressivSource.mimetype = MIMETYPE_MP4;

        this.progressive = Collections.singletonList(progressivSource);
    }
}
