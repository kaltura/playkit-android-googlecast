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
import java.util.List;

public class AdsModel {

    private GenericCastBuilder.AdTagType adTagType;
    private VmapAdRequest vmapAdRequest;
    private List<AdBreakClipInfo> vastAdBreakClipInfoList;
    private List<AdBreakInfo> vastAdBreakInfoList;

    public AdsModel(GenericCastBuilder.AdTagType adTagType) {
        this.adTagType = adTagType;
    }

    public GenericCastBuilder.AdTagType getAdTagType() {
        return adTagType;
    }

    public AdsModel setVmapAdRequest(VmapAdRequest vmapAdRequest) {
        this.vmapAdRequest = vmapAdRequest;
        return this;
    }

    public AdsModel setVastAdBreakClipInfoList(List<AdBreakClipInfo> vastAdBreakClipInfoList) {
        this.vastAdBreakClipInfoList = vastAdBreakClipInfoList;
        return this;
    }

    public AdsModel setVastAdBreakInfoList(List<AdBreakInfo> vastAdBreakInfoList) {
        this.vastAdBreakInfoList = vastAdBreakInfoList;
        return this;
    }

    public VmapAdRequest getVmapAdRequest() {
        return vmapAdRequest;
    }

    public List<AdBreakClipInfo> getVastAdBreakClipInfoList() {
        return vastAdBreakClipInfoList;
    }

    public List<AdBreakInfo> getVastAdBreakInfoList() {
        return vastAdBreakInfoList;
    }
}
