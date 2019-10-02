package com.ptut.domain.repository

import com.ptut.domain.model.Products
import io.reactivex.Single

interface ProductRepository {
    fun getProductsByBrand(
        brandName:String
    ):Single<List<Products>>
}