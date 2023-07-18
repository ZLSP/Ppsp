package com.zlsp.ppsphb.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.LocalThemeMode
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
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
}