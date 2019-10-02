package com.ptut.domain.model

data class Products (
    var websiteLink: String,
    var imageLink: String,
    var rating: Double,
    var description: String,
    var createdAt: String,
    var apiFeaturedImage: String,
    var productType: String,
    var updatedAt: String,
    var price: String,
    var tagList: List<Any>,
    var name: String,
    var priceSign: Any,
    var currency: Any,
    var id:ProductId,
    var category: Any,
    var productColors: List<Any>,
    var brand: String,
    var productApiUrl: String,
    var productLink: String
)

inline class ProductId (val value:Long)

