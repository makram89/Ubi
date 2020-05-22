package com.app.bricklist.ui.projectdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bricklist.data.AppRepository
import com.app.bricklist.ui.models.Brick

class ProjectDetailsViewModel(repository: AppRepository) : ViewModel() {

    private var bricksMutable = MutableLiveData<ArrayList<Brick>>()
    val liveBricks: LiveData<ArrayList<Brick>>
        get() = bricksMutable

    fun fetchBricks(id : Int)
    {

    }

}
