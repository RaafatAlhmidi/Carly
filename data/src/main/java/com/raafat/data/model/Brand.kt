package com.raafat.data.model

data class Brand(
    val name: String,
    val series: List<Series>
)

data class Series(
    val id: Int,
    val name: String,
    val maximumYear: Int,
    val minimumYear: Int,
    val features: List<Feature>,
    val fuelTypes: List<FuelType>
)

data class Feature(
    val id: Int,
    val name: String
)

data class FuelType(
    val id: Int,
    val name: String
)
