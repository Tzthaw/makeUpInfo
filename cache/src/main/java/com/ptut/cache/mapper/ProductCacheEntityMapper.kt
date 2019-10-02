package com.ptut.cache.mapper

import com.ptut.cache.entity.ProductCacheEntity
import com.ptut.data.entity.ProductEntity
import com.ptut.domain.mapper.BidirectionalMap
import javax.inject.Inject

class ProductCacheEntityMapper @Inject constructor() :
    BidirectionalMap<ProductCacheEntity, ProductEntity> {
    override fun map(item: ProductCacheEntity): ProductEntity {
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
            tagList = item.tagList,
            name = item.name,
            priceSign = item.priceSign,
            currency = item.currency,
            id = item.id,
            category = item.category,
            productColors = item.productColors,
            brand = item.brand,
            productApiUrl = item.productApiUrl,
            productLink = item.productLink
        )
    }

    override fun reverseMap(item: ProductEntity): ProductCacheEntity {
        return ProductCacheEntity(
            websiteLink = item.websiteLink,
            imageLink = item.imageLink,
            rating = item.rating,
            description = item.description,
            createdAt = item.createdAt,
            apiFeaturedImage = item.apiFeaturedImage,
            productType = item.productType,
            updatedAt = item.updatedAt,
            price = item.price,
            tagList = item.tagList,
            name = item.name,
            priceSign = item.priceSign,
            currency = item.currency,
            id = item.id,
            category = item.category,
            productColors = item.productColors,
            brand = item.brand,
            productApiUrl = item.productApiUrl,
            productLink = item.productLink
        )
    }
}