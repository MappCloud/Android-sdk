package mapp.android.android_demo_app

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by Aleksandar Marinkovic on 19/03/2020.
 * Copyright (c) 2020 MAPP.
 */
const val TAG: String = "APX"

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


fun Context.infoLog(message: String) {
    Log.i(TAG, message)
}

fun Context.debugLog(message: String) {
    Log.d(TAG, message)
}