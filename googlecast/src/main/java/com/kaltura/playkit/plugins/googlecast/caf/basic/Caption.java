package com.kaltura.playkit.plugins.googlecast.caf.basic;

import com.google.gson.annotations.SerializedName;

public class Caption {

    @SerializedName(value = "default")
    public Boolean isDefault;
    public String type;
    public String language;
    public String label;
    public String url;
}
