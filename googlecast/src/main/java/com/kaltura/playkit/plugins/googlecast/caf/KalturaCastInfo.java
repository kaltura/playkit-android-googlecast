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


package com.kaltura.playkit.plugins.googlecast.caf;

import android.text.TextUtils;

import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.TextTrackStyle;

import com.kaltura.playkit.plugins.googlecast.caf.adsmodel.AdsModel;
import com.kaltura.playkit.plugins.googlecast.caf.adsmodel.VmapAdsModel;

import org.json.JSONException;
import org.json.JSONObject;

class KalturaCastInfo {
    public static final String ENTRY_ID = "entryId";
    public static final String KS = "ks";
    public static final String MEDIA_INFO = "mediaInfo";
    public static final String TEXT_LANGUAGE = "textLanguage";
    public static final String AUDIO_LANGUAGE = "audioLanguage";
    public static final String VMAP_ADS_REQUEST = "vmapAdsRequest";

    private String mediaEntryId;
    private String ks;                     // optional
    private String textLanguage;           // optional
    private String audioLanguage;          // optional
    private MediaMetadata mediaMetadata;   // optional
    private TextTrackStyle textTrackStyle; // optional
    private CAFCastBuilder.StreamType streamType; // optional
    private AdsModel adsModel; // optional

    public KalturaCastInfo() {}

    public String getMediaEntryId() {
        return mediaEntryId;
    }

    public KalturaCastInfo setMediaEntryId(String mediaEntryId) {
        this.mediaEntryId = mediaEntryId;
        return this;
    }

    public String getKs() {
        return ks;
    }

    public KalturaCastInfo setKs(String ks) {
        this.ks = ks;
        return this;
    }

    public String getTextLanguage() {
        return textLanguage;
    }

    public KalturaCastInfo setDefaultTextLangaugeCode(String textLanguage) {
        this.textLanguage = textLanguage;
        return this;
    }

    public String getAudioLanguage() {
        return audioLanguage;
    }

    public KalturaCastInfo setDefaultAudioLanguageCode(String audioLanguage) {
        this.audioLanguage = audioLanguage;
        return this;
    }

    public MediaMetadata getMediaMetadata() {
        return mediaMetadata;
    }

    public KalturaCastInfo setMediaMetadata(MediaMetadata mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
        return this;
    }

    public TextTrackStyle getTextTrackStyle() {
        return textTrackStyle;
    }

    public KalturaCastInfo setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.textTrackStyle = textTrackStyle;
        return this;
    }

    public CAFCastBuilder.StreamType getStreamType() {
        return streamType;
    }

    public KalturaCastInfo setStreamType(CAFCastBuilder.StreamType streamType) {
        this.streamType = streamType;
        return this;
    }

    public AdsModel getAdsModel() {
        return adsModel;
    }

    public KalturaCastInfo setAdsModel(AdsModel adsModel) {
        this.adsModel = adsModel;
        return this;
    }

    public JSONObject getCustomData() {
        JSONObject customData = new JSONObject();
        try {
            JSONObject mediaData = new JSONObject();
            mediaData.put(ENTRY_ID, getMediaEntryId());
            if (!TextUtils.isEmpty(getKs())) {
                mediaData.put(KS, getKs());
            }

            customData.put(MEDIA_INFO, mediaData);
            if (!TextUtils.isEmpty(getTextLanguage())) {
                customData.put(TEXT_LANGUAGE, getTextLanguage());
            }
            if (!TextUtils.isEmpty(getAudioLanguage())) {
                customData.put(AUDIO_LANGUAGE, getAudioLanguage());
            }

            if(getAdsModel() != null && getAdsModel().getAdTagType() == CAFCastBuilder.AdTagType.VMAP) {
                if (((VmapAdsModel)getAdsModel()).getVmapAdRequest() != null) {
                    JSONObject vmapAdRequestJson = ((VmapAdsModel)getAdsModel()).getVmapAdRequest().toJSONObject();
                    if (vmapAdRequestJson != null) {
                        customData.put(VMAP_ADS_REQUEST, vmapAdRequestJson);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return customData;
    }
}

