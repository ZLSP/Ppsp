package com.zlsp.ppsphb.di

import android.content.Context
import android.content.SharedPreferences
import com.zlsp.ppsphb.data.repository.main.MainRepositoryImpl
import com.zlsp.ppsphb.data.UserStorage
import com.zlsp.ppsphb.data.repository.main.MainRepository
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
    fun provideMainRepository(userStorage: UserStorage): MainRepository =
        MainRepositoryImpl(userStorage)
}