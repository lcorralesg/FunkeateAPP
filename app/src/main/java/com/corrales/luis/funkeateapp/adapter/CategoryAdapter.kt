package com.corrales.luis.funkeateapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.corrales.luis.funkeateapp.R
import com.corrales.luis.funkeateapp.data.model.CategoryResponse
import com.corrales.luis.funkeateapp.databinding.ItemCategoryBinding

class CategoryAdapter (
    var list:List<CategoryResponse>,
    var callback: Callback
    ): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val view = parent.inflate(R.layout.item_category)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemCategoryBinding.bind(view)

        fun bind(category: CategoryResponse){
            with(binding){
                root.setOnClickListener{
                    callback.onClickCategoryInformation(category)
                }
                tvName.text = category.nombre
                Glide
                    .with(itemView)
                    .load(category.getCategoryImage())
                    .into(binding.ivCategory)
            }
        }
    }
    interface Callback{
        fun onClickCategoryInformation(category: CategoryResponse)
    }
}
fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)