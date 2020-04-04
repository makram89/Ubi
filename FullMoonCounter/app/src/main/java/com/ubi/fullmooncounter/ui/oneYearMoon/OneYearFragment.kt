package com.ubi.fullmooncounter.ui.oneYearMoon

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.ubi.fullmooncounter.R
import com.ubi.fullmooncounter.utils.Algorithms

class OneYearFragment : Fragment() {

    private lateinit var viewModel: OneYearViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_year_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val algorithms = Algorithms()

        viewModel = ViewModelProvider(this, OneYearViewModelFactory(algorithms)).get(
            OneYearViewModel::class.java)


    }

}
