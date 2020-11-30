package com.dynamo.spacex.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dynamo.spacex.data.repository.model.PastLaunch
import com.dynamo.spacex.data.usecase.launches.GetPastLaunchesUseCase
import com.dynamo.spacex.ui.main.launches.LaunchesViewModel
import com.dynamo.spacex.util.CoroutineTestRule
import com.dynamo.spacex.util.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author : Morteza Rastgoo
 * @since : 29/11/2020 AD
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LaunchesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()


    private lateinit var launchesViewModel: LaunchesViewModel

    @MockK
    lateinit var getPastLaunchesUseCase: GetPastLaunchesUseCase

    val pastLaunch = PastLaunch(
        flightNumber = 1,
        missionName = "FalconSat",
        date = "2006-03-24T22:30:00.000Z",
        description = "Rocket name: FalconSat\n" +
                "Rocket type: Merlin A\n" +
                "Launch site: Kwajalein Atoll\n" +
                "Year: 2006\n" +
                "Launch success: No",
        youtubeId = "https://www.youtube.com/watch?v=0a_00nJ_Y88",
        imageLink = "https://www.youtube.com/watch?v=0a_00nJ_Y88"
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        launchesViewModel = LaunchesViewModel(getPastLaunchesUseCase)

        coEvery {
            getPastLaunchesUseCase.invoke(any())
        } returns listOf(pastLaunch)
    }


    @Test
    fun getPastLaunches() = testCoroutineRule.runBlockingTest {
        launchesViewModel.getPastLaunches()
        val value = launchesViewModel.pastLaunches.value
        assertEquals(value, listOf(pastLaunch))
    }
}