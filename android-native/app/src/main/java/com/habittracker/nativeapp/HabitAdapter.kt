package com.habittracker.nativeapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter(
    private val items: MutableList<Habit>,
    private val onChanged: () -> Unit
) : RecyclerView.Adapter<HabitAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val check: CheckBox = v.findViewById(R.id.checkDone)
        val anchor: TextView = v.findViewById(R.id.anchorText)
        val micro: TextView = v.findViewById(R.id.microText)
        val feel: TextView = v.findViewById(R.id.feelText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val habit = items[position]
        holder.check.text = habit.name
        holder.check.isChecked = habit.done
        holder.anchor.text = "☕ ${habit.anchor}"
        holder.micro.text = "Micro: ${habit.micro}"
        holder.feel.text = habit.feel
        holder.feel.visibility = if (habit.done) View.VISIBLE else View.GONE
        holder.check.paintFlags = if (habit.done) holder.check.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else holder.check.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        holder.check.setOnCheckedChangeListener { _, isChecked ->
            habit.done = isChecked
            habit.streak = if (isChecked) habit.streak + 1 else 0
            onChanged()
            notifyItemChanged(position)
        }
    }

    fun data(): List<Habit> = items
}
