package com.kaltura.playkit.plugins.googlecast.caf.basic;

import java.util.List;

public class BasicCastInfo {

    private String poster;
    private Options options;
    private List<Hls> hls = null;
    private List<Dash> dash = null;
    private List<Progressive> progressive = null;
    private String id;
    private Integer duration;

    private String type;
    private Boolean dvr;
    private Object vr;
    private Metadata metadata;
    private List<Caption> captions = null;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public List<Hls> getHls() {
        return hls;
    }

    public void setHls(List<Hls> hls) {
        this.hls = hls;
    }

    public List<Dash> getDash() {
        return dash;
    }

    public void setDash(List<Dash> dash) {
        this.dash = dash;
    }

    public List<Progressive> getProgressive() {
        return progressive;
    }

    public void setProgressive(List<Progressive> progressive) {
        this.progressive = progressive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDvr() {
        return dvr;
    }

    public void setDvr(Boolean dvr) {
        this.dvr = dvr;
    }

    public Object getVr() {
        return vr;
    }

    public void setVr(Object vr) {
        this.vr = vr;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Caption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }

}