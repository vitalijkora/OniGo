package com.example.onigo.data.api

import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCategories() =
        apiService.getCategories()
}