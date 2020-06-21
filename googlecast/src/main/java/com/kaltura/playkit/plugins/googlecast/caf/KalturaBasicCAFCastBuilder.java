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

import androidx.annotation.NonNull;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.VastAdsRequest;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.AdsConfig;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VastAdsConfig;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VmapAdRequest;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VmapAdsConfig;
import com.kaltura.playkit.plugins.googlecast.caf.basic.PlaybackParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KalturaBasicCAFCastBuilder {

    private static final String TAG = KalturaBasicCAFCastBuilder.class.getSimpleName();


    private static final String CONTENT_ID = "CONTENT_ID";
    KalturaBasicCastInfo castInfo;

    public KalturaBasicCAFCastBuilder(PlaybackParams playbackParams) {
        castInfo = new KalturaBasicCastInfo(playbackParams);
    }

    public KalturaBasicCAFCastBuilder setAdsConfig(AdsConfig adsConfig) {
        castInfo.setAdsConfig(adsConfig);
        return this;
    }

    public KalturaBasicCAFCastBuilder setMetadata(@NonNull MediaMetadata mediaMetadata) {
        castInfo.setMediaMetadata(mediaMetadata);
        return this;
    }

    public KalturaBasicCAFCastBuilder setTextTrackStyle(@NonNull TextTrackStyle textTrackStyle) {
        castInfo.setTextTrackStyle(textTrackStyle);
        return this;
    }

    public KalturaBasicCAFCastBuilder setDefaultTextLangaugeCode(String textLanguage) {
        castInfo.setDefaultTextLangaugeCode(textLanguage);
        return this;
    }

    public KalturaBasicCAFCastBuilder setDefaultAudioLangaugeCode(String audioLanguage) {
        castInfo.setDefaultAudioLanguageCode(audioLanguage);
        return this;
    }

    public KalturaBasicCAFCastBuilder setStreamType(@NonNull CAFCastBuilder.StreamType streamType) {
        castInfo.setStreamType(streamType);
        return this;
    }

    public MediaInfo build() {
        return getMediaInfo(castInfo);
    }

    private MediaInfo getMediaInfo(KalturaBasicCastInfo castInfo) {

        validate(castInfo);


        JSONObject customData = castInfo.getCustomData();

        MediaInfo.Builder mediaInfoBuilder = new MediaInfo.Builder(CONTENT_ID).setCustomData(customData);
        List<MediaTrack> mediaTracks = new ArrayList<>();
        MediaTrack englishSubtitle = new MediaTrack.Builder(1 /* ID */,
                MediaTrack.TYPE_TEXT)
                .setName("Eng")
                .setContentId("https://qa-apache-php7.dev.kaltura.com/api_v3/index.php/service/caption_captionAsset/action/serve/captionAssetId/0_kd5r6b9c/v/2")
                /* language is required for subtitle type but optional otherwise */
                .setLanguage("en")
                .build();

        MediaTrack frenchSubtitle = new MediaTrack.Builder(2, MediaTrack.TYPE_TEXT)
                .setName("FR")
                .setContentId("https://qa-apache-php7.dev.kaltura.com/api_v3/index.php/service/caption_captionAsset/action/serve/captionAssetId/0_y5h9dvc1/v/2")
                .setLanguage("fr")
                .build();


        mediaTracks.add(englishSubtitle);
        mediaTracks.add(frenchSubtitle);
        mediaInfoBuilder.setMediaTracks(mediaTracks);
        setStreamType(mediaInfoBuilder, castInfo);
        setOptionalData(mediaInfoBuilder, castInfo);

        return mediaInfoBuilder.build();

    }

    /*
    This method sets data that isn't mandatory, and the developer may not provide
     */
    private void setOptionalData(MediaInfo.Builder mediaInfoBuilder, KalturaBasicCastInfo castInfo) {

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
            if (adsConfig.getAdTagType() == CAFCastBuilder.AdTagType.VAST) {
                VastAdsConfig vastAdsConfig = (VastAdsConfig) adsConfig;
                if (vastAdsConfig.getVastAdBreakClipInfoList() != null && vastAdsConfig.getVastAdBreakInfoList() != null) {
                    mediaInfoBuilder.setAdBreakClips(vastAdsConfig.getVastAdBreakClipInfoList());
                    mediaInfoBuilder.setAdBreaks(vastAdsConfig.getVastAdBreakInfoList());
                }
            }else if (adsConfig.getAdTagType() == CAFCastBuilder.AdTagType.VMAP) {
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

    private void setStreamType(MediaInfo.Builder mediaInfoBuilder, KalturaBasicCastInfo castInfo) {

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

    private void validate(KalturaBasicCastInfo castInfo) throws IllegalArgumentException {

        if (castInfo.getCustomData() == null) {
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
