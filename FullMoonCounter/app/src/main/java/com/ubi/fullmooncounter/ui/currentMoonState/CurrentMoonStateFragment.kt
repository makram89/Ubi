package com.ubi.fullmooncounter.ui.currentMoonState

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.ubi.fullmooncounter.R

class CurrentMoonStateFragment : Fragment() {
companion object

    private lateinit var viewModel: CurrentMoonStateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_moon_state_frament_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProvider(this, CurrentMoonStateViewModelFactory()).get(CurrentMoonStateViewModel::class.java)


    }




}
