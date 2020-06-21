package com.kaltura.playkit.plugins.googlecast.caf.basic;

import java.util.List;

public class Dash extends BaseMediaParams {

    private List<DrmData> drmData = null;

    public List<DrmData> getDrmData() {
        return drmData;
    }
    public void setDrmData(List<DrmData> drmData) {
        this.drmData = drmData;
    }
}
