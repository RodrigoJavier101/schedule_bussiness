package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class GestionRepository {

    private val dao: GestionDao = RoomApplication.gestionDatabase.getGestionDao()
    val allProductos: List<Productos_Entity> = dao.getAllFromProductosTable()

    fun getAllProductosAsyncTask() {
        AllProductosAsyncTask(dao)
    }

    /*fun insertProducto(producto: Productos_Entity) {
        InsertAsyncTask(dao).execute(producto)
    }*/

    /*  fun deleteAllVentas() {
          val lista_productos: Array<Productos_Entity>? = allProductos.value?.toTypedArray()

          if (lista_productos != null) {
              DeleteAsyncTask(dao).execute(*lista_productos)
          }
      }*/

    private class AllProductosAsyncTask internal constructor(
        private val dao:
        GestionDao
    ) :
        AsyncTask<Productos_Entity, Void, Void>() {
        override fun doInBackground(vararg p0: Productos_Entity?): Void? {
            dao.getAllFromProductosTable()
            return null
        }
    }


    /* private class InsertAsyncTask internal constructor(
        private val dao:
        GestionDao
    ) :
        AsyncTask<Productos_Entity, Void, Void>() {
        override fun doInBackground(vararg params: Productos_Entity): Void? {
            dao.insertProductos(params[0])
            return null
        }
    }*/

    /*  private class DeleteAsyncTask internal constructor(
          private val dao:
          GestionDao
      ) :
          AsyncTask<Productos_Entity, Void, Void>() {
          override fun doInBackground(vararg params: Productos_Entity): Void? {
              dao.deleteProductos(params)
              return null
          }
      }*/
}