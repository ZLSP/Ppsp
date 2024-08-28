package com.zlsp.ppsphb.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlsp.ppsphb.data.utils.FBAnalyticsUtils
import com.zlsp.ppsphb.data.utils.InAppUpdateUtils
import com.zlsp.ppsphb.data.utils.YandexAdsUtils
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.LocalThemeMode
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FBAnalyticsUtils.initFB()
        super.onCreate(savedInstanceState)
        InAppUpdateUtils.initAppUpdateManager(this)
        InAppUpdateUtils.checkForAppUpdates()
        YandexAdsUtils.loadFullScreenAd(this)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val state = viewModel.collectAsState().value
            val sendEvent = viewModel::sendEvent
            AppTheme(
                isDarkMode = state.isDarkMode,
                typoSize = state.typoSize
            ) {
                val themeModeHandler = LocalThemeMode.current
                MainContent(
                    onClickThemeMode = {
                        themeModeHandler.updateTheme()
                        sendEvent(MainActivityEvent.OnClickThemeMode)
                    },
                    onClickTypoSize = {
                        themeModeHandler.updateTypographySize()
                        sendEvent(MainActivityEvent.OnClickTypoSize)
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        InAppUpdateUtils.addUpdateSuccessListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        InAppUpdateUtils.unregisterUpdateListener()
    }
}