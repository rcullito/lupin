package com.robertculliton.moonlight.individual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.robertculliton.moonlight.R
import com.robertculliton.moonlight.database.SleepDatabase
import com.robertculliton.moonlight.databinding.FragmentIndividualBinding


class IndividualFragment : Fragment() {

  private lateinit var binding: FragmentIndividualBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = DataBindingUtil.inflate<FragmentIndividualBinding>(inflater,
      R.layout.fragment_individual, container, false)
    val application = requireNotNull(this.activity).application
    val dataSource = SleepDatabase.getInstance(application).sleepPositionDao

    val arguments =
      IndividualFragmentArgs.fromBundle(
        requireArguments()
      )
    val date = arguments.date

    val viewModelFactory =
      IndividualViewModelFactory(
        dataSource,
        application
      )
    val individualViewModel: IndividualViewModel by activityViewModels({ viewModelFactory })
    individualViewModel.getSpecificDate(date)
    binding.setLifecycleOwner(this)

    individualViewModel.positions.observe(viewLifecycleOwner, Observer {
      buildChart(binding, it)
    })

    return binding.root

  }

}
