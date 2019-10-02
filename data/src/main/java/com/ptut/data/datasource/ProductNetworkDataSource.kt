package com.ptut.data.datasource

import com.ptut.domain.model.Products
import io.reactivex.Single

interface ProductNetworkDataSource {
    fun getProductByBrand(brandName:String): List<Products>
}