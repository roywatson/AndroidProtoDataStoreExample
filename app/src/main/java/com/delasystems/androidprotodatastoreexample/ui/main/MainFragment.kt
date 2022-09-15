package com.delasystems.androidprotodatastoreexample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.delasystems.androidprotodatastoreexample.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.iAmLearning.observe(viewLifecycleOwner) { newVal ->
            view?.findViewById<TextView>(R.id.learning_tv)?.text = "i_am_learning = ${newVal.toString()}"
        }
        viewModel.Color.observe(viewLifecycleOwner) { newVal ->
            view?.findViewById<TextView>(R.id.color_tv)?.text = "Favorite color = ${newVal.toString()}"
        }
        viewModel.Number.observe(viewLifecycleOwner) { newVal ->
            view?.findViewById<TextView>(R.id.number_tv)?.text = "Favorite number = ${newVal.toString()}"
        }

        view?.findViewById<Button>(R.id.set_learning_true_button)?.setOnClickListener {
            viewModel.setIAmLearning(true)
        }

        view?.findViewById<Button>(R.id.set_learning_false_button)?.setOnClickListener {
            viewModel.setIAmLearning(false)
        }

        view?.findViewById<Button>(R.id.set_color_green_button)?.setOnClickListener {
            viewModel.setColor("green")
        }

        view?.findViewById<Button>(R.id.set_color_blue_button)?.setOnClickListener {
            viewModel.setColor("blue")
        }

        view?.findViewById<Button>(R.id.set_mumber_43_button)?.setOnClickListener {
            viewModel.setNumber(43)
        }

        view?.findViewById<Button>(R.id.set_mumber_86_button)?.setOnClickListener {
            viewModel.setNumber(86)
        }

        view?.findViewById<Button>(R.id.reset_defaults_button)?.setOnClickListener {
            viewModel.resetDefaults()
        }

        viewModel.observeMyPreferensesWithFlow()
    }

}