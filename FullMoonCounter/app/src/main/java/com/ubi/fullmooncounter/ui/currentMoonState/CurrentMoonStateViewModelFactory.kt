package com.ubi.fullmooncounter.ui.currentMoonState

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ubi.fullmooncounter.utils.Algorithms

@Suppress("UNCHECKED_CAST")
class CurrentMoonStateViewModelFactory(val algorithms: Algorithms): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentMoonStateViewModel(algorithms) as T
    }
}