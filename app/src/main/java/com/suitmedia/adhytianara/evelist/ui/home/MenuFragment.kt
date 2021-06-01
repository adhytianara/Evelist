package com.suitmedia.adhytianara.evelist.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.suitmedia.adhytianara.evelist.R
import com.suitmedia.adhytianara.evelist.databinding.FragmentMenuBinding
import com.suitmedia.adhytianara.evelist.ui.event.EventActivity


class MenuFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    companion object {
        const val REQUEST_EVENT_DATA = 1
        const val EVENT_DATA = "event_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = viewModel.getName()
        (resources.getString(R.string.nama) + name).also { binding.tvName.text = it }

        binding.btnEvent.setOnClickListener(this)
        binding.btnGuest.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_event -> {
                moveToEventActivity()
            }
            R.id.btn_guest -> {
            }
        }
    }

    private fun moveToEventActivity() {
        val intent = Intent(activity, EventActivity::class.java)
        startActivityForResult(intent, REQUEST_EVENT_DATA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_EVENT_DATA) {
                val message: String = data?.getStringExtra(EVENT_DATA).toString()
                binding.btnEvent.text = message
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}