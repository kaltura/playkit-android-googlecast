package com.kaltura.playkit.plugins.googlecast.cafreceiver;

import android.support.v4.util.Pair;

import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakInfo;

import org.json.JSONObject;

import java.util.List;

public class AdsModel {

    private VmapAdRequest vmapAdRequest;
    private GenericCastBuilder.AdTagType adTagType;
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
