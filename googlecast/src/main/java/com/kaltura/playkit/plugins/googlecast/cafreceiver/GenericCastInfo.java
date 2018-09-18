package com.kaltura.playkit.plugins.googlecast.cafreceiver;

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

import android.text.TextUtils;

import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.TextTrackStyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

class GenericCastInfo {
    //GenericData
    private String mediaEntryId;
    private String ks;
    private String textLanguage;
    private String audioLanguage;
    private MediaMetadata mediaMetadata;
    private TextTrackStyle textTrackStyle;
    private GenericCastBuilder.StreamType streamType;
    private AdsModel adsModel;

    //Phoenix Extra Data
    private GenericCastBuilder.KalturaAssetType mediaType;
    private GenericCastBuilder.PlaybackContextType contextType;
    private GenericCastBuilder.HttpProtocol protocol;
    private String fileIds; // 'FILE_ID1,FILE_ID2'
    private List<String> formats; //['Device_Format_1', 'Device_Format_2', 'Device_Format_3']

    public GenericCastInfo() {}

    public String getMediaEntryId() {
        return mediaEntryId;
    }

    public GenericCastInfo setMediaEntryId(String mediaEntryId) {
        this.mediaEntryId = mediaEntryId;
        return this;
    }

    public String getKs() {
        return ks;
    }

    public GenericCastInfo setKs(String ks) {
        this.ks = ks;
        return this;
    }

    public String getTextLanguage() {
        return textLanguage;
    }

    public GenericCastInfo setDefaultTextLangaugeCode(String textLanguage) {
        this.textLanguage = textLanguage;
        return this;
    }

    public String getAudioLanguage() {
        return audioLanguage;
    }

    public GenericCastInfo setDefaultAudioLanguageCode(String audioLanguage) {
        this.audioLanguage = audioLanguage;
        return this;
    }

    public MediaMetadata getMediaMetadata() {
        return mediaMetadata;
    }

    public GenericCastInfo setMediaMetadata(MediaMetadata mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
        return this;
    }

    public TextTrackStyle getTextTrackStyle() {
        return textTrackStyle;
    }

    public GenericCastInfo setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.textTrackStyle = textTrackStyle;
        return this;
    }

    public GenericCastBuilder.StreamType getStreamType() {
        return streamType;
    }

    public GenericCastInfo setStreamType(GenericCastBuilder.StreamType streamType) {
        this.streamType = streamType;
        return this;
    }

    public AdsModel getAdsModel() {
        return adsModel;
    }

    public GenericCastInfo setAdsModel(AdsModel adsModel) {
        this.adsModel = adsModel;
        return this;
    }

    public String getFileIds() {
        return fileIds;
    }

    public GenericCastInfo setFileIds(String fileIds) {
        this.fileIds = fileIds;
        return this;
    }

    public List<String> getFormats() {
        return formats;
    }

    public GenericCastInfo setFormats(List<String> formats) {
        this.formats = formats;
        return this;
    }

    public GenericCastBuilder.KalturaAssetType getMediaType() {
        return mediaType;
    }

    public GenericCastInfo setMediaType(GenericCastBuilder.KalturaAssetType kalturaAssetType) {
        this.mediaType = kalturaAssetType;
        return this;
    }

    public GenericCastBuilder.PlaybackContextType getContextType() {
        return contextType;
    }

    public GenericCastInfo setContextType(GenericCastBuilder.PlaybackContextType playbackContextType) {
        this.contextType = playbackContextType;
        return this;
    }

    public GenericCastBuilder.HttpProtocol getProtocol() {
        return protocol;
    }

    public GenericCastInfo setProtocol(GenericCastBuilder.HttpProtocol protocol) {
        this.protocol = protocol;
        return this;
    }



    public JSONObject getCustomData() {
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

