package com.corrales.luis.funkeateapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.corrales.luis.funkeateapp.R
import com.corrales.luis.funkeateapp.data.model.ProductResponse
import com.corrales.luis.funkeateapp.databinding.ItemProductDetailBinding

class ProductDetailAdapter(
        var list: List<ProductResponse>,
        var callback: Callback
        ):RecyclerView.Adapter<ProductDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailAdapter.ViewHolder {
        val view = parent.inflate(R.layout.item_product_detail)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductDetailAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemProductDetailBinding.bind(view)

        fun bind(product: ProductResponse){
            with(binding){
                root.setOnClickListener{
                    callback.onClickCategoryInformation(product)
                }
                tvName.text = product.nombre
                tvDescripcion.text = product.descripcion
                tvPrecio.text = product.precio.toString()
                tvDetalle.text = product.detalles
                tvCaja.text = product.tamaño_caja
                tvFunko.text = product.tamaño_funko

                Glide
                    .with(itemView)
                    .load(product.imagen)
                    .into(binding.ivProduct)
            }
        }
    }

    interface Callback{
        fun onClickCategoryInformation(product: ProductResponse)
    }
}
