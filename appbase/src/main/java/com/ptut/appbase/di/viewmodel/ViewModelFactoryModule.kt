package com.ptut.appbase.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.ptut.appbase.di.viewmodel.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 12/11/18
 */
@Module
abstract class ViewModelFactoryModule {

  @Binds
  abstract fun viewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}