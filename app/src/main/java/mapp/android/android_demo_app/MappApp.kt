package mapp.android.android_demo_app

import android.app.Application
import android.content.pm.ActivityInfo
import com.appoxee.Appoxee
import com.appoxee.Appoxee.OnInitCompletedListener
import com.appoxee.AppoxeeOptions
import com.appoxee.push.NotificationMode

class MappApp : Application() {

    private var APP_ID = "110036"
    private var CEP_URL = "https://jamie.a.shortest-route.com"
    private var GOOGLE_PROJECT_ID = "95178078564"
    private var SDK_KEY = "5e707e276bbc50.59848000"
    private var SERVER = "EMC_US"
    private var TENANT_ID = "60210"

    val initFinishedListener =
        OnInitCompletedListener { successful, failReason ->
            infoLog("init completed listener - Application class")
        }

    override fun onCreate() {
        super.onCreate()
        val opt = AppoxeeOptions()
        opt.sdkKey = SDK_KEY
        opt.googleProjectId = GOOGLE_PROJECT_ID
        opt.cepURL = CEP_URL
        opt.appID = APP_ID
        opt.tenantID = TENANT_ID
        opt.notificationMode = NotificationMode.BACKGROUND_AND_FOREGROUND
        opt.server = AppoxeeOptions.Server.EMC_US //your server

        Appoxee.engage(this, opt)

        Appoxee.instance().addInitListener(initFinishedListener)

        Appoxee.setOrientation(this, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val info = Appoxee.instance().deviceInfo

        infoLog("info (before init finished): $info")

        Appoxee.instance().receiver = MyPushBroadcastReceiver::class.java
    }
}
