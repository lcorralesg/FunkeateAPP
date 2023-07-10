package com.corrales.luis.funkeateapp.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.corrales.luis.funkeateapp.R
import com.corrales.luis.funkeateapp.data.model.ProductListResponse
import com.corrales.luis.funkeateapp.data.model.ProductResponse
import com.corrales.luis.funkeateapp.databinding.ItemProductoBinding
import com.corrales.luis.funkeateapp.ui.view.CatalogoFragment

class ProductAdapter(
    var list: List<ProductResponse>,
    var productClickListener: ProductClickListener
): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_producto)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemProductoBinding.bind(view)
        fun bind(product: ProductResponse) {
            with(binding) {
                tvName.text = product.nombre
                tvPrice.text = "S/.${product.precio}"
                tvCategory.text = product.categoria.nombre
                Glide
                    .with(itemView)
                    .load(product.imagen)
                    .into(binding.ivProduct)
                linearLayout3.setOnClickListener {
                    productClickListener.onProductClicked(product)
                }
            }
        }
    }

    interface ProductClickListener {
        fun onProductClicked(product: ProductResponse)
    }
}