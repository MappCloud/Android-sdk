package mapp.android.android_demo_app

import com.appoxee.push.PushData
import com.appoxee.push.PushDataReceiver

class MyPushBroadcastReceiver : PushDataReceiver() {

    override fun onPushOpened(pushData: PushData?) {
        super.onPushOpened(pushData)

        context.debugLog("Push received $pushData")
    }

    override fun onPushDismissed(pushData: PushData?) {
        super.onPushDismissed(pushData)

        context.debugLog("Push opened $pushData")
    }

    override fun onPushReceived(pushData: PushData?) {
        super.onPushReceived(pushData)

        context.debugLog("Push dismissed $pushData")
    }

    override fun onSilentPush(pushData: PushData?) {
        super.onSilentPush(pushData)

        context.debugLog("Push Silent $pushData")
    }
}