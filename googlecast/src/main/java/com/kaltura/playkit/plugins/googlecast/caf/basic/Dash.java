package com.kaltura.playkit.plugins.googlecast.caf.basic;

import java.util.Collections;
import java.util.List;

public class Dash extends BaseMediaParams {

    public List<DrmData> drmData = null;

    public Dash() {
        super();
    }

    public Dash(String id, String url, String mimetype, DrmData drmData) {
        super(id, url, mimetype);
        this.drmData = Collections.singletonList(drmData);;
    }
}
