package com.example.random_advice_generator.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.random_advice_generator.model.Advice

class AdviceRepository(context: Context) {

    private var adviceDao: AdviceDao

    init{
        val adviceRoomDatabase = AdviceRoomDatabase.getDatabase(context)
        adviceDao = adviceRoomDatabase!!.adviceDao()
    }

    fun getAllAdvice(): LiveData<List<Advice>>{
        return adviceDao.getAllAdvice()
    }

    fun insertAdvice(advice: Advice){
        return adviceDao.insertAdvice(advice)
    }

    suspend fun deleteAdvice(advice: Advice){
        return adviceDao.deleteAdvice(advice)
    }

    suspend fun updateAdvice(advice: Advice){
        return adviceDao.updateAdvice(advice)
    }

}