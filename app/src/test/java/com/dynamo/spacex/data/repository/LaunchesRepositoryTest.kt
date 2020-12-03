package com.dynamo.spacex.data.repository

import com.dynamo.spacex.data.network.LaunchesService
import com.dynamo.spacex.data.network.model.pastlaunch.Launch
import com.dynamo.spacex.data.network.model.pastlaunch.LaunchSite
import com.dynamo.spacex.data.network.model.pastlaunch.Links
import com.dynamo.spacex.data.network.model.pastlaunch.Rocket
import com.dynamo.spacex.data.repository.model.PastLaunch
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
 * @since : 28/11/2020 AD
 */

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LaunchesRepositoryTest {

    @get:Rule
    var testCoroutineRole = TestCoroutineRule()

    private lateinit var launchesRepository: LaunchesRepository

    @MockK
    lateinit var launchesService: LaunchesService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        launchesRepository = LaunchesRepository(launchesService)
    }

    @Test
    fun `calling getPastLaunches returns list of PastLaunch UI objects`() =
        testCoroutineRole.runBlockingTest {
            val pastLaunchesList = listOf(
                Launch(
                    flight_number = 1,
                    launch_year = "2006",
                    launch_success = false,
                    mission_name = "FalconSat",
                    launch_date_utc = "2006-03-24T22:30:00.000Z",
                    rocket = Rocket(rocket_name = "FalconSat", rocket_type = "Merlin A"),
                    links = Links(video_link = "https://www.youtube.com/watch?v=0a_00nJ_Y88"),
                    launch_site = LaunchSite(site_name = "Kwajalein Atoll")
                )
            )

            coEvery {
                launchesService.getPastLaunches()
            } returns pastLaunchesList

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
                imageLink = "https://www.youtube.com/watch?v=0a_00nJ_Y88",
            )

            val first = launchesRepository.getPastLaunches().first()

            assertEquals(first, pastLaunch)
        }

}