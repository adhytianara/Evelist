package com.suitmedia.adhytianara.evelist.ui.guest

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.suitmedia.adhytianara.evelist.databinding.ActivityGuestBinding
import com.suitmedia.adhytianara.evelist.ui.home.MenuFragment
import com.suitmedia.adhytianara.evelist.viewmodel.ViewModelFactory

class GuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestBinding
    private lateinit var guestAdapter: GuestAdapter
    private lateinit var viewModel: GuestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "GUEST"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[GuestViewModel::class.java]

        viewModel.getGuestList()
        observeGuestList()


        setupRecyclerViewAdapter()
        setupRecyclerView()
    }

    private fun observeGuestList() {
        viewModel.guestList.observe(this, { guestList ->
            guestAdapter.setData(guestList)
        })
    }

    private fun setupRecyclerViewAdapter() {
        guestAdapter = GuestAdapter()
        guestAdapter.onItemClick = { selectedData ->
            val name: String = selectedData.name
            val message: String = viewModel.getGuestMessage(selectedData.birthdate)

            val intent = Intent()
            intent.putExtra(MenuFragment.GUEST_DATA, name)
            intent.putExtra(MenuFragment.GUEST_MESSAGE, message)

            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun setupRecyclerView() {
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
        with(binding.rvGuests) {
            layoutManager = GridLayoutManager(context, spanCount)

            setHasFixedSize(true)
            adapter = guestAdapter
        }
    }
}