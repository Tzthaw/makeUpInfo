package com.ptut.network.dataSource

import com.ptut.data.datasource.ProductNetworkDataSource
import com.ptut.domain.model.Products
import com.ptut.network.mapper.ProductByBrandMapper
import com.ptut.network.service.SearchBrandService
import javax.inject.Inject

class ProductNetworkDataSourceRealImpl @Inject constructor(
    private val productService: SearchBrandService,
    private val productByBrandMapper: ProductByBrandMapper
) :ProductNetworkDataSource{
    override fun getProductByBrand(brandName: String): List<Products> {
        return productByBrandMapper.map(
            productService.getProductsByBrand(brandName).execute().body()!!
        )
    }
}