package com.habittracker.nativeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.habittracker.nativeapp.ui.AddHabitDialogFragment
import com.habittracker.nativeapp.ui.GardenFragment
import com.habittracker.nativeapp.ui.HomeFragment
import com.habittracker.nativeapp.ui.ProfileFragment
import com.habittracker.nativeapp.ui.RemindersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) open(HomeFragment())

        findViewById<BottomNavigationView>(R.id.bottomNav).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> open(HomeFragment())
                R.id.nav_garden -> open(GardenFragment())
                R.id.nav_reminders -> open(RemindersFragment())
                R.id.nav_profile -> open(ProfileFragment())
            }
            true
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            AddHabitDialogFragment { open(HomeFragment()) }
                .show(supportFragmentManager, "add_habit")
        }
    }

    private fun open(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
