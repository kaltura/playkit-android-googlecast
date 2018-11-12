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

import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakInfo;
import com.kaltura.playkit.plugins.googlecast.caf.CAFCastBuilder;

import java.util.List;

public class VastAdsConfig implements AdsConfig {

    private CAFCastBuilder.AdTagType adTagType = CAFCastBuilder.AdTagType.VAST;
    private List<AdBreakClipInfo> vastAdBreakClipInfoList;
    private List<AdBreakInfo> vastAdBreakInfoList;


    @Override
    public CAFCastBuilder.AdTagType getAdTagType() {
        return adTagType;
    }

    @Override
    public boolean isAdsConfigValid() {
        return !(getVastAdBreakClipInfoList() == null || getVastAdBreakInfoList() == null);
    }

    public VastAdsConfig setVastAdBreakClipInfoList(List<AdBreakClipInfo> vastAdBreakClipInfoList) {
        this.vastAdBreakClipInfoList = vastAdBreakClipInfoList;
        return this;
    }

    public VastAdsConfig setVastAdBreakInfoList(List<AdBreakInfo> vastAdBreakInfoList) {
        this.vastAdBreakInfoList = vastAdBreakInfoList;
        return this;
    }

    public List<AdBreakClipInfo> getVastAdBreakClipInfoList() {
        return vastAdBreakClipInfoList;
    }

    public List<AdBreakInfo> getVastAdBreakInfoList() {
        return vastAdBreakInfoList;
    }
}
