package com.app.bricklist.ui.projectdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.project_details_fragment.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


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
        val url = preferences.getString("URL", "")
        val api: BrickApi
        appDatabase = AppDatabase.getInstance(inflater.context)
        if (url != null && url.isNotEmpty()) {
            api = BrickApi(url as String)
            repository = AppRepository(appDatabase, api)
        } else {
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
        save_button.setOnClickListener {
            onSave()
        }

        viewModel.liveBricks.observe(viewLifecycleOwner, Observer { bricks ->
            view?.findViewById<RecyclerView>(R.id.rv_brick_list).also {
                it?.adapter = BricksListRVAdapter(bricks, this@ProjectDetailsFragment)
                it?.layoutManager = LinearLayoutManager(requireContext())
            }

        })

        sort_by.setOnCheckedChangeListener { _, isChecked ->
            onChange(isChecked)
        }
    }


    override fun onPlusClick(brick: Brick) {

        if (brick.QuantityInStore < brick.QuantityInSet) {
            brick.QuantityInStore++
            valueUpdate(brick)
        }
    }

    override fun onMinusClick(brick: Brick) {

        if (brick.QuantityInStore > 0) {
            brick.QuantityInStore--
            valueUpdate(brick)
        }
    }

    override fun valueUpdate(brick: Brick) {
        viewModel.updateBrick(brick)

    }

    override fun onSave() {
        val bricks = viewModel.liveBricks.value as ArrayList<Brick>
        val docBuilder: DocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc: Document = docBuilder.newDocument()

        val mainElement: Element = doc.createElement("INVENTORY")

        for (brick in bricks) {
            if (brick.QuantityInStore == brick.QuantityInSet) {
                continue
            }
            val rootElement: Element = doc.createElement("ITEM")

            val itemType = doc.createElement("ITEMTYPE")
            itemType.appendChild((doc.createTextNode(brick.TypeID)))

            val itemID = doc.createElement("ITEMID")
            itemID.appendChild((doc.createTextNode(brick.ItemID)))

            val color = doc.createElement("COLOR")
            color.appendChild((doc.createTextNode(brick.ColorID.toString())))

            val qty = doc.createElement("QTYFILLED")
            qty.appendChild((doc.createTextNode((brick.QuantityInSet - brick.QuantityInStore).toString())))

            rootElement.appendChild(itemType)
            rootElement.appendChild(itemID)
            rootElement.appendChild(color)
            rootElement.appendChild(qty)
            mainElement.appendChild(rootElement)
        }

        doc.appendChild(mainElement)
        val fileName = "project" + bricks[0].InventoryID.toString() + ".xml"
        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")

//        val file = File(Environment.getExternalStorageDirectory(), fileName)
        val outDir = File("/storage/emulated/0/Android/data", "com.app.bricklist")
        outDir.mkdir()
        val file = File( outDir, fileName)


        transformer.transform(DOMSource(doc), StreamResult(file))
//        val uri: Uri = Uri.fromFile(file)
        val uri: Uri = FileProvider.getUriForFile(
            requireContext(),
            requireContext().applicationContext.packageName.toString() + ".provider",
            file
        )

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            putExtra(Intent.EXTRA_STREAM, uri)
        }
        startActivity(Intent.createChooser(emailIntent, "Send email..."))


        Snackbar.make(
            super.requireView(),
            "Missing bricks also at: %s".format(file.canonicalPath),
            Snackbar.LENGTH_LONG
        )
            .setAction("Action", null)
            .setDuration(12000)
            .show()

    }

    override fun onChange(checked: Boolean) {

        viewModel.sortData(checked)

        viewModel.liveBricks.observe(viewLifecycleOwner, Observer { bricks ->
            view?.findViewById<RecyclerView>(R.id.rv_brick_list).also {
                it?.adapter = BricksListRVAdapter(bricks, this@ProjectDetailsFragment)
                it?.layoutManager = LinearLayoutManager(requireContext())
            }

        })
    }


}
