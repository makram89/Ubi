package com.ubi.fullmooncounter.ui.currentMoonState

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubi.fullmooncounter.utils.Algorithms
import kotlinx.coroutines.*


class CurrentMoonStateViewModel(val algorithms: Algorithms) : ViewModel() {

    val stateOfMoon = MutableLiveData<Double>()

    private lateinit var job: Job

//TODO choose algorithm

    fun calculateState(year :Int, month : Int, day:Int) {
        GlobalScope.launch {
            stateOfMoon.value = algorithms.Trig2(year, month, day)
        }
    }

    fun getMoonPhase(year :Int, month : Int, day:Int) : Double
    {
        return algorithms.Trig2(year, month, day)
    }


}
