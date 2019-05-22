package hu.ait.plantordieapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.activity_searchable.*
import kotlinx.android.synthetic.main.activity_plant_info.*

class SearchableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

        navSearchScreen.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        btnSearch.setOnClickListener() {
            var plantString = etSearch.text.toString()
            showSearchPlantResult(plantString)
        }
    }

    private fun showSearchPlantResult(searchPlant: String) {
        var intentDetails = Intent()
        intentDetails.setClass(this@SearchableActivity, PlantInfoActivity::class.java)

        intentDetails.putExtra("PLANT_NAME", searchPlant)
        startActivity(intentDetails)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_my_plants -> {
                var intentSearch= Intent()
                intentSearch.setClass(this@SearchableActivity,
                    ScrollingActivity::class.java)

                startActivity(intentSearch)

                return@OnNavigationItemSelectedListener true
            }

            R.id.action_search-> {
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
