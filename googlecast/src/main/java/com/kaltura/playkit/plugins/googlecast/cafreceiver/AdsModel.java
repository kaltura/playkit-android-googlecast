package com.kaltura.playkit.plugins.googlecast.cafreceiver;

import android.support.v4.util.Pair;

import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.VastAdsRequest;

import java.util.ArrayList;
import java.util.List;

public class AdsModel {

    String adTagUrl;
    boolean isVast;
    private List<AdBreakClipInfo> adBreakClipInfoList;
    private List<AdBreakInfo> adBreakInfoList;

    public AdsModel(String adTagUrl, boolean isVast) {
        this.adTagUrl = adTagUrl;
        this.isVast = isVast;
        if (isVast) {
            Pair<List<AdBreakClipInfo>,List<AdBreakInfo>> vastModelPair = getAdsData(adTagUrl);
            adBreakClipInfoList = vastModelPair.first;
            adBreakInfoList = vastModelPair.second;
        }
    }

    public String getAdTagUrl() {
        return adTagUrl;
    }

    public boolean isVast() {
        return isVast;
    }

    public List<AdBreakClipInfo> getAdBreakClipInfoList() {
        return adBreakClipInfoList;
    }

    public List<AdBreakInfo> getAdBreakInfoList() {
        return adBreakInfoList;
    }

    private Pair<List<AdBreakClipInfo>,List<AdBreakInfo>> getAdsData(String vastAdTag) {

        List<AdBreakClipInfo> adBreakClipInfoList = new ArrayList<>();
        VastAdsRequest vastRequest = new VastAdsRequest.Builder().setAdTagUrl(vastAdTag).build();
        AdBreakClipInfo clipInfo1 = new AdBreakClipInfo.Builder("100").setVastAdsRequest(vastRequest).build();
        adBreakClipInfoList.add(clipInfo1);

        List<AdBreakInfo> adBreakInfoList = new ArrayList<>();
        final String [] breakClipIds  = new String [] {"100"};
        AdBreakInfo adBreakInfo1 = new AdBreakInfo.Builder(0).setBreakClipIds(breakClipIds).setId("101").build();
        adBreakInfoList.add(adBreakInfo1);
        return new Pair(adBreakClipInfoList, adBreakInfoList);
    }
}
