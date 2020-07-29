package com.example.location_vietnam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_eatting.*



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            title = "TourNote"
        }
        val adapter = MyViewPageAdapter(supportFragmentManager)


        adapter.addFragment(EattingFragment(), "An Uong")
        adapter.addFragment(ShoppingFragment(), "mua sam")
        adapter.addFragment(VisitingFragment(), "tham quan")
        view_page.adapter = adapter

        tabs.setupWithViewPager(view_page)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



}