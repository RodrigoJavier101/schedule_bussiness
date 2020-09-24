package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized

/*
@Database(
    entities = [(User_Entity::class), (Productos_Entity::class), (Ventas_Entity::class), (Gastos_Entity::class),
        (Clientes_Entity::class), (Proveedores_Entity::class), (Ruta_Entity::class)],
    version = 12, exportSchema = false
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
*/
@Database(
    entities = [(User_Entity::class), (Productos_Entity::class), (Ventas_Entity::class), (Gastos_Entity::class),
        (Clientes_Entity::class), (Proveedores_Entity::class), (Ruta_Entity::class)],
    version = 12, exportSchema = false
)
//@Database(entities = arrayOf(Clientes_Entity::class), version = 1)
abstract class GestionDatabase : RoomDatabase() {
    abstract fun getGestionDao(): GestionDao

    companion object {
        @Volatile
        private var INSTANCE: GestionDatabase? = null

        fun getInstance(context: Context): GestionDatabase {
//           synchronized(this) {
               if (INSTANCE == null) {
                   INSTANCE = Room.databaseBuilder(
                       context.applicationContext,
                       GestionDatabase::class.java,
                       "gestion_database"
                   ).build()
               }
//           }
            return INSTANCE as GestionDatabase
        }

        @SuppressLint("StaticFieldLeak")
        fun insertData(data: GestionDatabase, cliente: Clientes_Entity) {
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    data.getGestionDao().addCliente(cliente)
                    return null
                }
            }.execute()
        }

        @SuppressLint("StaticFieldLeak")
        fun getData(data: GestionDatabase): LiveData<MutableList<Clientes_Entity>> {
            lateinit var lists: LiveData<MutableList<Clientes_Entity>>

            return object : AsyncTask<Void, Void, LiveData<MutableList<Clientes_Entity>>>() {
                override fun doInBackground(vararg voids: Void): LiveData<MutableList<Clientes_Entity>>? {
                    lists = data.getGestionDao().getClienteInfo()
                    return lists
                }
            }.execute().get()
        }

        @SuppressLint("StaticFieldLeak")
        fun deleteData(mydata: GestionDatabase, cliente: Clientes_Entity?) {
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    mydata.getGestionDao().deleteCliente(cliente)
                    return null
                }
            }.execute()
        }
    }
}