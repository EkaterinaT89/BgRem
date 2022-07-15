package com.bgrem.domain.utils

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.fragment.app.FragmentActivity

object MediaUtils {

    fun getRealPathFromUri(uri: Uri, requireActivity: FragmentActivity): String? {
        var cursor: Cursor? = null
        return try {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            cursor = requireActivity.contentResolver.query(uri, projection, null, null, null)
            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            cursor?.getString(columnIndex!!)
        } finally {
            cursor?.close()
        }
    }

}