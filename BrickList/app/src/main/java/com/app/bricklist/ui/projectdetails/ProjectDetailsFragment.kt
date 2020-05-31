package com.app.bricklist.ui.projectdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.app.bricklist.R
import com.app.bricklist.data.AppRepository
import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.data.network.BrickApi
import com.app.bricklist.ui.models.Brick

class ProjectDetailsFragment : Fragment(), BrickListener {

    companion object {
        fun newInstance() = ProjectDetailsFragment()
    }

    private lateinit var viewModel: ProjectDetailsViewModel
    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: AppRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val url = preferences.getString("URL","")
        val api : BrickApi
        appDatabase = AppDatabase.getInstance(inflater.context)
        if ( url != null && url.isNotEmpty())
        {
            api = BrickApi(url as String)
            repository = AppRepository(appDatabase, api)
        }
        else{
            api = BrickApi(this.resources.getString(R.string.base_url))
            repository = AppRepository(appDatabase, api)
            Log.d("ERROR", "Incorrect URL, using default")
        }
        return inflater.inflate(R.layout.project_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(
                this,
                ProjectDetailsVMFactory(repository)
            ).get(ProjectDetailsViewModel::class.java)
        val projectID = arguments?.getInt("InventoryID")

        if (projectID != null) {
            viewModel.fetchBricks(projectID)
        }

        viewModel.liveBricks.observe(viewLifecycleOwner, Observer { bricks ->
            view?.findViewById<RecyclerView>(R.id.rv_brick_list).also {
                it?.adapter = BricksListRVAdapter(bricks, this@ProjectDetailsFragment)
                it?.layoutManager = LinearLayoutManager(requireContext())
            }
            Log.d("CHECKbricks", bricks.size.toString())
        })

    }

    override fun onPlusClick(brick: Brick) {

        if (brick.QuantityInStore < brick.QuantityInSet)
        {
            brick.QuantityInStore++
            valueUpdate(brick)
        }
    }

    override fun onMinusClick(brick: Brick) {

        if (brick.QuantityInStore > 0)
        {
            brick.QuantityInStore--
            valueUpdate(brick)
        }
    }

    override fun valueUpdate(brick: Brick) {
        viewModel.updateBrick(brick)

    }


}
