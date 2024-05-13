package com.raafat.carly.screens.carCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raafat.data.di.DefaultDispatcher
import com.raafat.data.model.Brand
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series
import com.raafat.domain.services.carCreation.CarCreationService
import com.raafat.domain.services.carSelection.CarSelectionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarCreationViewModel @Inject constructor(
    private val carCreationService: CarCreationService,
    private val carSelectionService: CarSelectionService,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _currentState = MutableStateFlow<CarsSelectionUIState>(SelectBrands(emptyList()))
    private val _prevState = MutableStateFlow<CarsSelectionUIState?>(null)
    private val _nextState = MutableStateFlow<CarsSelectionUIState?>(null)

    private val _uiState = MutableStateFlow<CarsSelectionUIState>(SelectBrands(emptyList()))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val brands = carCreationService.loadBrands(null)
            _currentState.emit(SelectBrands(brands))
        }

        viewModelScope.launch {
            _currentState.collect {
                _uiState.emit(it)
            }
        }
    }


    /**
     * @return true if there is a no previous state, which indicate to navigate back to the previous screen
     */
    fun prevState(): Boolean {
        val prevState = _prevState.value
        val nextState = _currentState.value
        viewModelScope.launch {
            if (prevState != null) {
                _nextState.emit(nextState)
                _currentState.emit(prevState)
            }
            setPreviousState()
        }

        return prevState == null
    }

    private suspend fun setPreviousState() {

        val previousState = when (val currentState = _currentState.value) {
            is SelectBrands -> null
            is SelectSeries -> SelectBrands(carCreationService.loadBrands(null))
            is SelectYear -> SelectSeries(currentState.selectedBrand, carCreationService.loadSeries(currentState.selectedBrand, null))
            is SelectFuelType -> SelectYear(
                currentState.selectedBrand,
                currentState.selectedSeries,
                carCreationService.loadYears(currentState.selectedSeries, null)
            )

            is Finish -> null
        }

        _prevState.emit(previousState)
    }

    fun selectBrand(brand: Brand) {
        viewModelScope.launch {
            val series = carCreationService.loadSeries(brand, null)
            _prevState.emit(_currentState.value)
            _currentState.emit(SelectSeries(selectedBrand = brand, items = series))
            _nextState.emit(SelectYear(selectedBrand = brand, selectedSeries = null, items = emptyList()))
        }
    }

    fun selectSeries(series: Series) {
        viewModelScope.launch {
            val years = carCreationService.loadYears(series, null)
            val selectedBrand = (_uiState.value as? SelectSeries)?.selectedBrand
            _prevState.emit(_currentState.value)
            _currentState.emit(SelectYear(selectedBrand = selectedBrand, selectedSeries = series, items = years))
        }
    }

    fun selectYear(year: Int) {
        viewModelScope.launch {
            val selectedSeries = (_uiState.value as? SelectYear)?.selectedSeries
            val selectedBrand = (_uiState.value as? SelectYear)?.selectedBrand
            val fuelTypes = selectedSeries?.let { carCreationService.loadFuelTypes(it, null) } ?: emptyList()
            _prevState.emit(_currentState.value)
            _currentState.emit(
                SelectFuelType(
                    selectedBrand = selectedBrand,
                    selectedSeries = selectedSeries,
                    selectedYear = year,
                    items = fuelTypes
                )
            )
        }
    }

    fun selectFuelType(fuelType: FuelType) {
        viewModelScope.launch(dispatcher) {
            val selectedBrand = (_uiState.value as? SelectFuelType)?.selectedBrand
            val selectedSeries = (_uiState.value as? SelectFuelType)?.selectedSeries
            val selectedYear = (_uiState.value as? SelectFuelType)?.selectedYear
            if (selectedBrand != null && selectedSeries != null && selectedYear != null) {
                carSelectionService.createCar(selectedBrand, selectedSeries, selectedYear, fuelType)
            }
            _uiState.emit(Finish)
        }
    }

    fun filter(query: String) {
        viewModelScope.launch(dispatcher) {
            when (val currentState = _uiState.value) {
                is SelectBrands -> {
                    val brands = carCreationService.loadBrands(query)
                    _currentState.emit(SelectBrands(brands))
                }

                is SelectSeries -> {
                    val currentBrand = currentState.selectedBrand
                    val series = carCreationService.loadSeries(currentBrand, query)
                    _currentState.emit(SelectSeries(currentBrand, series))
                }

                is SelectYear -> {
                    val currentBrand = currentState.selectedBrand
                    val currentSeries = currentState.selectedSeries
                    val years = carCreationService.loadYears(currentSeries, query)
                    _currentState.emit(SelectYear(currentBrand, currentSeries, years))

                }

                is SelectFuelType -> {
                    val currentBrand = currentState.selectedBrand
                    val currentSeries = currentState.selectedSeries
                    val currentYear = currentState.selectedYear
                    val fuelTypes = carCreationService.loadFuelTypes(currentSeries, query)
                    _currentState.emit(SelectFuelType(currentBrand, currentSeries, currentYear, fuelTypes))
                }

                is Finish -> {

                }
            }
        }
    }
}

sealed class CarsSelectionUIState(
    val onGoingSelectionText: String? = null
)

data class SelectBrands(val items: List<Brand>) :
    CarsSelectionUIState(
        onGoingSelectionText = null
    )

data class SelectSeries(
    val selectedBrand: Brand?,
    val items: List<Series>
) :
    CarsSelectionUIState(
        onGoingSelectionText = "${selectedBrand?.name}"
    )

data class SelectYear(
    val selectedBrand: Brand?,
    val selectedSeries: Series?,
    val items: List<Int>
) :
    CarsSelectionUIState(
        onGoingSelectionText = "${selectedBrand?.name}. ${selectedSeries?.name}"
    )

data class SelectFuelType(
    val selectedBrand: Brand?,
    val selectedSeries: Series?,
    val selectedYear: Int?,
    val items: List<FuelType>
) :
    CarsSelectionUIState(
        onGoingSelectionText = "${selectedBrand?.name}, ${selectedSeries?.name}, $selectedYear"
    )

data object Finish : CarsSelectionUIState()