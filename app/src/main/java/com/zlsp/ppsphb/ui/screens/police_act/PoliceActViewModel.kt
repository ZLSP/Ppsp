package com.zlsp.ppsphb.ui.screens.police_act

import com.zlsp.ppsphb.base.BaseViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container

class PoliceActViewModel :
    BaseViewModel<PoliceActScreenState, PoliceActScreenEffect, PoliceActScreenEvent>() {
    override val containerHost: Container<PoliceActScreenState, PoliceActScreenEffect> = container(
        PoliceActScreenState(
            emptyList(),
            0.0,
            emptyList()
        )
    )

    override fun sendEvent(event: PoliceActScreenEvent) {

    }
}