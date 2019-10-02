package com.ptut.domain.rxusecase

import com.ptut.domain.executor.PostExecutionThread
import com.ptut.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.schedulers.Schedulers

abstract class FlowableUseCase<T, Params>(
  private val postExecutionThread: PostExecutionThread, private val threadExecutor: ThreadExecutor
) {

  /**
   * Implement this method in your custom AsyncUseCase in order to provide the final [Flowable].
   *
   * @param params The Params.
   * @return The provided [Flowable].
   */
  abstract protected fun provideFlowable(params: Params): Flowable<T>

  /**
   * Builds the provided [Flowable] and performs some transformation on it.
   *
   * @return The Observable with any transformation applied.
   */
  private fun buildUseCaseFlowable(): FlowableTransformer<T, T> {

    return FlowableTransformer { flowable ->
      flowable.subscribeOn(Schedulers.from(threadExecutor)).observeOn(
        postExecutionThread.scheduler
      )
    }
  }

  /**
   * Executes the provided [Flowable]
   *
   * @param params The Params.
   * @return The provided Flowable.
   */
  fun execute(params: Params): Flowable<T> {
    val flowable = provideFlowable(params)
    return flowable.compose(buildUseCaseFlowable())
  }

}