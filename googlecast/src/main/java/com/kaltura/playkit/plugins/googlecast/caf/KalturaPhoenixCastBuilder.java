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

import com.kaltura.playkit.plugins.googlecast.caf.adsmodel.VmapAdsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.AUDIO_LANGUAGE;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.ENTRY_ID;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.KS;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.MEDIA_INFO;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.TEXT_LANGUAGE;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.VMAP_ADS_REQUEST;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaPhoenixCastInfo.ASSET_REFERENCE_TYPE;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaPhoenixCastInfo.CONTEXT_TYPE;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaPhoenixCastInfo.FILE_IDS;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaPhoenixCastInfo.FORMATS;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaPhoenixCastInfo.MEDIA_TYPE;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaPhoenixCastInfo.PROTOCOL;

public class KalturaPhoenixCastBuilder extends CAFCastBuilder {

    public KalturaPhoenixCastBuilder() {
        castInfo = new KalturaPhoenixCastInfo();
    }

    public KalturaPhoenixCastBuilder setFileIds(String fileIds) {
        ((KalturaPhoenixCastInfo)castInfo).setFileIds(fileIds);
        return this;
    }

    public KalturaPhoenixCastBuilder setFormats(List<String> formats) {
        ((KalturaPhoenixCastInfo)castInfo).setFormats(formats);
        return this;
    }
    public KalturaPhoenixCastBuilder setMediaType(KalturaAssetType kalturaAssetType) {
        ((KalturaPhoenixCastInfo)castInfo).setMediaType(kalturaAssetType);
        return this;
    }

    public KalturaPhoenixCastBuilder setContextType(PlaybackContextType playbackContextType) {
        ((KalturaPhoenixCastInfo)castInfo).setContextType(playbackContextType);
        return this;
    }

    public KalturaPhoenixCastBuilder setProtocol(HttpProtocol protocol) {
        ((KalturaPhoenixCastInfo)castInfo).setProtocol(protocol);
        return this;
    }

    public KalturaPhoenixCastBuilder setAssetReferenceType(AssetReferenceType assetReferenceType) {
        ((KalturaPhoenixCastInfo)castInfo).setAssetReferenceType(assetReferenceType);
        return this;
    }

    public JSONObject getCustomData() {
        JSONObject customData = new JSONObject();
        try {
            JSONObject mediaData = new JSONObject();
            mediaData.put(ENTRY_ID, castInfo.getMediaEntryId());
            if (!TextUtils.isEmpty(castInfo.getKs())) {
                mediaData.put(KS, castInfo.getKs());
            }

            if (((KalturaPhoenixCastInfo)castInfo).getMediaType() != null) {
                mediaData.put(MEDIA_TYPE, ((KalturaPhoenixCastInfo)castInfo).getMediaType().value);
            }
            if (((KalturaPhoenixCastInfo)castInfo).getAssetReferenceType() != null) {
                mediaData.put(ASSET_REFERENCE_TYPE, ((KalturaPhoenixCastInfo)castInfo).getAssetReferenceType().value);
            }
            if (((KalturaPhoenixCastInfo)castInfo).getContextType() != null) {
                mediaData.put(CONTEXT_TYPE, ((KalturaPhoenixCastInfo)castInfo).getContextType().value);
            }
            if (((KalturaPhoenixCastInfo)castInfo).getProtocol() != null) {
                mediaData.put(PROTOCOL, ((KalturaPhoenixCastInfo)castInfo).getProtocol().value);
            }
            if (!TextUtils.isEmpty(((KalturaPhoenixCastInfo)castInfo).getFileIds())) {
                mediaData.put(FILE_IDS, ((KalturaPhoenixCastInfo)castInfo).getFileIds());
            }
            if (((KalturaPhoenixCastInfo)castInfo).getFormats() != null) {
                JSONArray formatsArray = new JSONArray();
                for (String format : ((KalturaPhoenixCastInfo)castInfo).getFormats()) {
                    formatsArray.put(format);
                }
                mediaData.put(FORMATS, formatsArray);
            }
            customData.put(MEDIA_INFO, mediaData);
            if (!TextUtils.isEmpty(castInfo.getTextLanguage())) {
                customData.put(TEXT_LANGUAGE, castInfo.getTextLanguage());
            }
            if (!TextUtils.isEmpty(castInfo.getAudioLanguage())) {
                customData.put(AUDIO_LANGUAGE, castInfo.getAudioLanguage());
            }

            if(castInfo.getAdsModel() != null && castInfo.getAdsModel().getAdTagType() == CAFCastBuilder.AdTagType.VMAP) {
                if (((VmapAdsModel)castInfo.getAdsModel()).getVmapAdRequest() != null) {
                    JSONObject vmapAdRequestJson = ((VmapAdsModel)castInfo.getAdsModel()).getVmapAdRequest().toJSONObject();
                    if (vmapAdRequestJson != null) {
                        customData.put(VMAP_ADS_REQUEST, vmapAdRequestJson);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return customData;
    }
}
