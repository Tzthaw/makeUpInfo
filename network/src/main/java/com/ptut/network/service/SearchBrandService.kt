package com.ptut.network.service

import com.ptut.network.entity.ProductByBrand
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchBrandService {
    @GET("products.json")
    fun getProductsByBrand(
        @Query("brand")brandName:String
    ): Call<List<ProductByBrand>>
}