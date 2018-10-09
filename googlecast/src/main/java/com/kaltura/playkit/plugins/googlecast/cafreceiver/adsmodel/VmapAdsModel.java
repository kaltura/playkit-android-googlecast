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

package com.kaltura.playkit.plugins.googlecast.cafreceiver.adsmodel;

import com.kaltura.playkit.plugins.googlecast.cafreceiver.GenericCastBuilder;
import com.kaltura.playkit.plugins.googlecast.cafreceiver.VmapAdRequest;

public class VmapAdsModel implements AdsModel {

    private GenericCastBuilder.AdTagType adTagType = GenericCastBuilder.AdTagType.VMAP;
    private VmapAdRequest vmapAdRequest;

    @Override
    public GenericCastBuilder.AdTagType getAdTagType() {
        return adTagType;
    }

    @Override
    public boolean isAdModelValid() {
        if (getVmapAdRequest() == null || getVmapAdRequest().toJSONObject() == null) {
           return false;
        }
        return true;
    }

    public VmapAdsModel setVmapAdRequest(VmapAdRequest vmapAdRequest) {
        this.vmapAdRequest = vmapAdRequest;
        return this;
    }

    public VmapAdRequest getVmapAdRequest() {
        return vmapAdRequest;
    }
}
