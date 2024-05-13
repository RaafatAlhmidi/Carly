package com.raafat.carly.screens.dashboard

import androidx.lifecycle.ViewModel
import com.raafat.data.model.Car
import com.raafat.data.model.Feature
import com.raafat.domain.services.carSelection.CarSelectionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val carSelectionService: CarSelectionService
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {

    }
}

sealed class DashboardUiState {
    data object Loading : DashboardUiState()
    data class Success(val selectedCar: Car?, val features: List<Feature>) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()

}