package com.example.onigo.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.onigo.data.api.ApiRepository
import com.example.onigo.models.CategoryResponse
import com.example.onigo.utils.Resource
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    val categoriesData: MutableLiveData<Resource<CategoryResponse>> = MutableLiveData()

    init {
        getCategory()
    }

    fun getCategory() = viewModelScope.launch {
        val response = repository.getCategory()
        repository.getCategory().let {
            if (response.isSuccessful) {
                response.body().let { res ->
                    categoriesData.postValue(Resource.Success(res))
                }
            } else {
                categoriesData.postValue(Resource.Error(message = response.message()))
            }
        }
    }

}