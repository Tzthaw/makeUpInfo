package com.ptut.appbase.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Vincent on 12/6/18
 */
@Singleton
class DaggerViewModelFactory @Inject constructor(
  private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    val creator = creators[modelClass] ?: creators.entries.firstOrNull {
      modelClass.isAssignableFrom(it.key)
    }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
    try {
      @Suppress("UNCHECKED_CAST")
      return creator.get() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }

  }
}