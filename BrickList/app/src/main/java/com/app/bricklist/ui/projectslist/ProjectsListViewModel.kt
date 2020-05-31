package com.app.bricklist.ui.projectslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bricklist.data.AppRepository
import com.app.bricklist.data.models.Inventories
import com.app.bricklist.utils.Coroutines
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProjectsListViewModel(private val repository: AppRepository) : ViewModel() {


    private var projectsMutable = MutableLiveData<ArrayList<Inventories>>()
    val liveProjects: LiveData<ArrayList<Inventories>>
        get() = projectsMutable


    fun fetchProjects() {
        Coroutines.ioThenMain(
            {
                repository.getProjects()
            },
            {
                projectsMutable.value = it as ArrayList<Inventories>
            }
        )
    }

    fun fetchActiveProjects() {
        Coroutines.ioThenMain(
            {
                repository.getActiveProjects()
            },
            {
                projectsMutable.value = it as ArrayList<Inventories>
            }
        )
    }

    fun counter() {
        GlobalScope.launch {
            Log.d("CHECK CODES", repository.counter().toString())
        }
    }
}
