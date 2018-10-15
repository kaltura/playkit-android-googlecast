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



public class KalturaPhoenixCastBuilder extends CAFCastBuilder<KalturaPhoenixCastBuilder> {

    public KalturaPhoenixCastBuilder setFileIds(String fileIds) {
        castInfo.setFileIds(fileIds);
        return this;
    }

    public KalturaPhoenixCastBuilder setFormats(List<String> formats) {
        castInfo.setFormats(formats);
        return this;
    }
    public KalturaPhoenixCastBuilder setMediaType(KalturaAssetType kalturaAssetType) {
        castInfo.setMediaType(kalturaAssetType);
        return this;
    }

    public KalturaPhoenixCastBuilder setContextType(PlaybackContextType playbackContextType) {
        castInfo.setContextType(playbackContextType);
        return this;
    }

    public KalturaPhoenixCastBuilder setProtocol(HttpProtocol protocol) {
        castInfo.setProtocol(protocol);
        return this;
    }

    public KalturaPhoenixCastBuilder setAssetReferenceType(AssetReferenceType assetReferenceType) {
        castInfo.setAssetReferenceType(assetReferenceType);
        return this;
    }
}
