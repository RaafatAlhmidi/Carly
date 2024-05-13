package com.raafat.carly.screens.carList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raafat.carly.screens.carList.CarListUiState.Success
import com.raafat.data.di.DefaultDispatcher
import com.raafat.data.model.Car
import com.raafat.domain.services.carSelection.CarSelectionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val carSelectionService: CarSelectionService,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher

): ViewModel() {

    private val _uiState = MutableStateFlow<CarListUiState>(CarListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCars()
    }

    private fun getCars() {
        viewModelScope.launch(dispatcher) {
            carSelectionService.getAllCars().collect {
                _uiState.value = Success(it)
            }
        }
    }

    fun selectCar(car: Car) {
        viewModelScope.launch(dispatcher) {
            carSelectionService.selectCar(car)
        }
    }

    fun deleteCar(car: Car) {
        viewModelScope.launch(dispatcher) {
            carSelectionService.deleteCar(car)
        }
    }
}

sealed class CarListUiState{
    data class Success(val cars: List<Car>) : CarListUiState()
    data object Loading: CarListUiState()
}

