package com.ubi.fullmooncounter.ui.oneYearMoon

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubi.fullmooncounter.R
import kotlinx.android.synthetic.main.recycler_view_date_item.view.*

class DateItemsAdapter(private var dataset: ArrayList<String>) :
    RecyclerView.Adapter<DateItemsAdapter.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_date_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(dataset[position])
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) {
            itemView.date_filed.text = item
        }

    }


}