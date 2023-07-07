package com.corrales.luis.funkeateapp.ui.view

import android.app.Dialog
import android.content.Context
import com.bumptech.glide.Glide
import com.corrales.luis.funkeateapp.data.model.ProductResponse
import com.corrales.luis.funkeateapp.databinding.DialogProductBinding

class ProductDialog(context: Context, private val product: ProductResponse) : Dialog(context) {

    // Binding del layout DialogProductBinding
    private val binding = DialogProductBinding.inflate(layoutInflater)
    init {
        // Configurar el diseño del diálogo
        setContentView(binding.root)
        window?.setLayout(
            (context.resources.displayMetrics.widthPixels * 0.90).toInt(),
            (context.resources.displayMetrics.heightPixels * 0.75).toInt()
        )

        // Configurar los datos del producto
        Glide.with(context).load(product.imagen).into(binding.ivProductImage)
        binding.tvProductName.text = "Nombre: ${product.nombre}"
        binding.tvProductDescription.text = "Descripción: ${product.descripcion}"
        binding.tvDetails.text = "Detalles: ${product.detalles}"
        binding.tvPrice.text = "Precio: ${product.precio}€"
        binding.tvEstado.text = "Estado: ${product.estado}"
        binding.tvCajaPersonalizada.text = "Caja personalizada: ${product.caja_personalizada}"
        binding.tvTamanoCaja.text = "Tamaño de caja: ${product.tamanoCaja}"
        binding.tvTamanoFunko.text = "Tamaño de funko: ${product.tamanoFunko}"
        binding.tvCategoria.text = "Categoría: ${product.categoria.nombre}"

        // Configurar el botón de cerrar diálogo
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}