package com.ptut.domain.rxusecase

import com.ptut.domain.executor.PostExecutionThread
import com.ptut.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, Params>(
  private val postExecutionThread: PostExecutionThread, private val threadExecutor: ThreadExecutor
) {

  /**
   * Implement this method in your custom AsyncUseCase in order to provide the final [Single].
   *
   * @param params The Params.
   * @return The provided [Single].
   */
  abstract protected fun provideSingle(params: Params): Single<T>

  /**
   * Builds the provided [Single] and performs some transformation on it.
   *
   * @return The Observable with any transformation applied.
   */
  private fun buildUseCaseSingle(): SingleTransformer<T, T> {

    return SingleTransformer { single ->
      single.subscribeOn(Schedulers.from(threadExecutor)).observeOn(
        postExecutionThread.scheduler
      )
    }
  }

  /**
   * Executes the provided [Single]
   *
   * @param params The Params.
   * @return The provided Single.
   */
  fun execute(params: Params): Single<T> {
    val single = provideSingle(params)
    return single.compose(buildUseCaseSingle())
  }

}