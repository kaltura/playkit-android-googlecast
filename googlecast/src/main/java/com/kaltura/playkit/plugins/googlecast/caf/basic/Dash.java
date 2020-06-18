package com.kaltura.playkit.plugins.googlecast.caf.basic;

import java.util.List;

public class Dash {

    private String id;
    private String url;
    private String mimetype;

    private List<DrmData> drmData = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public List<DrmData> getDrmData() {
        return drmData;
    }

    public void setDrmData(List<DrmData> drmData) {
        this.drmData = drmData;
    }
}
