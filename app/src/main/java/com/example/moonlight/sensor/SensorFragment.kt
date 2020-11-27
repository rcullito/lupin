package com.example.moonlight.sensor

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.moonlight.R
import com.example.moonlight.databinding.FragmentSensorBinding


class SensorFragment : Fragment() {

  private lateinit var binding: FragmentSensorBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  @SuppressLint("ServiceCast")
  @Suppress("DEPRECATION")
  fun <T> Context.isServiceRunning(service: Class<T>): Boolean {
    return (getSystemService(ACTIVITY_SERVICE) as ActivityManager)
      .getRunningServices(Integer.MAX_VALUE)
      .any { it -> it.service.className == service.name }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = DataBindingUtil.inflate<FragmentSensorBinding>(inflater,
      R.layout.fragment_sensor, container, false)


    // set the state initially before any button is clicked
    var running = context?.isServiceRunning(SensorService::class.java)

    if (running!!) {
      binding.cancelServiceButton.visibility = View.VISIBLE
      binding.startServiceButton.visibility = View.INVISIBLE
    } else {
      binding.cancelServiceButton.visibility = View.INVISIBLE
      binding.startServiceButton.visibility = View.VISIBLE
    }

    // TODO see if we need to set state manually in the wake of a button click
    // better to have it derived from the state of the service itself
    // rather than the click

    binding.startServiceButton.setOnClickListener {
      Log.i("SensorFragment", "start service click listener called")

      context?.let { it1 ->
        SensorService.startService(
          it1,
          "Tracking Sleep Movements"
        )
      }
    }

    binding.cancelServiceButton.setOnClickListener {
      Log.i("SensorFragment", "cancel service click listener called")
      context?.let { it1 ->
        SensorService.cancelService(
          it1
        )
      }

    }
    // Inflate the layout for this fragment
    return binding.root
  }
}
