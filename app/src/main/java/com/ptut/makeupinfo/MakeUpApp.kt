package com.ptut.makeupinfo

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import com.ptut.appbase.di.AppInjector
import com.ptut.makeupinfo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MakeUpApp:Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector:
            DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        AppInjector.initAutoInjection(this)


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            //Stetho.initializeWithDefaults(this)
        }
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }
}