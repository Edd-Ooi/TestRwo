package com.habittracker.nativeapp

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class HabitStorage(context: Context) {
    private val prefs = context.getSharedPreferences("habit_tracker_native", Context.MODE_PRIVATE)

    fun load(): MutableList<Habit> {
        val raw = prefs.getString("habits", null) ?: return defaultHabits()
        return try {
            val arr = JSONArray(raw)
            MutableList(arr.length()) { i ->
                val o = arr.getJSONObject(i)
                Habit(
                    id = o.getString("id"),
                    name = o.getString("name"),
                    anchor = o.getString("anchor"),
                    micro = o.optString("micro", "Juste commencer"),
                    feel = o.optString("feel", "Je me sens mieux"),
                    done = o.getBoolean("done"),
                    streak = o.optInt("streak", 0)
                )
            }
        } catch (_: Exception) { defaultHabits() }
    }

    fun save(habits: List<Habit>) {
        val arr = JSONArray()
        habits.forEach {
            arr.put(JSONObject().put("id", it.id).put("name", it.name).put("anchor", it.anchor).put("micro", it.micro).put("feel", it.feel).put("done", it.done).put("streak", it.streak))
        }
        prefs.edit().putString("habits", arr.toString()).apply()
    }

    fun loadGraceDays(): Int = prefs.getInt("grace_days", 2)
    fun saveGraceDays(value: Int) { prefs.edit().putInt("grace_days", value.coerceIn(0, 2)).apply() }

    private fun defaultHabits() = mutableListOf(
        Habit("h1", "Lire", "Après mon café", "Lire 2 minutes", "Je me sens plus calme"),
        Habit("h2", "Respirer", "Après la douche", "3 respirations", "Je me sens ancré·e"),
        Habit("h3", "Marcher", "Après déjeuner", "Marcher 2 minutes", "Je me sens léger·ère")
    )
}
