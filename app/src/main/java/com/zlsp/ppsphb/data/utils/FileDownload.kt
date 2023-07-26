package com.zlsp.ppsphb.data.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import timber.log.Timber

object FileDownload {

    fun download(url: String, fileName: String, context: Context) {
        try {
            val downloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            DownloadManager.Request(Uri.parse(url))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                .setTitle(fileName)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(false)
                .also(downloadManager::enqueue)

            Toast.makeText(context, "Файл сохранен", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Timber.e(e)
            Toast.makeText(context, "Не удалось сохранить файл", Toast.LENGTH_LONG).show()
        }
    }
}