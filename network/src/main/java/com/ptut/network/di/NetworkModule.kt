package com.ptut.network.di

import com.ptut.data.datasource.ProductNetworkDataSource
import com.ptut.network.BuildConfig
import com.ptut.network.dataSource.ProductNetworkDataSourceRealImpl
import com.ptut.network.exception.NetworkExceptionInterceptor
import com.ptut.network.service.SearchBrandService
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule.Providers::class])
abstract class NetworkModule {
    @Binds
    abstract fun newsDataSource(
        newsNetworkDataSource: ProductNetworkDataSourceRealImpl
    ):ProductNetworkDataSource

    @Module
    internal object Providers{
        @JvmStatic
        @Provides
        @Singleton
        fun okHttpClient(): OkHttpClient {
            val builder= OkHttpClient.Builder()
            val loggerInterceptor= HttpLoggingInterceptor()
            when(BuildConfig.DEBUG){
                true->loggerInterceptor.level= HttpLoggingInterceptor.Level.BODY
                false-> loggerInterceptor.level= HttpLoggingInterceptor.Level.NONE
            }
            val networkExceptionInterceptor= NetworkExceptionInterceptor()
            builder.addInterceptor(loggerInterceptor)
                .addInterceptor(networkExceptionInterceptor)
            return builder.build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun retrofit(okHttpClient: OkHttpClient): Retrofit {
            val builder= Retrofit.Builder()
            val moshi= Moshi.Builder().build()
            builder.baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))

            return builder.build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun productService(retrofit: Retrofit):SearchBrandService{
            return retrofit.create(SearchBrandService::class.java)
        }
    }

}