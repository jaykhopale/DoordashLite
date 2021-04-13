package com.jayantkhopale.doordash.lite.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.jayantkhopale.doordash.lite.data.storedetail.StoreDetail
import com.jayantkhopale.doordash.lite.data.stores.Stores
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.lang.Exception
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

/**
 * A suite of tests for [StoresRepository] service.
 */
@RunWith(JUnit4::class)
class StoresRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var storesRepository: StoresRepository
    private lateinit var fakeStoresService: FakeStoresService

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun initRepository() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.doordash.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        val networkBehavior = NetworkBehavior.create()
        networkBehavior.setFailurePercent(0)
        val mockRetrofit = MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build()
        val delegate = mockRetrofit.create(StoresService::class.java)
        fakeStoresService =
                FakeStoresService(
                        delegate, dispatcher
                )
        storesRepository = StoresRepository(fakeStoresService, dispatcher)
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalTime
    @Test
    fun `test successful stores data response`() {
        runBlocking {
            storesRepository.getStores().test(timeout = 4.seconds) {
                val stores: Stores = expectItem()
                val storesList = stores.stores
                val responseSize = storesList.size

                assertThat(responseSize).isEqualTo(5)

                val firstStore = storesList[0]
                assertThat(firstStore.name).isEqualTo("L&L Hawaiian Barbecue")
                assertThat(firstStore.shortDescription).isEqualTo("Hawaiian, Chicken, ...")
                assertThat(firstStore.deliveryTime).isEqualTo("27 min")

                val lastStore = storesList.last()
                assertThat(lastStore.name).isEqualTo("Jack in the Box")
                assertThat(lastStore.shortDescription).isEqualTo("American (New), Sandwich, ...")
                assertThat(lastStore.deliveryTime).isEqualTo("25 min")

                expectComplete()
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test empty store data response`() {
        runBlockingTest {
            val stores = fakeStoresService.getEmptyResponse(37.422740, -122.139956, 5, 0)
            assertThat(stores).isNotNull()
            assertThat(stores.stores).isEmpty()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test store data malformed response`() {
        runBlockingTest {
            try {
                val stores = fakeStoresService.getMalformedResponse(37.422740, -122.139956, 5, 0)
            } catch (exception: Exception) {
                assertThat(exception).isNotNull()
                assertThat(exception).hasMessageThat().isEqualTo("End of input")
            }
        }
    }

    @ExperimentalTime
    @Test
    fun `test store details response`() {
        runBlocking {
            storesRepository.getStoreDetail(52948).test(timeout = 4.seconds) {
                val storeDetail: StoreDetail = expectItem()

                assertThat(storeDetail.id).isEqualTo(52948)
                assertThat(storeDetail.coverImgUrl).isNotEmpty()
                assertThat(storeDetail.status).isEqualTo("28 - 38 mins")
                assertThat(storeDetail.name).isEqualTo("L&L Hawaiian BBQ (El Camino Real)")
                expectComplete()
            }
        }
    }
}