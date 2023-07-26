package com.zlsp.ppsphb.main

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yandex.mobile.ads.banner.BannerAdView
import com.zlsp.ppsphb.R
import com.zlsp.ppsphb.base.baseComposable
import com.zlsp.ppsphb.data.utils.FileDownload
import com.zlsp.ppsphb.data.utils.YandexAdsUtils
import com.zlsp.ppsphb.domain.Screen
import com.zlsp.ppsphb.ui.screens.authority.AuthorityScreen
import com.zlsp.ppsphb.ui.screens.authority.AuthorityScreenEffect
import com.zlsp.ppsphb.ui.screens.authority.AuthorityViewModel
import com.zlsp.ppsphb.ui.screens.grounds.GroundsScreen
import com.zlsp.ppsphb.ui.screens.grounds.GroundsScreenEffect
import com.zlsp.ppsphb.ui.screens.grounds.GroundsViewModel
import com.zlsp.ppsphb.ui.screens.gun.GunScreen
import com.zlsp.ppsphb.ui.screens.materials.MaterialsScreen
import com.zlsp.ppsphb.ui.screens.materials.MaterialsScreenEffect
import com.zlsp.ppsphb.ui.screens.materials.MaterialsScreenEvent
import com.zlsp.ppsphb.ui.screens.materials.MaterialsViewModel
import com.zlsp.ppsphb.ui.screens.police_act.PoliceActScreen
import com.zlsp.ppsphb.ui.screens.police_act.PoliceActScreenEffect
import com.zlsp.ppsphb.ui.screens.police_act.PoliceActViewModel
import com.zlsp.ppsphb.ui.theme.Theme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MainContent(
    navController: NavHostController = rememberNavController(),
    onClickThemeMode: () -> Unit,
    onClickTypoSize: () -> Unit
) {
    var isVisibleUiBars by remember { mutableStateOf(true) }
    Scaffold(
        backgroundColor = Theme.colors.background,
        topBar = {
            TopBar(
                navController = navController,
                isVisible = isVisibleUiBars,
                onClickThemeMode = onClickThemeMode,
                onClickTypoSize = onClickTypoSize,
                onClickFullScreen = { isVisibleUiBars = false }
            )
        },
        bottomBar = {
            Column(Modifier.fillMaxWidth()) {
                BottomBar(navController, isVisibleUiBars)
                YandexBanner()
            }
        },
        floatingActionButton = {
            FloatingButtons(
                isVisible = !isVisibleUiBars,
                onClickThemeMode = onClickThemeMode,
                onClickTypoSize = onClickTypoSize,
                onClickFullScreen = { isVisibleUiBars = true }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Box(Modifier.fillMaxSize()) {
            NavHost(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                startDestination = Screen.POLICE_ACT.route
            ) {
                baseComposable(
                    route = Screen.POLICE_ACT.route,
                    getViewModel = { hiltViewModel<PoliceActViewModel>() }
                ) { viewModel ->
                    val state = viewModel.collectAsState().value
                    val sendEvent = viewModel::sendEvent
                    val context = LocalContext.current
                    PoliceActScreen(state, isVisibleUiBars, sendEvent)
                    viewModel.collectSideEffect { effect ->
                        when (effect) {
                            PoliceActScreenEffect.ShowAd -> YandexAdsUtils.showInterstitial(context)
                        }
                    }
                }

                baseComposable(
                    route = Screen.GROUNDS.route,
                    getViewModel = { hiltViewModel<GroundsViewModel>() }
                ) { viewModel ->
                    val state = viewModel.collectAsState().value
                    val sendEvent = viewModel::sendEvent
                    val context = LocalContext.current
                    GroundsScreen(state, sendEvent)
                    viewModel.collectSideEffect { effect ->
                        when (effect) {
                            GroundsScreenEffect.ShowAd -> YandexAdsUtils.showInterstitial(context)
                        }
                    }
                }

                composable(Screen.GUN.route) {
                    GunScreen()
                }

                baseComposable(
                    route = Screen.AUTHORITY.route,
                    getViewModel = { hiltViewModel<AuthorityViewModel>() }
                ) { viewModel ->
                    val state = viewModel.collectAsState().value
                    val sendEvent = viewModel::sendEvent
                    val context = LocalContext.current
                    AuthorityScreen(state, sendEvent)
                    viewModel.collectSideEffect { effect ->
                        when (effect) {
                            AuthorityScreenEffect.ShowAd -> YandexAdsUtils.showInterstitial(context)
                        }
                    }
                }

                baseComposable(
                    route = Screen.MATERIALS.route,
                    getViewModel = { hiltViewModel<MaterialsViewModel>() }
                ) { viewModel ->
                    val state = viewModel.collectAsState().value
                    val sendEvent = viewModel::sendEvent
                    val context = LocalContext.current
                    MaterialsScreen(state, sendEvent)
                    viewModel.collectSideEffect { effect ->
                        when (effect) {
                            is MaterialsScreenEffect.ShowRewardedAd -> YandexAdsUtils.showRewarded(
                                ctx = context,
                                onSuccessAction = {
                                    FileDownload.download(
                                        url = effect.material.link,
                                        fileName = effect.material.title + ".docx",
                                        context = context
                                    )
                                },
                                showContent = { sendEvent(MaterialsScreenEvent.ShowContent) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun YandexBanner() {
    val widthDp = LocalConfiguration.current.screenWidthDp
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = {
            val banner = BannerAdView(it)
            YandexAdsUtils.initBanner(it, banner, widthDp)
            banner
        }
    )
}

@Composable
private fun BottomBar(navController: NavHostController, isVisible: Boolean) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(expandFrom = Alignment.Top),
        exit = shrinkVertically(shrinkTowards = Alignment.Top)
    ) {
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
}

@Composable
private fun TopBar(
    navController: NavHostController,
    isVisible: Boolean,
    onClickThemeMode: () -> Unit,
    onClickTypoSize: () -> Unit,
    onClickFullScreen: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val titleRes =
        Screen.values().firstOrNull { it.route == (currentDestination?.route ?: "") }?.nameRes
            ?: R.string.police_act_screen_name
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
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
                        style = Theme.typography.titleScreen
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
                    IconButton(onClick = onClickFullScreen) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_full_screen),
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
}

@Composable
private fun FloatingButtons(
    isVisible: Boolean,
    onClickThemeMode: () -> Unit,
    onClickTypoSize: () -> Unit,
    onClickFullScreen: () -> Unit
) {
    var isExpand by remember { mutableStateOf(false) }
    val animateAlpha by animateFloatAsState(if (isExpand) 1f else 0.5f)
    val rotationState by animateFloatAsState(targetValue = if (isExpand) 90f else -90f)
    if (isVisible) {
        BackHandler() {
            isExpand = false
            onClickFullScreen()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(Modifier.size(120.dp)) {
            FloatingBtn(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .rotate(rotationState)
                    .alpha(animateAlpha),
                iconRes = R.drawable.ic_arrow,
                animationEnter = fadeIn(),
                animationExit = fadeOut(),
                isVisible = isVisible,
                onClick = { isExpand = !isExpand }
            )

            FloatingBtn(
                modifier = Modifier.align(Alignment.BottomStart),
                iconRes = R.drawable.ic_mode_night,
                animationEnter = slideInHorizontally(initialOffsetX = { it }),
                animationExit = slideOutHorizontally(targetOffsetX = { it }),
                isVisible = isExpand,
                onClick = {
                    isExpand = !isExpand
                    onClickThemeMode()
                }
            )

            FloatingBtn(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.TopStart),
                iconRes = R.drawable.ic_normal_screen,
                animationEnter = slideIn(initialOffset = {
                    IntOffset(
                        x = it.width,
                        y = it.height
                    )
                }),
                animationExit = slideOut(targetOffset = {
                    IntOffset(
                        x = it.width,
                        y = it.height
                    )
                }),
                isVisible = isExpand,
                onClick = {
                    isExpand = !isExpand
                    onClickFullScreen()
                }
            )

            FloatingBtn(
                modifier = Modifier.align(Alignment.TopEnd),
                iconRes = R.drawable.ic_typo_size,
                animationEnter = slideInVertically(initialOffsetY = { it }),
                animationExit = slideOutVertically(targetOffsetY = { it }),
                isVisible = isExpand,
                onClick = onClickTypoSize
            )
        }
    }
}

@Composable
private fun FloatingBtn(
    modifier: Modifier,
    @DrawableRes iconRes: Int,
    animationEnter: EnterTransition,
    animationExit: ExitTransition,
    isVisible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = animationEnter + fadeIn(),
        exit = animationExit + fadeOut()
    ) {
        Button(
            modifier = Modifier
                .size(50.dp),
            onClick = onClick,
            shape = CircleShape,
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Theme.colors.onBackground,
                contentColor = Theme.colors.background
            ),
            contentPadding = PaddingValues()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(iconRes),
                contentDescription = null
            )
        }
    }
}

