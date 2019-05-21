package hu.ait.plantordieapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText
import hu.ait.plantordieapp.data.Plant
import kotlinx.android.synthetic.main.new_plant_dialog.*
import kotlinx.android.synthetic.main.new_plant_dialog.view.*
import java.lang.RuntimeException
import java.util.*

class PlantDialog : DialogFragment() {

    interface PlantHandler {
        fun plantCreated(item: Plant)
        fun plantUpdated(item: Plant)
    }

    private lateinit var plantHandler: PlantHandler

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is PlantHandler) {
            plantHandler = context
        } else {
            throw RuntimeException(
                "The activity does not implement the PlantHandlerInterface"
            )
        }
    }

    private lateinit var etNickName: EditText
    private lateinit var etType: EditText
    private lateinit var etText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("New Plant")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_plant_dialog, null
        )

        etNickName = rootView.etPlantName
        etType = rootView.etPlantType
        etText = rootView.etPlantDesc
        builder.setView(rootView)

        val arguments = this.arguments

        // IF I AM IN EDIT MODE
        if (arguments != null && arguments.containsKey(
                ScrollingActivity.KEY_ITEM_TO_EDIT
            )
        ) {

            val plantItem = arguments.getSerializable(
                ScrollingActivity.KEY_ITEM_TO_EDIT
            ) as Plant

            etNickName.setText(plantItem.nickname)
            etType.setText(plantItem.plant)
            etText.setText(plantItem.description)

            builder.setTitle("Edit plant")
        }

        builder.setPositiveButton("OK") { dialog, witch ->
            // empty
        }

        return builder.create()
    }


    override fun onResume() {
        super.onResume()

        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etNickName.text.isNotEmpty()) {
                val arguments = this.arguments
                // IF EDIT MODE
                if (arguments != null && arguments.containsKey(ScrollingActivity.KEY_ITEM_TO_EDIT)) {
                    handlePlantEdit()
                } else {
                    handlePlantCreate()
                }

                dialog.dismiss()
            } else {
                etPlantName.error = "This field can not be empty"
            }
        }
    }

    private fun handlePlantCreate() {
        plantHandler.plantCreated(
            Plant(
                null,
                etNickName.text.toString(),
                etType.toString(),
                etText.text.toString(),
                false
            )
        )
    }

    private fun handlePlantEdit() {
        val plantToEdit = arguments?.getSerializable(
            ScrollingActivity.KEY_ITEM_TO_EDIT
        ) as Plant
        plantToEdit.plant = etType.text.toString()
        plantToEdit.description = etText.text.toString()
        plantToEdit.nickname = etNickName.text.toString()

        plantHandler.plantUpdated(plantToEdit)
    }

}