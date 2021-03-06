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

package com.kaltura.playkit.plugins.googlecast;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by itanbarpeled on 13/12/2016.
 */


abstract class CastConfigHelper {

    protected static final String TAG = "CastConfigHelper";


    JSONObject getCustomData(CastInfo castInfo) {

        String uiConf = castInfo.getUiConfId();
        String fileFormat = castInfo.getFormat();
        String entryId = castInfo.getMediaEntryId();
        String partnerId = castInfo.getPartnerId();
        String adTagUrl = castInfo.getAdTagUrl();
        String sessionInfo = getSessionInfo(castInfo);
        String mwEmbedUrl = castInfo.getMwEmbedUrl();
        String textLangaugeLabel = castInfo.getDefaultTextLangaugeLabel();

        JSONObject customData = new JSONObject();
        JSONObject embedConfig = new JSONObject();

        setEmbedConfig(customData, embedConfig, uiConf, fileFormat, entryId, partnerId, adTagUrl, sessionInfo, mwEmbedUrl, textLangaugeLabel);
        return customData;
    }

    private void setEmbedConfig(JSONObject customData, JSONObject embedConfig, String uiConf,
                                String fileFormat, String entryId,
                                String partnerId, String adTagUrl, String sessionInfo,
                                String mwEmbedUrl, String textLangaugeLabel) {

        try {

            if (!TextUtils.isEmpty(mwEmbedUrl)) { //mwEmbedUrl isn't mandatory
                embedConfig.put("lib", mwEmbedUrl);
            }

            embedConfig.put("publisherID", partnerId);
            embedConfig.put("entryID", entryId);
            embedConfig.put("uiconfID", uiConf);

            setFlashVars(embedConfig, sessionInfo, adTagUrl, fileFormat, entryId);

            customData.put("embedConfig", embedConfig);

            //Add default captions language in cast
            if (!TextUtils.isEmpty(textLangaugeLabel)) {
                JSONObject receiverConfig = new JSONObject();
                receiverConfig.put("defaultLanguageKey", textLangaugeLabel);
                customData.put("receiverConfig", receiverConfig);
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    protected abstract String getSessionInfo(CastInfo castInfo);

    private void setFlashVars(JSONObject embedConfig, String sessionInfo, String adTagUrl,
                              String fileFormat, String entryId) {

        try {

            JSONObject flashVars = getFlashVars(sessionInfo, adTagUrl, fileFormat, entryId);

            if (flashVars.length() > 0) {

                embedConfig.put("flashVars", flashVars);

            }
            
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

    }

    private JSONObject getFlashVars(String sessionInfo, String adTagUrl,
                                    String fileFormat, String entryId) {

        JSONObject flashVars = new JSONObject();

        setProxyData(flashVars, sessionInfo, fileFormat, entryId);
        setDoubleClickPlugin(flashVars, adTagUrl);

        return flashVars;
    }

    protected abstract void setProxyData(JSONObject flashVars, String sessionData,
                                         String fileFormat, String entryId);

    private void setDoubleClickPlugin(JSONObject flashVars, String adTagUrl) {

        JSONObject doubleClick = new JSONObject();

        if (!TextUtils.isEmpty(adTagUrl)) {

            try {

                doubleClick.put("plugin", true);
                doubleClick.put("adTagUrl", adTagUrl);
                flashVars.put("doubleClick", doubleClick);

            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
