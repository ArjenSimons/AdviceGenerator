package com.example.random_advice_generator.view


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.random_advice_generator.R
import com.example.random_advice_generator.model.Advice
import kotlinx.android.synthetic.main.fragment_generate.*
import kotlinx.android.synthetic.main.rate_popup.*

/**
 * A simple [Fragment] subclass.
 */
class GenerateFragment : Fragment() {

    private lateinit var viewModel: GenerateFragmentViewModel
    private lateinit var alertDialog: AlertDialog

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

    /**
     * Sets listeners
     *
     */
    private fun setOnClickListeners(){
        btnRefresh.setOnClickListener { viewModel.getRandomAdvice() }
        btnSave.setOnClickListener{ openRatePopup() }
    }

    /**
     * Initializes the viewModel
     *
     */
    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(GenerateFragmentViewModel::class.java)
        viewModel.onAdviceChanged = this::onAdviceChanged
        viewModel.getRandomAdvice()
    }

    /**
     * Handles the advice being changed.
     *
     * @param advice The advice
     * @param failed True if api call failed.
     */
    private fun onAdviceChanged(advice: String, failed: Boolean = false){
        if (!failed)
            tvAdvice.text = advice
        else
            tvAdvice.text = context!!.getString(R.string.response_failed_notify)
    }

    /**
     * Opens the rate popup
     *
     */
    private fun openRatePopup(){
        val mDialogView =LayoutInflater.from(context).inflate(R.layout.rate_popup, null)
        val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
        alertDialog = mBuilder.show()

        setRateListeners()
    }

    /**
     * Sets listeners for the rate buttons
     *
     */
    private fun setRateListeners(){
        alertDialog.btnOne.setOnClickListener{ onAdviceRated(1) }
        alertDialog.btnTwo.setOnClickListener{ onAdviceRated(2) }
        alertDialog.btnThree.setOnClickListener{ onAdviceRated(3) }
        alertDialog.btnFour.setOnClickListener{ onAdviceRated(4) }
        alertDialog.btnFive.setOnClickListener{ onAdviceRated(5) }
    }

    /**
     * Handles an advice being rated
     *
     * @param rating The rating given
     */
    private fun onAdviceRated(rating: Int){
        viewModel.insertAdvice(Advice(viewModel.currentAdvice, rating))
        alertDialog.dismiss()
    }
}
