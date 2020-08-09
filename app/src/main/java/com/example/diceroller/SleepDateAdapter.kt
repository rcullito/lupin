package com.example.diceroller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diceroller.databinding.ListItemSleepNightBinding

/**
 * ViewHolder that holds a single [TextView].
 *
 * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
 * to the RecyclerView such as where on the screen it was last drawn during scrolling.
 */

class SleepNightListener(val clickListener: () -> Unit) {
  fun onClick() = clickListener()
}

class SleepDateAdapter(val clickListener: SleepNightListener): RecyclerView.Adapter<SleepDateAdapter.ViewHolder>() {
  var data = listOf<String>()
      set(value) {
        field = value
        notifyDataSetChanged()
      }

  override fun getItemCount(): Int {
    return data.size
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = data[position]
    holder.bind(clickListener, item)
  }

  class ViewHolder private constructor(val binding: ListItemSleepNightBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(clickListener: SleepNightListener, item: String) {
      binding.sleepDate.text = item
      binding.clickListener = clickListener
      binding.executePendingBindings()
    }

    companion object {
      fun from(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
      }
    }
  }
}


