package com.ptut.appbase

import android.os.Looper
import com.ptut.domain.executor.PostExecutionThread

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vincent on 2/16/19
 */
@Singleton
class UIThread @Inject constructor() : PostExecutionThread {

  override val scheduler: Scheduler
    get() = {
      val asyncMainThreadScheduler = AndroidSchedulers.from(Looper.getMainLooper(), true)
      RxAndroidPlugins.setInitMainThreadSchedulerHandler {
        asyncMainThreadScheduler
      }
      AndroidSchedulers.mainThread()
    }.invoke()

}