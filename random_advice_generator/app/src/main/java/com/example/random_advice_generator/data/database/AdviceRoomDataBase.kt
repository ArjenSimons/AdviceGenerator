package com.example.random_advice_generator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.random_advice_generator.model.Advice

@Database(entities = [Advice::class], version = 1, exportSchema = false)
abstract class AdviceRoomDatabase : RoomDatabase(){

    abstract fun adviceDao(): AdviceDao

    companion object{
        private const val DATABASE_NAME = "ADVICE_DATABASE"

        @Volatile
        private var adviceRoomDataBaseInstance: AdviceRoomDatabase? = null

        fun getDatabase(context: Context): AdviceRoomDatabase? {
            if(adviceRoomDataBaseInstance == null){
                synchronized(AdviceRoomDatabase::class.java){
                    if(adviceRoomDataBaseInstance == null){
                        adviceRoomDataBaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            AdviceRoomDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }

            return adviceRoomDataBaseInstance
        }
    }
}