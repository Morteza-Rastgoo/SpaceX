package com.dynamo.spacex

import android.app.Application
import com.dynamo.spacex.di.component.AppComponent
import com.dynamo.spacex.di.component.DaggerAppComponent
import com.dynamo.spacex.di.module.AppModule


class SpaceXApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
    }

}