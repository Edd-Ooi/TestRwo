package com.habittracker.nativeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.habittracker.nativeapp.R

class RemindersFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_reminders, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val preview = view.findViewById<TextView>(R.id.reminderPreview)
        view.findViewById<RadioGroup>(R.id.toneGroup).setOnCheckedChangeListener { _, checkedId ->
            preview.text = when (checkedId) {
                R.id.toneNeutral -> "Rappel neutre ⚪"
                R.id.toneMotiv -> "C'est l'heure 🔥"
                else -> "Petit rappel doux 🌿"
            }
        }
        view.findViewById<android.widget.RadioButton>(R.id.toneSoft).isChecked = true
    }
}
