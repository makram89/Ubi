package com.ubi.fullmooncounter.ui.oneYearMoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ubi.fullmooncounter.utils.Algorithms

@Suppress("UNCHECKED_CAST")
class OneYearViewModelFactory(val algorithms: Algorithms): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OneYearViewModel(algorithms) as T
    }
}