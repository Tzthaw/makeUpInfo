package com.ptut.makeupinfo.feature.productbybrand

import androidx.lifecycle.LiveData
import com.ptut.appbase.core.mvp.Viewable
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.domain.model.Products

interface ProductByBrandView:Viewable {
    fun subscribeToProduct(productLD:LiveData<AsyncViewResource<List<Products>>>)
    fun showError(msg:String)
}