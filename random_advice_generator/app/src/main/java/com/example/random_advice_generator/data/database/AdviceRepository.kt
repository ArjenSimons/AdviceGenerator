package com.example.random_advice_generator.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.random_advice_generator.model.Advice

class AdviceRepository(context: Context) {

    private var adviceDao: AdviceDao

    init{
        val adviceRoomDatabase =
            AdviceRoomDatabase.getDatabase(
                context
            )
        adviceDao = adviceRoomDatabase!!.adviceDao()
    }

    /**
     * Gets all saved advices
     *
     * @return A list with saved advices
     */
    fun getAllAdvice(): LiveData<List<Advice>>{
        return adviceDao.getAllAdvice()
    }

    /**
     * Inserts an advice to the local database
     *
     * @param advice The advice to insert
     */
    fun insertAdvice(advice: Advice){
        return adviceDao.insertAdvice(advice)
    }

    /**
     * Deletes an advice from the local database
     *
     * @param advice The advice to delete
     */
    suspend fun deleteAdvice(advice: Advice){
        return adviceDao.deleteAdvice(advice)
    }

    /**
     * Updates an advice from the local database
     *
     * @param advice The advice to update
     */
    suspend fun updateAdvice(advice: Advice){
        return adviceDao.updateAdvice(advice)
    }

}