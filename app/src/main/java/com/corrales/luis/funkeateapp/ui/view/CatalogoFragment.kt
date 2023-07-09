package com.corrales.luis.funkeateapp.ui.view

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.viewModels
import com.corrales.luis.funkeateapp.adapter.CategoryAdapterFilter
import com.corrales.luis.funkeateapp.adapter.ProductAdapter
import com.corrales.luis.funkeateapp.data.model.CategoryResponse
import com.corrales.luis.funkeateapp.data.model.ProductResponse
import com.corrales.luis.funkeateapp.databinding.FragmentCatalogoBinding
import com.corrales.luis.funkeateapp.ui.viewmodel.CatalogoViewModel

class CatalogoFragment : BaseFragment<FragmentCatalogoBinding>(FragmentCatalogoBinding::inflate), CategoryAdapterFilter.CategoryClickListener, ProductAdapter.ProductClickListener
{
    private val catalogoViewModel: CatalogoViewModel by viewModels()
    private var listProducts = mutableListOf<ProductResponse>()
    private var listCategories = mutableListOf<CategoryResponse>()
    private val productAdapter by lazy { ProductAdapter(listProducts, this) }
    private val categoryAdapter by lazy { CategoryAdapterFilter(listCategories, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catalogoViewModel.getAllProducts(requireContext())
        catalogoViewModel.getAllCategories(requireContext())

        binding.rvProducts.adapter = productAdapter
        binding.rvCategories.adapter = categoryAdapter

        catalogoViewModel.productList.observe(this) {
            listProducts.clear()
            listProducts.addAll(it)
            productAdapter.notifyDataSetChanged()
        }

        catalogoViewModel.catalogList.observe(this) {
            listCategories.clear()
            listCategories.addAll(it)
            categoryAdapter.setShowAllButton(true) // Habilita el botón "Mostrar todos"
            categoryAdapter.notifyDataSetChanged()
        }
    }

    override fun onCategoryClicked(category: CategoryResponse) {
        catalogoViewModel.getProductsByCategoryName(category.nombre)
    }

    override fun onShowAllClicked() {
        catalogoViewModel.getAllProducts(requireContext())
    }

    override fun onProductClicked(product: ProductResponse) {
        catalogoViewModel.getProductDetailById(product.id)
        val productDialog = ProductDialog(requireContext(), product)
        productDialog.show()
    }
}
