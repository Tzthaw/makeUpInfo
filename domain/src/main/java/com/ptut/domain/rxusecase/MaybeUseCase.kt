package com.ptut.domain.rxusecase

import com.ptut.domain.executor.PostExecutionThread
import com.ptut.domain.executor.ThreadExecutor
import io.reactivex.Maybe
import io.reactivex.MaybeTransformer
import io.reactivex.schedulers.Schedulers

abstract class MaybeUseCase<T, Params>(
  private val postExecutionThread: PostExecutionThread, private val threadExecutor: ThreadExecutor
) {

  /**
   * Implement this method in your custom AsyncUseCase in order to provide the final [Maybe].
   *
   * @param params The Params.
   * @return The provided [Maybe].
   */
  abstract protected fun provideMaybe(params: Params): Maybe<T>

  /**
   * Builds the provided [Maybe] and performs some transformation on it.
   *
   * @return The Observable with any transformation applied.
   */
  private fun buildUseCaseMaybe(): MaybeTransformer<T, T> {

    return MaybeTransformer { maybe ->
      maybe.subscribeOn(Schedulers.from(threadExecutor)).observeOn(
        postExecutionThread.scheduler
      )
    }
  }

  /**
   * Executes the provided [Maybe]
   *
   *
   * @param params The Params.
   * @return The provided Maybe.
   */
  fun execute(params: Params): Maybe<T> {
    val maybe = provideMaybe(params)
    return maybe.compose(buildUseCaseMaybe())
  }

}