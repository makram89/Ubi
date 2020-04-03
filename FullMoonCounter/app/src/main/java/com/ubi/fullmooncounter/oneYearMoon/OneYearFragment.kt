package com.ubi.fullmooncounter.oneYearMoon

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ubi.fullmooncounter.R

class OneYearFragment : Fragment() {

    companion object {
        fun newInstance() = OneYearFragment()
    }

    private lateinit var viewModel: OneYearViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_year_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OneYearViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
