package com.example.onigo.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.onigo.data.api.ApiRepository
import com.example.onigo.models.Product
import com.example.onigo.utils.Resource
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    val categoriesData: MutableLiveData<Resource<Map<String, List<Product>>>> = MutableLiveData()

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        repository.getCategories().let {
            if (it.isSuccessful) {
                it.body().let { res ->
                    categoriesData.postValue(Resource.Success(res))
                }
            } else {
                categoriesData.postValue(Resource.Error(message = it.message()))
            }
        }
    }
}