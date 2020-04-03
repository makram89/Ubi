package com.ubi.fullmooncounter.currentMoonState

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ubi.fullmooncounter.R

class CurrentMoonStateFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentMoonStateFragment()
    }

    private lateinit var viewModel: CurrentMoonStateFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_moon_state_frament_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentMoonStateFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
