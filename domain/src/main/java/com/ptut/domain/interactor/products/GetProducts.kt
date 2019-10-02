package com.ptut.domain.interactor.products

import com.ptut.domain.executor.PostExecutionThread
import com.ptut.domain.executor.ThreadExecutor
import com.ptut.domain.model.Products
import com.ptut.domain.repository.ProductRepository
import com.ptut.domain.rxusecase.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val productRepository: ProductRepository,
    postExecutionThread: PostExecutionThread,
    threadExecutor: ThreadExecutor
) :SingleUseCase<List<Products>,GetProducts.Params>(postExecutionThread,threadExecutor){
    override fun provideSingle(params: Params): Single<List<Products>> {
        return productRepository.getProductsByBrand(params.brandName)
    }

    data class Params(
        val brandName : String
    )
}