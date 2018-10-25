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

package com.kaltura.playkit.plugins.googlecast.caf.adsconfig;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.cast.VastAdsRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class VmapAdRequest {

    private String adTagUrl;
    private String adsResponse;

    public VmapAdRequest setAdTagUrl(@NonNull String adTagUrl) {
        this.adTagUrl = adTagUrl;
        return this;
    }

    public VmapAdRequest setAdsResponse(@NonNull String adsResponse) {
        this.adsResponse = adsResponse;
        return this;
    }

//    public JSONObject toJSONObject() {
//        JSONObject vmapAdsRequest = new JSONObject();
//        try {
//            if (!TextUtils.isEmpty(adTagUrl)) {
//                vmapAdsRequest.put("adTagUrl", adTagUrl);
//            } else if (!TextUtils.isEmpty(adsResponse)) {
//                vmapAdsRequest.put("adsResponse", adsResponse);
//            } else {
//                return null;
//            }
//        } catch(JSONException e){
//            return null;
//        }
//
//        return vmapAdsRequest;
//    }

    public VastAdsRequest getVastAdsRequestForAdTag() {
        if (adTagUrl == null) {
            return null;
        }
        return new VastAdsRequest.Builder().setAdTagUrl(adTagUrl).build();
    }

    public VastAdsRequest getVastAdRequestForAdResponse() {
        if (adsResponse == null) {
            return null;
        }
        return new VastAdsRequest.Builder().setAdsResponse(adsResponse).build();
    }
}
