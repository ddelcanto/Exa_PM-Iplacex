package com.example.evaluacion1.db
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Planificador(
        @PrimaryKey val id: UUID,
        @ColumnInfo(name = "orden") var orden: Int?,
        @ColumnInfo(name = "nombreLugar") var nombreLugar: String?,
        @ColumnInfo(name = "rutaImagen") var rutaImagen: String?,
        @ColumnInfo(name = "latitud") var latitud: String?,
        @ColumnInfo(name = "longitud") var longitud: String?,
        @ColumnInfo(name = "costoAlojamiento") var costoAlojamiento: Int?,
        @ColumnInfo(name = "costoTraslado") var costoTraslado: Int?,
        @ColumnInfo(name = "comentario") var comentario: String?
)