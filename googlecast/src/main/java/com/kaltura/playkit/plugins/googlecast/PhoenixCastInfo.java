/*
 * ============================================================================
 * Copyright (C) 2017 Kaltura Inc.
 * 
 * Licensed under the AGPLv3 license, unless a different license for a
 * particular library is specified in the applicable library path.
 * 
 * You may obtain a copy of the License at
 * https://www.gnu.org/licenses/agpl-3.0.html
 * ============================================================================
 */

package com.kaltura.playkit.plugins.googlecast;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PhoenixCastInfo extends GenericCastInfo {

    private KalturaAssetType mediaType;
    private PlaybackContextType  contextType;
    private HttpProtocol protocol;
    private String fileIds; // 'FILE_ID1,FILE_ID2'
    private List<String> formats; //['Device_Format_1', 'Device_Format_2', 'Device_Format_3']

    public PhoenixCastInfo() {}

    @Override
    public PhoenixCastInfo setMediaEntryId(String mediaEntryId) {
        super.setMediaEntryId(mediaEntryId);
        return this;
    }

    @Override
    public PhoenixCastInfo setKs(String ks) {
        super.setKs(ks);
        return this;
    }

    @Override
    public PhoenixCastInfo setTextLanguage(String textLanguage) {
        super.setTextLanguage(textLanguage);
        return this;
    }

    @Override
    public PhoenixCastInfo setAudioLanguage(String audioLanguage) {
        super.setAudioLanguage(audioLanguage);
        return this;
    }

    public String getFileIds() {
        return fileIds;
    }

    public PhoenixCastInfo setFileIds(String fileIds) {
        this.fileIds = fileIds;
        return this;
    }

    public List<String> getFormats() {
        return formats;
    }

    public PhoenixCastInfo setFormats(List<String> formats) {
        this.formats = formats;
        return this;
    }

    public KalturaAssetType getMediaType() {
        return mediaType;
    }

    public PhoenixCastInfo setMediaType(KalturaAssetType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public PlaybackContextType getContextType() {
        return contextType;
    }

    public PhoenixCastInfo setContextType(PlaybackContextType contextType) {
        this.contextType = contextType;
        return this;
    }

    public HttpProtocol getProtocol() {
        return protocol;
    }

    public PhoenixCastInfo setProtocol(HttpProtocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public enum HttpProtocol {
        Http("http"),
        Https("https"),
        All("all");

        public String value;

        HttpProtocol(String value){
            this.value = value;
        }
    }

    public enum KalturaAssetType {
        Media("media"),
        Epg("epg"),
        Recording("recording");

        public String value;

        KalturaAssetType(String value){
            this.value = value;
        }
    }

    public enum PlaybackContextType {
        Trailer("TRAILER"),
        Catchup("CATCHUP"),
        StartOver("START_OVER"),
        Playback("PLAYBACK");

        public String value;

        PlaybackContextType(String value){
            this.value = value;
        }
    }

    public JSONObject getCastInfoAsJson() {
        JSONObject customData = new JSONObject();
        try {
            JSONObject mediaData = new JSONObject();
            mediaData.put("entryId", getMediaEntryId());
            if (!TextUtils.isEmpty(getKs())) {
                mediaData.put("ks", getKs());
            }

            if (getMediaType() != null) {
                mediaData.put("mediaType", getMediaType().value);
            }
            if (getContextType() != null) {
                mediaData.put("contextType", getContextType().value);
            }
            if (getProtocol() != null) {
                mediaData.put("protocol", getProtocol().value);
            }
            if (!TextUtils.isEmpty(getFileIds())) {
                mediaData.put("fileIds", getFileIds());
            }
            if (getFormats() != null) {
                JSONArray formatsArray = new JSONArray();
                for (String format : getFormats()) {
                    formatsArray.put(format);
                }
                mediaData.put("formats", formatsArray);
            }
            customData.put("mediaInfo", mediaData);
            if (!TextUtils.isEmpty(getTextLanguage())) {
                customData.put("textLanguage", getTextLanguage());
            }
            if (!TextUtils.isEmpty(getAudioLanguage())) {
                customData.put("audioLanguage", getAudioLanguage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return customData;
    }
}
