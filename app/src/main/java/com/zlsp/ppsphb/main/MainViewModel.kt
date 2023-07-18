package com.zlsp.ppsphb.main

import com.zlsp.ppsphb.base.BaseViewModel
import com.zlsp.ppsphb.data.repository.theme.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val themeRepository: ThemeRepository) :
    BaseViewModel<MainActivityState, MainActivityEffect, MainActivityEvent>() {
    override val containerHost: Container<MainActivityState, MainActivityEffect> = container(
        MainActivityState(
            isDarkMode = themeRepository.getIsDarkMode(),
            typoSize = themeRepository.getTypoSize()
        )
    )

    override fun sendEvent(event: MainActivityEvent) {
        when(event) {
            MainActivityEvent.OnClickTypoSize -> themeRepository.updateTypoSize()
            MainActivityEvent.OnClickThemeMode -> themeRepository.updateThemeMode()
        }
    }
}