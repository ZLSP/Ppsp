package com.zlsp.ppsphb.data.repository.yandex

import com.zlsp.ppsphb.data.utils.UserStorage
import javax.inject.Inject

class YandexAdRepositoryImpl @Inject constructor(private val userStorage: UserStorage) :
    YandexAdRepository {
    override fun checkIsShowAd(): Boolean = userStorage.checkIsShowAd()
}