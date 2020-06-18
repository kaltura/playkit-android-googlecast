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

import java.util.List;


public class KalturaBasicCastBuilder extends CAFCastBuilder<KalturaBasicCastBuilder> {

    public KalturaBasicCastBuilder setFileIds(String fileIds) {
        castInfo.setFileIds(fileIds);
        return this;
    }

    public KalturaBasicCastBuilder setFormats(List<String> formats) {
        castInfo.setFormats(formats);
        return this;
    }
    public KalturaBasicCastBuilder setMediaType(KalturaAssetType kalturaAssetType) {
        castInfo.setMediaType(kalturaAssetType);
        return this;
    }

    public KalturaBasicCastBuilder setContextType(PlaybackContextType playbackContextType) {
        castInfo.setContextType(playbackContextType);
        return this;
    }

    public KalturaBasicCastBuilder setProtocol(HttpProtocol protocol) {
        castInfo.setProtocol(protocol);
        return this;
    }

    public KalturaBasicCastBuilder setAssetReferenceType(AssetReferenceType assetReferenceType) {
        castInfo.setAssetReferenceType(assetReferenceType);
        return this;
    }
}
