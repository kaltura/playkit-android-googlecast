package com.kaltura.playkit.plugins.googlecast.cafreceiver;

import android.support.annotation.NonNull;
import android.text.TextUtils;

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

    public JSONObject toSONObject() {
        JSONObject vmapAdsRequest = new JSONObject();
        try {
            if (!TextUtils.isEmpty(adTagUrl)) {
                vmapAdsRequest.put("adTagUrl", adTagUrl);
            } else if (!TextUtils.isEmpty(adsResponse)) {

                vmapAdsRequest.put("adsResponse", adsResponse);
            } else {
                return null;
            }
        } catch(JSONException e){
            return null;
        }

        return vmapAdsRequest;
    }
}
