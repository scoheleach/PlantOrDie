package hu.ait.plantordieapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.bumptech.glide.Glide
import hu.ait.plantordieapp.data.PlantResult
import hu.ait.plantordieapp.network.PlantAPI
import kotlinx.android.synthetic.main.activity_plant_info.*
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.activity_searchable.*
import hu.ait.plantordieapp.adapter.PlantAdapter

class PlantInfoActivity : AppCompatActivity() {

    private val HOST_URL = "https://trefle.io/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_info)

        navPlant.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        var plant = ""
        val API_KEY = "NTdmZHVNZlJtR09iY2lTN3VZVlJ2UT09"

        if (intent.extras.containsKey("PLANT_NAME")) {
            plant = intent.getStringExtra("PLANT_NAME")
        }

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

        btnAddThisPlant.setOnClickListener() {
            showAddPlantDialog()
        }
    }

    private fun showAddPlantDialog() {
        PlantDialog().show(supportFragmentManager, "TAG_PLANT_DIALOG")
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_search -> {
                var intentSearch= Intent()
                intentSearch.setClass(this@PlantInfoActivity,
                    SearchableActivity::class.java)

                startActivity(intentSearch)

                return@OnNavigationItemSelectedListener true
            }

            R.id.action_my_plants-> {
                var intentScroll= Intent()
                intentScroll.setClass(this@PlantInfoActivity,
                    ScrollingActivity::class.java)

                startActivity(intentScroll)

                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
