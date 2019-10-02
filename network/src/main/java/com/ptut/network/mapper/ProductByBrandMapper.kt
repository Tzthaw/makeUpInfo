package com.ptut.network.mapper

import com.ptut.domain.mapper.UnidirectionalMap
import com.ptut.domain.model.ProductId
import com.ptut.domain.model.Products
import com.ptut.network.entity.ProductByBrand
import javax.inject.Inject

class ProductByBrandMapper @Inject constructor() :
    UnidirectionalMap<List<ProductByBrand>, List<Products>> {
    override fun map(item: List<ProductByBrand>): List<Products> {
        val dummyProducts = ArrayList<Products>()
        for (product in item) {
            val newProduct = Products(
                websiteLink = product.websiteLink ?: "",
                imageLink = product.imageLink ?: "",
                rating = product.rating ?: 0.0,
                description = product.description ?: "",
                createdAt = product.createdAt ?: "",
                apiFeaturedImage = product.apiFeaturedImage ?: "",
                productType = product.productType ?: "",
                updatedAt = product.updatedAt ?: "",
                price = product.price ?: "",
                tagList = product.tagList ?: emptyList(),
                name = product.name ?: "",
                id = ProductId(product.id!!.toLong()),
                priceSign = product.priceSign ?: "",
                currency = product.currency ?: "",
                category = product.category ?: "",
                productColors = product.productColors ?: emptyList(),
                brand = product.brand ?: "",
                productApiUrl = product.productApiUrl ?: "",
                productLink = product.productLink ?: ""
            )
            dummyProducts.add(newProduct)
        }

        return dummyProducts
    }
}