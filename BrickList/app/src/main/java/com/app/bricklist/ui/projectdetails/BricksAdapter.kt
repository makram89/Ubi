package com.app.bricklist.ui.projectdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.bricklist.R
import com.app.bricklist.databinding.RvItemBrickBinding
import com.app.bricklist.ui.models.Brick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item_brick.view.*

class BricksListRVAdapter(
    private var bricksDataset: ArrayList<Brick>,
    private val listener: BrickListener
) :
    RecyclerView.Adapter<BricksListRVAdapter.BrickItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrickItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemBrickBinding.inflate(inflater, parent, false)

        return BrickItemHolder(binding)
    }

    override fun getItemCount() = bricksDataset.size


    override fun onBindViewHolder(holder: BrickItemHolder, position: Int) {
        holder.bind(bricksDataset[position], listener)
    }


    class BrickItemHolder(private val binding: RvItemBrickBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Brick, listener: BrickListener) {
            binding.brick = item
            binding.executePendingBindings()
            binding.root.add_part_in_store.setOnClickListener {
                listener.onPlusClick(item)
                binding.brick = item
                binding.executePendingBindings()
            }
            binding.root.del_part_in_store.setOnClickListener {
                listener.onMinusClick(item)
                binding.brick = item
                binding.executePendingBindings()

            }

            var url = "https://www.lego.com"
            if (item.code != null && item.code != 0) {
                url = "https://www.lego.com/service/bricks/5/2/%d".format(item.code)
            } else if (item.ColorID != 0) {
                url = "http://img.bricklink.com/P/%d/%s.gif".format(item.ColorID, item.ItemID)

            } else {
                url = "https://www.bricklink.com/PL/%s.jpg".format(item.ItemID)
            }

            val ivThumb: ImageView = itemView.findViewById(R.id.iv_thumb)

            Picasso.get()
                .load(url)
                .error(R.drawable.no_image)
                .into(ivThumb)

        }
    }


}