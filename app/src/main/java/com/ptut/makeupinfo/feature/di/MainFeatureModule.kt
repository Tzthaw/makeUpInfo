package com.ptut.makeupinfo.feature.di

import androidx.lifecycle.ViewModel
import com.ptut.appbase.di.viewmodel.ViewModelKey
import com.ptut.makeupinfo.feature.productbybrand.ProductByBrandActivity
import com.ptut.makeupinfo.feature.productbybrand.ProductByBrandViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainFeatureModule {

    @ContributesAndroidInjector
    abstract fun productByNameActivity():ProductByBrandActivity

    @Binds
    @IntoMap
    @ViewModelKey(ProductByBrandViewModel::class)
    abstract fun productViewModel(
        productByBrandViewModel: ProductByBrandViewModel
    ):ViewModel
}