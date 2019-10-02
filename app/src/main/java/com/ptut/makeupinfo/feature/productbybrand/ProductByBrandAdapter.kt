package com.ptut.makeupinfo.feature.productbybrand

import androidx.recyclerview.widget.DiffUtil
import com.ptut.domain.model.Products
import com.ptut.makeupinfo.R
import com.ptut.makeupinfo.databinding.DataBindingAdapter


val diffUtilProducts= object : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem==newItem
    }

}
class ProductByBrandAdapter :DataBindingAdapter<Products>(diffUtilProducts){
    override fun getItemViewType(position: Int) = R.layout.item_product
}