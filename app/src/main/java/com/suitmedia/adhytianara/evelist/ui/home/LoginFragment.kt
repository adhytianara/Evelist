package com.suitmedia.adhytianara.evelist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.suitmedia.adhytianara.evelist.R
import com.suitmedia.adhytianara.evelist.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_next) {
            val name = binding.inputName.text.toString()

            if (name.isNotBlank() && name.isNotEmpty()) {
                viewModel.setName(name)
                val fragment = MenuFragment()
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit()
            } else {
                binding.inputName.error = "Name cannot be empty or blank."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}