package com.zlsp.ppsphb.data.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdError
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestConfiguration
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import com.yandex.mobile.ads.interstitial.InterstitialAdLoadListener
import com.yandex.mobile.ads.interstitial.InterstitialAdLoader
import com.yandex.mobile.ads.rewarded.Reward
import com.yandex.mobile.ads.rewarded.RewardedAd
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener
import com.yandex.mobile.ads.rewarded.RewardedAdLoadListener
import com.yandex.mobile.ads.rewarded.RewardedAdLoader
import timber.log.Timber

object YandexAdsUtils {
    private const val YANDEX_MOBILE_ADS_TAG = "YandexMobileAds"

    private const val YANDEX_BANNER_TAG = "YandexBanner"
    private const val YANDEX_BANNER_ID = "R-M-1579265-1"

    private const val YANDEX_INTERSTITIAL_TAG = "YandexInterstitial"
    private const val YANDEX_INTERSTITIAL_ID = "R-M-1579265-3"

    private const val YANDEX_REWARDED_TAG = "YandexRewarded"
    private const val YANDEX_REWARDED_ID = "R-M-1579265-4"

    private val adRequest = AdRequest.Builder().build()
    //https://yandex.ru/support2/mobile-ads/ru/dev/android/interstitial
    private var interstitialAd: InterstitialAd? = null
    private var interstitialAdLoader: InterstitialAdLoader? = null

    //https://yandex.ru/support2/mobile-ads/ru/dev/android/rewarded
    private var rewardedAd: RewardedAd? = null
    private var rewardedAdLoader: RewardedAdLoader? = null

    fun initYandex(ctx: Context) {
        MobileAds.initialize(ctx) {
            Timber.tag(YANDEX_MOBILE_ADS_TAG).d("SDK initialized")
        }
    }

    fun loadFullScreenAd(activity: Activity) {
        loadInterstitial(activity)
        loadRewarded(activity)
    }

    // region BANNER
    fun initBanner(ctx: Context, banner: BannerAdView, width: Int) {
        bannerListener(banner)
        banner.apply {
            setAdSize(BannerAdSize.stickySize(ctx, width))
            setAdUnitId(YANDEX_BANNER_ID)
            loadAd(adRequest)
        }
    }

    private fun bannerListener(banner: BannerAdView) {
        banner.setBannerAdEventListener(object : BannerAdEventListener {
            override fun onAdLoaded() {
                Timber.tag(YANDEX_BANNER_TAG).d("onAdLoaded")
            }

            override fun onAdFailedToLoad(error: AdRequestError) {
                Timber.tag(YANDEX_BANNER_TAG).d("onAdFailedToLoad: $error")
            }

            override fun onAdClicked() {
                Timber.tag(YANDEX_BANNER_TAG).d("onAdClicked")
            }

            override fun onLeftApplication() {
                Timber.tag(YANDEX_BANNER_TAG).d("onLeftApplication")
            }

            override fun onReturnedToApplication() {
                Timber.tag(YANDEX_BANNER_TAG).d("onReturnedToApplication")
            }

            override fun onImpression(impressionData: ImpressionData?) {
                Timber.tag(YANDEX_BANNER_TAG).d("onImpression: ${impressionData?.rawData.toString()}")
            }

        })
    }
    // endregion
    // region INTERSTITIAL
    private fun loadInterstitial(activity: Activity) {
        interstitialAdLoader = InterstitialAdLoader(activity).apply {
            setAdLoadListener(object : InterstitialAdLoadListener {
                override fun onAdFailedToLoad(error: AdRequestError) {
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdFailedToLoad: ${error.description}")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdLoaded")
                    this@YandexAdsUtils.interstitialAd = interstitialAd
                }
            })
        }
        loadInterstitialAd()
    }

    fun showInterstitial(activity: Activity) {
        interstitialAdLoader = InterstitialAdLoader(activity).apply {
            setAdLoadListener(object : InterstitialAdLoadListener {
                override fun onAdFailedToLoad(error: AdRequestError) {
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdFailedToLoad: ${error.description}")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    this@YandexAdsUtils.interstitialAd = interstitialAd
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdLoaded")
                }
            })
        }

        interstitialAd?.apply {
            setAdEventListener(object : InterstitialAdEventListener{
                override fun onAdClicked() {
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdClicked")
                }

                override fun onAdDismissed() {
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdDismissed")
                    reloadInterstitialAd()
                }

                override fun onAdFailedToShow(adError: AdError) {
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdFailedToLoad: ${adError.description}")
                    reloadInterstitialAd()
                }

                override fun onAdImpression(impressionData: ImpressionData?) {
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onImpression: ${impressionData?.rawData.toString()}")
                }

                override fun onAdShown() {
                    Timber.tag(YANDEX_INTERSTITIAL_TAG).d("onAdShown")
                    FBAnalyticsUtils.logEvent(FBAnalyticsUtils.LOG_SHOW_AD_INTERSTITIAL)
                }

            })
            show(activity)
        }
    }
    private fun loadInterstitialAd() {
        val adRequestConfiguration = AdRequestConfiguration.Builder(YANDEX_INTERSTITIAL_ID).build()
        interstitialAdLoader?.loadAd(adRequestConfiguration)
    }

    private fun reloadInterstitialAd() {
        interstitialAd?.setAdEventListener(null)
        interstitialAd = null
        loadInterstitialAd()
    }
    // endregion
    // region REWARDED
    fun showRewarded(
        activity: Activity,
        onSuccessAction: () -> Unit,
        showContent: () -> Unit
    ) {
        rewardedAd?.apply {
            setAdEventListener(object : RewardedAdEventListener {
                override fun onAdClicked() {
                    Timber.tag(YANDEX_REWARDED_TAG).d("onAdClicked")
                }

                override fun onAdDismissed() {
                    showContent()
                    Timber.tag(YANDEX_REWARDED_TAG).d("onAdDismissed")
                    reloadRewardedAd()
                }

                override fun onAdFailedToShow(adError: AdError) {
                    showContent()
                    Toast.makeText(activity, "Повторите попытку позже", Toast.LENGTH_SHORT).show()
                    Timber.tag(YANDEX_REWARDED_TAG).d("onAdFailedToLoad: ${adError.description}")
                    reloadRewardedAd()
                }

                override fun onAdImpression(impressionData: ImpressionData?) {
                    Timber.tag(YANDEX_REWARDED_TAG).d(
                        "onImpression: ${impressionData?.rawData.toString()}"
                    )
                }

                override fun onAdShown() {
                    FBAnalyticsUtils.logEvent(FBAnalyticsUtils.LOG_SHOW_AD_REWARDED)
                    Timber.tag(YANDEX_REWARDED_TAG).d("onAdShown")
                }

                override fun onRewarded(reward: Reward) {
                    onSuccessAction()
                    showContent()
                    Timber.tag(YANDEX_REWARDED_TAG).d("onRewarded: ${reward.type} - ${reward.amount}")
                    reloadRewardedAd()
                }
            })
            show(activity)
        }
    }
    private fun loadRewarded(activity: Activity) {
        rewardedAdLoader = RewardedAdLoader(activity).apply {
            setAdLoadListener(object : RewardedAdLoadListener {
                override fun onAdLoaded(rewarded: RewardedAd) {
                    rewardedAd = rewarded
                }

                override fun onAdFailedToLoad(error: AdRequestError) {
                    Timber.tag(YANDEX_REWARDED_TAG).d("onAdFailedToLoad: ${error.description}")
                }
            })
        }
        loadRewardedAd()
    }

    private fun loadRewardedAd() {
        val adRequestConfiguration = AdRequestConfiguration.Builder(YANDEX_REWARDED_ID).build()
        rewardedAdLoader?.loadAd(adRequestConfiguration)
    }

    private fun reloadRewardedAd() {
        rewardedAd?.setAdEventListener(null)
        rewardedAd = null
        loadRewardedAd()
    }
    // endregion
}