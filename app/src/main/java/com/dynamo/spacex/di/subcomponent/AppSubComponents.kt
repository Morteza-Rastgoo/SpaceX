package com.dynamo.spacex.di.subcomponent

import com.dynamo.spacex.di.component.MainComponent
import dagger.Module

// This module tells a Component which are its subComponents
@Module(
    subcomponents = [
        MainComponent::class,
    ]
)
class AppSubComponents
