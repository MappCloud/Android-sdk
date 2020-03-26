package mapp.android.android_demo_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)

        val uri: Uri?
        if (intent != null) {
            if ("com.appoxee.VIEW_DEEPLINK" == intent.action) {
                uri = intent.data
                //Data supplied from the front-end.
                if (uri != null) {
                    val link = uri.getQueryParameter("link")
                    //This is the messageId
                    val messageId = uri.getQueryParameter("message_id")
                    //This is the eventTrigger only for version 5.0.7 and higher
                    val eventTrigger = uri.getQueryParameter("event_trigger")

                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(link)
                    startActivity(i)
                }

            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri: Uri?
        if (intent != null) {
            if ("com.appoxee.VIEW_DEEPLINK" == intent.action) {
                uri = intent.data
                //Data supplied from the front-end.
                if (uri != null) {
                    val link = uri.getQueryParameter("link")
                    //This is the messageId
                    val messageId = uri.getQueryParameter("message_id")
                    //This is the eventTrigger only for version 5.0.7 and higher
                    val eventTrigger = uri.getQueryParameter("event_trigger")

                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(link)
                    startActivity(i)
                }

            }
        }
    }
}
