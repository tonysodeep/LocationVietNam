package com.example.location_vietnam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_location_detail.*

class LocationDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)
        setSupportActionBar(deatail_toolbar)
        supportActionBar?.apply {
            title = "Location Detail"
        }
        val location: LocationData = intent.getSerializableExtra("data") as LocationData
        Glide.with(this)
            .load(location.locaIamge)
            .into(iv_location)
        tv_location_name.text = location.locaName
        tv_location_description.text = location.locaDescription
        tv_location_feedback.text = location.locaFeedBack
    }

}