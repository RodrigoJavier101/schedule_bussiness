package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.entities.Agenda_Entity


/*@Database(
    entities = [(User::class), (Productos::class), (Ventas::class), (Gastos::class),
        (Clientes::class), (Proveedores::class), (Agenda::class)],
    version = 1,
    exportSchema = false
)*/
@Database(
    entities = [(Agenda_Entity::class)],
    version = 1,
    exportSchema = false
)
abstract class GestionDatabase : RoomDatabase() {
    abstract fun getGestionDao(): GestionDao
}