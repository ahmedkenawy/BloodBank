package com.a7medkenawy.bloodbank.utils

import android.app.Activity
import android.net.Uri
import android.webkit.MimeTypeMap
import com.facebook.CallbackManager

object Constants {
    val callBackManager= CallbackManager.Factory.create()!!
    const val male="male"
    const val female="female"
    const val RESULT_LOAD_IMG=555
    const val USERS="users"

    fun getImageExtension(activity: Activity, ImageUri: Uri): String {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(ImageUri))!!
    }
}