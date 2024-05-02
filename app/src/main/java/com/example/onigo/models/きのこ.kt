package com.example.onigo.models

data class きのこ(
    val adult: Boolean,
    val amount: Int,
    val categories: List<String>,
    val idx: Int,
    val image: String,
    val imageIds: List<String>,
    val live: Boolean,
    val max: Int,
    val noDiscount: Boolean,
    val price: Int,
    val pricePerWeight: Any,
    val pricePerWeightUnit: Any,
    val priceWithTax: Double,
    val productId: String,
    val tags: List<Any>,
    val tax: Double,
    val temperature: String,
    val title: String,
    val unit: String
)