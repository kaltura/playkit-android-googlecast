package com.kaltura.playkit.plugins.googlecast.caf.basic;

import com.google.gson.annotations.SerializedName;

public class Caption {

    @SerializedName(value = "default")
    private Boolean isDefault;
    private String type;
    private String language;
    private String label;
    private String url;

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
