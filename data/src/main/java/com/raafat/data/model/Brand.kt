package com.raafat.data.model

data class Brand(
    val name: String,
    val series: List<Series>
)

data class Series(
    val name: String,
    val maximumYear: Int,
    val minimumYear: Int
)
