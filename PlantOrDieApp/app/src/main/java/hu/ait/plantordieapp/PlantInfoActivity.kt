package hu.ait.plantordieapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.bumptech.glide.Glide
import hu.ait.plantordieapp.network.PlantAPI

class PlantInfoActivity : AppCompatActivity() {

    private val HOST_URL = "https://trefle.io/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_info)

        var plant = ""
        if (intent.extras.containsKey("PLANT_NAME")) {
            plant = intent.getStringExtra("PLANT_NAME")
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherAPI = retrofit.create(PlantAPI::class.java)
    }
}
