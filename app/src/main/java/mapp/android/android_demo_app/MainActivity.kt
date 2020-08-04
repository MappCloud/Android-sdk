package mapp.android.android_demo_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appoxee.Appoxee
import com.appoxee.Appoxee.*
import com.appoxee.DeviceInfo
import mapp.android.android_demo_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnInitCompletedListener {
    private lateinit var binding: ActivityMainBinding

    private var apx: Appoxee = instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apx.addInitListener(this)

        handleRichPush(this, intent)

        binding.getAlias.setOnClickListener {

            val alias: String? = apx.alias

            if (alias.isNullOrEmpty()) {
                toast("Please set alias value")
            } else {
                binding.aliasEtxt.setText(alias)
            }

        }

        binding.setAlias.setOnClickListener{

            val newAlias = binding.aliasEtxt.text.toString()

            apx.alias = newAlias
            binding.aliasEtxt.text.clear()

            toast("Set Alias")
        }

        binding.getTag.setOnClickListener {

            val allTags: String = apx.tags.toString()
            val newStr = allTags.replace("[", "").replace("]", "")

            binding.tagEtxt.setText(newStr)
        }


        binding.setTag.setOnClickListener {

            val newTag = binding.tagEtxt.text.toString()

            apx.addTag(newTag)
            binding.tagEtxt.text.clear()

            toast("Set tag")
        }

        binding.removeTag.setOnClickListener {

            val tag = binding.removeTagEtxt.text.toString()

            apx.removeTag(tag)
            binding.removeTagEtxt.text.clear()

            toast("Removed tag")
        }

        binding.setNumField.setOnClickListener {

            val key: String = binding.numFieldKeyEtxt.text.toString()
            val value: String = binding.numFieldValueEtxt.text.toString()

            apx.setAttribute(key, value)
            binding.numFieldKeyEtxt.text.clear()
            binding.numFieldValueEtxt.text.clear()

            toast("Set numerical field")
        }

        binding.setStrField.setOnClickListener {

            val key: String = binding.strFieldKeyEtxt.text.toString()
            val value: String = binding.strFieldValueEtxt.text.toString()

            apx.setAttribute(key, value)
            binding.strFieldKeyEtxt.text.clear()
            binding.strFieldValueEtxt.text.clear()

            toast("Set string field")
        }

        binding.getAttributeValue.setOnClickListener{

            val key = binding.attributeValueEtxt.text.toString()

            val attributeValue = apx.getAttributeStringValue(key)
            binding.attributeValueEtxt.setText(attributeValue)
        }

        binding.getDeviceInfo.setOnClickListener {

            val deviceInfo: DeviceInfo = apx.deviceInfo

            val info = "App version:${deviceInfo.appVersion} \nDevice model:${deviceInfo.deviceModel} \nSdk version:${deviceInfo.sdkVersion} \n"

            binding.deviceInfoTxt.text = info
        }

        binding.getInApps.setOnClickListener {
            apx.triggerDMCCallInApp(
                this,
                "app_open"
            )
        }

    }

    override fun onInitCompleted(successful: Boolean, failReason: Exception?) {
        if (failReason != null) {
            failReason.message?.let { infoLog(it) }
        }
        else {
            infoLog("init completed listener - MainActivity")
            runOnUiThread {
                this.toast("init completed listener - MainActivity", Toast.LENGTH_SHORT)
                apx.triggerDMCCallInApp(this, "app_open")
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        handleRichPush(this,intent)
    }

}
