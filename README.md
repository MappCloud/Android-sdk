# Android-sdk

The Mapp Mobile SDK is available for Mapp Engage mobile integrated customers.
The SDKs enable you to integrate mobile push and in-app messaging into your campaign orchestration.
SDK documentation is available at [Mapp Cloud Mobile Integration](https://mapp-wiki.atlassian.net/wiki/spaces/MIC/pages/430492/Initialize+Android+FCM+SDK+for+Mapp+Cloud).

# Contents
- [Installation](#installation)
- [Initialization](#initialization)

# Installation
Gradle
```groovy
implementation 'com.mapp.sdk:mapp-android:6.0.4'
```

The SDK requires that you enable Java 8 in your builds.
```groovy
compileOptions {
       sourceCompatibility JavaVersion.VERSION_1_8
       targetCompatibility JavaVersion.VERSION_1_8
}
```

Allow the network permission in your app manifest.
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

The SDK supports min Android SDK (19).

Note that the SDK uses [AndroidX](https://developer.android.com/jetpack/androidx), make sure to migrate your app to [AndroidX Migration](https://developer.android.com/jetpack/androidx#using_androidx) to avoid Manifest merger failure.

# Initialization

Make sure your application build.gradle file include the applicationId attribute for android defaultConfig. Alternately, you can replace ${applicationId} with your application package (e.g com.yourapppackage.app). Include the following dependencies to your app's gradle.build dependencies section :

```
android {
    compileSdkVersion 29
    buildToolsVersion "29.0.2"
    defaultConfig {
        applicationId "com.yourapppackage.app" . //make sure you have this   
        minSdkVersion 19
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'com.mapp.sdk:mapp-android:6.0.3'
}

apply plugin: 'com.google.gms.google-services'
```

In the Application class of your android project, add following code

```
public class AppoxeeTestApp extends Application {
...
@Override
public void onCreate() {
    super.onCreate();
    ...
    AppoxeeOptions opt = new AppoxeeOptions();
    opt.sdkKey = SDK_KEY;
    opt.googleProjectId = GOOGLE_PROJECT_ID;
    opt.cepURL= CEP_URL;//(optional for SDK 6.0.0 and above)
    opt.appID = APP_ID;
    opt.tenantID= TENANT_ID;
   //Only for version 5.0.10+
    opt.notificationMode = NotificationMode.BACKGROUND_AND_FOREGROUND; (optional)
    //for SDK 6.0.0 and above
    opt.server = Appoxee.Server.L3;
 
    Appoxee.engage(this, opt);
    //This line is necessary for Android Oreo.
    Appoxee.instance().setReceiver(MyPushBroadcastReceiver.class);
    //Necessary for applications with locked screen rotation. 
    Appoxee.setOrientation(this, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    //Registered users are opt-outed by default.
    Appoxee.instance().setPushEnabled(true);
    ...
}
  
...
}
```

pass your *sdk key* and *google project id*, *sdk key* is present in your CEP dashboard. Add the application in the Android manifest file.

*NotificationMode* is enum and you can choose one of three options:
* BACKGROUND_ONLY - notification will show only when the app is closed or in idle mode.<br/> 
* BACKGROUND_AND_FOREGROUND - notification will show every time when push notification comes.<br/> 
* SILENT_ONLY - notification never show on the device.
If you don't choose one of these options, by default is BACKGROUND_ONLY.  This feature is available only for user who use SDK above 5.0.10.


*AppoxeeOptions*. The server is enum and you can choose one of four options:<br/>
* L3<br/>
* EMC<br/>
* CROC<br/>
* TEST

An account manager will provide you info which one you should use in your application (L3, EMC or CROC).<br/>Our developers use TEST for development purpose and you shouldn't use this one.

<br/>If you don't choose one of these options, by default is a TEST. This feature is available only for users who use SDK 6.0.0 and above.
<br/>In SDK 6.0.0 and above is opt.cepURL= CEP URL is optional.






 You can find older version of SDK at this [link](https://developers.mapp.com/#eu-region) 
