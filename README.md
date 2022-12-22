[![CI Status](https://github.com/kaltura/playkit-android-googlecast/actions/workflows/build.yml/badge.svg)](https://github.com/kaltura/playkit-android-googlecast/actions/workflows/build.yml)
[![Download](https://img.shields.io/maven-central/v/com.kaltura.playkit/googlecast?label=Download)](https://search.maven.org/artifact/com.kaltura.playkit/googlecast)
[![License](https://img.shields.io/badge/license-AGPLv3-black.svg)](https://github.com/kaltura/playkit-android-kava/blob/master/LICENSE)
![Android](https://img.shields.io/badge/platform-android-green.svg)

# playkit-android-googlecast

Using the Google Cast plugin, Developers can extend the media to another TV, Audio system etc. It will be a remote media streaming and your app will become a remote for it.

We provide Cast Application Framework(CAF) for it. Our plugin is developed on top of Google's designed CAF framework.

Before understanding that how to setup Google cast plugin on Android, let's understand few terms,

##### Sender Application: 
Your Android app which gives the user the ability to connect to the casting device, is a Sender Application.

##### Receiver Application:
When your sender app connects to the cast device then cast device downloads an script from the server which is actually configured against the CastId for your application. Basically it's a Web receiver Application. [How to build a Receiver Application](https://github.com/kaltura/playkit-js-cast-receiver)

##### Cast Receiver ID: 
To register the receiver application, Developers has to register on [Google Cast SDK Developer Console](https://cast.google.com/publish). 
This receiver ID is the gateway for the sender app to communicate with the remote receiver app.

### Setup

Add GoogleCast plugin dependency to `build.gradle`. In android, we keep all plugins aligned with same version.

`implementation 'com.kaltura.playkit:googlecast:x.x.x'`

**You can find the latest version here:**

[Latest Release](https://github.com/kaltura/playkit-android-googlecast/releases)

### How to use Google Cast plugin

##### 1. Create `CastContext`

`mCastContext = CastContext.getSharedInstance(this)`

##### 2. Create `SessionManagerListener` 

A `Session` is a connection between Sender and Receiver Application. [More Info](https://developers.google.com/android/reference/com/google/android/gms/cast/framework/Session?hl=en)

```kotlin
mCastContext?.sessionManager?.addSessionManagerListener(
                mSessionManagerListener, CastSession::class.java)
```

In this, `onApplicationConnected` of `SessionManagerListener` callback will help application to load the media.

##### 3. Get `RemoteMediaClient`,

App will get `remoteMediaClient` in the callback `onSessionResumed` of `SessionManagerListener`. Once app get the `remoteMediaClient` then register the  listener for `RemoteMediaClient.Callback()`. 

This listener has a callback `onStatusUpdated` where we will know that there is a nearby casting device and app can connect to that.

##### 4. Getting the Cast icon and the remote controller activity for the App

Here comes an important step, how the user will get the cast icon, 

A menu item can be added for and `Activity`,

```xml
<item
        android:id="@+id/media_route_menu_item"
        android:title="@string/media_route_menu_title"
        app:actionProviderClass="androidx.mediarouter.app.MediaRouteActionProvider"
        app:showAsAction="always"/>

```	 

This will enable a cast icon in the tool bar menu item.

For the expanded controller for the receiver application. Launch an activity which extents to Cast framework class `ExpandedControllerActivity`. 
Then in this activity's menu inflation method, app has to setup the `MediaRouteButton`,

```kotlin
override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.expanded_controller, menu)
        CastButtonFactory.setUpMediaRouteButton(this, menu, R.id.media_route_menu_item)
        return true
    }

```

This new acitivity should be launched in ``onStatusUpdated` callback of `RemoteMediaClient.Callback()` on `remoteMediaClient`,

```kotlin
remoteMediaClient!!.registerCallback(object : RemoteMediaClient.Callback() {
            override fun onStatusUpdated() {
                val intent = Intent(this@MainActivity, ExpandedControlsActivity::class.java)
                startActivity(intent)
                //remoteMediaClient?.unregisterCallback(this)
            }
            ...
            ...

```
##### 5. Load the Media for Casting

###### GoogleCast for Kaltura OTT Customers

```kotlin
val castBuilder = KalturaPhoenixCastBuilder()
                .setMediaEntryId(mediaId)
                .setKs("")
                .setFormats(formats)
                .setStreamType(CAFCastBuilder.StreamType.VOD)
                .setAssetReferenceType(CAFCastBuilder.AssetReferenceType.Media)
                .setContextType(CAFCastBuilder.PlaybackContextType.Playback)
                .setMediaType(CAFCastBuilder.KalturaAssetType.Media)
                .setProtocol(protocol)

var mediaInfo: MediaInfo = castBuilder.build()

```

###### GoogleCast for Kaltura OVP Customers

```kotlin
val castBuilder = KalturaCastBuilder()
                .setMediaEntryId(entryId)
                .setKs("")
                .setStreamType(CAFCastBuilder.StreamType.VOD)
var mediaInfo: MediaInfo = castBuilder.build()

```

###### GoogleCast for Non Kaltura Customers (Basic Cast Provider)

```Kotlin
val castBuilder = KalturaBasicCAFCastBuilder(playbackParams)
                .setStreamType(CAFCastBuilder.StreamType.VOD)
var mediaInfo: MediaInfo = castBuilder.build()

```

> How to create `PlaybackParams` for Non-Kaltura Customers 

```kotlin
        val playbackParams = PlaybackParams();
        playbackParams.poster = "Poster_URL"
        playbackParams.id = "ID"
        playbackParams.duration = Duration
        playbackParams.type = "Vod" // For Live "Live" can be set
        playbackParams.dvr = false
        playbackParams.vr = null
        
		 // If DASH Widevine source is required 
        playbackParams.setDashSource("ID","PlaybackURL","LicenseURL")
        
        // For Clear DASH
        // playbackParams.setDashSource("ID","URL","") // Pass empty License URL
        
        // Another way of passing clear DASH media
        // val drmData = DrmData() // This is important to be passed
        // drmData.licenseUrl = "" // Empty License URL
        // drmData.scheme = PlaybackParams.DRMSCHEME_WIDEVINE
        // playbackParams.dash = listOf(Dash("ID","PlaybackURL", PlaybackParams.MIMETYPE_DASH, drmData))
        

        // If HLS source is required 
        // playbackParams.setHlsSource("ID","PlaybackURL")
        
        // Another way of passing HLS Source
        // val hlsSource = Hls()
        // hlsSource.id = "ID"
        // hlsSource.url = "PlaybackURL"
        // hlsSource.mimetype = PlaybackParams.MIMETYPE_HLS
        // playbackParams.hls = listOf(hlsSource)
        

		// If Progressive Source (Ex. mp4) is required
        // playbackParams.setProgressivehSource("ID","PlaybackURL")
        
        // Another way of passing Progressive Source
        // val progressiveSource = Progressive();
        // progressiveSource.id = "ID";
        // progressiveSource.url = "PlaybackURL";
        // progressiveSource.mimetype = PlaybackParams.MIMETYPE_MP4;
        // playbackParams.progressive = listOf(progressiveSource)

        val metadata: Metadata = Metadata()
        metadata.description = "" // Description Text which will be visible on the media on Cast device

        metadata.name = "Text Tracks" // Title Text which will be visible on the media on Cast device
        metadata.tags = "";
        playbackParams.metadata = metadata
        playbackParams.captions =  // Pass external captions (How to build it is given in the next part of the document)

```

##### 6. Create `MediaLoadOptions` before actually loading the media using `RemoteMediaClient`

```kotlin
var pendingResult: PendingResult<RemoteMediaClient.MediaChannelResult>? = null
val loadOptions = MediaLoadOptions.Builder().setAutoplay(true).setPlayPosition(0L).build()

```
> **NOTE: In case for Live Media, `setPlayPosition` should be passed `MediaInfoUtils.LIVE_EDGE` or `-1L`**. 
> 
> **In case, for VOD assets, app wants to pass the start position for the player, this parameter can be used.**

##### 7. Finally load the `RemoteMediaClient`,

```kotlin
pendingResult = remoteMediaClient!!.load(mediaInfo object, loadOptions)

```

#### Play Ads with Google Cast

For VAST Ad URL, pass AdUrl in `MediaInfoUtils`,

`val adsConfig = MediaInfoUtils.createAdsConfigVastInPosition(startPosition, adTagUrl)`

For VMAP, pass AdUrl like this,

`val adsConfig = MediaInfoUtils.createAdsConfigVmap(adTagUrl)`

For VAST Ad response XML, pass ad response to

`MediaInfoUtils.createAdsResponseConfigVastInPosition(startPosition, adResponse)`

For VMAP Ad response XML, pass ad response to

`MediaInfoUtils.createAdResponseConfigVmap(adResponse)`

Then pass the `AdsConfig` object to `setAdsConfig` on the respective cast builder object (OTT/OVP or Basic)

**OTT:** `phoenixCastBuilder.setAdsConfig(Ads_Config)`

**OVP:** `ovpV3CastBuilder.setAdsConfig(Ads_Config)`

**BASIC:**`basicCastBuilder.setAdsConfig(Ads_Config)`

`startPosition` is the cast player start position. Let's suppose media is playing back at some position x seconds and when the user connects then app want to media to start from x seconds then use this parameter. This is only meant for VAST ad url/response. It will be one time ad playback.

For VMAP, anyways based on the time offset given in the VMAP, ads will be played.

> **NOTE : We have [IMA Plugin](https://github.com/kaltura/playkit-android-ima) for Ad playback. That is only meant for in app Ad playback. IMA Plugin does not help to play the Ads while using the cast.**

> **For Cast the above given configuration is must to have otherwise Ad will not play in GoogleCast.**


#### Pass External Subtitles for the media

Create a list of `Caption` object,

```kotlin
private fun getExternalVttCaptions(): List<Caption> {
        val caption1 = Caption()
        caption1.isDefault = false
        caption1.type = "vtt"
        caption1.label = "Heb"
        caption1.language = "he"
        caption1.url = "subtitle_url"
        val caption2 = Caption()
        caption2.isDefault = false
        caption2.type = "vtt"
        caption2.label = "Jap"
        caption2.language = "ja"
        caption2.url = "subtitle_url"
        val caption3 = Caption()
        caption3.isDefault = false
        caption3.type = "vtt"
        caption3.label = "Rus"
        caption3.language = "ru"
        caption3.url = "subtitle_url"
        var captions: List<Caption> = mutableListOf(caption1, caption2, caption3)
        return captions
    }
```

And then pass this to the respective cast builder object,

`phoenixCastBuilder.setExternalVttCaptions(getExternalVttCaptions())`

#### Next Media Playback (Change Media)

Simply load the next media given in **Step - 7** above. 

### Samples:

[Kaltura Player Google Cast Sample](https://github.com/kaltura/kaltura-player-android-samples/tree/develop/AdvancedSamples/ChromecastCAFSample)
