package com.ptut.cache.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.ptut.cache.ProductByBrandDatabase
import com.ptut.cache.entity.ProductCacheEntity
import com.ptut.cache.entity.ProductWithBrand
import com.ptut.cache.mapper.ProductCacheEntityMapper
import com.ptut.data.datasource.ProductCacheDataSource
import com.ptut.data.entity.ProductEntity
import com.ptut.domain.model.ProductId
import com.squareup.moshi.Moshi
import javax.inject.Inject

class ProductCacheDataSourceRealImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val database: ProductByBrandDatabase,
    private val productCacheEntityMapper: ProductCacheEntityMapper
):ProductCacheDataSource{

    companion object {
        private const val PREF_KEY_PRODUCT = "PRODUCT"
    }

    private val moshi=Moshi.Builder().build()


    override fun putProduct(productEntity: List<ProductEntity>) {

      database.transaction {
          productEntity.forEach{
              item->
              database.productByBrandQueries.insert_or_replace(
                  websiteLink = item.websiteLink,
                  imageLink = item.imageLink,
                  rating = item.rating,
                  description = item.description,
                  createdAt = item.createdAt,
                  apiFeaturedImage = item.apiFeaturedImage,
                  productType = item.productType,
                  updatedAt = item.updatedAt,
                  price = item.price,
                  name = item.name,
                  id = ProductId(item.id.toLong()),
                  brand = item.brand,
                  productApiUrl = item.productApiUrl,
                  productLink = item.productLink
              )
          }
      }
    }

    override fun getProduct(): List<ProductEntity> {
        return database.productByBrandQueries
            .select_all()
            .executeAsList()
            .map(::mapRequestToProduct)
    }

    override fun clearUser() {
        sharedPreferences.edit {
            remove(PREF_KEY_PRODUCT)
        }
    }

    fun mapRequestToProduct(item:ProductWithBrand):ProductEntity{
        val product=database.productByBrandQueries.select_by_id(item.id).executeAsOne()
        return ProductEntity(
            websiteLink = item.websiteLink,
            imageLink = item.imageLink,
            rating = item.rating,
            description = item.description,
            createdAt = item.createdAt,
            apiFeaturedImage = item.apiFeaturedImage,
            productType = item.productType,
            updatedAt = item.updatedAt,
            price = item.price,
            tagList = emptyList(),
            name = item.name,
            priceSign = "",
            currency = "",
            id = item.id.value.toInt(),
            category ="",
            productColors = emptyList(),
            brand = item.brand,
            productApiUrl = item.productApiUrl,
            productLink = item.productLink
        )
    }

}