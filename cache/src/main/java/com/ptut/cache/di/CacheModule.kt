package com.ptut.cache.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.ptut.cache.ProductByBrandDatabase
import com.ptut.cache.datasource.ProductCacheDataSourceRealImpl
import com.ptut.cache.entity.ProductWithBrand
import com.ptut.data.datasource.ProductCacheDataSource
import com.ptut.domain.model.ProductId
import com.ptut.domain.model.Products
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [CacheModule.Providers::class])
abstract class CacheModule {
    @Binds
    abstract fun productCacheDataSource(
        productCacheDataSourceImpl: ProductCacheDataSourceRealImpl
    ):ProductCacheDataSource

    @Module
    internal object Providers{

        @Provides
        @JvmStatic
        @Singleton
        fun sqlDriver(context: Context):SqlDriver{
            return AndroidSqliteDriver(
                ProductByBrandDatabase.Schema,
                context,
                "productByBrand.db"
            )
        }

        @Provides
        @Singleton
        @JvmStatic
        fun database(sqlDriver: SqlDriver):ProductByBrandDatabase{
            val productIdAdapter=object :ColumnAdapter<ProductId,Long>{
                override fun decode(databaseValue: Long): ProductId {
                    return ProductId(databaseValue)
                }

                override fun encode(value: ProductId): Long {
                    return value.value
                }
            }
            return ProductByBrandDatabase(
                sqlDriver,
                ProductWithBrand.Adapter(
                    productIdAdapter)
            )
        }

        @JvmStatic
        @Provides
        fun sharedPref(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }
    }
}