package com.ptut.network.exception

import okhttp3.Interceptor
import okhttp3.Response

class NetworkExceptionInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response=chain.proceed(chain.request())
        when(response.isSuccessful){
            true-> return response
            false->{
                val responseBody=response.body()
                throw NetworkException(responseBody,response.code())
            }
        }
    }
}