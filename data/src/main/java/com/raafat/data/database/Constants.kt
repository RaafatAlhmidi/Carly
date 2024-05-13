package com.raafat.data.database

data object Constants{
    const val BRAND_TABLE = "brands"
    const val BRAND_ID_COLUMN = "brand_id"
    const val BRAND_NAME_COLUMN = "brand_name"

    const val FEATURE_TABLE = "features"
    const val FEATURE_ID_COLUMN = "feature_id"
    const val FEATURE_NAME_COLUMN = "feature_name"

    const val SERIES_TABLE = "series"
    const val SERIES_ID_COLUMN = "series_id"
    const val SERIES_NAME_COLUMN = "series_name"
    const val SERIES_MAXIMUM_YEAR_COLUMN = "series_maximum_year"
    const val SERIES_MINIMUM_YEAR_COLUMN = "series_minimum_year"

    const val SERIES_AND_FEATURE_CROSS_REF_TABLE = "series_feature"
    const val SERIES_AND_FUEL_CROSS_REF_TABLE = "series_fuel"

    const val CAR_TABLE = "cars"
    const val CAR_YEAR_COLUMN = "car_year"
    const val CAR_ID_COLUMN = "car_id"
    const val CAR_IS_SELECTED_COLUMN = "car_is_selected"


    const val FUEL_TYPE_TABLE = "fuel_types"
    const val FUEL_ID_COLUMN = "fuel_id"
    const val FUEL_TYPE_NAME_COLUMN = "fuel_type_name"

}