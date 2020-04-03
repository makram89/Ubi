package com.ubi.fullmooncounter.ui.currentMoonState

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class CurrentMoonStateViewModelFactory(): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentMoonStateViewModel() as T
    }
}