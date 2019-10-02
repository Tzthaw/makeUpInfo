package com.ptut.domain.exception

interface GenericErrorMessageFactory {
    fun getErrorMessage(throwable: Throwable): CharSequence
}