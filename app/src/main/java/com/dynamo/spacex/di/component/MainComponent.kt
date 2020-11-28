package com.dynamo.spacex.di.component

import com.dynamo.spacex.di.module.viewmodel.MainViewModelModule
import com.dynamo.spacex.di.scope.MainScope
import com.dynamo.spacex.ui.main.MainFragment
import dagger.Subcomponent

// Scope annotation that the LoginComponent uses
// Classes annotated with @ActivityScope will have a unique instance in this Component
@MainScope
// Definition of a Dagger subcomponent
@Subcomponent(modules = [MainViewModelModule::class])
interface MainComponent {

    // Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    //The parameter of the interface methods define what classes request injection
    // Classes that can be injected by this Component

    fun inject(fragment: MainFragment)
}
