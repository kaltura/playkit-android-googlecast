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
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.AdsConfig;
import com.kaltura.playkit.plugins.googlecast.caf.basic.Caption;
import com.kaltura.playkit.plugins.googlecast.caf.basic.PlaybackParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

class KalturaBasicCastInfo {

    public static final String MEDIA_CONFIG = "mediaConfig";
    public static final String TEXT_LANGUAGE = "textLanguage";
    public static final String AUDIO_LANGUAGE = "audioLanguage";
    public static final String VMAP_ADS_REQUEST = "vmapAdsRequest";

    private PlaybackParams playbackParams;
    private String textLanguage;           // optional
    private String audioLanguage;          // optional
    private MediaMetadata mediaMetadata;   // optional
    private TextTrackStyle textTrackStyle; // optional
    private CAFCastBuilder.StreamType streamType; // optional
    private AdsConfig adsConfig; // optional

    public KalturaBasicCastInfo(PlaybackParams playbackParams) {
        this.playbackParams = playbackParams;
    }

    public String getTextLanguage() {
        return textLanguage;
    }

    public KalturaBasicCastInfo setDefaultTextLangaugeCode(String textLanguage) {
        this.textLanguage = textLanguage;
        return this;
    }

    public String getAudioLanguage() {
        return audioLanguage;
    }

    public KalturaBasicCastInfo setDefaultAudioLanguageCode(String audioLanguage) {
        this.audioLanguage = audioLanguage;
        return this;
    }

    public MediaMetadata getMediaMetadata() {
        return mediaMetadata;
    }

    public KalturaBasicCastInfo setMediaMetadata(MediaMetadata mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
        return this;
    }

    public TextTrackStyle getTextTrackStyle() {
        return textTrackStyle;
    }

    public KalturaBasicCastInfo setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.textTrackStyle = textTrackStyle;
        return this;
    }

    public CAFCastBuilder.StreamType getStreamType() {
        return streamType;
    }

    public KalturaBasicCastInfo setStreamType(CAFCastBuilder.StreamType streamType) {
        this.streamType = streamType;
        return this;
    }

    public AdsConfig getAdsConfig() {
        return adsConfig;
    }

    public KalturaBasicCastInfo setAdsConfig(AdsConfig adsConfig) {
        this.adsConfig = adsConfig;
        return this;
    }



    public JSONObject getCustomData() {
        JSONObject customData = new JSONObject();
        try {
            customData.put(MEDIA_CONFIG, playbackParams.getMediaConfig());
            if (!TextUtils.isEmpty(getTextLanguage())) {
                customData.put(TEXT_LANGUAGE, getTextLanguage());
            }
            if (!TextUtils.isEmpty(getAudioLanguage())) {
                customData.put(AUDIO_LANGUAGE, getAudioLanguage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return customData;
    }

    public List<Caption> getExternalVttCaptions() {
        if (playbackParams != null) {
            return playbackParams.captions;
        }
        return null;
    }
}

