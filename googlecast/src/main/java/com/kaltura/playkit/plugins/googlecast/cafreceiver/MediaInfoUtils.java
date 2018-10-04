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

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.TextTrackStyle;

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
}
