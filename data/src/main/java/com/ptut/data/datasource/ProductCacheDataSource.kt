package com.ptut.data.datasource

import com.ptut.data.entity.ProductEntity

interface ProductCacheDataSource {
    fun putProduct(productList:List<ProductEntity>)
    fun getProduct():List<ProductEntity>?
    fun clearUser()
}