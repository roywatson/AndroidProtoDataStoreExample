/*
Copyright (C) 2022 Roy Watson

Permission is hereby granted, free of charge, to any person obtaining a copy of this
software and associated documentation files (the "Software"), to deal in the Software
without restriction, including without limitation the rights to use, copy, modify, merge,
publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies
or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
OR OTHER DEALINGS IN THE SOFTWARE.
*/
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