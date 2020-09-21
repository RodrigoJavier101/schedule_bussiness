package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [(User_Entity::class), (Productos_Entity::class), (Ventas_Entity::class), (Gastos_Entity::class),
        (Clientes_Entity::class), (Proveedores_Entity::class), (Ruta_Entity::class)],
    version = 10, exportSchema = false
)
abstract class GestionDatabase : RoomDatabase() {
    abstract fun getGestionDao(): GestionDao

    companion object {
        @Volatile
        private var INSTANCE: GestionDatabase? = null
        fun getDatabase(context: Context): GestionDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GestionDatabase::class.java,
                    "gestion_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

