package com.raafat.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.CarEntity
import com.raafat.data.database.relations.CarRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {


//    @Query("SELECT * FROM ${Constants.BRAND_TABLE} ")

    @Query("SELECT * FROM ${Constants.CAR_TABLE}")
    fun getAll(): Flow<List<CarRelations>>

    @Query("SELECT * FROM ${Constants.CAR_TABLE} WHERE ${Constants.CAR_IS_SELECTED_COLUMN} = 1")
    fun getSelectedCar(): Flow<CarRelations?>

    @Query("SELECT * FROM ${Constants.CAR_TABLE} WHERE ${Constants.CAR_IS_SELECTED_COLUMN} = 1")
    fun getSelectedCarSync(): CarRelations?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCar(carEntity: CarEntity): Long

    @Update
    suspend fun updateCar(carEntity: CarEntity)

    @Delete
    suspend fun deleteCar(carEntity: CarEntity)
}