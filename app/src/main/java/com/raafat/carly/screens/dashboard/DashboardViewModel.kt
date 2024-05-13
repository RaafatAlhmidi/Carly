package com.raafat.carly.screens.dashboard

import androidx.lifecycle.ViewModel
import com.raafat.data.repository.brands.CarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    carsRepository: CarsRepository
) : ViewModel() {

//    private val _brands = carsRepository.getBrands()
//    val brands = _brands.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = emptyList()
//    )
}