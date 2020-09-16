package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class GestionRepository {

    private val gestionDao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()
    val allProductos: LiveData<List<Productos_Entity>> = gestionDao.getAllFromProductosTable()

    fun insertProducto(producto: Productos_Entity) {
        InsertAsyncTask(gestionDao).execute(producto)
    }

    fun deleteAllVentas() {
        val lista_productos: Array<Productos_Entity>? = allProductos.value?.toTypedArray()

        if (lista_productos != null) {
            DeleteAsyncTask(gestionDao).execute(*lista_productos)
        }
    }


    private class InsertAsyncTask internal constructor(
        private val dao:
        GestionDao
    ) :
        AsyncTask<Productos_Entity, Void, Void>() {
        override fun doInBackground(vararg params: Productos_Entity): Void? {
            dao.insertProductos(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(
        private val dao:
        GestionDao
    ) :
        AsyncTask<Productos_Entity, Void, Void>() {
        override fun doInBackground(vararg params: Productos_Entity): Void? {
            dao.deleteProductos(params)
            return null
        }
    }
}