package com.ptut.appbase.di.module

import com.ptut.cache.di.CacheModule
import com.ptut.data.repositoryImpl.ProductRepositoryRealImpl
import com.ptut.domain.repository.ProductRepository
import com.ptut.network.di.NetworkModule
import dagger.Binds
import dagger.Module

/**
 * Created by Vincent on 12/6/18
 */
@Module(includes = [CacheModule::class,NetworkModule::class])
abstract class RepositoryModule {

  @Binds
  abstract fun searchByBrandProducts(
    productRepositoryRealImpl: ProductRepositoryRealImpl
  ) :ProductRepository

}