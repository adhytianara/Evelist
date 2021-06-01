package com.suitmedia.adhytianara.evelist.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suitmedia.adhytianara.evelist.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val myFragment = LoginFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, myFragment)
            .commit()
    }
}