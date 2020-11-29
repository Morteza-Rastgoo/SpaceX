package com.dynamo.spacex.di.component

import com.dynamo.spacex.SpaceXApplication
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

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SpaceXApplication): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }

    // Types that can be retrieved from the graph
    fun launchesComponent(): LaunchesComponent.Factory
}
