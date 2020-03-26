package mapp.android.android_demo_app

import android.app.Application
import android.content.pm.ActivityInfo
import com.appoxee.Appoxee
import com.appoxee.Appoxee.OnInitCompletedListener
import com.appoxee.AppoxeeOptions
import com.appoxee.push.NotificationMode

class MappApp : Application() {

    val initFinishedListener =
        OnInitCompletedListener { successful, failReason ->
            infoLog("init completed listener - Application class")
        }

    override fun onCreate() {
        super.onCreate()
        val opt = AppoxeeOptions()
        opt.sdkKey = "YOUR_SDK_KEY"
        opt.googleProjectId = "YOUR_GOOGLE_PROJECT_ID"
        opt.cepURL = "YOUR_CEP_URL"
        opt.appID = "YOUR_APP_ID"
        opt.tenantID = "YOUR_TENANT_ID"
        opt.notificationMode = NotificationMode.BACKGROUND_AND_FOREGROUND
        opt.server = AppoxeeOptions.Server.TEST //your server

        Appoxee.engage(this, opt)

        Appoxee.instance().addInitListener(initFinishedListener)

        Appoxee.setOrientation(this, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val info = Appoxee.instance().deviceInfo

        infoLog("info (before init finished): $info")

        Appoxee.instance().receiver = MyPushBroadcastReceiver::class.java
    }
}
