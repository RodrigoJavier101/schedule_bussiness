package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room.entities.Productos_DataView

class GestionRepository {

    private val gestionDao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()
    val allProductos: LiveData<List<Productos_DataView>> = gestionDao.getAllFromProductosTable()

    fun insertProducto(producto: Productos_DataView) {
        InsertAsyncTask(gestionDao).execute(producto)
    }

    fun deleteAllVentas() {
        val lista_productos: Array<Productos_DataView>? = allProductos.value?.toTypedArray()

        if (lista_productos != null) {
            DeleteAsyncTask(gestionDao).execute(*lista_productos)
        }
    }


    private class InsertAsyncTask internal constructor(
        private val dao:
        GestionDao
    ) :
        AsyncTask<Productos_DataView, Void, Void>() {
        override fun doInBackground(vararg params: Productos_DataView): Void? {
            dao.insertProductos(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(
        private val dao:
        GestionDao
    ) :
        AsyncTask<Productos_DataView, Void, Void>() {
        override fun doInBackground(vararg params: Productos_DataView): Void? {
            dao.deleteProductos(params)
            return null
        }
    }
}