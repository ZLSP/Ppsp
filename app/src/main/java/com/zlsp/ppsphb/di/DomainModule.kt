package com.zlsp.ppsphb.di

import android.content.Context
import android.content.SharedPreferences
import com.zlsp.ppsphb.data.network.ktor.KtorDataSource
import com.zlsp.ppsphb.data.repository.authority.AuthorityRepository
import com.zlsp.ppsphb.data.repository.authority.AuthorityRepositoryImpl
import com.zlsp.ppsphb.data.repository.grounds.GroundsRepository
import com.zlsp.ppsphb.data.repository.grounds.GroundsRepositoryImpl
import com.zlsp.ppsphb.data.repository.main.MainRepository
import com.zlsp.ppsphb.data.repository.main.MainRepositoryImpl
import com.zlsp.ppsphb.data.repository.materials.MaterialsRepository
import com.zlsp.ppsphb.data.repository.materials.MaterialsRepositoryImpl
import com.zlsp.ppsphb.data.repository.police_act.PoliceActRepository
import com.zlsp.ppsphb.data.repository.police_act.PoliceActRepositoryImpl
import com.zlsp.ppsphb.data.repository.theme.ThemeRepository
import com.zlsp.ppsphb.data.repository.theme.ThemeRepositoryImpl
import com.zlsp.ppsphb.data.repository.yandex.YandexAdRepository
import com.zlsp.ppsphb.data.repository.yandex.YandexAdRepositoryImpl
import com.zlsp.ppsphb.data.utils.AssetJsonReader
import com.zlsp.ppsphb.data.utils.UserStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideUserStorage(pref: SharedPreferences): UserStorage =
        UserStorage(pref)

    @Provides
    @Singleton
    fun provideThemeRepository(userStorage: UserStorage): ThemeRepository =
        ThemeRepositoryImpl(userStorage)

    @Provides
    @Singleton
    fun provideKtorDataSource() = KtorDataSource()

    @Provides
    @Singleton
    fun provideAssetJsonManager(@ApplicationContext context: Context) = AssetJsonReader(context)

    @Provides
    @Singleton
    fun provideMainRepository(
        dataSource: KtorDataSource,
        assetJsonReader: AssetJsonReader
    ): MainRepository =
        MainRepositoryImpl(dataSource, assetJsonReader)

    @Provides
    @Singleton
    fun providePoliceActRepository(mainRepository: MainRepository): PoliceActRepository =
        PoliceActRepositoryImpl(mainRepository)

    @Provides
    @Singleton
    fun provideGroundsRepository(mainRepository: MainRepository): GroundsRepository =
        GroundsRepositoryImpl(mainRepository)

    @Provides
    @Singleton
    fun provideAuthorityRepository(mainRepository: MainRepository): AuthorityRepository =
        AuthorityRepositoryImpl(mainRepository)

    @Provides
    @Singleton
    fun provideMaterialsRepository(mainRepository: MainRepository): MaterialsRepository =
        MaterialsRepositoryImpl(mainRepository)

    @Provides
    @Singleton
    fun provideYandexAdRepository(userStorage: UserStorage): YandexAdRepository =
        YandexAdRepositoryImpl(userStorage)
}