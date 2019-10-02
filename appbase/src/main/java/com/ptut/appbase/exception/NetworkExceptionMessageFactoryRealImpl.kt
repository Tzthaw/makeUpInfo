package com.ptut.appbase.exception

import android.content.Context
import com.ptut.appbase.R
import com.ptut.network.exception.NetworkException
import com.ptut.network.exception.NetworkExceptionMessageFactory
import okhttp3.ResponseBody
import org.json.JSONObject
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Vincent on 2/26/19
 */
class NetworkExceptionMessageFactoryRealImpl @Inject constructor(
  private val context: Context
) :
  NetworkExceptionMessageFactory {

  override fun getErrorMessage(networkException: NetworkException): CharSequence {
    when (networkException.errorCode) {
      400 -> return parseMessageFromErrorBody(networkException.errorBody)
      401 -> return parseMessageFromErrorBody(networkException.errorBody)
      422 -> return parseMessageFromErrorBody(networkException.errorBody)
      403 -> return parseMessageFromErrorBody(networkException.errorBody)
      404 -> return context.getString(R.string.error_server_404)
      500 -> return context.getString(R.string.error_server_500)
    }

    return context.getString(R.string.error_generic)
  }

  private fun parseMessageFromErrorBody(errorBody: ResponseBody?): CharSequence {

    if (errorBody == null) {
      return context.getString(R.string.error_generic)
    }

    val errorBodyInString = errorBody.string()
    Timber.i("error body in string : $errorBodyInString")

    try {
      val errorBodyJson = JSONObject(errorBodyInString)
      val errorMessage = errorBodyJson.getString("message")

      return errorMessage
    } catch (exception: Exception) {
      Timber.e(exception)
    }

    return context.getString(R.string.error_generic)
  }

  override fun getErrorMessage(unknownHostException: UnknownHostException): CharSequence {
    return context.getString(R.string.error_no_internet)
  }

  override fun getErrorMessage(socketTimeoutException: SocketTimeoutException): CharSequence {
    return context.getString(R.string.error_socket_timeout)
  }

  override fun getErrorMessage(connectException: ConnectException): CharSequence {
    return context.getString(R.string.error_no_internet)
  }

}
