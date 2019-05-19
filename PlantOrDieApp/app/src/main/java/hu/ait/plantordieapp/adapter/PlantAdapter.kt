package hu.ait.plantordieapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.ait.plantordieapp.R
import hu.ait.plantordieapp.ScrollingActivity
import hu.ait.plantordieapp.data.AppDatabase
import hu.ait.plantordieapp.data.Plant
import hu.ait.plantordieapp.touch.PlantTouchHelperCallback
import kotlinx.android.synthetic.main.plant_row.view.*
import java.util.*

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.ViewHolder>, PlantTouchHelperCallback {


    var plantItems = mutableListOf<Plant>()

    private val context: Context

    constructor(context: Context, listPlans: List<Plant>) : super() {
        this.context = context
        plantItems.addAll(listPlans)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val plantRowView = LayoutInflater.from(context).inflate(
            R.layout.plant_row, viewGroup, false
        )
        return ViewHolder(plantRowView)
    }

    override fun getItemCount(): Int {
        return plantItems.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val plant  = plantItems.get(viewHolder.adapterPosition)

        viewHolder.tvDate.text = plant.createDate
        viewHolder.cbDone.text = plant.plantText
        viewHolder.cbDone.isChecked = plant.done

        viewHolder.btnDelete.setOnClickListener {
            deletePlant(viewHolder.adapterPosition)
        }

        viewHolder.cbDone.setOnClickListener {
            plant.done = viewHolder.cbDone.isChecked
            updatePlant(plant)
        }

        viewHolder.btnEdit.setOnClickListener {
            (context as ScrollingActivity).showEditPlantDialog(plant,
                viewHolder.adapterPosition)
        }
    }

    fun updatePlant(plant: Plant) {
        Thread{
            AppDatabase.getInstance(context).plantDao().updatePlant(plant)


        }.start()
    }

    fun updatePlant(plant: Plant, editIndex: Int) {
        plantItems.set(editIndex, plant)
        notifyItemChanged(editIndex)
    }


    fun addPlant(plant: Plant) {
        plantItems.add(0, plant)
        //notifyDataSetChanged()
        notifyItemInserted(0)
    }

    fun deletePlant(deletePosition: Int) {
        Thread{
            AppDatabase.getInstance(context).plantDao().deletePlant(plantItems.get(deletePosition))

            (context as ScrollingActivity).runOnUiThread {
                plantItems.removeAt(deletePosition)
                notifyItemRemoved(deletePosition)
            }
        }.start()
    }

    override fun onDismissed(position: Int) {
        deletePlant(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(plantItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvDate = itemView.tvDate
        var cbDone = itemView.cbDone
        var btnDelete = itemView.btnDelete
        var btnEdit = itemView.btnEdit
    }

}