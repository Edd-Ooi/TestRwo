package com.habittracker.nativeapp.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.habittracker.nativeapp.HabitStorage
import com.habittracker.nativeapp.R

class GardenFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_garden, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val habits = HabitStorage(requireContext()).load()
        val total = habits.sumOf { it.streak.coerceAtLeast(0) }
        view.findViewById<TextView>(R.id.gardenSummary).text = "$total jours cumulés · ${habits.size} plantes"
        val done = habits.count { it.done }
        val rate = if (habits.isEmpty()) 0 else (done * 100 / habits.size)
        view.findViewById<TextView>(R.id.gardenStreak).text = "Streak: $total"
        view.findViewById<TextView>(R.id.gardenMonth).text = "Ce mois: ${done * 6}"
        view.findViewById<TextView>(R.id.gardenRate).text = "Taux: $rate%"

        val heat = view.findViewById<GridLayout>(R.id.gardenHeatmap)
        heat.removeAllViews()
        repeat(28) { i ->
            val dot = View(requireContext())
            val p = GridLayout.LayoutParams()
            p.width = 30; p.height = 30
            p.setMargins(4,4,4,4)
            dot.layoutParams = p
            dot.setBackgroundColor(if (i % 3 == 0) Color.parseColor("#3D9E5F") else Color.parseColor("#E4E4E4"))
            heat.addView(dot)
        }
    }
}
