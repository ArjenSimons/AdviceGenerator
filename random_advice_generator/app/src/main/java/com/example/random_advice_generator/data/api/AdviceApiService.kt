package com.example.random_advice_generator.data.api

import com.example.random_advice_generator.model.RandomAdvice
import retrofit2.Call
import retrofit2.http.GET

interface AdviceApiService {

    @GET("/advice")
    fun getRandomAdvice(): Call<RandomAdvice>
}