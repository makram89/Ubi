package com.app.bricklist.ui.addproject


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.app.bricklist.R
import com.app.bricklist.data.AppRepository
import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.data.models.Inventories
import com.app.bricklist.data.models.InventoriesParts
import com.app.bricklist.data.network.BrickApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_project_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import org.w3c.dom.Node
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
        val id = project_id_field.text.toString().toInt()
        viewModel.check(id)
    }

    fun onAddClick() {

        GlobalScope.launch {
            val projectName = name_field.text.toString()
            val project = Inventories(
                Active = 1,
                LastAccessed = System.currentTimeMillis(),
                Name = projectName,
                id = viewModel.counter() + 1
            )
            val id = viewModel.addProject(project).toInt()
            Log.d("success!!", id.toString())

            val dbFactory = DocumentBuilderFactory.newInstance()
            val dBuilder = dbFactory.newDocumentBuilder()
            val xmlInput = InputSource(StringReader(viewModel.xmlLive.value.toString()))
            val doc = dBuilder.parse(xmlInput)

            val element = doc.documentElement
            element.normalize()
            val nList = doc.getElementsByTagName("ITEM")


            var parts = viewModel.partsCounter()

            for (i in 0 until nList.length) {
                val itemNode: Node = nList.item(i)
                if (itemNode.nodeType == Node.ELEMENT_NODE) {
                    val elem = itemNode as Element
                    val children = elem.childNodes

                    var currentType: String? = null
                    var currentId: String? = null
                    var currentQty: String? = null
                    var currentColor: String? = null
                    var currentExtra: String? = null
                    var currentAltenate: String? = null

                    for (j in 0 until children.length) {
                        val node = children.item(j)
                        if (node is Element) {
                            when (node.nodeName) {
                                "ITEMTYPE" -> {
                                    currentType = node.textContent
                                }
                                "ITEMID" -> {
                                    currentId = node.textContent
                                }
                                "QTY" -> {
                                    currentQty = node.textContent
                                }
                                "COLOR" -> {
                                    currentColor = node.textContent
                                }
                                "EXTRA" -> {
                                    currentExtra = node.textContent
                                }
                                "ALTERNATE" -> {
                                    currentAltenate = node.textContent
                                }
                            }
                        }
                    }
                    if (currentAltenate.toString() == "N") {
                        val id_p = viewModel.addPart(
                            InventoriesParts(
                                currentColor.toString().toInt(),
                                currentExtra.toString(),
                                id,
                                currentId.toString(),
                                currentQty.toString().toInt(),
                                TypeID = currentType.toString(),
                                id = parts
                            )
                        )
//                        Log.d("PART loaded", id_p.toString())
                        parts++

                    } else {
                        Toast.makeText(
                            context,
                            "Problem with brick: %d, %s".format(currentId, currentColor),
                            Toast.LENGTH_LONG
                        ).show();

                    }

                }

            }
            findNavController().navigate(R.id.action_addProject_to_projectsList)
            Snackbar.make(super.requireView(), "Project sucesfully added", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
