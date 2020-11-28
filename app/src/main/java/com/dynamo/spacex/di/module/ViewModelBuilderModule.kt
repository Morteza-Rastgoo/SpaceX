package com.dynamo.spacex.di.module

import androidx.lifecycle.ViewModel
import com.dynamo.spacex.di.AssistedSavedStateViewModelFactory
import dagger.Module
import dagger.multibindings.Multibinds


@Module
abstract class ViewModelBuilderModule {
    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    @Multibinds
    abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>
}