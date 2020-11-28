package com.dynamo.spacex.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.dynamo.spacex.di.scope.ViewModelKey
import com.dynamo.spacex.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}