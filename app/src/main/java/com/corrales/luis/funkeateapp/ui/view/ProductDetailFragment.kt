package com.corrales.luis.funkeateapp.ui.view

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.corrales.luis.funkeateapp.adapter.ProductDetailAdapter
import com.corrales.luis.funkeateapp.data.model.ProductResponse
import com.corrales.luis.funkeateapp.databinding.FragmentProductDetailBinding
import com.corrales.luis.funkeateapp.ui.viewmodel.ProductDetailViewModel
import com.yuyakaido.android.cardstackview.*

class ProductDetailFragment: BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate),
    CardStackListener, ProductDetailAdapter.Callback{

    private var listProduct: List<ProductResponse> = emptyList()

    private val productDetalViewModel: ProductDetailViewModel by viewModels()

    private val manager by lazy { CardStackLayoutManager(context, this) }

    private val adapter by lazy { ProductDetailAdapter(listProduct, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeTinderCard()
        observeValues()
    }

    private fun observeValues(){
        binding.floatingActionButton.setOnClickListener {
            // Rewind
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(setting)
            binding.rvTinderProductDetail.rewind()
        }
        binding.floatingActionButton2.setOnClickListener {
            //Skip
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvTinderProductDetail.swipe()
        }
        binding.floatingActionButton3.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvTinderProductDetail.swipe()
        }
        productDetalViewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }
        productDetalViewModel.productdetailList.observe(this) {
            adapter.list = it
            adapter.notifyDataSetChanged()
            binding.floatingActionButton.visibility = View.VISIBLE
            binding.floatingActionButton2.visibility = View.VISIBLE
            binding.floatingActionButton3.visibility = View.VISIBLE
        }
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

        binding.rvTinderProductDetail.layoutManager = manager

        binding.rvTinderProductDetail.adapter = adapter

        binding.rvTinderProductDetail.itemAnimator.apply {
            if(this is DefaultItemAnimator){
                supportsChangeAnimations = false
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }

    override fun onClickCategoryInformation(product: ProductResponse) {
    }
}