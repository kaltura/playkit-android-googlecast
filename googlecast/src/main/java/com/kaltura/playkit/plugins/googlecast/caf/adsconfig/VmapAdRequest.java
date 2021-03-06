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

import androidx.annotation.NonNull;
import com.google.android.gms.cast.VastAdsRequest;

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
