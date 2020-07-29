package com.example.location_vietnam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_location.*

class EditLocation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_location)
        val locationData: LocationData = intent.getSerializableExtra("edit_data") as LocationData
        edit_date.setText(locationData.locaDate)
        edit_full_date.setText(locationData.locaFullDate)
        edit_location_name.setText(locationData.locaName)
        edit_location_description.setText(locationData.locaDescription)
        edit_location_feedback.setText(locationData.locaFeedBack)
        edit_image_url.setText(locationData.locaIamge)
        edit_bt_save.setOnClickListener {
            val addDate: String = edit_date.text.toString()
            val addFullDate: String = edit_full_date.text.toString()
            val addLocaName: String = edit_location_name.text.toString()
            val addLocaDescription: String = edit_location_description.text.toString()
            val addLocaFeedBack: String = edit_location_feedback.text.toString()
            val addLocaImage: String = edit_image_url.text.toString()
            val chageLocation = LocationData(
                addDate, addLocaName, addLocaDescription, addLocaImage, addFullDate
                , addLocaFeedBack,
                R.drawable.editblue,
                R.drawable.shareblue,
                R.drawable.deletblue
            )
            val returnIntent = Intent()
            returnIntent.putExtra("edit_data",chageLocation)
            setResult(Activity.RESULT_OK,returnIntent)
            finish()

        }
        edit_bt_cancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

}