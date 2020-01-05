package com.example.random_advice_generator.view


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.random_advice_generator.R
import kotlinx.android.synthetic.main.fragment_generate.*

/**
 * A simple [Fragment] subclass.
 */
class GenerateFragment : Fragment() {

    private lateinit var viewModel: GenerateFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        btnRefresh.setOnClickListener { viewModel.getRandomAdvice() }
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(GenerateFragmentViewModel::class.java)
        viewModel.onAdviceChanged = this::onAdviceChanged
        viewModel.getRandomAdvice()
    }

    private fun onAdviceChanged(advice: String, failed: Boolean = false){
        if (!failed)
            tvAdvice.text = advice
        else
            tvAdvice.text = "Woops, something went wrong, try again!"
    }

}
