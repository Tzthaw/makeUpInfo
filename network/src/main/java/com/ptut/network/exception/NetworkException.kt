package com.ptut.network.exception

import okhttp3.ResponseBody
import java.io.IOException

data class NetworkException constructor(
    val errorBody:ResponseBody?=null,
    val errorCode:Int
):IOException()