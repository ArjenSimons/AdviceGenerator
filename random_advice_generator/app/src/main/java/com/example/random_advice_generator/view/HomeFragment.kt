package com.example.random_advice_generator.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.random_advice_generator.R
import com.example.random_advice_generator.model.Advice
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val advices = arrayListOf<Advice>()
    private val adapter = AdviceAdapter(advices, this::onRemoveButtonClicked)

    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.advices.sortBy { it.rating }
        initViews()
        initViewModel()
    }

    private fun initViews(){
        rvAdvice.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvAdvice.adapter = adapter
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)

        viewModel.advices.observe(this, Observer { advices ->
            this@HomeFragment.advices.clear()
            this@HomeFragment.advices.addAll(advices)
            adapter.notifyDataSetChanged()
            adapter.advices.sortByDescending { it.rating }
        })
    }

    private fun onRemoveButtonClicked(advice: Advice){
        viewModel.deleteAdvice(advice)
    }
}
