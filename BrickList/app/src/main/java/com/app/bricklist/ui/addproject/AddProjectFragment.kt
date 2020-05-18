package com.app.bricklist.ui.addproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.app.bricklist.R
import com.app.bricklist.data.AppRepository
import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.data.network.BrickApi
import kotlinx.android.synthetic.main.add_project_fragment.*
import org.json.JSONObject
import org.json.XML
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory


class AddProjectFragment : Fragment() {


    private lateinit var viewModel: AddProjectViewModel
    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: AppRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appDatabase = AppDatabase.getInstance(inflater.context)
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        val url = preferences.getString("URL", "")
        val api: BrickApi
        if (url != null && url.isNotEmpty()) {
            api = BrickApi(url as String)
            repository = AppRepository(appDatabase, api)
        } else {
            api = BrickApi(this.resources.getString(R.string.base_url))
            repository = AppRepository(appDatabase, api)
            Log.d("ERROR", "Incorrect URL, using default")
        }


        return inflater.inflate(R.layout.add_project_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProvider(
                this,
                AddProjectVMFactory(repository)
            ).get(AddProjectViewModel::class.java)

        check_button.setOnClickListener { onCheckClick() }
        add_button.setOnClickListener { onAddClick() }

        viewModel.responseLiveCode.observe(viewLifecycleOwner, Observer {
            add_button.isEnabled = it in 200..299
            project_id_field.isEnabled = it !in 200..299
        })

    }

    fun onCheckClick() {
        project_id_field.isEnabled = false
        val id =project_id_field.text.toString().toInt()
        viewModel.check(id)
    }

    fun onAddClick(){
//        well now succky things


        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val xmlInput = InputSource(StringReader(viewModel.xmlLive.value.toString()))
        val doc = dBuilder.parse(xmlInput)

        val element = doc.documentElement
        element.normalize()
        val nList = doc.getElementsByTagName("ITEM")
//        for (i in 0 until nList.length) {
//            val node = nList.item(i)
//
//        }
        println(nList.length)
        val sampleXml = viewModel.xmlLive.value.toString()
        var jsonObj: JSONObject? = null
        try {
            jsonObj = XML.toJSONObject(sampleXml)
        } catch (e: Throwable) {
            Log.e("JSON exception", e.message)
            e.printStackTrace()
        }

//        Log.d("XML", sampleXml)

        Log.d("JSON", jsonObj.toString())

    }

}
