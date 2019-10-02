package com.ptut.makeupinfo.feature.productbybrand

import androidx.lifecycle.MutableLiveData
import com.ptut.appbase.core.mvp.BaseViewModel
import com.ptut.appbase.helper.AsyncViewResource
import com.ptut.domain.interactor.products.GetProducts
import com.ptut.domain.model.Products
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ProductByBrandViewModel @Inject constructor(
    private val getProducts: GetProducts
) :BaseViewModel<ProductByBrandView>(){
    private val products = MutableLiveData<AsyncViewResource<List<Products>>>()
    override fun attachView(viewable: ProductByBrandView) {
        super.attachView(viewable)
        view?.subscribeToProduct(products)
    }

    fun refreshProductList(brandName:String){
        getProducts.execute(GetProducts.Params(brandName))
            .subscribeBy (
                onSuccess = {
                    products.postValue(AsyncViewResource.Success(it))
                },
                onError = {
                    view?.showError(it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}