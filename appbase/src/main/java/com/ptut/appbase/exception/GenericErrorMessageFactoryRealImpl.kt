package com.ptut.appbase.exception

import android.content.Context
import com.ptut.appbase.R
import com.ptut.domain.exception.GenericErrorMessageFactory
import com.ptut.network.exception.NetworkException
import com.ptut.network.exception.NetworkExceptionMessageFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Vincent on 2/26/19
 */
class GenericErrorMessageFactoryRealImpl @Inject constructor(
  private val context: Context,
  private val networkExceptionMessageFactory: NetworkExceptionMessageFactory
) : GenericErrorMessageFactory {

  override fun getErrorMessage(throwable: Throwable): CharSequence {
    return when (throwable) {
      is UnknownHostException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is SocketTimeoutException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is ConnectException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      is NetworkException -> networkExceptionMessageFactory.getErrorMessage(throwable)
      else -> {
        throwable.message ?: context.getString(R.string.error_generic)
      }

    }
  }

}