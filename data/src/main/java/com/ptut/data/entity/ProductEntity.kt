package com.ptut.data.entity

data class ProductEntity(
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
    var id: Int,
    var category: Any,
    var productColors: List<Any>,
    var brand: String,
    var productApiUrl: String,
    var productLink: String
)
