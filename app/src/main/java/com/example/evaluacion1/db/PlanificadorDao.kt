package com.example.evaluacion1.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlanificadorDao {
    /*Query que obtiene todos los registros de la BD ordenados por el campo COMPRADO
    de forma ASC.,
    * No en la cesta es 0 y en la cesta es 1 */
    @Query("SELECT * FROM Planificador order by orden asc ")
    fun getAll(): List<Planificador>

    @Query("SELECT count(*) FROM Planificador ")
    fun contar():Int

    @Insert
    fun insertar(planificador:Planificador)

    @Update
    fun actualizar(planificador:Planificador)
    @Delete
    fun eliminar(planificador:Planificador)


}