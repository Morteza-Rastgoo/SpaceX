package com.dynamo.spacex.data.repository

import com.dynamo.spacex.data.network.LaunchesService
import com.dynamo.spacex.util.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 */

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LaunchesRepositoryTest {

    private lateinit var launchesRepository: LaunchesRepository

    @get:Rule
    var testCoroutineRole = TestCoroutineRule()

    @MockK
    lateinit var launchesService: LaunchesService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        launchesRepository = LaunchesRepository(launchesService)
    }
}