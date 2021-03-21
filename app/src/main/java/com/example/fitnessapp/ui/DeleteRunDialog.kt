package com.example.fitnessapp.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.fitnessapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DeleteRunDialog : DialogFragment() {

    private var agreeListener: (() -> Unit)? = null

    fun setAgreeListener(listener: () -> Unit) {
        agreeListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
                .setTitle("Delete the Run?")
                .setMessage("Are you sure to delete the run?")
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("Yes") { _, _ ->
                    agreeListener?.let { yes ->
                        yes()
                    }
                }
                .setNegativeButton("No") { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .create()
    }
}