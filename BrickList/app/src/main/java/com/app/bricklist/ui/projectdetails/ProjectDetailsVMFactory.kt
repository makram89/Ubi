package com.app.bricklist.ui.projectdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.bricklist.data.AppRepository


@Suppress("UNCHECKED_CAST")
class ProjectDetailsVMFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectDetailsViewModel(repository) as T
    }
}
