package com.mohammad.bahadori.features.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammad.bahadori.base.models.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel:ViewModel() {
    fun <T>  (suspend ()->T).execute(block:(Resource<T>)->Unit){
        block(Resource.Loading(true))
        viewModelScope.launch {
            try {
                block(Resource.Success(invoke()))
            } catch (e: Exception) {
                block(Resource.Error(e))
            }
        }
    }
}