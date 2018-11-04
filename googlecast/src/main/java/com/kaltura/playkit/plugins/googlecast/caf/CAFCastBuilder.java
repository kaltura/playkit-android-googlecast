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

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.VastAdsRequest;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.AdsConfig;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VastAdsConfig;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VmapAdRequest;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VmapAdsConfig;

import org.json.JSONObject;

public abstract class CAFCastBuilder<T extends CAFCastBuilder<T>> {

    private static final String TAG = CAFCastBuilder.class.getSimpleName();

    public enum StreamType {
        VOD,
        LIVE
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

    public enum AssetReferenceType {
        Media("media"),
        InternalEpg("epg_internal"),
        ExternalEpg("epg_external");

        public String value;

        AssetReferenceType(String value){
            this.value = value;
        }
    }

    public enum AdTagType {
        UNKNOWN,
        VMAP,
        VAST
    }

    private static final String CONTENT_ID = "CONTENT_ID";
    KalturaCastInfo castInfo;


    public CAFCastBuilder() {
        castInfo = new KalturaCastInfo();
    }

    public T setAdsConfig(AdsConfig adsConfig) {
        castInfo.setAdsConfig(adsConfig);
        return (T) this;
    }

    public T setKs(String ks) {
        castInfo.setKs(ks);
        return (T) this;
    }

    public T setMediaEntryId(@NonNull String mediaEntryId) {
        castInfo.setMediaEntryId(mediaEntryId);
        return (T) this;
    }

    public T setMetadata(@NonNull MediaMetadata mediaMetadata) {
        castInfo.setMediaMetadata(mediaMetadata);
        return (T) this;
    }

    public T setTextTrackStyle(@NonNull TextTrackStyle textTrackStyle) {
        castInfo.setTextTrackStyle(textTrackStyle);
        return (T) this;
    }

    public T setDefaultTextLangaugeCode(String textLanguage) {
        castInfo.setDefaultTextLangaugeCode(textLanguage);
        return (T) this;
    }

    public T setDefaultAudioLangaugeCode(String audioLanguage) {
        castInfo.setDefaultAudioLanguageCode(audioLanguage);
        return (T) this;
    }

    public T setStreamType(@NonNull StreamType streamType) {
        castInfo.setStreamType(streamType);
        return (T) this;
    }

    public MediaInfo build() {
        return getMediaInfo(castInfo);
    }

    private MediaInfo getMediaInfo(KalturaCastInfo castInfo) {

        validate(castInfo);


        JSONObject customData = castInfo.getCustomData();

        MediaInfo.Builder mediaInfoBuilder = new MediaInfo.Builder(CONTENT_ID).setCustomData(customData);

        //setContentType(mediaInfoBuilder, castInfo);  // contentType not needed for new receiver
        setStreamType(mediaInfoBuilder, castInfo);
        setOptionalData(mediaInfoBuilder, castInfo);

        return mediaInfoBuilder.build();

    }

    /*
    This method sets data that isn't mandatory, and the developer may not provide
     */
    private void setOptionalData(MediaInfo.Builder mediaInfoBuilder, KalturaCastInfo castInfo) {

        MediaMetadata mediaMetadata = castInfo.getMediaMetadata();
        if (mediaMetadata != null) {
            mediaInfoBuilder.setMetadata(mediaMetadata);
        }

        TextTrackStyle textTrackStyle = castInfo.getTextTrackStyle();
        if (textTrackStyle != null) {
            mediaInfoBuilder.setTextTrackStyle(textTrackStyle);
        }

        AdsConfig adsConfig = castInfo.getAdsConfig();
        if (adsConfig != null) {
            if (adsConfig.getAdTagType() == AdTagType.VAST) {
                VastAdsConfig vastAdsConfig = (VastAdsConfig) adsConfig;
                if (vastAdsConfig.getVastAdBreakClipInfoList() != null && vastAdsConfig.getVastAdBreakInfoList() != null) {
                    mediaInfoBuilder.setAdBreakClips(vastAdsConfig.getVastAdBreakClipInfoList());
                    mediaInfoBuilder.setAdBreaks(vastAdsConfig.getVastAdBreakInfoList());
                }
            }else if (adsConfig.getAdTagType() == AdTagType.VMAP) {
                VastAdsRequest vastAdsRequest = null;
                VmapAdsConfig vmapAdsConfig = (VmapAdsConfig)adsConfig;
                if (vmapAdsConfig.getVmapAdRequest() != null) {
                    VmapAdRequest vmapAdRequest = vmapAdsConfig.getVmapAdRequest();
                    if (vmapAdRequest.getVastAdsRequestForAdTag() != null) {
                        vastAdsRequest = vmapAdRequest.getVastAdsRequestForAdTag();
                    } else if (vmapAdRequest.getVastAdRequestForAdResponse() != null) {
                        vastAdsRequest = vmapAdRequest.getVastAdRequestForAdResponse();
                    }
                    if (vastAdsRequest != null) {
                        mediaInfoBuilder.setVmapAdsRequest(vastAdsRequest);
                    }
                }
            }
        }
    }

    private void setStreamType(MediaInfo.Builder mediaInfoBuilder, KalturaCastInfo castInfo) {

        CAFCastBuilder.StreamType streamType = castInfo.getStreamType();
        if (streamType == null) {
            return;
        }
        int castStreamType;

        switch (streamType) {

            case VOD:
                castStreamType = MediaInfo.STREAM_TYPE_BUFFERED;
                break;

            case LIVE:
                castStreamType = MediaInfo.STREAM_TYPE_LIVE;
                break;

            default:
                castStreamType = MediaInfo.STREAM_TYPE_BUFFERED;
                break;
        }

        mediaInfoBuilder.setStreamType(castStreamType);
    }

    private void validate(KalturaCastInfo castInfo) throws IllegalArgumentException {

        if (TextUtils.isEmpty(castInfo.getMediaEntryId())) {
            throw new IllegalArgumentException();
        }

        // adTagUrl isn't mandatory, but if you set adTagUrl it must be valid
        AdsConfig adsConfig = castInfo.getAdsConfig();
        if (adsConfig != null && !adsConfig.isAdsConfigValid()) {
            throw new IllegalArgumentException();
        }


        //if (castInfo.getStreamType() == null) {
        //    throw new IllegalArgumentException();
        //}
    }
}
