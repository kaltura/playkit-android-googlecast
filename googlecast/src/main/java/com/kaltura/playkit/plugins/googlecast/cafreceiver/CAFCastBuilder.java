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

package com.kaltura.playkit.plugins.googlecast.cafreceiver;

import android.support.annotation.NonNull;
import android.text.TextUtils;

public class CAFCastBuilder extends GenericCastBuilder<CAFCastBuilder> {


    public CAFCastBuilder setKs(@NonNull String ks) {
        castInfo.setKs(ks);
        return this;
    }


    @Override
    protected void validate(GenericCastInfo castInfo) throws IllegalArgumentException {

        super.validate(castInfo);

        // ks isn't mandatory in OVP/OTT environment, but if you do set a ks it must be valid
        String ks = castInfo.getKs();
        if (ks != null && TextUtils.isEmpty(ks)) {
            throw new IllegalArgumentException();
        }
    }
}
