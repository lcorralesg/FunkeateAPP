package com.corrales.luis.funkeateapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.corrales.luis.funkeateapp.R
import com.corrales.luis.funkeateapp.data.model.CarritoData
import com.corrales.luis.funkeateapp.data.model.Producto
import com.corrales.luis.funkeateapp.databinding.ItemCarritoBinding

class CarritoAdapter(
    private val list: List<CarritoData>
): RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_carrito, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.bindCarrito(item)
        }

        override fun getItemCount(): Int = list.size

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            private val binding = ItemCarritoBinding.bind(view)
            fun bindCarrito(item: CarritoData) {
                binding.tvNombre.text = item.producto.nombre
                binding.tvPrecio.text = item.producto.precio.toString()
                binding.tvCantidad.text = item.cantidad.toString()
                Glide
                    .with(itemView)
                    .load(item.producto.imagen)
                    .into(binding.ivProduct)
            }
        }
}
