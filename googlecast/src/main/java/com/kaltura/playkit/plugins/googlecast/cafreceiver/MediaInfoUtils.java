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
