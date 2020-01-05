package com.example.random_advice_generator.model

import com.google.gson.annotations.SerializedName

data class RandomAdvice(
    @SerializedName("slip") val slip: Slip
){
    data class Slip(
        @SerializedName("advice") val advice: String,
        @SerializedName("slip_id") val slipId: Int
    )
}