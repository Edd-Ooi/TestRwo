package com.habittracker.nativeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.habittracker.nativeapp.HabitStorage
import com.habittracker.nativeapp.R

class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val storage = HabitStorage(requireContext())
        val habits = storage.load()
        val score = if (habits.isEmpty()) 0 else (habits.count { it.done } * 100 / habits.size)
        view.findViewById<TextView>(R.id.profileScore).text = "$score%"
        view.findViewById<TextView>(R.id.profileGrace).text = "Grace days: ${storage.loadGraceDays()}"
    }
}
