package com.habittracker.nativeapp.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.habittracker.nativeapp.Habit
import com.habittracker.nativeapp.HabitStorage

class AddHabitDialogFragment(private val onDone: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ctx = requireContext()
        val storage = HabitStorage(ctx)
        val name = EditText(ctx).apply { hint = "Quelle activité ?" }
        val anchor = EditText(ctx).apply { hint = "Quand (ancre) ?" }
        val micro = EditText(ctx).apply { hint = "Version minuscule" }
        val feel = EditText(ctx).apply { hint = "Comment te sentir ?" }

        val wrap = android.widget.LinearLayout(ctx).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(40, 24, 40, 0)
            addView(name); addView(anchor); addView(micro); addView(feel)
        }

        return AlertDialog.Builder(ctx)
            .setTitle("Nouvelle activité")
            .setView(wrap)
            .setPositiveButton("Créer") { _, _ ->
                val list = storage.load()
                if (list.size < 3) {
                    list.add(Habit("h${System.currentTimeMillis()}", name.text.toString().ifBlank { "Habitude" }, anchor.text.toString().ifBlank { "Après mon café" }, micro.text.toString().ifBlank { "Juste commencer" }, feel.text.toString().ifBlank { "Apaisé·e" }))
                    storage.save(list)
                }
                onDone()
            }
            .setNegativeButton("Annuler", null)
            .create()
    }
}
