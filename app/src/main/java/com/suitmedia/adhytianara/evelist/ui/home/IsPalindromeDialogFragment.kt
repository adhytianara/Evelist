package com.suitmedia.adhytianara.evelist.ui.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.suitmedia.adhytianara.evelist.R


class IsPalindromeDialogFragment : DialogFragment() {
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val name = viewModel.getName()
            val result = viewModel.nameIsPalindrome(name)
            builder.setMessage(result)
                .setPositiveButton(
                    getString(R.string.continue_txt)
                ) { _, _ ->
                    val fragment = MenuFragment()
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit()
                }
                .setNegativeButton(
                    getString(R.string.cancel)
                ) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException(getString(R.string.activity_cannot_be_null))
    }
}