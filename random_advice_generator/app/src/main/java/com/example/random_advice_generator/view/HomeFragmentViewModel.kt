package com.example.random_advice_generator.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.random_advice_generator.data.database.AdviceRepository
import com.example.random_advice_generator.model.Advice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application){

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val adviceRepository =
        AdviceRepository(application.applicationContext)

    val advices = adviceRepository.getAllAdvice()

    /**
     * Deletes an advice
     *
     * @param advice
     */
    fun deleteAdvice(advice: Advice){
        ioScope.launch {
            adviceRepository.deleteAdvice(advice)
        }
    }
}