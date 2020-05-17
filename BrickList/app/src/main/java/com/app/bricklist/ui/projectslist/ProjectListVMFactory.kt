package com.app.bricklist.ui.projectslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.bricklist.data.AppRepository


@Suppress("UNCHECKED_CAST")
class ProjectListVMFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectsListViewModel(repository) as T
    }
}
