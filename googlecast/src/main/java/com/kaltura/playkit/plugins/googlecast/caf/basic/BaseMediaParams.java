package com.kaltura.playkit.plugins.googlecast.caf.basic;

public class BaseMediaParams {
    public String id;
    public String url;
    public String mimetype;

    public BaseMediaParams() {}

    public BaseMediaParams(String id, String url, String mimetype) {
        this.id = id;
        this.url = url;
        this.mimetype = mimetype;
    }
}
