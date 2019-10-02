package com.ptut.appbase.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Vincent on 3/1/19
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

  @get:LayoutRes
  protected abstract val layoutId: Int

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): android.view.View? {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    val view = inflater.inflate(layoutId, container, false)
    return view
  }

}


