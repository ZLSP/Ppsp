package com.zlsp.ppsphb.data.utils

import android.app.Activity
import android.app.AlertDialog
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.zlsp.ppsphb.R

object InAppUpdateUtils {
    private var appUpdateManager: AppUpdateManager? = null
    private var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>? = null
    private var updateStateListener: InstallStateUpdatedListener? = null

    fun initAppUpdateManager(activity: ComponentActivity) {
        appUpdateManager = AppUpdateManagerFactory.create(activity)
        activityResultLauncher = activity.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            if (it.resultCode != Activity.RESULT_OK) {
                Toast.makeText(
                    activity,
                    activity.getString(R.string.in_app_update_toast_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        updateStateListener = InstallStateUpdatedListener { state ->
            updateStatus(state.installStatus(), activity)
        }
    }

    fun checkForAppUpdates() {
        appUpdateManager?.run {
            appUpdateInfo.addOnSuccessListener { info ->
                if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && info.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                ) {
                    activityResultLauncher?.let { activityResult ->
                        startUpdateFlowForResult(
                            info,
                            activityResult,
                            AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE).build())
                    }
                }
            }

            updateStateListener?.let { registerListener(it) }
        }
    }

    private fun updateStatus(status: Int, activity: Activity) {
        println(status)
        if (status == InstallStatus.DOWNLOADED && !activity.isFinishing) {
            AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.in_app_update_alert_title))
                .setPositiveButton(activity.getString(R.string.in_app_update_alert_button_positive)) { _, _ ->
                    appUpdateManager?.completeUpdate()
                }
                .setCancelable(true)
                .create()
                .show()
        }
    }

    fun addUpdateSuccessListener(activity: Activity) {
        appUpdateManager?.appUpdateInfo?.run {
            addOnSuccessListener { info ->
                updateStatus(
                    status = info.installStatus(),
                    activity = activity
                )
            }
        }
    }

    fun unregisterUpdateListener() {
        updateStateListener?.let { appUpdateManager?.unregisterListener(it) }
    }
}