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

package com.kaltura.playkit.plugins.googlecast.cafreceiver;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.TextTrackStyle;

import org.json.JSONObject;

import java.util.List;

public abstract class GenericCastBuilder<T extends GenericCastBuilder> {

    private static final String TAG = GenericCastBuilder.class.getSimpleName();

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
    private static final String MOCK_DATA = "MOCK_DATA";
    GenericCastInfo castInfo;


    public GenericCastBuilder() {
        castInfo = new GenericCastInfo();
    }

    public T setAdsModel(AdsModel adsModel) {
        castInfo.setAdsModel(adsModel);
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

    public T setFileIds(String fileIds) {
        castInfo.setFileIds(fileIds);
        return (T) this;
    }

    public T setFormats(List<String> formats) {
        castInfo.setFormats(formats);
        return (T) this;
    }

    public T setMediaType(KalturaAssetType kalturaAssetType) {
        castInfo.setMediaType(kalturaAssetType);
        return (T) this;
    }

    public T setContextType(PlaybackContextType playbackContextType) {
        castInfo.setContextType(playbackContextType);
        return (T) this;    }

    public T setProtocol(HttpProtocol protocol) {
        castInfo.setProtocol(protocol);
        return (T) this;    }

    public T getCustomData(@NonNull StreamType streamType) {
        castInfo.setStreamType(streamType);
        return (T) this;
    }

    public MediaInfo build() {
        return getMediaInfo(castInfo);
    }

    private MediaInfo getMediaInfo(GenericCastInfo castInfo) {

        validate(castInfo);


        JSONObject customData = castInfo.getCustomData();

        MediaInfo.Builder mediaInfoBuilder = new MediaInfo.Builder(MOCK_DATA)
                .setContentType(MOCK_DATA)
                .setCustomData(customData);

        setStreamType(mediaInfoBuilder, castInfo);
        setOptionalData(mediaInfoBuilder, castInfo);

        return mediaInfoBuilder.build();

    }

    /*
    This method sets data that isn't mandatory, and the developer may not provide
     */
    private void setOptionalData(MediaInfo.Builder mediaInfoBuilder, GenericCastInfo castInfo) {

        MediaMetadata mediaMetadata = castInfo.getMediaMetadata();
        if (mediaMetadata != null) {
            mediaInfoBuilder.setMetadata(mediaMetadata);
        }

        TextTrackStyle textTrackStyle = castInfo.getTextTrackStyle();
        if (textTrackStyle != null) {
            mediaInfoBuilder.setTextTrackStyle(textTrackStyle);
        }

        AdsModel adsModel = castInfo.getAdsModel();
        if (adsModel != null && adsModel.isVast) {
            mediaInfoBuilder.setAdBreakClips(adsModel.getAdBreakClipInfoList());
            mediaInfoBuilder.setAdBreaks(adsModel.getAdBreakInfoList());
        }
    }

    private void setStreamType(MediaInfo.Builder mediaInfoBuilder, GenericCastInfo castInfo) {

        GenericCastBuilder.StreamType streamType = castInfo.getStreamType();
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

    protected void validate(GenericCastInfo castInfo) throws IllegalArgumentException {

        if (TextUtils.isEmpty(castInfo.getMediaEntryId())) {
            throw new IllegalArgumentException();
        }

        // adTagUrl isn't mandatory, but if you set adTagUrl it must be valid
        if (castInfo.getAdsModel() != null) {
            String adTagUrl = castInfo.getAdsModel().adTagUrl;
            if (adTagUrl != null && TextUtils.isEmpty(adTagUrl)) {
                throw new IllegalArgumentException();
            }
        }

        if (castInfo.getStreamType() == null) {
            throw new IllegalArgumentException();
        }
    }


}
