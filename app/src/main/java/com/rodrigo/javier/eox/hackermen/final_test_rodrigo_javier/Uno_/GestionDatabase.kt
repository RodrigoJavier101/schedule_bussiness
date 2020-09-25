package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.Uno_

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [(Clientes_Entity::class)],
    version = 14, exportSchema = false
)
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
                    data.getGestionDao().updateCliente(cliente)
                    return null
                }
            }.execute()
        }

        @SuppressLint("StaticFieldLeak")
        fun getData(data: GestionDatabase): LiveData<MutableList<Clientes_Entity>> {
            lateinit var lists: LiveData<MutableList<Clientes_Entity>>

            return object : AsyncTask<Void, Void, LiveData<MutableList<Clientes_Entity>>>() {
                override fun doInBackground(vararg voids: Void): LiveData<MutableList<Clientes_Entity>>? {
                    lists = data.getGestionDao().getAllFromClientesTable()
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