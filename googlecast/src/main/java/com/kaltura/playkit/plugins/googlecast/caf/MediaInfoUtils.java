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

public class MediaInfoUtils {

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

    public static AdsConfig createAdsConfigVastInPosition(long playbackPositionInMs, String adTagUrl) {
        List<AdBreakClipInfo> adBreakClipInfoList = new ArrayList<>();
        VastAdsRequest vastRequest = new VastAdsRequest.Builder().setAdTagUrl(adTagUrl).build();
        AdBreakClipInfo clipInfo1 = new AdBreakClipInfo.Builder("100").setVastAdsRequest(vastRequest).build();
        adBreakClipInfoList.add(clipInfo1);

        List<AdBreakInfo> adBreakInfoList = new ArrayList<>();
        final String[] breakClipIds = new String[]{"100"};
        AdBreakInfo adBreakInfo1 = new AdBreakInfo.Builder(playbackPositionInMs).setBreakClipIds(breakClipIds).setId("101").build();
        adBreakInfoList.add(adBreakInfo1);

        AdsConfig adsConfig = new VastAdsConfig().setVastAdBreakClipInfoList(adBreakClipInfoList).setVastAdBreakInfoList(adBreakInfoList);
        return adsConfig;
    }

    public static AdsConfig createAdsConfigVmap(String adTagUrl) {
        AdsConfig adsConfig = new VmapAdsConfig()
                .setVmapAdRequest(new VmapAdRequest()
                .setAdTagUrl(adTagUrl));
        return adsConfig;
    }
}
