package com.dynamo.spacex.data.usecase.launches

import com.dynamo.spacex.data.repository.LaunchesRepository
import com.dynamo.spacex.data.repository.model.PastLaunch
import com.dynamo.spacex.data.usecase.base.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPastLaunchesUseCase @Inject constructor(
    private val repository: LaunchesRepository,
    defaultDispatcher: CoroutineDispatcher
) : SuspendUseCase<Int, List<PastLaunch>>(defaultDispatcher) {

    override suspend fun execute(parameters: Int): List<PastLaunch> {
        return repository.getPastLaunches(offset = parameters)
    }

}