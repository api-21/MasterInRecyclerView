package com.xpresscure.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xpresscure.recyclerview.databinding.RcvItemBinding

sealed class RecyclerViewHolder( val root: View):RecyclerView.ViewHolder(root) {

    class ItemVH(private val binding:RcvItemBinding, private val onRcvItemClick: OnRcvItemClick):RecyclerViewHolder(binding.root){
        fun bind(item: DataFactor.ItemData){
//            binding.itemCard.setOnClickListener {
//                onRcvItemClick.onClick(0)
//            }
            if (item.isActive==1){
                binding.btnTesting.isEnabled = false
            }
            binding.title.text = item.title
            binding.subTitle.text = item.subTitle
        }
    }
}