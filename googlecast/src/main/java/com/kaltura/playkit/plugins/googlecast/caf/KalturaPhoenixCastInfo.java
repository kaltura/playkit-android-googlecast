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


package com.kaltura.playkit.plugins.googlecast.caf;

import android.text.TextUtils;

import com.kaltura.playkit.plugins.googlecast.caf.adsmodel.VmapAdsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

class KalturaPhoenixCastInfo extends KalturaCastInfo {

    public static final String MEDIA_TYPE = "mediaType";
    public static final String ASSET_REFERENCE_TYPE = "assetReferenceType";
    public static final String CONTEXT_TYPE = "contextType";
    public static final String PROTOCOL = "protocol";
    public static final String FILE_IDS = "fileIds";
    public static final String FORMATS = "formats";

    private CAFCastBuilder.KalturaAssetType mediaType;
    private CAFCastBuilder.AssetReferenceType assetReferenceType;
    private CAFCastBuilder.PlaybackContextType contextType;
    private CAFCastBuilder.HttpProtocol protocol;
    private String fileIds; // 'FILE_ID1,FILE_ID2'
    private List<String> formats; //['Device_Format_1', 'Device_Format_2', 'Device_Format_3']

    public KalturaPhoenixCastInfo() {}

    public String getFileIds() {
        return fileIds;
    }

    public KalturaPhoenixCastInfo setFileIds(String fileIds) {
        this.fileIds = fileIds;
        return this;
    }

    public List<String> getFormats() {
        return formats;
    }

    public KalturaPhoenixCastInfo setFormats(List<String> formats) {
        this.formats = formats;
        return this;
    }

    public CAFCastBuilder.KalturaAssetType getMediaType() {
        return mediaType;
    }

    public KalturaPhoenixCastInfo setMediaType(CAFCastBuilder.KalturaAssetType kalturaAssetType) {
        this.mediaType = kalturaAssetType;
        return this;
    }

    public CAFCastBuilder.PlaybackContextType getContextType() {
        return contextType;
    }

    public KalturaPhoenixCastInfo setContextType(CAFCastBuilder.PlaybackContextType playbackContextType) {
        this.contextType = playbackContextType;
        return this;
    }

    public CAFCastBuilder.AssetReferenceType getAssetReferenceType() {
        return assetReferenceType;
    }

    public KalturaPhoenixCastInfo setAssetReferenceType(CAFCastBuilder.AssetReferenceType assetReferenceType) {
        this.assetReferenceType = assetReferenceType;
        return this;
    }
    public CAFCastBuilder.HttpProtocol getProtocol() {
        return protocol;
    }

    public KalturaPhoenixCastInfo setProtocol(CAFCastBuilder.HttpProtocol protocol) {
        this.protocol = protocol;
        return this;
    }
}

