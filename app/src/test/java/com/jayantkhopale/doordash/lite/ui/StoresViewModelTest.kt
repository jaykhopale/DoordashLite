package com.jayantkhopale.doordash.lite.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.jayantkhopale.doordash.lite.api.*
import com.jayantkhopale.doordash.lite.data.stores.Store
import com.jayantkhopale.doordash.lite.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

/**
 * A suite of tests for [StoresViewModel]
 */
@RunWith(JUnit4::class)
class StoresViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var storesRepository: StoresRepository
    private lateinit var fakeStoresService: FakeStoresService
    private lateinit var storesViewModel: StoresViewModel

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun initViewModel() {
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
        storesViewModel = StoresViewModel(SavedStateHandle(), storesRepository)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `test successful response for stores`() {
        storesViewModel.storesData.observeForever {
            val initial = storesViewModel.storesData.getOrAwaitValue()
            assertThat(initial is StoresResult.Loading).isTrue()

            val storesResult =  storesViewModel.storesData.getOrAwaitValue()
            assertThat(storesResult is StoresResult.Success).isTrue()

            val stores: List<Store> = (storesResult as StoresResult.Success).stores
            assertThat(stores).hasSize(5)

            val firstStore = stores[0]
            assertThat(firstStore.name).isEqualTo("L&L Hawaiian Barbecue")
            assertThat(firstStore.shortDescription).isEqualTo("Hawaiian, Chicken, ...")
            assertThat(firstStore.deliveryTime).isEqualTo("27 min")

            val lastStore = stores.last()
            assertThat(lastStore.name).isEqualTo("Jack in the Box")
            assertThat(lastStore.shortDescription).isEqualTo("American (New), Sandwich, ...")
            assertThat(lastStore.deliveryTime).isEqualTo("25 min")
        }
    }

    @Test
    fun `test successful response for store detail`() {
        storesViewModel.storeDetailResult.observeForever {
            val initial = storesViewModel.storeDetailResult.getOrAwaitValue()
            assertThat(initial is StoreDetailResult.Loading).isTrue()

            val storeDetailResult =  storesViewModel.storeDetailResult.getOrAwaitValue()
            assertThat(storeDetailResult is StoreDetailResult.Success).isTrue()

            val storeDetail = (storeDetailResult as StoreDetailResult.Success).storeDetail
            assertThat(storeDetail.name).isEqualTo("L&L Hawaiian BBQ (El Camino Real)")
            assertThat(storeDetail.id).isEqualTo(52948)
            assertThat(storeDetail.coverImgUrl).isNotEmpty()
            assertThat(storeDetail.status).isEqualTo("28 - 38 mins")
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}