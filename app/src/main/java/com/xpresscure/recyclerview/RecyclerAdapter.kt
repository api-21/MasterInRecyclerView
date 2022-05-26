package com.xpresscure.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpresscure.recyclerview.databinding.RcvItemBinding
import kotlinx.android.synthetic.main.rcv_item.view.*
import java.lang.IllegalArgumentException

class RecyclerAdapter :RecyclerView.Adapter<RecyclerViewHolder>() {

    var onItemClick : OnRcvItemClick?=null

    var itemList = listOf<DataFactor>()
    set(value) {
        field =value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when(viewType){
            R.layout.rcv_item ->RecyclerViewHolder.ItemVH(RcvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),onItemClick!!)
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        when(holder){
            is RecyclerViewHolder.ItemVH -> {
                holder.itemView.itemCard.setOnClickListener {
                    onItemClick?.onClick(position)
                }
                (holder.bind(itemList[position] as DataFactor.ItemData))
            }
        }
    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position]){
            is DataFactor.ItemData -> R.layout.rcv_item
            else ->throw IllegalArgumentException("Invalid View Type")
        }
    }

}