package com.habittracker.nativeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.habittracker.nativeapp.HabitAdapter
import com.habittracker.nativeapp.HabitStorage
import com.habittracker.nativeapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val storage = HabitStorage(requireContext())
        val habits = storage.load()
        val progress = view.findViewById<TextView>(R.id.homeProgress)
        val greet = view.findViewById<TextView>(R.id.homeGreeting)
        val streak = view.findViewById<TextView>(R.id.homeStreak)
        val grace = view.findViewById<TextView>(R.id.homeGrace)
        view.findViewById<TextView>(R.id.homeDate).text = SimpleDateFormat("EEEE d MMMM", Locale.FRENCH).format(Date())

        val adapter = HabitAdapter(habits) {
            storage.save(habits)
            val done = habits.count { it.done }
            progress.text = "Progression: $done/${habits.size}"
            greet.text = if (done == habits.size && habits.isNotEmpty()) "Bravo ! 🎉" else "Bonjour 👋"
            streak.text = "🔥 ${habits.sumOf { it.streak }} jours de suite"
        }
        view.findViewById<RecyclerView>(R.id.homeList).apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
        val done = habits.count { it.done }
        progress.text = "Progression: $done/${habits.size}"
        greet.text = if (done == habits.size && habits.isNotEmpty()) "Bravo ! 🎉" else "Bonjour 👋"
        streak.text = "🔥 ${habits.sumOf { it.streak }} jours de suite"
        grace.text = "🛡️ ${storage.loadGraceDays()} jours de rattrapage disponibles"
    }
}
