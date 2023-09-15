package com.best.toplanguage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.best.toplanguage.DetailActivity.Companion.KEY_COLLECTION

class ListCollectionAdapter(private val listCollection: ArrayList<Collection>) :
    RecyclerView.Adapter<ListCollectionAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_first, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listCollection[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            val intenDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intenDetail.putExtra(KEY_COLLECTION, listCollection[holder.adapterPosition])
            onItemClickCallback.onItemClicked(listCollection[holder.adapterPosition])
            holder.itemView.context.startActivity(intenDetail)
        }
    }

    override fun getItemCount(): Int = listCollection.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Collection)
    }
}