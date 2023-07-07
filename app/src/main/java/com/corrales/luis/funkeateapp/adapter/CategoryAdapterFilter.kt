package com.corrales.luis.funkeateapp.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.corrales.luis.funkeateapp.R
import com.corrales.luis.funkeateapp.data.model.CategoryResponse
import com.corrales.luis.funkeateapp.databinding.ItemCategoriaFiltroBinding

class CategoryAdapterFilter (
    private val list: List<CategoryResponse>,
    private val categoryClickListener: CategoryClickListener
): RecyclerView.Adapter<CategoryAdapterFilter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_categoria_filtro)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCategoriaFiltroBinding.bind(view)

        fun bind(category: CategoryResponse) {
            with(binding) {
                tvCategoryName.text = "   ${category.nombre}   "
                tvCategoryName.setOnClickListener {
                    categoryClickListener.onCategoryClicked(category)
                }
            }
        }
    }

    interface CategoryClickListener {
        fun onCategoryClicked(category: CategoryResponse)
    }
}
