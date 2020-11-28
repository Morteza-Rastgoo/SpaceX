package com.dynamo.spacex.di.component

import android.content.Context
import com.dynamo.spacex.di.module.AppModule
import com.dynamo.spacex.di.module.usecase.LaunchesModule
import com.dynamo.spacex.di.subcomponent.AppSubComponents
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Scope annotation that the AppComponent uses
// Classes annotated with @Singleton will have a unique instance in this Component
@Singleton
// Definition of a Dagger component that adds info from the different modules to the graph
@Component(
    modules = [
        AppModule::class,
        AppSubComponents::class,
        LaunchesModule::class,
    ]
)
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Types that can be retrieved from the graph
    fun mainComponent(): MainComponent.Factory
}
