package com.example.onigo.data.api

import com.example.onigo.models.Product
import com.example.onigo.utils.Constants.Companion.CATEGORY_URL
import com.example.onigo.utils.Constants.Companion.DEFAULT_CATEGORY
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(CATEGORY_URL + DEFAULT_CATEGORY)

    suspend fun getCategories():Response<Map<String, List<Product>>>
}