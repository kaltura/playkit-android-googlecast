///*
// * ============================================================================
// * Copyright (C) 2017 Kaltura Inc.
// *
// * Licensed under the AGPLv3 license, unless a different license for a
// * particular library is specified in the applicable library path.
// *
// * You may obtain a copy of the License at
// * https://www.gnu.org/licenses/agpl-3.0.html
// * ============================================================================
// */
//
//
//package com.kaltura.playkit.plugins.googlecast.caf;
//
//import android.text.TextUtils;
//
//import com.google.android.gms.cast.MediaMetadata;
//import com.google.android.gms.cast.TextTrackStyle;
//import com.kaltura.playkit.plugins.googlecast.caf.adsconfig.AdsConfig;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.List;
//
//
//{
//        "poster": "http://externaltests.dev.kaltura.com/player/library_Player_V3/smartPages/images/Kaltura_poster.png",
//        "options": {},
//        "hls": [
//        {
//        "id": "0_2jiaa9tb_972,applehttp",
//        "url": "https://qa-apache-php7.dev.kaltura.com/p/4171/sp/417100/playManifest/entryId/0_2jiaa9tb/protocol/https/format/applehttp/flavorIds/0_91up74iz,0_psbr7wf7,0_ujs8h5pq,0_cf9m7hw9,0_4u0xdczl,0_qmnf9ic4/a.m3u8?uiConfId=15213831&playSessionId=32ef4478-0f5e-91e3-a62b-646675c70743:0ca9a81b-cd01-fb1b-da92-300dd73cea97&referrer=aHR0cHM6Ly9leHRlcm5hbHRlc3RzLmRldi5rYWx0dXJhLmNvbS9wbGF5ZXIvbGlicmFyeV9QbGF5ZXJfVjMvc21hcnRQYWdlcy9QbGF5ZXJfVjNfZ2VuZXJpY19wYWdlLnBocD9jZG5Vcmw9dGVzdCZwYXJ0bmVySWQ9NDE3MSZ1aUNvbmZJZD10ZXN0RHJtJnNlbGVjdGVkVWlDb25mPTE1MjEzODMxJmVudHJ5SWQ9dHN0RHJtJnNlbGVjdGVkRW50cnlJZD0wXzJqaWFhOXRiJmNvbXBMc3Q9JmNvbXBWZXI9JmNvbXBWZXJzPSZsb2dzPWRlYiZwbGxzdEJ5RW50SWQ9JnBsbHN0SWQ9JmNudER3blRUUz0mY250RHduRHVyPSZlbmcxPWh0bWwmZW5nMj1odG1sJmVuZzM9aHRtbCZzdFByMT0mc3RQcjI9JnN0UHIzPSZ0eHRMYW5nPWRlZmF1bHQmYXVkaW9MYW5nPWRlZmF1bHQmYV9zPTAmc3RyUG9zPSZwbEluPW9uJmFkVGFnPW5vQWQmYWRBZnRUaW1lPSZudW1SZWRpcmVjdHM9JmRhaT1ub0RhaSZidW1wZXI9bm9CdW1wJmJ1bXBQb3M9ZGVmQnVtcCZjY0VuYj1vbiZXTVBsYWNlPWJvdFJpZ2h0&clientTag=html5:v0.54.0",
//        "mimetype": "application/x-mpegURL",
//        "drmData": [
//        {
//        "licenseUrl": "https://udrm2.dev.kaltura.com/fps/license?custom_data=eyJjYV9zeXN0ZW0iOiJPVlAiLCJ1c2VyX3Rva2VuIjoiWWpsaVlUSmpOemN6TmpJellXVXpaVFU1WWpneFlXVTNZalZsWm1GaU1EazVNbVJrTjJNek5YdzBNVGN4T3pReE56RTdNVFU1TWpRd01qVTVNanN3T3pFMU9USXpNVFl4T1RJdU1UTTRPekE3ZG1sbGR6b3FMSGRwWkdkbGREb3hPenM9IiwiYWNjb3VudF9pZCI6NDE3MSwiY29udGVudF9pZCI6IjBfMmppYWE5dGIiLCJmaWxlcyI6IjBfOTF1cDc0aXosMF9wc2JyN3dmNywwX3VqczhoNXBxLDBfY2Y5bTdodzksMF80dTB4ZGN6bCwwX3FtbmY5aWM0In0%3D&signature=ek%2BKT7gznEmOqiAaD%2BOshnaF5s0%3D&sessionId=32ef4478-0f5e-91e3-a62b-646675c70743:0ca9a81b-cd01-fb1b-da92-300dd73cea97&clientTag=html5:v0.54.0&referrer=aHR0cHM6Ly9leHRlcm5hbHRlc3RzLmRldi5rYWx0dXJhLmNvbS9wbGF5ZXIvbGlicmFyeV9QbGF5ZXJfVjMvc21hcnRQYWdlcy9QbGF5ZXJfVjNfZ2VuZXJpY19wYWdlLnBocD9jZG5Vcmw9dGVzdCZwYXJ0bmVySWQ9NDE3MSZ1aUNvbmZJZD10ZXN0RHJtJnNlbGVjdGVkVWlDb25mPTE1MjEzODMxJmVudHJ5SWQ9dHN0RHJtJnNlbGVjdGVkRW50cnlJZD0wXzJqaWFhOXRiJmNvbXBMc3Q9JmNvbXBWZXI9JmNvbXBWZXJzPSZsb2dzPWRlYiZwbGxzdEJ5RW50SWQ9JnBsbHN0SWQ9JmNudER3blRUUz0mY250RHduRHVyPSZlbmcxPWh0bWwmZW5nMj1odG1sJmVuZzM9aHRtbCZzdFByMT0mc3RQcjI9JnN0UHIzPSZ0eHRMYW5nPWRlZmF1bHQmYXVkaW9MYW5nPWRlZmF1bHQmYV9zPTAmc3RyUG9zPSZwbEluPW9uJmFkVGFnPW5vQWQmYWRBZnRUaW1lPSZudW1SZWRpcmVjdHM9JmRhaT1ub0RhaSZidW1wZXI9bm9CdW1wJmJ1bXBQb3M9ZGVmQnVtcCZjY0VuYj1vbiZXTVBsYWNlPWJvdFJpZ2h0",
//        "scheme": "com.apple.fairplay",
//        "certificate": "MIIFETCCA/mgAwIBAgIISWLo8KcYfPMwDQYJKoZIhvcNAQEFBQAwfzELMAkGA1UEBhMCVVMxEzARBgNVBAoMCkFwcGxlIEluYy4xJjAkBgNVBAsMHUFwcGxlIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MTMwMQYDVQQDDCpBcHBsZSBLZXkgU2VydmljZXMgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkwHhcNMTYwMjAxMTY0NTQ0WhcNMTgwMjAxMTY0NTQ0WjCBijELMAkGA1UEBhMCVVMxKDAmBgNVBAoMH1ZJQUNPTSAxOCBNRURJQSBQUklWQVRFIExJTUlURUQxEzARBgNVBAsMClE5QU5HR0w4TTYxPDA6BgNVBAMMM0ZhaXJQbGF5IFN0cmVhbWluZzogVklBQ09NIDE4IE1FRElBIFBSSVZBVEUgTElNSVRFRDCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA2YmfdPWM86+te7Bbt4Ic6FexXwMeL+8AmExIj8jAaNxhKbfVFnUnuXzHOajGC7XDbXxsFbPqnErqjw0BqUoZhs+WVMy+0X4AGqHk7uRpZ4RLYganel+fqitL9rz9w3p41x8JfLV+lAej+BEN7zNeqQ2IsC4BxkViu1gA6K22uGsCAwEAAaOCAgcwggIDMB0GA1UdDgQWBBQK+Gmarl2PO3jtLP6A6TZeihOL3DAMBgNVHRMBAf8EAjAAMB8GA1UdIwQYMBaAFGPkR1TLhXFZRiyDrMxEMWRnAyy+MIHiBgNVHSAEgdowgdcwgdQGCSqGSIb3Y2QFATCBxjCBwwYIKwYBBQUHAgIwgbYMgbNSZWxpYW5jZSBvbiB0aGlzIGNlcnRpZmljYXRlIGJ5IGFueSBwYXJ0eSBhc3N1bWVzIGFjY2VwdGFuY2Ugb2YgdGhlIHRoZW4gYXBwbGljYWJsZSBzdGFuZGFyZCB0ZXJtcyBhbmQgY29uZGl0aW9ucyBvZiB1c2UsIGNlcnRpZmljYXRlIHBvbGljeSBhbmQgY2VydGlmaWNhdGlvbiBwcmFjdGljZSBzdGF0ZW1lbnRzLjA1BgNVHR8ELjAsMCqgKKAmhiRodHRwOi8vY3JsLmFwcGxlLmNvbS9rZXlzZXJ2aWNlcy5jcmwwDgYDVR0PAQH/BAQDAgUgMEgGCyqGSIb3Y2QGDQEDAQH/BDYBZ2diOGN5bXpsb21vdXFqb3p0aHg5aXB6dDJ0bThrcGdqOGNwZGlsbGVhMWI1aG9saWlyaW8wPQYLKoZIhvdjZAYNAQQBAf8EKwF5aHZlYXgzaDB2Nno5dXBqcjRsNWVyNm9hMXBtam9zYXF6ZXdnZXFkaTUwDQYJKoZIhvcNAQEFBQADggEBAIaTVzuOpZhHHUMGd47XeIo08E+Wb5jgE2HPsd8P/aHwVcR+9627QkuAnebftasV/h3FElahzBXRbK52qIZ/UU9nRLCqqKwX33eS2TiaAzOoMAL9cTUmEa2SMSzzAehb7lYPC73Y4VQFttbNidHZHawGp/844ipBS7Iumas8kT8G6ZmIBIevWiggd+D5gLdqXpOFI2XsoAipuxW6NKnnlKnuX6aNReqzKO0DmQPDHO2d7pbd3wAz5zJmxDLpRQfn7iJKupoYGqBs2r45OFyM14HUWaC0+VSh2PaZKwnSS8XXo4zcT/MfEcmP0tL9NaDfsvIWnScMxHUUTNNsZIp3QXA="
//        }
//        ]
//        }
//        ],
//        "dash": [
//        {
//        "id": "0_2jiaa9tb_997,mpegdash",
//        "url": "https://qa-apache-php7.dev.kaltura.com/p/4171/sp/417100/playManifest/entryId/0_2jiaa9tb/protocol/https/format/mpegdash/flavorIds/0_ujs8h5pq,0_cf9m7hw9,0_4u0xdczl,0_qmnf9ic4/a.mpd?uiConfId=15213831&playSessionId=32ef4478-0f5e-91e3-a62b-646675c70743:0ca9a81b-cd01-fb1b-da92-300dd73cea97&referrer=aHR0cHM6Ly9leHRlcm5hbHRlc3RzLmRldi5rYWx0dXJhLmNvbS9wbGF5ZXIvbGlicmFyeV9QbGF5ZXJfVjMvc21hcnRQYWdlcy9QbGF5ZXJfVjNfZ2VuZXJpY19wYWdlLnBocD9jZG5Vcmw9dGVzdCZwYXJ0bmVySWQ9NDE3MSZ1aUNvbmZJZD10ZXN0RHJtJnNlbGVjdGVkVWlDb25mPTE1MjEzODMxJmVudHJ5SWQ9dHN0RHJtJnNlbGVjdGVkRW50cnlJZD0wXzJqaWFhOXRiJmNvbXBMc3Q9JmNvbXBWZXI9JmNvbXBWZXJzPSZsb2dzPWRlYiZwbGxzdEJ5RW50SWQ9JnBsbHN0SWQ9JmNudER3blRUUz0mY250RHduRHVyPSZlbmcxPWh0bWwmZW5nMj1odG1sJmVuZzM9aHRtbCZzdFByMT0mc3RQcjI9JnN0UHIzPSZ0eHRMYW5nPWRlZmF1bHQmYXVkaW9MYW5nPWRlZmF1bHQmYV9zPTAmc3RyUG9zPSZwbEluPW9uJmFkVGFnPW5vQWQmYWRBZnRUaW1lPSZudW1SZWRpcmVjdHM9JmRhaT1ub0RhaSZidW1wZXI9bm9CdW1wJmJ1bXBQb3M9ZGVmQnVtcCZjY0VuYj1vbiZXTVBsYWNlPWJvdFJpZ2h0&clientTag=html5:v0.54.0",
//        "mimetype": "application/dash+xml",
//        "drmData": [
//        {
//        "licenseUrl": "https://udrm2.dev.kaltura.com/cenc/playready/license?custom_data=eyJjYV9zeXN0ZW0iOiJPVlAiLCJ1c2VyX3Rva2VuIjoiWWpsaVlUSmpOemN6TmpJellXVXpaVFU1WWpneFlXVTNZalZsWm1GaU1EazVNbVJrTjJNek5YdzBNVGN4T3pReE56RTdNVFU1TWpRd01qVTVNanN3T3pFMU9USXpNVFl4T1RJdU1UTTRPekE3ZG1sbGR6b3FMSGRwWkdkbGREb3hPenM9IiwiYWNjb3VudF9pZCI6NDE3MSwiY29udGVudF9pZCI6IjBfMmppYWE5dGIiLCJmaWxlcyI6IjBfdWpzOGg1cHEsMF9jZjltN2h3OSwwXzR1MHhkY3psLDBfcW1uZjlpYzQifQ%3D%3D&signature=M%2BVmumYctFj20KrrJUZPO5lPVyA%3D&sessionId=32ef4478-0f5e-91e3-a62b-646675c70743:0ca9a81b-cd01-fb1b-da92-300dd73cea97&clientTag=html5:v0.54.0&referrer=aHR0cHM6Ly9leHRlcm5hbHRlc3RzLmRldi5rYWx0dXJhLmNvbS9wbGF5ZXIvbGlicmFyeV9QbGF5ZXJfVjMvc21hcnRQYWdlcy9QbGF5ZXJfVjNfZ2VuZXJpY19wYWdlLnBocD9jZG5Vcmw9dGVzdCZwYXJ0bmVySWQ9NDE3MSZ1aUNvbmZJZD10ZXN0RHJtJnNlbGVjdGVkVWlDb25mPTE1MjEzODMxJmVudHJ5SWQ9dHN0RHJtJnNlbGVjdGVkRW50cnlJZD0wXzJqaWFhOXRiJmNvbXBMc3Q9JmNvbXBWZXI9JmNvbXBWZXJzPSZsb2dzPWRlYiZwbGxzdEJ5RW50SWQ9JnBsbHN0SWQ9JmNudER3blRUUz0mY250RHduRHVyPSZlbmcxPWh0bWwmZW5nMj1odG1sJmVuZzM9aHRtbCZzdFByMT0mc3RQcjI9JnN0UHIzPSZ0eHRMYW5nPWRlZmF1bHQmYXVkaW9MYW5nPWRlZmF1bHQmYV9zPTAmc3RyUG9zPSZwbEluPW9uJmFkVGFnPW5vQWQmYWRBZnRUaW1lPSZudW1SZWRpcmVjdHM9JmRhaT1ub0RhaSZidW1wZXI9bm9CdW1wJmJ1bXBQb3M9ZGVmQnVtcCZjY0VuYj1vbiZXTVBsYWNlPWJvdFJpZ2h0",
//        "scheme": "com.microsoft.playready"
//        },
//        {
//        "licenseUrl": "https://udrm2.dev.kaltura.com/cenc/widevine/license?custom_data=eyJjYV9zeXN0ZW0iOiJPVlAiLCJ1c2VyX3Rva2VuIjoiWWpsaVlUSmpOemN6TmpJellXVXpaVFU1WWpneFlXVTNZalZsWm1GaU1EazVNbVJrTjJNek5YdzBNVGN4T3pReE56RTdNVFU1TWpRd01qVTVNanN3T3pFMU9USXpNVFl4T1RJdU1UTTRPekE3ZG1sbGR6b3FMSGRwWkdkbGREb3hPenM9IiwiYWNjb3VudF9pZCI6NDE3MSwiY29udGVudF9pZCI6IjBfMmppYWE5dGIiLCJmaWxlcyI6IjBfdWpzOGg1cHEsMF9jZjltN2h3OSwwXzR1MHhkY3psLDBfcW1uZjlpYzQifQ%3D%3D&signature=M%2BVmumYctFj20KrrJUZPO5lPVyA%3D&sessionId=32ef4478-0f5e-91e3-a62b-646675c70743:0ca9a81b-cd01-fb1b-da92-300dd73cea97&clientTag=html5:v0.54.0&referrer=aHR0cHM6Ly9leHRlcm5hbHRlc3RzLmRldi5rYWx0dXJhLmNvbS9wbGF5ZXIvbGlicmFyeV9QbGF5ZXJfVjMvc21hcnRQYWdlcy9QbGF5ZXJfVjNfZ2VuZXJpY19wYWdlLnBocD9jZG5Vcmw9dGVzdCZwYXJ0bmVySWQ9NDE3MSZ1aUNvbmZJZD10ZXN0RHJtJnNlbGVjdGVkVWlDb25mPTE1MjEzODMxJmVudHJ5SWQ9dHN0RHJtJnNlbGVjdGVkRW50cnlJZD0wXzJqaWFhOXRiJmNvbXBMc3Q9JmNvbXBWZXI9JmNvbXBWZXJzPSZsb2dzPWRlYiZwbGxzdEJ5RW50SWQ9JnBsbHN0SWQ9JmNudER3blRUUz0mY250RHduRHVyPSZlbmcxPWh0bWwmZW5nMj1odG1sJmVuZzM9aHRtbCZzdFByMT0mc3RQcjI9JnN0UHIzPSZ0eHRMYW5nPWRlZmF1bHQmYXVkaW9MYW5nPWRlZmF1bHQmYV9zPTAmc3RyUG9zPSZwbEluPW9uJmFkVGFnPW5vQWQmYWRBZnRUaW1lPSZudW1SZWRpcmVjdHM9JmRhaT1ub0RhaSZidW1wZXI9bm9CdW1wJmJ1bXBQb3M9ZGVmQnVtcCZjY0VuYj1vbiZXTVBsYWNlPWJvdFJpZ2h0",
//        "scheme": "com.widevine.alpha"
//        }
//        ]
//        }
//        ],
//        "progressive": [],
//        "id": "0_2jiaa9tb",
//        "duration": 741,
//        "type": "Vod",
//        "dvr": false,
//        "vr": null,
//        "metadata": {
//        "name": "Audio Tracks with DRM",
//        "description": "",
//        "tags": ""
//        },
//        "captions": [
//        {
//        "default": false,
//        "type": "srt",
//        "language": "nl",
//        "label": "Ger",
//        "url": "https://qa-apache-php7.dev.kaltura.com/api_v3/index.php/service/caption_captionAsset/action/serve/captionAssetId/0_kd5r6b9c/v/2"
//        },
//        {
//        "default": false,
//        "type": "srt",
//        "language": "ru",
//        "label": "Rus",
//        "url": "https://qa-apache-php7.dev.kaltura.com/api_v3/index.php/service/caption_captionAsset/action/serve/captionAssetId/0_y5h9dvc1/v/2"
//        },
//        {
//        "default": false,
//        "type": "srt",
//        "language": "en",
//        "label": "Eng",
//        "url": "https://qa-apache-php7.dev.kaltura.com/api_v3/index.php/service/caption_captionAsset/action/serve/captionAssetId/0_gcgssdfu/v/2"
//        }
//        ]
//        }
//class KalturaBasicCastInfo {
//    public static final String HLS = "hls";
//    public static final String DASH = "dash";
//    public static final String PROGRESSIVE = "progressive";
//    public static final String TEXT_LANGUAGE = "textLanguage";
//    public static final String AUDIO_LANGUAGE = "audioLanguage";
//    public static final String VMAP_ADS_REQUEST = "vmapAdsRequest";
//
//    //Phoenix
//    public static final String MEDIA_TYPE = "mediaType";
//    public static final String ASSET_REFERENCE_TYPE = "assetReferenceType";
//    public static final String CONTEXT_TYPE = "contextType";
//    public static final String PROTOCOL = "protocol";
//    public static final String FILE_IDS = "fileIds";
//    public static final String FORMATS = "formats";
//
//
//    //Basic
//    private String mediaEntryId;
//    private String ks;                     // optional
//    private String textLanguage;           // optional
//    private String audioLanguage;          // optional
//    private MediaMetadata mediaMetadata;   // optional
//    private TextTrackStyle textTrackStyle; // optional
//    private CAFCastBuilder.StreamType streamType; // optional
//    private AdsConfig adsConfig; // optional
//
//    //Phoenix
//    private CAFCastBuilder.KalturaAssetType mediaType;
//    private CAFCastBuilder.AssetReferenceType assetReferenceType;
//    private CAFCastBuilder.PlaybackContextType contextType;
//    private CAFCastBuilder.HttpProtocol protocol;
//    private String fileIds; // 'FILE_ID1,FILE_ID2'
//    private List<String> formats; //['Device_Format_1', 'Device_Format_2', 'Device_Format_3']
//
//
//    public KalturaBasicCastInfo() {}
//
//    public String getMediaEntryId() {
//        return mediaEntryId;
//    }
//
//    public KalturaBasicCastInfo setMediaEntryId(String mediaEntryId) {
//        this.mediaEntryId = mediaEntryId;
//        return this;
//    }
//
//    public String getKs() {
//        return ks;
//    }
//
//    public KalturaBasicCastInfo setKs(String ks) {
//        this.ks = ks;
//        return this;
//    }
//
//    public String getTextLanguage() {
//        return textLanguage;
//    }
//
//    public KalturaBasicCastInfo setDefaultTextLangaugeCode(String textLanguage) {
//        this.textLanguage = textLanguage;
//        return this;
//    }
//
//    public String getAudioLanguage() {
//        return audioLanguage;
//    }
//
//    public KalturaBasicCastInfo setDefaultAudioLanguageCode(String audioLanguage) {
//        this.audioLanguage = audioLanguage;
//        return this;
//    }
//
//    public MediaMetadata getMediaMetadata() {
//        return mediaMetadata;
//    }
//
//    public KalturaBasicCastInfo setMediaMetadata(MediaMetadata mediaMetadata) {
//        this.mediaMetadata = mediaMetadata;
//        return this;
//    }
//
//    public TextTrackStyle getTextTrackStyle() {
//        return textTrackStyle;
//    }
//
//    public KalturaBasicCastInfo setTextTrackStyle(TextTrackStyle textTrackStyle) {
//        this.textTrackStyle = textTrackStyle;
//        return this;
//    }
//
//    public CAFCastBuilder.StreamType getStreamType() {
//        return streamType;
//    }
//
//    public KalturaBasicCastInfo setStreamType(CAFCastBuilder.StreamType streamType) {
//        this.streamType = streamType;
//        return this;
//    }
//
//    public AdsConfig getAdsConfig() {
//        return adsConfig;
//    }
//
//    public KalturaBasicCastInfo setAdsConfig(AdsConfig adsConfig) {
//        this.adsConfig = adsConfig;
//        return this;
//    }
//
//    public String getFileIds() {
//        return fileIds;
//    }
//
//    public KalturaBasicCastInfo setFileIds(String fileIds) {
//        this.fileIds = fileIds;
//        return this;
//    }
//
//    public List<String> getFormats() {
//        return formats;
//    }
//
//    public KalturaBasicCastInfo setFormats(List<String> formats) {
//        this.formats = formats;
//        return this;
//    }
//
//    public CAFCastBuilder.KalturaAssetType getMediaType() {
//        return mediaType;
//    }
//
//    public KalturaBasicCastInfo setMediaType(CAFCastBuilder.KalturaAssetType kalturaAssetType) {
//        this.mediaType = kalturaAssetType;
//        return this;
//    }
//
//    public CAFCastBuilder.PlaybackContextType getContextType() {
//        return contextType;
//    }
//
//    public KalturaBasicCastInfo setContextType(CAFCastBuilder.PlaybackContextType playbackContextType) {
//        this.contextType = playbackContextType;
//        return this;
//    }
//
//    public CAFCastBuilder.AssetReferenceType getAssetReferenceType() {
//        return assetReferenceType;
//    }
//
//    public KalturaBasicCastInfo setAssetReferenceType(CAFCastBuilder.AssetReferenceType assetReferenceType) {
//        this.assetReferenceType = assetReferenceType;
//        return this;
//    }
//    public CAFCastBuilder.HttpProtocol getProtocol() {
//        return protocol;
//    }
//
//    public KalturaBasicCastInfo setProtocol(CAFCastBuilder.HttpProtocol protocol) {
//        this.protocol = protocol;
//        return this;
//    }
//
//    public JSONObject getCustomData() {
//        JSONObject customData = new JSONObject();
//        try {
//            JSONObject mediaData = new JSONObject();
//            mediaData.put(ENTRY_ID, getMediaEntryId());
//            if (!TextUtils.isEmpty(getKs())) {
//                mediaData.put(KS, getKs());
//            }
//
//            if (getMediaType() != null) {
//                mediaData.put(MEDIA_TYPE, getMediaType().value);
//            }
//            if (getAssetReferenceType() != null) {
//                mediaData.put(ASSET_REFERENCE_TYPE, getAssetReferenceType().value);
//            }
//            if (getContextType() != null) {
//                mediaData.put(CONTEXT_TYPE, getContextType().value);
//            }
//            if (getProtocol() != null) {
//                mediaData.put(PROTOCOL, getProtocol().value);
//            }
//            if (!TextUtils.isEmpty(getFileIds())) {
//                mediaData.put(FILE_IDS, getFileIds());
//            }
//            if (getFormats() != null) {
//                JSONArray formatsArray = new JSONArray();
//                for (String format : getFormats()) {
//                    formatsArray.put(format);
//                }
//                mediaData.put(FORMATS, formatsArray);
//            }
//            customData.put(MEDIA_INFO, mediaData);
//            if (!TextUtils.isEmpty(getTextLanguage())) {
//                customData.put(TEXT_LANGUAGE, getTextLanguage());
//            }
//            if (!TextUtils.isEmpty(getAudioLanguage())) {
//                customData.put(AUDIO_LANGUAGE, getAudioLanguage());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return customData;
//    }
//}
//
