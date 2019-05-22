package hu.ait.plantordieapp

import android.app.SearchManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.bumptech.glide.Glide
import hu.ait.plantordieapp.data.PlantResult
import hu.ait.plantordieapp.network.PlantAPI
import kotlinx.android.synthetic.main.activity_plant_info.*

class PlantInfoActivity : AppCompatActivity() {

    private val HOST_URL = "https://trefle.io/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_info)

        var plant = intent.getStringExtra(SearchManager.QUERY)
        val API_KEY = "NTdmZHVNZlJtR09iY2lTN3VZVlJ2UT09"

     //   if (intent.extras.containsKey("PLANT_NAME")) {
//        if (Intent.ACTION_SEARCH == intent.action) {
////            plant = intent.getStringExtra("PLANT_NAME")
//            plant = intent.getStringExtra(SearchManager.QUERY)
//        }

        val retrofit = Retrofit.Builder()
            .baseUrl(HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val plantAPI = retrofit.create(PlantAPI::class.java)
        val plantCall = plantAPI.getPlantInfo(plant, API_KEY)

        plantCall.enqueue(object : Callback<PlantResult> {
            override fun onFailure(call: Call<PlantResult>, t: Throwable) {
                var errorString = "Error. \n" + t.message
                tvPlantCare.text = errorString
                tvPlantType.text = "Could not find $plant"
                var imageurl = "https://images-na.ssl-images-amazon.com/images/I/81WVK%2B1Fc4L._SX425_.jpg"
                Glide.with(this@PlantInfoActivity).load((imageurl)).into(imgView)
            }

            override fun onResponse(call: Call<PlantResult>, response: Response<PlantResult>) {
                val plantResult = response.body()
                tvPlantType.text = "$plant"

                var careString = ""
                careString += "Lifespan: " + plantResult?.specifications?.lifespan.toString() + "\n"
                careString += "Toxicity: " + plantResult?.specifications?.toxicity.toString() + "\n"
                careString += "Precipitation Minimum: " + plantResult?.growth?.precipitation_minimum?.inches.toString() + " inches \n"
                careString += "Precipitation Maximum: " + plantResult?.growth?.precipitation_maximum?.inches.toString() + " inches \n"
                careString += "Temperature Minimum: " + plantResult?.growth?.temperature_minimum?.deg_c.toString() + " oC\n"
                careString += "Root Depth Minimum: " + plantResult?.growth?.root_depth_minimum?.inches.toString() + " inches\n"
                careString += "Fertility Requirement: " + plantResult?.growth?.fertility_requirement.toString() + "\n"

                tvPlantCare.text = careString

                var imageurl = "" + plantResult?.images?.get(0)
                //imageurl = "https://images-na.ssl-images-amazon.com/images/I/81WVK%2B1Fc4L._SX425_.jpg"
                Glide.with(this@PlantInfoActivity).load((imageurl)).into(imgView)
            }
        })
    }
}
