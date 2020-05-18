package com.app.bricklist.ui.addproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.bricklist.data.AppRepository
import com.app.bricklist.data.network.BrickApi

@Suppress("UNCHECKED_CAST")
class AddProjectVMFactory(
    private val repository: AppRepository
) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddProjectViewModel(repository) as T
        }
    }
