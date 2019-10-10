package com.ptut.makeupinfo.di

import android.app.ActivityManager
import android.content.Context
import com.ptut.appbase.di.module.BaseAppModule
import com.ptut.makeupinfo.BuildConfig
import com.ptut.makeupinfo.feature.di.MainFeatureModule
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module(
    includes = [
    BaseAppModule::class,
    MainFeatureModule::class])
abstract class AppModule {

}