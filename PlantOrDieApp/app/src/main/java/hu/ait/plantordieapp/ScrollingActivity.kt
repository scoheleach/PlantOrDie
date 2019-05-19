package hu.ait.plantordieapp

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import hu.ait.plantordieapp.adapter.PlantAdapter
import hu.ait.plantordieapp.data.AppDatabase
import hu.ait.plantordieapp.data.Plant
import hu.ait.plantordieapp.touch.PlantRecyclerTouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt

class ScrollingActivity : AppCompatActivity(), PlantDialog.PlantHandler {

    companion object {
        val KEY_ITEM_TO_EDIT = "KEY_ITEM_TO_EDIT"
    }

    lateinit var plantAdapter : PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            showAddPlantDialog()
        }

        if (!wasOpenedEarlier()) {
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.fab)
                .setPrimaryText("New PLANT")
                .setSecondaryText("Click here to create new plant items")
                .show()
        }

        saveFirstOpenInfo()
        initRecyclerViewFromDB()

    }

    fun saveFirstOpenInfo() {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = sharedPref.edit()
        editor.putBoolean("KEY_WAS_OPEN", true)
        editor.apply()
    }

    fun wasOpenedEarlier() : Boolean {
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)

        return sharedPref.getBoolean("KEY_WAS_OPEN", false)
    }



    private fun initRecyclerViewFromDB() {
        Thread {
            var plantList = AppDatabase.getInstance(this@ScrollingActivity).plantDao().getAllPlants()

            runOnUiThread {
                // Update UI

                plantAdapter = PlantAdapter(this, plantList)

                recyclerPlant.layoutManager = LinearLayoutManager(this)

                //recyclerPlant.layoutManager = GridLayoutManager(this, 2)
                //recyclerPlant.layoutManager = StaggeredGridLayoutManager(2,
                //    StaggeredGridLayoutManager.VERTICAL)

                recyclerPlant.adapter = plantAdapter

                val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
                recyclerPlant.addItemDecoration(itemDecoration)

                val callback = PlantRecyclerTouchCallback(plantAdapter)
                val touchHelper = ItemTouchHelper(callback)
                touchHelper.attachToRecyclerView(recyclerPlant)
            }

        }.start()
    }

    private fun showAddPlantDialog() {
        PlantDialog().show(supportFragmentManager, "TAG_PLANT_DIALOG")
    }

    var editIndex: Int = -1

    public fun showEditPlantDialog(plantToEdit: Plant, idx: Int) {
        editIndex = idx
        val editItemDialog = PlantDialog()

        val bundle = Bundle()
        bundle.putSerializable(KEY_ITEM_TO_EDIT, plantToEdit)
        editItemDialog.arguments = bundle

        editItemDialog.show(supportFragmentManager,
            "EDITITEMDIALOG")
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



    override fun plantCreated(item: Plant) {
        Thread {
            var newId = AppDatabase.getInstance(this).plantDao().insertPlant(item)

            item.plantId = newId

            runOnUiThread {
                plantAdapter.addPlant(item)
            }
        }.start()
    }

    override fun plantUpdated(item: Plant) {
        Thread {
            AppDatabase.getInstance(this).plantDao().updatePlant(item)

            runOnUiThread {
                plantAdapter.updatePlant(item, editIndex)
            }
        }.start()
    }

}
