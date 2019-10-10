package com.ptut.makeupinfo

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import com.ptut.appbase.di.AppInjector
import com.ptut.makeupinfo.di.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MakeUpApp:Application(), HasActivityInjector {

    companion object {
        lateinit var component: AppGlideComponent
    }

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
                .build())

        createComponent()


        DaggerAppComponent.builder()
            .application(this)
            .build().inject(this)

        component.inject(this)

        AppInjector.initAutoInjection(this)


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            //Stetho.initializeWithDefaults(this)
        }



    }

    private fun createComponent() {
        component = DaggerAppGlideComponent
            .builder()
            .appGlideModule(AppGlideModule(this))
            .build()
    }
}