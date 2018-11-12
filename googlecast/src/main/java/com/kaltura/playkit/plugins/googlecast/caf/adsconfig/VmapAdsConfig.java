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

import com.kaltura.playkit.plugins.googlecast.caf.CAFCastBuilder;

public class VmapAdsConfig implements AdsConfig {

    private CAFCastBuilder.AdTagType adTagType = CAFCastBuilder.AdTagType.VMAP;
    private VmapAdRequest vmapAdRequest;

    @Override
    public CAFCastBuilder.AdTagType getAdTagType() {
        return adTagType;
    }

    @Override
    public boolean isAdsConfigValid() {
        return !(getVmapAdRequest() == null || (getVmapAdRequest().getVastAdsRequestForAdTag() == null && getVmapAdRequest().getVastAdRequestForAdResponse() == null));
    }

    public VmapAdsConfig setVmapAdRequest(VmapAdRequest vmapAdRequest) {
        this.vmapAdRequest = vmapAdRequest;
        return this;
    }

    public VmapAdRequest getVmapAdRequest() {
        return vmapAdRequest;
    }
}
