package com.ptut.appbase.di.module

import android.app.Application
import android.content.Context
import com.ptut.appbase.di.viewmodel.ViewModelFactoryModule
import com.ptut.appbase.exception.GenericErrorMessageFactoryRealImpl
import com.ptut.appbase.exception.NetworkExceptionMessageFactoryRealImpl
import com.ptut.domain.executor.PostExecutionThread
import com.ptut.domain.executor.ThreadExecutor
import com.ptut.appbase.UIThread
import com.ptut.data.JobExecutor
import com.ptut.domain.exception.GenericErrorMessageFactory
import com.ptut.network.exception.NetworkExceptionMessageFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [BaseAppModule.Provider::class, ViewModelFactoryModule::class, RepositoryModule::class])
abstract class BaseAppModule {

  @Binds
  abstract fun threadExecutor(jobExecutor: JobExecutor): ThreadExecutor

  @Binds
  abstract fun postExecutionThread(uiThread: UIThread): PostExecutionThread

  @Binds
  abstract fun genericErrorMessageFactory(genericErrorMessageFactory: GenericErrorMessageFactoryRealImpl): GenericErrorMessageFactory

  @Binds
  abstract fun networkErrorMessageFactory(networkExceptionMessageFactory: NetworkExceptionMessageFactoryRealImpl): NetworkExceptionMessageFactory

  @Module
  object Provider {

    @Provides @JvmStatic @Singleton fun context(application: Application): Context {
      return application.applicationContext
    }

  }

}

