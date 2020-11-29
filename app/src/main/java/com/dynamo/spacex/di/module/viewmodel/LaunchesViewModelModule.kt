package com.dynamo.spacex.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.dynamo.spacex.di.scope.ViewModelKey
import com.dynamo.spacex.ui.main.launches.LaunchesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LaunchesViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(LaunchesViewModel::class)
    abstract fun bindLaunchesViewModel(viewModel: LaunchesViewModel): ViewModel
}