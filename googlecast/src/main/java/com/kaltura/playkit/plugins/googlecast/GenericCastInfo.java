package com.kaltura.playkit.plugins.googlecast;

/*
 * ============================================================================
 * Copyright (C) 2017 Kaltura Inc.
 *
 * Licensed under the AGPLv3 license, unless a different license for a
 * particular library is specified in the applicable library path.
 *
 * You may obtain a copy of the License at
 * https://www.gnu.org/licenses/agpl-3.0.html
 * ============================================================================
 */

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class GenericCastInfo {
    private String mediaEntryId;
    private String ks;
    private String textLanguage;
    private String audioLanguage;

    public GenericCastInfo() {}

    public String getMediaEntryId() {
        return mediaEntryId;
    }

    public GenericCastInfo setMediaEntryId(String mediaEntryId) {
        this.mediaEntryId = mediaEntryId;
        return this;
    }

    public String getKs() {
        return ks;
    }

    public GenericCastInfo setKs(String ks) {
        this.ks = ks;
        return this;
    }

    public String getTextLanguage() {
        return textLanguage;
    }

    public GenericCastInfo setTextLanguage(String textLanguage) {
        this.textLanguage = textLanguage;
        return this;
    }

    public String getAudioLanguage() {
        return audioLanguage;
    }

    public GenericCastInfo setAudioLanguage(String audioLanguage) {
        this.audioLanguage = audioLanguage;
        return this;
    }

    public JSONObject getCastInfoAsJson() {
        JSONObject mediaInfo = new JSONObject();
        try {

            JSONObject customData = new JSONObject();
            customData.put("entryId", getMediaEntryId());
            if (!TextUtils.isEmpty(getKs())) {
                customData.put("ks", getKs());
            }
            if (!TextUtils.isEmpty(getTextLanguage())) {
                customData.put("textLanguage", getTextLanguage());
            }
            if (!TextUtils.isEmpty(getAudioLanguage())) {
                customData.put("audioLanguage", getAudioLanguage());
            }
            mediaInfo.put("mediaInfo", customData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mediaInfo;
    }
}

