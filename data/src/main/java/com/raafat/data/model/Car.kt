package com.raafat.data.model

data class Car(
    val id: Int?,
    val brand: Brand,
    val series: Series,
    val year: Int,
    val fuelType: FuelType,
    val isSelected: Boolean
)