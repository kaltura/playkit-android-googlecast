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

import com.kaltura.playkit.plugins.googlecast.caf.adsmodel.VmapAdsModel;

import org.json.JSONException;
import org.json.JSONObject;

import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.AUDIO_LANGUAGE;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.ENTRY_ID;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.KS;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.MEDIA_INFO;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.TEXT_LANGUAGE;
import static com.kaltura.playkit.plugins.googlecast.caf.KalturaCastInfo.VMAP_ADS_REQUEST;

public class KalturaCastBuilder extends CAFCastBuilder {

    public KalturaCastBuilder() {
        castInfo = new KalturaCastInfo();
    }

    public JSONObject getCustomData() {
        JSONObject customData = new JSONObject();
        try {
            JSONObject mediaData = new JSONObject();
            mediaData.put(ENTRY_ID, castInfo.getMediaEntryId());
            if (!TextUtils.isEmpty(castInfo.getKs())) {
                mediaData.put(KS, castInfo.getKs());
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
