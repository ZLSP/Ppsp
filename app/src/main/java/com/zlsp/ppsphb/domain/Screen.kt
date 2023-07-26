package com.zlsp.ppsphb.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zlsp.ppsphb.R

enum class Screen(val route: String, @StringRes val nameRes: Int, @DrawableRes val iconRes: Int) {
    POLICE_ACT(
        route = "policeAct",
        nameRes = R.string.police_act_screen_name,
        iconRes = R.drawable.ic_tab_police_act
    ),
    GROUNDS(
        route = "grounds",
        nameRes = R.string.grounds_screen_name,
        iconRes = R.drawable.ic_tab_authority
    ),
    GUN(
        route = "gun",
        nameRes = R.string.gun_screen_name,
        iconRes = R.drawable.ic_tab_gun
    ),
    AUTHORITY(
        route = "authority",
        nameRes = R.string.authority_screen_name,
        iconRes = R.drawable.ic_tab_grounds
    ),
    MATERIALS(
        route = "materials",
        nameRes = R.string.materials_screen_name,
        iconRes = R.drawable.ic_tab_materials
    ),
}