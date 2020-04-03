package com.ubi.fullmooncounter.ui.currentMoonState

import java.time.LocalDateTime


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.ubi.fullmooncounter.R
import com.ubi.fullmooncounter.utils.Algorithms

class CurrentMoonStateFragment : Fragment() {

    private lateinit var viewModel: CurrentMoonStateViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_moon_state_frament_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val algorithms = Algorithms()

        viewModel = ViewModelProvider(this, CurrentMoonStateViewModelFactory(algorithms)).get(CurrentMoonStateViewModel::class.java)

        val currentDate = LocalDateTime.now()
//        viewModel.calculateState(currentDate.year, currentDate.monthValue, currentDate.dayOfMonth)

        val moonPhase = viewModel.getMoonPhase(currentDate.year, currentDate.monthValue, currentDate.dayOfMonth)
        Log.d("MoonPhase", moonPhase.toString())

    }




}
