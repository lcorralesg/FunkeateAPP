package com.corrales.luis.funkeateapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import com.corrales.luis.funkeateapp.adapter.CategoryAdapter
import com.corrales.luis.funkeateapp.data.model.CategoryResponse
import com.corrales.luis.funkeateapp.data.network.ApiService
import com.corrales.luis.funkeateapp.databinding.ActivityCategoryBinding
import com.yuyakaido.android.cardstackview.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate),
    CardStackListener,
    CategoryAdapter.Callback {

    private val adapter by lazy { CategoryAdapter(listCategory, this) }

    private var listCategory:List<CategoryResponse> = emptyList()

    private val manager by lazy { CardStackLayoutManager(this , this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeTinderCard()
        getAllCategories()
    }

    private fun getAllCategories(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getCategories()
            if(call.isSuccessful){
                call.body()?.let {
                    runOnUiThread{
                        adapter.list = it.data
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initializeTinderCard(){
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.rvTinderCategory.layoutManager = manager

        binding.rvTinderCategory.adapter = adapter
        binding.rvTinderCategory.itemAnimator.apply {
            if(this is DefaultItemAnimator){
                supportsChangeAnimations = false
            }
        }
    }

    override fun onClickCategoryInformation(categoryResponse: CategoryResponse){
        Toast.makeText(this, "Click Category: ${categoryResponse.nombre} url: ${categoryResponse.imagen}", Toast.LENGTH_SHORT).show()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        if(direction != null){
            Log.e("OnCardSwiped", direction.name)
        }
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }
}