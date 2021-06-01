package com.suitmedia.adhytianara.evelist.ui.event

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.adhytianara.evelist.databinding.ActivityEventBinding
import com.suitmedia.adhytianara.evelist.ui.home.MenuFragment.Companion.EVENT_DATA
import com.suitmedia.adhytianara.evelist.viewmodel.ViewModelFactory


class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    private lateinit var eventAdapter: EventAdapter
    private lateinit var viewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "EVENT"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]

        viewModel.getEventList()
        observeEventList()


        setupRecyclerViewAdapter()
        setupRecyclerView()
    }

    private fun observeEventList() {
        viewModel.eventList.observe(this, { eventList ->
            eventAdapter.setData(eventList)
        })
    }

    private fun setupRecyclerViewAdapter() {
        eventAdapter = EventAdapter()
        eventAdapter.onItemClick = { selectedData ->
            val message: String = selectedData.name
            val intent = Intent()
            intent.putExtra(EVENT_DATA, message)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = eventAdapter
        }
    }
}