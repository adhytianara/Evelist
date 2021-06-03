package com.suitmedia.adhytianara.evelist.ui.event

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.suitmedia.adhytianara.evelist.R
import com.suitmedia.adhytianara.evelist.databinding.ActivityEventBinding


class EventActivity : AppCompatActivity() {
    companion object {
        private const val EVENT_LIST_FRAGMENT = "event_list"
        private const val MAPS_FRAGMENT = "maps"
    }

    private lateinit var binding: ActivityEventBinding
    private lateinit var actionBarView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_action_bar)
        actionBarView = supportActionBar?.customView!!
        setOnClickActionBarItems()

        val myFragment = EventListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, myFragment, EVENT_LIST_FRAGMENT)
            .commit()
    }

    private fun setOnClickActionBarItems() {
        val btnPlus = actionBarView.findViewById<ImageButton>(R.id.btn_plus)
        btnPlus.setOnClickListener {
            if (isDisplayed(EVENT_LIST_FRAGMENT)) {
                displayMapsFragment()
            } else if (isDisplayed(MAPS_FRAGMENT)) {
                displayEventListFragment()
            }
        }
    }

    private fun displayEventListFragment() {
        val myFragment = EventListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, myFragment, EVENT_LIST_FRAGMENT)
            .commit()
    }

    private fun displayMapsFragment() {
        val myFragment = MapsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, myFragment, MAPS_FRAGMENT)
            .commit()
    }

    private fun isDisplayed(tag: String): Boolean {
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        return (fragment != null && fragment.isVisible)
    }
}