package com.zlsp.ppsphb.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zlsp.ppsphb.R
import com.zlsp.ppsphb.base.baseComposable
import com.zlsp.ppsphb.domain.Screen
import com.zlsp.ppsphb.ui.screens.police_act.PoliceActViewModel
import com.zlsp.ppsphb.ui.theme.AppTheme
import com.zlsp.ppsphb.ui.theme.Theme

@Composable
fun MainContent(
    navController: NavHostController = rememberNavController(),
    onClickThemeMode: () -> Unit,
    onClickTypoSize: () -> Unit
) {
    Scaffold(
        backgroundColor = Theme.colors.background,
        topBar = { TopBar(navController, onClickThemeMode, onClickTypoSize) },
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screen.POLICE_ACT.route
        ) {
            baseComposable(
                route = Screen.POLICE_ACT.route,
                getViewModel = { hiltViewModel<PoliceActViewModel>() }
            ) { state, sendEvent ->

            }

            baseComposable(
                route = Screen.AUTHORITY.route,
                getViewModel = { hiltViewModel<PoliceActViewModel>() }
            ) { state, sendEvent ->

            }

            baseComposable(
                route = Screen.GUN.route,
                getViewModel = { hiltViewModel<PoliceActViewModel>() }
            ) { state, sendEvent ->

            }

            baseComposable(
                route = Screen.GROUNDS.route,
                getViewModel = { hiltViewModel<PoliceActViewModel>() }
            ) { state, sendEvent ->

            }

            baseComposable(
                route = Screen.MATERIALS.route,
                getViewModel = { hiltViewModel<PoliceActViewModel>() }
            ) { state, sendEvent ->

            }
        }
    }
}

@Composable
private fun BottomBar(navController: NavHostController) {
    Column {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Theme.colors.secondary
        )
        BottomNavigation(
            backgroundColor = Theme.colors.background,
            contentColor = Theme.colors.onBackground,
            elevation = 20.dp
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val tabs = Screen.values()
            tabs.forEach { tab ->
                val isActiveTab =
                    currentDestination?.hierarchy?.any { it.route == tab.route } == true
                BottomNavigationItem(
                    selected = isActiveTab,
                    icon = { Icon(ImageVector.vectorResource(tab.iconRes), null) },
                    selectedContentColor = Theme.colors.onBackground,
                    unselectedContentColor = Theme.colors.onBackground.copy(0.5f),
                    onClick = {
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    navController: NavHostController,
    onClickThemeMode: () -> Unit,
    onClickTypoSize: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val titleRes =
        Screen.values().firstOrNull { it.route == (currentDestination?.route ?: "") }?.nameRes
            ?: R.string.police_act_screen_name
    Column {
        TopAppBar(
            backgroundColor = Theme.colors.background,
            contentPadding = PaddingValues(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(titleRes),
                    style = Theme.typography.title
                )
                IconButton(onClick = onClickThemeMode) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_mode_night),
                        contentDescription = null,
                        tint = Theme.colors.onBackground
                    )
                }
                IconButton(onClick = onClickTypoSize) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_typo_size),
                        contentDescription = null,
                        tint = Theme.colors.onBackground
                    )
                }
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Theme.colors.secondary
        )
    }
}

@Preview
@Composable
private fun PreviewTopBar() = AppTheme() {
    TopBar(
        navController = rememberNavController(),
        onClickThemeMode = {},
        onClickTypoSize = {}
    )
}
