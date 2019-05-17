package com.km.application

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(val context: Context, val list: ArrayList<Any>) : RecyclerView.Adapter<RecyclerViewAdapter.GenericViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {

        when (viewType) {
            0 -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
                return TitleHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
                return ContentsHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return (list[position] as ViewType).viewType
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.setDataOnView(position)
    }

    abstract inner class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun setDataOnView(position: Int)
    }

    inner class TitleHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun setDataOnView(position: Int) {
            val model: TitleModel = list[position] as TitleModel
            //itemView.owner_image.setImageResource()
            itemView.owner_name.text = model.name
        }
    }

    inner class ContentsHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun setDataOnView(position: Int) {
            val model: RecyclerViewModel = list[position] as RecyclerViewModel
            itemView.item_name.text = model.name
            itemView.item_description.text = model.description
            itemView.item_star_count.text = model.star.toString()
        }
    }
}