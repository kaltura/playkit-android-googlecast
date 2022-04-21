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

import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.VastAdsRequest;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.AdsConfig;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VastAdsConfig;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VmapAdRequest;
import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.VmapAdsConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class may be used to build the OVP, OTT media info objects
 * for Cast builders.
 * Along with that AdConfig objects can also be build
 * using Ad URL or Ad response
 */
public class MediaInfoUtils {

    /**
     * Must to set variable in `setPlayPosition`
     * for the LIVE medias
     */
    public static final int LIVE_EDGE = -1000;

    public static MediaInfo buildOVPMediaInfo(String entryId,
                                              String ks,
                                              boolean isLiveMedia,
                                              String defaultTextLang,
                                              String defaultAudioLang,
                                              TextTrackStyle textTrackStyle,
                                              MediaMetadata mediaMetadata,
                                              AdsConfig adsConfig) {
        MediaInfo mediaInfo = new KalturaCastBuilder()
                .setMediaEntryId(entryId)
                .setKs(ks)
                .setStreamType(isLiveMedia ? CAFCastBuilder.StreamType.LIVE : CAFCastBuilder.StreamType.VOD)
                .setDefaultTextLangaugeCode(defaultTextLang)
                .setDefaultAudioLangaugeCode(defaultAudioLang)
                .setTextTrackStyle(textTrackStyle)
                .setMetadata(mediaMetadata)
                .setAdsConfig(adsConfig)
                .build();
        return mediaInfo;
    }

    public static MediaInfo buildOTTMediaInfo(String entryId,
                                              String ks,
                                              boolean isLiveMedia,
                                              CAFCastBuilder.KalturaAssetType mediaType,
                                              CAFCastBuilder.AssetReferenceType assetReferenceType,
                                              CAFCastBuilder.PlaybackContextType playbackContextType,
                                              CAFCastBuilder.KalturaStreamerType streamerType,
                                              CAFCastBuilder.KalturaUrlType urlType,
                                              List<String> formats,
                                              String fileIds,
                                              CAFCastBuilder.HttpProtocol protocol,
                                              String defaultTextLang,
                                              String defaultAudioLang,
                                              TextTrackStyle textTrackStyle,
                                              MediaMetadata mediaMetadata,
                                              AdsConfig adsConfig) {
        MediaInfo mediaInfo = new KalturaPhoenixCastBuilder()
                .setMediaEntryId(entryId)
                .setKs(ks)
                .setStreamType(isLiveMedia ? CAFCastBuilder.StreamType.LIVE : CAFCastBuilder.StreamType.VOD)
                .setMediaType(mediaType)
                .setAssetReferenceType(assetReferenceType)
                .setContextType(playbackContextType)
                .setStreamerType(streamerType)
                .setUrlType(urlType)
                .setFormats(formats)
                .setFileIds(fileIds)
                .setProtocol(protocol)
                .setDefaultTextLangaugeCode(defaultTextLang)
                .setDefaultAudioLangaugeCode(defaultAudioLang)
                .setTextTrackStyle(textTrackStyle)
                .setMetadata(mediaMetadata)
                .setAdsConfig(adsConfig)
                .build();
        return mediaInfo;
    }

    /**
     * Create the VAST AdConfig for GoogleCast with playback position
     *
     * @param playbackPositionInMs start position in miliseconds
     * @param adTagUrl VAST ad url
     * @return AdsConfig
     */
    public static AdsConfig createAdsConfigVastInPosition(long playbackPositionInMs, String adTagUrl) {
        List<AdBreakClipInfo> adBreakClipInfoList = new ArrayList<>();
        VastAdsRequest vastRequest = new VastAdsRequest.Builder().setAdTagUrl(adTagUrl).build();
        AdBreakClipInfo clipInfo1 = new AdBreakClipInfo.Builder("100").setVastAdsRequest(vastRequest).build();
        adBreakClipInfoList.add(clipInfo1);

        List<AdBreakInfo> adBreakInfoList = new ArrayList<>();
        final String[] breakClipIds = new String[]{"100"};
        AdBreakInfo adBreakInfo1 = new AdBreakInfo.Builder(playbackPositionInMs).setBreakClipIds(breakClipIds).setId("101").build();
        adBreakInfoList.add(adBreakInfo1);

        return new VastAdsConfig().setVastAdBreakClipInfoList(adBreakClipInfoList).setVastAdBreakInfoList(adBreakInfoList);
    }

    /**
     * Create the VAST AdConfig for GoogleCast with playback position
     *
     * @param playbackPositionInMs start position in miliseconds
     * @param adTagResponse VAST XML ad response
     * @return AdsConfig
     */
    public static AdsConfig createAdsResponseConfigVastInPosition(long playbackPositionInMs, String adTagResponse) {
        List<AdBreakClipInfo> adBreakClipInfoList = new ArrayList<>();
        VastAdsRequest vastRequest = new VastAdsRequest.Builder().setAdsResponse(adTagResponse).build();
        AdBreakClipInfo clipInfo1 = new AdBreakClipInfo.Builder("100").setVastAdsRequest(vastRequest).build();
        adBreakClipInfoList.add(clipInfo1);

        List<AdBreakInfo> adBreakInfoList = new ArrayList<>();
        final String[] breakClipIds = new String[]{"100"};
        AdBreakInfo adBreakInfo1 = new AdBreakInfo.Builder(playbackPositionInMs).setBreakClipIds(breakClipIds).setId("101").build();
        adBreakInfoList.add(adBreakInfo1);

        return new VastAdsConfig().setVastAdBreakClipInfoList(adBreakClipInfoList).setVastAdBreakInfoList(adBreakInfoList);
    }

    /**
     * Create the VMAP AdConfig for GoogleCast
     *
     * @param adTagUrl VMAP ad url
     * @return AdsConfig
     */
    public static AdsConfig createAdsConfigVmap(String adTagUrl) {
        return new VmapAdsConfig()
                .setVmapAdRequest(new VmapAdRequest()
                .setAdTagUrl(adTagUrl));
    }

    /**
     * Create the VMAP AdConfig for GoogleCast
     *
     * @param adTagResponse VMAP XML ad response
     * @return AdsConfig
     */
    public static AdsConfig createAdResponseConfigVmap(String adTagResponse) {
        return new VmapAdsConfig()
                .setVmapAdRequest(new VmapAdRequest()
                        .setAdsResponse(adTagResponse));
    }
}
