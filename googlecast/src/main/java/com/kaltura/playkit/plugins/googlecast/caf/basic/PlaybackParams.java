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

    public void setHlsSource(String id, String url) {
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<Hls> getHls() {
        return hls;
    }

    public void setHls(List<Hls> hls) {
        this.hls = hls;
    }

    public List<Dash> getDash() {
        return dash;
    }

    public void setDash(List<Dash> dash) {
        this.dash = dash;
    }

    public List<Progressive> getProgressive() {
        return progressive;
    }

    public void setProgressive(List<Progressive> progressive) {
        this.progressive = progressive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDvr() {
        return dvr;
    }

    public void setDvr(Boolean dvr) {
        this.dvr = dvr;
    }

    public Object getVr() {
        return vr;
    }

    public void setVr(Object vr) {
        this.vr = vr;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Caption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }
}