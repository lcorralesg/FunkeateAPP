package com.corrales.luis.funkeateapp.ui.view

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.corrales.luis.funkeateapp.R
import com.corrales.luis.funkeateapp.databinding.ActivityProductDetailBinding
import com.corrales.luis.funkeateapp.ui.viewmodel.ProductDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding>
    (ActivityProductDetailBinding::inflate) {

    private val viewModel by lazy { ProductDetailViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }
}