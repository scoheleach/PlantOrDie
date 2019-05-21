package hu.ait.plantordieapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import hu.ait.plantordieapp.data.Plant
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.activity_searchable.*

class SearchableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

        navSearch.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        btnSearch.setOnClickListener{
            doMySearch(etSearch.text.toString())
        }
    }

    override fun onNewIntent(intent: Intent) {
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
    }

    private fun doMySearch(query: String) {
        var intentDetails = Intent()
        intentDetails.setClass(this@SearchableActivity, PlantInfoActivity::class.java)

        intentDetails.putExtra("PLANT_NAME", query)
        startActivity(intentDetails)
    }

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
