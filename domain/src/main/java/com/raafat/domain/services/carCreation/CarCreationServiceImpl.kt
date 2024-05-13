package com.raafat.domain.services.carCreation

import com.raafat.data.model.Brand
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series
import com.raafat.data.repository.brands.CarsRepository
import javax.inject.Inject

class CarCreationServiceImpl @Inject constructor(
    private val carRepository: CarsRepository
) : CarCreationService {


    /**
     * load brands from repository,
     * and filter based on query the brands name or the series of this brand
     */
    override suspend fun loadBrands(query: String?): List<Brand> {
        return carRepository.getBrands().filter { brand: Brand ->
            query == null
                || brand.name.contains(query, ignoreCase = true)
                || brand.series.any { series -> series.name.contains(query, ignoreCase = true) }
        }
    }

    override suspend fun loadSeries(brand: Brand?, query: String?): List<Series> {
        return brand?.series?.filter { series -> series.name.contains(query ?: "", ignoreCase = true) } ?: carRepository.getSeries()
    }

    override fun loadYears(series: Series?, query: String?): List<Int> {
        return ((series?.minimumYear ?: 1990)..(series?.maximumYear ?: 2024))
            .filter { year -> year.toString().contains(query ?: "", ignoreCase = true) }
    }

    override suspend fun loadFuelTypes(series: Series?, query: String?): List<FuelType> {
        return series?.fuelTypes?.filter { fuelType ->
            fuelType.name.contains(query ?: "", ignoreCase = true)
        } ?: carRepository.getFuelTypes()
    }
}