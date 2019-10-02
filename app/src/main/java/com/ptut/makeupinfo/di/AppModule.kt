package com.ptut.makeupinfo.di

import com.ptut.appbase.di.module.BaseAppModule
import com.ptut.makeupinfo.feature.di.MainFeatureModule
import dagger.Module

@Module(
    includes = [
    BaseAppModule::class,
    MainFeatureModule::class])
abstract class AppModule {

}