package com.zlsp.ppsphb.di

import android.content.Context
import android.content.SharedPreferences
import com.zlsp.ppsphb.data.network.ktor.KtorDataSource
import com.zlsp.ppsphb.data.repository.main.MainRepository
import com.zlsp.ppsphb.data.repository.main.MainRepositoryImpl
import com.zlsp.ppsphb.data.repository.police_act.PoliceActRepository
import com.zlsp.ppsphb.data.repository.police_act.PoliceActRepositoryImpl
import com.zlsp.ppsphb.data.repository.theme.ThemeRepositoryImpl
import com.zlsp.ppsphb.data.utils.UserStorage
import com.zlsp.ppsphb.data.repository.theme.ThemeRepository
import com.zlsp.ppsphb.data.utils.AssetJsonReader
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
    fun provideMainRepository(dataSource: KtorDataSource, assetJsonReader: AssetJsonReader): MainRepository =
        MainRepositoryImpl(dataSource, assetJsonReader)

    @Provides
    @Singleton
    fun providePoliceActRepository(mainRepository: MainRepository): PoliceActRepository =
        PoliceActRepositoryImpl(mainRepository)
}