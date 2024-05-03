package com.example.onigo.models

data class Category(
    val adult: Boolean,
    val amount: Int,
    val categories: List<String>,
    val idx: Int,
    val image: String,
    val imageIds: List<String>,
    val live: Boolean,
    val max: Any,
    val noDiscount: Boolean,
    val price: Int,
    val pricePerWeight: Any,
    val pricePerWeightUnit: Any,
    val priceWithTax: Double,
    val productId: String,
    val tags: List<String>,
    val tax: Double,
    val temperature: String,
    val title: String,
    val unit: String
)