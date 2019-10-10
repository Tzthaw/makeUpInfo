package com.ptut.makeupinfo.di

import android.app.Application
import com.ptut.makeupinfo.MakeUpApp
import com.ptut.makeupinfo.glide.PerformanceChecker
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import javax.inject.Singleton



@Singleton
@Component(
    modules =[AppModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder


        fun build(): AppComponent


    }

    fun inject(application: MakeUpApp)

}