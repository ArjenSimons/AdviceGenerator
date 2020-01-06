package com.example.random_advice_generator.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.random_advice_generator.R
import com.example.random_advice_generator.data.api.AdviceApiRepository
import com.example.random_advice_generator.data.database.AdviceRepository
import com.example.random_advice_generator.model.Advice
import com.example.random_advice_generator.model.RandomAdvice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenerateFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var currentAdvice = ""

    lateinit var onAdviceChanged: (String, Boolean) -> Unit

    private val context = application.applicationContext
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val adviceRepository =
        AdviceRepository(application.applicationContext)

    private val adviceApiRepository = AdviceApiRepository()
    val advice = MutableLiveData<RandomAdvice>()
    private val error = MutableLiveData<String>()

    /**
     * Gets a random advice from the api
     *
     */
    fun getRandomAdvice(){
        adviceApiRepository.getRandomAdvice().enqueue(object : Callback<RandomAdvice> {

            override fun onResponse(call: Call<RandomAdvice>, response: Response<RandomAdvice>) {
                if (response.isSuccessful){
                    advice.value = response.body()
                    currentAdvice = advice.value!!.slip.advice
                    onAdviceChanged(currentAdvice, false)
                }else {
                    error.value = context.getString(R.string.response_failed)
                    onAdviceChanged("", true)
                }
            }

            override fun onFailure(call: Call<RandomAdvice>, t: Throwable) {
                error.value = t.message
                onAdviceChanged("", true)
            }
        })
    }

    /**
     * Adds an advice to the local database
     *
     * @param advice The advice to add
     */
    fun insertAdvice(advice: Advice){
        ioScope.launch {
            adviceRepository.insertAdvice(advice)
        }
    }
}