package com.ptut.network.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductByBrand(
	@Json(name = "website_link")
	val websiteLink: String? = null,
	@Json(name = "image_link")
	val imageLink: String? = null,
	val rating: Double? = null,
	val description: String? = null,
	@Json(name="created_at")
	val createdAt: String? = null,
	@Json(name="api_featured_image")
	val apiFeaturedImage: String? = null,
	@Json(name="product_type")
	val productType: String? = null,
	@Json(name="updated_at")
	val updatedAt: String? = null,
	val price: String? = null,
	val tagList: List<Any>? = null,
	val name: String? = null,
	val priceSign: Any? = null,
	val currency: Any? = null,
	val id: Int? = null,
	val category: Any? = null,
	val productColors: List<Any>? = null,
	val brand: String? = null,
	@Json(name="product_api_url")
	val productApiUrl: String? = null,
	@Json(name="product_link")
	val productLink: String? = null
)
