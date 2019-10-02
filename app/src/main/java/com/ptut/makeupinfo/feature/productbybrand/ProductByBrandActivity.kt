package com.ptut.makeupinfo.feature.productbybrand

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ptut.appbase.core.mvp.MvpActivity
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.domain.model.Products
import com.ptut.makeupinfo.R
import kotlinx.android.synthetic.main.activity_main.*
import android.view.WindowManager
import androidx.core.content.ContextCompat


class ProductByBrandActivity
    : MvpActivity<ProductByBrandView, ProductByBrandViewModel>(),
    ProductByBrandView {

    private val productAdapter by lazy { ProductByBrandAdapter() }

    override val viewModel: ProductByBrandViewModel by contractedViewModels()
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, com.ptut.makeupinfo.R.color.my_statusbar_color)

        productRecycler.hasFixedSize()
        productRecycler.layoutManager= GridLayoutManager(this,2)
        productRecycler.adapter=productAdapter
        productRefresh.setOnRefreshListener {
            viewModel.refreshProductList("maybelline")
        }
    }

    override fun subscribeToProduct(productLD: LiveData<AsyncViewResource<List<Products>>>) {
        productLD.observe(this, Observer {
            when (it) {
                is AsyncViewResource.Loading -> {

                }
                is AsyncViewResource.Success -> {
                    productRefresh.isRefreshing = false
                    productAdapter.submitList(it.value)
                }
                is AsyncViewResource.Error -> {
                    productRefresh.isRefreshing = false
                    Toast.makeText(this,it.error.toString(),Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshProductList("maybelline")
    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}