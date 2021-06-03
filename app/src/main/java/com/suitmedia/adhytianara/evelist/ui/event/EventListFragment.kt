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
import com.suitmedia.adhytianara.evelist.databinding.FragmentEventBinding
import com.suitmedia.adhytianara.evelist.ui.home.MenuFragment
import com.suitmedia.adhytianara.evelist.viewmodel.ViewModelFactory

class EventListFragment : Fragment() {
    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventListViewModel
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[EventListViewModel::class.java]
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
        eventAdapter = EventAdapter()
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
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = eventAdapter
        }
    }
}