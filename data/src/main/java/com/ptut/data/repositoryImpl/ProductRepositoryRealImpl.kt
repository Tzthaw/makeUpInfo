package com.ptut.data.repositoryImpl

import com.ptut.data.datasource.ProductCacheDataSource
import com.ptut.data.datasource.ProductNetworkDataSource
import com.ptut.data.mapper.ProductCacheMapper
import com.ptut.domain.model.Products
import com.ptut.domain.repository.ProductRepository
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject

class ProductRepositoryRealImpl @Inject constructor(
    private val productNetworkDataSource: ProductNetworkDataSource,
    private val productCacheDataSource: ProductCacheDataSource,
    private val productCacheMapper: ProductCacheMapper
) : ProductRepository {
    val threadCt = Runtime.getRuntime().availableProcessors() + 1
    val executor = Executors.newFixedThreadPool(threadCt)!!
    val scheduler = Schedulers.from(executor)
    override fun getProductsByBrand(brandName: String): Single<List<Products>> {
//        return Single.fromCallable {
//            productNetworkDataSource.getProductByBrand(brandName)
//        }.doOnError {
//            productCacheMapper.map(
//                productCacheDataSource.getProduct()!!)
//        }
        productCacheDataSource.putProduct(
            productCacheMapper.reverseMap(productNetworkDataSource.getProductByBrand(brandName)))
        return Single.fromCallable {
            productCacheMapper.map(productCacheDataSource.getProduct()!!)
        }

    }
}