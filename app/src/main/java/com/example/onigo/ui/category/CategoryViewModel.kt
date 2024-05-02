package com.example.onigo.ui.category

import android.util.Log
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
                Log.d("##### response.body",response.body().toString()) //FIXME: remove
                response.body().let { res ->
                    categoriesData.postValue(Resource.Success(res))
                }
            } else {

                Log.d("##### response.message",response.message().toString()) //FIXME: remove
                categoriesData.postValue(Resource.Error(message = response.message()))
            }
        }
    }

}