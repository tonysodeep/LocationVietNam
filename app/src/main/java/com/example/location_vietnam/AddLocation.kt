package com.example.location_vietnam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_location.*

class AddLocation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)
        supportActionBar?.apply {
            title = "add location"
        }
        bt_save.setOnClickListener {
            val addDate: String = ed_date.text.toString()
            val addFullDate: String = ed_full_date.text.toString()
            val addLocaName: String = ed_location_name.text.toString()
            val addLocaDescription: String = ed_location_description.text.toString()
            val addLocaFeedBack: String = ed_location_feedback.text.toString()
            val addLocaImage: String = ed_image_url.text.toString()
            val newLocation = LocationData(
                addDate, addLocaName, addLocaDescription, addLocaImage, addFullDate
                , addLocaFeedBack,
                R.drawable.editblue,
                R.drawable.shareblue,
                R.drawable.deletblue
            )
            val returnIntent = Intent()
            returnIntent.putExtra("return_location",newLocation)
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }
        bt_cancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

}