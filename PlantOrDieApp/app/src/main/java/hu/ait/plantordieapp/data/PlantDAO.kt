package hu.ait.plantordieapp.data

import android.arch.persistence.room.*


@Dao
interface PlantDAO {
    @Query("SELECT * FROM plant")
    fun getAllPlants(): List<Plant>

    @Insert
    fun insertPlant(plant: Plant): Long

    @Insert
    fun insertPlant(vararg plant: Plant): List<Long>

    @Delete
    fun deletePlant(plant: Plant)

    @Update
    fun updatePlant(plan: Plant)
}