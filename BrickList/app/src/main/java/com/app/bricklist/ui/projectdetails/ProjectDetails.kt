package com.app.bricklist.ui.projectdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.bricklist.R

class ProjectDetails : Fragment() {

    companion object {
        fun newInstance() = ProjectDetails()
    }

    private lateinit var viewModel: ProjectDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.project_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProjectDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
