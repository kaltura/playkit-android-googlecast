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

import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.VastAdsRequest;
import com.kaltura.playkit.plugins.googlecast.cafreceiver.adsmodel.AdsModel;
import com.kaltura.playkit.plugins.googlecast.cafreceiver.adsmodel.VastAdsModel;
import com.kaltura.playkit.plugins.googlecast.cafreceiver.adsmodel.VmapAdRequest;
import com.kaltura.playkit.plugins.googlecast.cafreceiver.adsmodel.VmapAdsModel;

import java.util.ArrayList;
import java.util.List;

public class MediaInfoUtils {

    public static MediaInfo buildOVPMediaInfo(String entryId,
                                              String ks,
                                              boolean isLiveMedia,
                                              String defaultTextLang,
                                              String defaultAudioLang,
                                              TextTrackStyle textTrackStyle,
                                              MediaMetadata mediaMetadata,
                                              AdsModel adsModel) {
        MediaInfo mediaInfo = new CAFCastBuilder()
                .setMediaEntryId(entryId)
                .setKs(ks)
                .setStreamType(isLiveMedia ? GenericCastBuilder.StreamType.LIVE : GenericCastBuilder.StreamType.VOD)
                .setDefaultTextLangaugeCode(defaultTextLang)
                .setDefaultAudioLangaugeCode(defaultAudioLang)
                .setTextTrackStyle(textTrackStyle)
                .setMetadata(mediaMetadata)
                .setAdsModel(adsModel)
                .build();
        return mediaInfo;
    }

    public static MediaInfo buildOTTMediaInfo(String entryId,
                                              String ks,
                                              boolean isLiveMedia,
                                              GenericCastBuilder.KalturaAssetType mediaType,
                                              GenericCastBuilder.AssetReferenceType assetReferenceType,
                                              GenericCastBuilder.PlaybackContextType playbackContextType,
                                              List<String> formats,
                                              String fileIds,
                                              GenericCastBuilder.HttpProtocol protocol,
                                              String defaultTextLang,
                                              String defaultAudioLang,
                                              TextTrackStyle textTrackStyle,
                                              MediaMetadata mediaMetadata,
                                              AdsModel adsModel) {
        MediaInfo mediaInfo = new CAFCastBuilder()
                .setMediaEntryId(entryId)
                .setKs(ks)
                .setMediaType(mediaType)
                .setAssetReferenceType(assetReferenceType)
                .setContextType(playbackContextType)
                .setFormats(formats)
                .setFileIds(fileIds)
                .setProtocol(protocol)
                .setStreamType(isLiveMedia ? GenericCastBuilder.StreamType.LIVE : GenericCastBuilder.StreamType.VOD)
                .setDefaultTextLangaugeCode(defaultTextLang)
                .setDefaultAudioLangaugeCode(defaultAudioLang)
                .setTextTrackStyle(textTrackStyle)
                .setMetadata(mediaMetadata)
                .setAdsModel(adsModel)
                .build();
        return mediaInfo;
    }

    public static AdsModel createAdsModelVastInPosition(long playbackPositionInMs, String adTagUrl) {
        List<AdBreakClipInfo> adBreakClipInfoList = new ArrayList<>();
        VastAdsRequest vastRequest = new VastAdsRequest.Builder().setAdTagUrl(adTagUrl).build();
        AdBreakClipInfo clipInfo1 = new AdBreakClipInfo.Builder("100").setVastAdsRequest(vastRequest).build();
        adBreakClipInfoList.add(clipInfo1);

        List<AdBreakInfo> adBreakInfoList = new ArrayList<>();
        final String[] breakClipIds = new String[]{"100"};
        AdBreakInfo adBreakInfo1 = new AdBreakInfo.Builder(playbackPositionInMs).setBreakClipIds(breakClipIds).setId("101").build();
        adBreakInfoList.add(adBreakInfo1);

        AdsModel adsModel = new VastAdsModel().setVastAdBreakClipInfoList(adBreakClipInfoList).setVastAdBreakInfoList(adBreakInfoList);
        return adsModel;
    }

    public static AdsModel createAdsModelVmap(String adTagUrl) {
        AdsModel adsModel = new VmapAdsModel()
                .setVmapAdRequest(new VmapAdRequest()
                .setAdTagUrl(adTagUrl));
        return adsModel;
    }
}