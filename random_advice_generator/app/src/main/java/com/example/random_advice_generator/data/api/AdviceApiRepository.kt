package com.example.random_advice_generator.data.api

class AdviceApiRepository{
    private val adviceApi: AdviceApiService = AdviceApi.createApi()

    fun getRandomAdvice() = adviceApi.getRandomAdvice()
}