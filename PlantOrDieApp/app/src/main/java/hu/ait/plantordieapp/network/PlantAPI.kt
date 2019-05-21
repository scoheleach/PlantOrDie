package hu.ait.plantordieapp.network

import hu.ait.plantordieapp.data.PlantResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlantAPI {
    @GET("/api/species")
    fun getPlantInfo(@Query("q") base: String) : Call<PlantResult>
}