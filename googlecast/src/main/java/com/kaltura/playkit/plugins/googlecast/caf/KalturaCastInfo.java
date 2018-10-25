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
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VmapAdsConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


class KalturaCastInfo {
    public static final String ENTRY_ID = "entryId";
    public static final String KS = "ks";
    public static final String MEDIA_INFO = "mediaInfo";
    public static final String TEXT_LANGUAGE = "textLanguage";
    public static final String AUDIO_LANGUAGE = "audioLanguage";
    public static final String VMAP_ADS_REQUEST = "vmapAdsRequest";

    //Phoenix
    public static final String MEDIA_TYPE = "mediaType";
    public static final String ASSET_REFERENCE_TYPE = "assetReferenceType";
    public static final String CONTEXT_TYPE = "contextType";
    public static final String PROTOCOL = "protocol";
    public static final String FILE_IDS = "fileIds";
    public static final String FORMATS = "formats";

    private String mediaEntryId;
    private String ks;                     // optional
    private String textLanguage;           // optional
    private String audioLanguage;          // optional
    private MediaMetadata mediaMetadata;   // optional
    private TextTrackStyle textTrackStyle; // optional
    private CAFCastBuilder.StreamType streamType; // optional
    private AdsConfig adsConfig; // optional

    //Phoenix
    private CAFCastBuilder.KalturaAssetType mediaType;
    private CAFCastBuilder.AssetReferenceType assetReferenceType;
    private CAFCastBuilder.PlaybackContextType contextType;
    private CAFCastBuilder.HttpProtocol protocol;
    private String fileIds; // 'FILE_ID1,FILE_ID2'
    private List<String> formats; //['Device_Format_1', 'Device_Format_2', 'Device_Format_3']


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

    public AdsConfig getAdsConfig() {
        return adsConfig;
    }

    public KalturaCastInfo setAdsConfig(AdsConfig adsConfig) {
        this.adsConfig = adsConfig;
        return this;
    }

    public String getFileIds() {
        return fileIds;
    }

    public KalturaCastInfo setFileIds(String fileIds) {
        this.fileIds = fileIds;
        return this;
    }

    public List<String> getFormats() {
        return formats;
    }

    public KalturaCastInfo setFormats(List<String> formats) {
        this.formats = formats;
        return this;
    }

    public CAFCastBuilder.KalturaAssetType getMediaType() {
        return mediaType;
    }

    public KalturaCastInfo setMediaType(CAFCastBuilder.KalturaAssetType kalturaAssetType) {
        this.mediaType = kalturaAssetType;
        return this;
    }

    public CAFCastBuilder.PlaybackContextType getContextType() {
        return contextType;
    }

    public KalturaCastInfo setContextType(CAFCastBuilder.PlaybackContextType playbackContextType) {
        this.contextType = playbackContextType;
        return this;
    }

    public CAFCastBuilder.AssetReferenceType getAssetReferenceType() {
        return assetReferenceType;
    }

    public KalturaCastInfo setAssetReferenceType(CAFCastBuilder.AssetReferenceType assetReferenceType) {
        this.assetReferenceType = assetReferenceType;
        return this;
    }
    public CAFCastBuilder.HttpProtocol getProtocol() {
        return protocol;
    }

    public KalturaCastInfo setProtocol(CAFCastBuilder.HttpProtocol protocol) {
        this.protocol = protocol;
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

            if (getMediaType() != null) {
                mediaData.put(MEDIA_TYPE, getMediaType().value);
            }
            if (getAssetReferenceType() != null) {
                mediaData.put(ASSET_REFERENCE_TYPE, getAssetReferenceType().value);
            }
            if (getContextType() != null) {
                mediaData.put(CONTEXT_TYPE, getContextType().value);
            }
            if (getProtocol() != null) {
                mediaData.put(PROTOCOL, getProtocol().value);
            }
            if (!TextUtils.isEmpty(getFileIds())) {
                mediaData.put(FILE_IDS, getFileIds());
            }
            if (getFormats() != null) {
                JSONArray formatsArray = new JSONArray();
                for (String format : getFormats()) {
                    formatsArray.put(format);
                }
                mediaData.put(FORMATS, formatsArray);
            }
            customData.put(MEDIA_INFO, mediaData);
            if (!TextUtils.isEmpty(getTextLanguage())) {
                customData.put(TEXT_LANGUAGE, getTextLanguage());
            }
            if (!TextUtils.isEmpty(getAudioLanguage())) {
                customData.put(AUDIO_LANGUAGE, getAudioLanguage());
            }

            //VMAP VIA RECIVER
//            if(getAdsConfig() != null && getAdsConfig().getAdTagType() == CAFCastBuilder.AdTagType.VMAP) {
//                if (((VmapAdsConfig) getAdsConfig()).getVmapAdRequest() != null) {
//                    JSONObject vmapAdRequestJson = ((VmapAdsConfig) getAdsConfig()).getVmapAdRequest().toJSONObject();
//                    if (vmapAdRequestJson != null) {
//                        customData.put(VMAP_ADS_REQUEST, vmapAdRequestJson);
//                    }
//                }
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return customData;
    }
}

