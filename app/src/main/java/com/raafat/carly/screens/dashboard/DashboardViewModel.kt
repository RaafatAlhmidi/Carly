package com.raafat.carly.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raafat.data.di.DefaultDispatcher
import com.raafat.data.model.Car
import com.raafat.data.model.Feature
import com.raafat.domain.services.carCreation.CarCreationService
import com.raafat.domain.services.carSelection.CarSelectionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val carSelectionService: CarSelectionService,
    private val carCreationService: CarCreationService,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher

) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch(dispatcher) {
            carSelectionService.getSelectedCar().collect {
                if (it == null) {
                    _uiState.emit(DashboardUiState.Empty)
                    return@collect
                }
                _uiState.emit(DashboardUiState.Success(it, it.series.features))
            }
        }
    }
}

sealed class DashboardUiState {
    data object Loading : DashboardUiState()
    data object Empty: DashboardUiState()
    data class Success(val selectedCar: Car?, val features: List<Feature>) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()

}