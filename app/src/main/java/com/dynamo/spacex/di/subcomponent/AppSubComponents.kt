package com.dynamo.spacex.di.subcomponent

import com.dynamo.spacex.di.component.LaunchesComponent
import dagger.Module

// This module tells a Component which are its subComponents
@Module(
    subcomponents = [
        LaunchesComponent::class,
    ]
)
class AppSubComponents
