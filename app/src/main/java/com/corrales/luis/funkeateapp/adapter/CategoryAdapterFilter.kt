package com.corrales.luis.funkeateapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.corrales.luis.funkeateapp.R
import com.corrales.luis.funkeateapp.data.model.CategoryResponse
import com.corrales.luis.funkeateapp.databinding.ItemCategoriaFiltroBinding
import com.corrales.luis.funkeateapp.databinding.ItemMostrarTodosBinding

class CategoryAdapterFilter (
    private val list: List<CategoryResponse>,
    private val categoryClickListener: CategoryClickListener
): RecyclerView.Adapter<CategoryAdapterFilter.ViewHolder>() {

    private var showAllButton = false

    companion object {
        private const val VIEW_TYPE_CATEGORY = 0
        private const val VIEW_TYPE_SHOW_ALL = 1
        private const val SHOW_ALL_CATEGORY_ID = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_CATEGORY -> {
                val view = inflater.inflate(R.layout.item_categoria_filtro, parent, false)
                ViewHolder(view)
            }
            VIEW_TYPE_SHOW_ALL -> {
                val view = inflater.inflate(R.layout.item_mostrar_todos, parent, false)
                ViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_CATEGORY) {
            val item = list[position]
            holder.bindCategory(item)
        } else {
            holder.bindShowAllButton()
        }
    }

    override fun getItemCount(): Int {
        val categoryCount = list.size
        return if (showAllButton) categoryCount + 1 else categoryCount
    }

    override fun getItemViewType(position: Int): Int {
        return if (showAllButton && position == list.size) {
            VIEW_TYPE_SHOW_ALL
        } else {
            VIEW_TYPE_CATEGORY
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCategoriaFiltroBinding.bind(view)

        fun bindCategory(category: CategoryResponse) {
            with(binding) {
                tvCategoryName.text = category.nombre
                tvCategoryName.setOnClickListener {
                    categoryClickListener.onCategoryClicked(category)
                }
            }
        }

        fun bindShowAllButton() {
            with(binding) {
                tvCategoryName.text = "Mostrar todos"
                tvCategoryName.setOnClickListener {
                    categoryClickListener.onShowAllClicked()
                }
            }
        }
    }

    interface CategoryClickListener {
        fun onCategoryClicked(category: CategoryResponse)
        fun onShowAllClicked()
    }

    fun setShowAllButton(showAll: Boolean) {
        if (showAllButton != showAll) {
            showAllButton = showAll
            notifyDataSetChanged()
        }
    }
}

