package com.corrales.luis.funkeateapp.ui.view

import android.os.Bundle
import androidx.navigation.findNavController
import com.corrales.luis.funkeateapp.databinding.ActivityCategoryBinding
import com.corrales.luis.funkeateapp.ui.viewmodel.CategoryViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.corrales.luis.funkeateapp.R

class CategoryActivity: BaseActivity<ActivityCategoryBinding>
    (ActivityCategoryBinding::inflate){

        private val viewModel by lazy { CategoryViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)

        }
    }