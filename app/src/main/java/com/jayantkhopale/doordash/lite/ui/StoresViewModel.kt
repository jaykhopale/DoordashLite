package com.jayantkhopale.doordash.lite.ui

import androidx.lifecycle.*
import com.jayantkhopale.doordash.lite.api.StoreDetailResult
import com.jayantkhopale.doordash.lite.api.StoresRepository
import com.jayantkhopale.doordash.lite.api.StoresResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A [ViewModel] to fetch the list of stores for a given location. It uses [LiveData] to let the
 * view know about error states and successful responses.
 */
@HiltViewModel
class StoresViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val storesRepository: StoresRepository,
) : ViewModel() {

    /**
     * A [LiveData] object to notify the view of loading, success and error states for stores
     * list response.
     */
    val storesData: LiveData<StoresResult> = liveData(viewModelScope.coroutineContext) {
        emit(StoresResult.Loading)
        try {
            storesRepository.getStores().collect { result ->
                emit(StoresResult.Success(result.stores))
            }
        } catch (throwable: Throwable) {
            emit(StoresResult.Failure(throwable))
        }
    }

    /**
     * A [LiveData] object to notify the view of loading, success and error states for a store's
     * detail response.
     */
    val storeDetailResult: LiveData<StoreDetailResult> get() = _storeDetailResult
    private val _storeDetailResult = MutableLiveData<StoreDetailResult>()

    fun getStoreDetail(id: Int) {
        _storeDetailResult.postValue(StoreDetailResult.Loading)
        if (savedStateHandle.contains(id.toString())) {
            _storeDetailResult.postValue(StoreDetailResult.Success(savedStateHandle[id.toString()]!!))
            return
        }
        viewModelScope.launch {
            try {
                storesRepository.getStoreDetail(id).collect {
                    _storeDetailResult.postValue(StoreDetailResult.Success(it))
                    savedStateHandle[id.toString()] = it
                }
            } catch (throwable: Throwable) {
                _storeDetailResult.postValue(StoreDetailResult.Failure(throwable))
            }
        }
    }
}