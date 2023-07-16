package com.zlsp.ppsphb.main

import com.zlsp.ppsphb.base.BaseViewModel
import com.zlsp.ppsphb.data.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel<MainActivityState, MainActivityEffect, MainActivityEvent>() {
    override val containerHost: Container<MainActivityState, MainActivityEffect> = container(
        MainActivityState(
            isDarkMode = mainRepository.getIsDarkMode(),
            typoSize = mainRepository.getTypoSize()
        )
    )

    override fun sendEvent(event: MainActivityEvent) {
        when(event) {
            MainActivityEvent.OnClickTypoSize -> mainRepository.updateTypoSize()
            MainActivityEvent.OnClickThemeMode -> mainRepository.updateThemeMode()
        }
    }
}