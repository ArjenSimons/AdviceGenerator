package com.example.random_advice_generator.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.random_advice_generator.model.Advice

@Dao
interface AdviceDao {

    @Query("SELECT * FROM adviceTable")
    fun getAllAdvice(): LiveData<List<Advice>>

    @Insert
    fun insertAdvice(advice: Advice)

    @Delete
    suspend fun deleteAdvice(advice: Advice)

    @Update
    suspend fun updateAdvice(advice: Advice)
}