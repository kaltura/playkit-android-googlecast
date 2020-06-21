package com.kaltura.playkit.plugins.googlecast.caf.basic;

public class Metadata {

    public String name;
    public String description;
    public String tags;

    public Metadata() {}

    public Metadata(String name, String description, String tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }
}