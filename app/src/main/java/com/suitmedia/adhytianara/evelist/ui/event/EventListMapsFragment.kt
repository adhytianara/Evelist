package com.suitmedia.adhytianara.evelist.ui.event

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.adhytianara.evelist.R
import com.suitmedia.adhytianara.evelist.databinding.FragmentEventListMapsBinding
import com.suitmedia.adhytianara.evelist.ui.home.LoginFragment
import com.suitmedia.adhytianara.evelist.ui.home.MenuFragment
import com.suitmedia.adhytianara.evelist.viewmodel.ViewModelFactory

class EventListMapsFragment : Fragment() {
    private var _binding: FragmentEventListMapsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventListMapsViewModel
    private lateinit var eventAdapter: EventHorizontalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventListMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myFragment = MapsFragment()
        childFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, myFragment)
            .commit()

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[EventListMapsViewModel::class.java]
        viewModel.getEventList()
        observeEventList()

        setupRecyclerViewAdapter()
        setupRecyclerView()
    }


    private fun observeEventList() {
        viewModel.eventList.observe(viewLifecycleOwner, { eventList ->
            eventAdapter.setData(eventList)
        })
    }

    private fun setupRecyclerViewAdapter() {
        eventAdapter = EventHorizontalAdapter()
        eventAdapter.onItemClick = { selectedData ->
            val message: String = selectedData.name
            val intent = Intent()
            intent.putExtra(MenuFragment.EVENT_DATA, message)
            activity?.setResult(AppCompatActivity.RESULT_OK, intent)
            activity?.finish()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = eventAdapter
        }
    }
}