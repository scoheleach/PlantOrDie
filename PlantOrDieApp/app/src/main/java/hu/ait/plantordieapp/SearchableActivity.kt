package hu.ait.plantordieapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.SearchView
import hu.ait.plantordieapp.data.Plant
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.activity_searchable.*

class SearchableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

        navSearch.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        searchBar.isSubmitButtonEnabled = true

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.setClass(this@SearchableActivity, PlantInfoActivity::class.java)
            startActivity(intent)
           // intent.getStringExtra(SearchManager.QUERY)
        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        (searchBar as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }

    }


//    override fun onNewIntent(intent: Intent) {
//        setIntent(intent)
//        handleIntent(intent)
//    }
//
//    private fun handleIntent(intent: Intent) {
//        if (Intent.ACTION_SEARCH == intent.action) {
//            intent.getStringExtra(SearchManager.QUERY)
//        }
//    }

//    private fun doMySearch(query: String) {
//        var intentDetails = Intent()
//        intentDetails.setClass()
//
//        intentDetails.putExtra("PLANT_NAME", query)
//        startActivity(intentDetails)
//    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_search -> {
                return@OnNavigationItemSelectedListener true
            }

            R.id.action_my_plants-> {

                    var intentMyPlants= Intent()
                intentMyPlants.setClass(this@SearchableActivity,
                        ScrollingActivity::class.java)

                    startActivity(intentMyPlants)

                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

}
