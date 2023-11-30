package com.example.evaluacion1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Planificador::class], version=1)
abstract class PlanificadorDatabase: RoomDatabase() {
    abstract fun planificadorDao(): PlanificadorDao

    companion object {

        @Volatile
        private var BASE_DATOS: PlanificadorDatabase? = null
        fun getInstance(contexto: Context): PlanificadorDatabase {
            return BASE_DATOS ?: synchronized(this) {
                Room.databaseBuilder(
                    contexto.applicationContext,
                    PlanificadorDatabase::class.java,
                    "PlanificadorDB.bd"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { BASE_DATOS = it }
            }
        }
    }
}